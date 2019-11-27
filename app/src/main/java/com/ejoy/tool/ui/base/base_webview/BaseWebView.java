package com.ejoy.tool.ui.base.base_webview;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ejoy.tool.scaffold.utils.LogUtils;
import com.ejoy.tool.scaffold.view.IProgressDialog;


import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * CN:      BaseWebView
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/27
 * Des:    WebView
 */
public class BaseWebView extends WebView {
    protected static boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
    private ResultResolve mResultResolve;
    private String mTitle;
    private WebSettings webSettings;



    public BaseWebView(Context context) {
        super(context);
        initSetting();
        initWebViewClient();
        initWebChromeClient();

    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting();
        initWebViewClient();
        initWebChromeClient();

    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSetting();
        initWebViewClient();
        initWebChromeClient();

    }

    private void initSetting() {
        //声明WebSettings子类
        webSettings = this.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);//设置渲染的优先级
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        //在MIUI系统自带的浏览器的设置功能中，提供了“网页文字大小”的功能。想实现这种功能很简单，
        // 只要我们修改 setTextZoom 的百分比值就实现了。100就是正常大小，90就是小，80就是超小，110就是大，120就是超大。
        webSettings.setTextZoom(120); // 通过百分比来设置文字的大小，默认值是100。
//        webSettings.setTextSize(5);
//        webSettings.setTextSize(WebSettings.TextSize.LARGEST);
    }


    private void initWebViewClient() {
        this.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e("webview url = "+url);
                if(url.startsWith("alipays:") || url.startsWith("alipay")) {
                    try {
                        //  mShowLoading = false;
                        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    } catch (Exception e) {
                        new AlertDialog.Builder(getContext())
                                .setMessage("未检测到支付宝客户端，请安装后重试。")
                                .setPositiveButton("立即安装", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Uri alipayUrl = Uri.parse("https://d.alipay.com");
                                        getContext().startActivity(new Intent("android.intent.action.VIEW", alipayUrl));
                                    }
                                }).setNegativeButton("取消", null).show();
                    }
                    return true;
                }else if(url.startsWith("weixin:") ){

                    try {
                        // mShowLoading = false;
                        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    } catch (Exception e) {
                        new AlertDialog.Builder(getContext())
                                .setMessage("未检测到微信客户端，请安装后重试。").setNegativeButton("确定", null).show();
                    }
                    return true;
                }else if(url.startsWith("mc-sdk:")){
                    if(mResultResolve != null){
                        mResultResolve.handle(url);
                    }


                }else if(url.contains("wx.tenpay.com")){
                    LogUtils.e("外部浏览器："+url);
                    return super.shouldOverrideUrlLoading(view, url);

                }else{
                    //内部浏览器打开网页
                    //mShowLoading = true;
                    view.loadUrl(url);

                }
                return true;
            }
            //网页开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                IProgressDialog.showSuccinctProgress(getContext(), "加载中，请稍后...", true);

            }
            //网页加载完毕
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (IProgressDialog.isShowing()) {
                    IProgressDialog.dismiss();
                }

            }
            //https验证错误，还继续加载网页
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (IProgressDialog.isShowing()) {
                    IProgressDialog.dismiss();
                }
                if (DISABLE_SSL_CHECK_FOR_TESTING) {
                    handler.cancel();
                } else {
                    // 接受所有网站的证书
                    handler.proceed();
                }
            }
        });
    }


    private void initWebChromeClient() {
        this.setWebChromeClient(new WebChromeClient(){
            //获取网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitle = title;
                LogUtils.e("webview title = "+title);

//                EventBus.getDefault().post(new TitleEvent(mTitle));

            }
            //获取网页加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    // 网页加载完成
                    if (IProgressDialog.isShowing()) {
                        IProgressDialog.dismiss();
                    }
                    webSettings.setBlockNetworkImage(false);
                } else {
                    // 网页加载中
                }
            }
        });


    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public ResultResolve getResultResolve() {
        return mResultResolve;
    }

    public void setResultResolve(ResultResolve resultResolve) {
        this.mResultResolve = resultResolve;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && this.canGoBack()) {
            this.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public interface ResultResolve{
        void handle(String url);

    }

    public void onDestory(){
        //清除网页缓存
        this.clearHistory();
        this.clearCache(true);
        this.loadUrl("about:blank");
        this.pauseTimers();
        this.stopLoading();
        this.destroy();

    }


}

