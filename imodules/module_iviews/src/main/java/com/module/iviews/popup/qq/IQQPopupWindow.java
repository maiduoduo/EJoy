package com.module.iviews.popup.qq;
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

import android.animation.Animator;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.module.ires.bean.utils.IAnimUtil;
import com.module.ires.bean.view.EDividerItemDecoration;
import com.module.iviews.R;

import java.util.List;

/**
 * CN:      IQQPopupWindow
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/2
 * Des:    TODO:仿qq右上角弹窗
 */
public class IQQPopupWindow extends PopupWindow{
    public static final String TAG = "IQQPopupWindow";
    private Activity context;
    /** PopupWindow对象 */
    private IQQPopupWindow mPopupWindow = null ;
    private IAnimUtil animUtil;
    private float bgAlpha = 1f;
    private boolean isAddLine = false;
    private boolean bright = false;
    private static final long DURATION = 500;
    private static final float START_ALPHA = 0.7f;
    private static final float END_ALPHA = 1f;
    private View mContentView;
    private RecyclerView mRecyclerView;
    private List<String> mData;
    private BaseQuickAdapter mPopAdapter;
    private IQQPopupItemListener iQQPopupItemListener;

    public IQQPopupWindow(Activity context) {
        super(context);
        this.context = context;
    }


    /**
     * 调用：第一步
     * @return
     */
    public IQQPopupWindow builder() {
        if (mPopupWindow == null){
            synchronized (IQQPopupWindow.class){
                if (mPopupWindow == null){
                    mPopupWindow = new IQQPopupWindow(context);
                }
            }
        }
        return this;
    }


    /**
     * 调用：第二步
     * 创建
     * @return
     */
    public IQQPopupWindow showIt(List<String> data) {
        this.mData = data;
        Log.e(TAG, "showIt-----------");
        Log.e(TAG, "showIt: "+new Gson().toJson(mData));
        animUtil = new IAnimUtil();
        initWindow();
        return this;
    }

    private void initWindow() {
        // 设置布局文件
        this.setContentView(mContentView = LayoutInflater.from(context).inflate(R.layout.layout_qqpop, null));
        this.mRecyclerView = mContentView.findViewById(R.id.qqPopRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        if (isAddLine) mRecyclerView.addItemDecoration(new EDividerItemDecoration(context,1));
        // 为了避免部分机型不显示，我们需要重新设置一下宽高
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置pop透明效果
        this.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop出入动画
        this.setAnimationStyle(R.style.QQPopAnim);
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        this.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        this.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        this.setOutsideTouchable(true);
        // 设置pop关闭监听，用于改变背景透明度
        this.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
            }
        });
        Log.e(TAG, "showIt-----------");
        mRecyclerView.setAdapter(mPopAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_qqpop, mData) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_content,item);
            }
        });
        mPopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (iQQPopupItemListener != null){
                    iQQPopupItemListener.onPopItemClick(view,position);
                    dismiss();
                }
            }
        });

    }

    /**
     * 调用：第三步：若需要分割线则调用
     * 条目之间是否添加分割线
     *              TODO:注意：要在showIt（）之前调用
     * @param isAddLine
     * @return
     */
    public IQQPopupWindow showLine(boolean isAddLine){
        this.isAddLine = isAddLine;
        if (mRecyclerView != null) {
            if (isAddLine) mRecyclerView.addItemDecoration(new EDividerItemDecoration(context, 1, 1));
            else mRecyclerView.addItemDecoration(new EDividerItemDecoration(context, 1, 0));
        }
        return this;
    }

    /**
     * 调用：第四步
     *  弹窗位置与偏移
     * @param view
     * @return
     */
    public IQQPopupWindow showAtBottom(View view) {
        //弹窗位置设置
        // 相对于 + 号正下面，同时可以设置偏移量
        this.showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 0);
//        this.showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 10);
//        this.showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 110);//有偏差
        return this;
    }

    /**
     * 调用：第五步
     *  弹窗位置与偏移
     * @return
     */
    public IQQPopupWindow toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animUtil.addUpdateListener(new IAnimUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        animUtil.addEndListner(new IAnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        animUtil.startAnimator();
        return this;
    }

    //设置添加屏幕的背景透明度
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    /*-------------------点击监听回调-----------------------------------*/

    public interface IQQPopupItemListener{
        void onPopItemClick(View view, int position);
    }

    /**
     * 调用：最后一步
     * 注册监听事件
     * @param listener
     */
    public void setIQQPopupItemListener(IQQPopupItemListener listener) {
        this.iQQPopupItemListener = listener;
    }


}