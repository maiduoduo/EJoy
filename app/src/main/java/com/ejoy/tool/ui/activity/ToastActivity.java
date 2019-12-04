package com.ejoy.tool.ui.activity;
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

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.IToast;
import com.ejoy.tool.scaffold.utils.IToastImageType;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * CN:      ToastActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/18
 * Des:    Toast
 */
public class ToastActivity extends BaseActivity {
    @BindView(R.id.btnToastSuccess)
    Button btnToastSuccess;
    @BindView(R.id.btnToastFail)
    Button btnToastFail;
    @BindView(R.id.btnToastTip)
    Button btnToastTip;
    @BindView(R.id.btnToastError)
    Button btnToastError;
    @BindView(R.id.btnToastDefault)
    Button btnToastDefault;
    @BindView(R.id.tvFloat)
    TextView tvFloat;

    private IToast iToast;
    private String floattext =
            "实现的功能：\n" +
                    "自定义悬浮弹窗；点击其他地方该布局不受影响；\n" +
                    "可自定义显示时间；可以设置点击事件,自定义悬浮弹窗；点击其他地方该布局不受影响；\n" +
                    "可自定义显示时间；可以设置点击事件,自定义悬浮弹窗；点击其他地方该布局不受影响；\n" +
                    "可自定义显示时间；可以设置点击事件；";
    private String floatTitle = "仿微信悬浮通知栏/悬浮通知";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_itoast;
    }

    @Override
    protected void initView(View mRootView) {
        iToast = new IToast().builder();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void initStatusbar() {
        super.initStatusbar();
    }


    @OnClick({
            R.id.btnToastSuccess
            , R.id.btnToastFail
            , R.id.btnToastTip
            , R.id.btnToastError
            , R.id.btnToastDefault
            , R.id.tvFloat
            , R.id.tvNoRepeat
            , R.id.btnSelectIcon
            , R.id.tvSimpleTextIco
            , R.id.tvSimpleIco
            , R.id.tvSimpleText
            , R.id.tvSimpleDefault
            , R.id.btnToastCustomDefault
            , R.id.tvSimpleTargetIco
            , R.id.tvSimpleTargetAll
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.btnToastCustomDefault://默认效果
                iToast.showIImgToast("默认效果");
                break;
            case R.id.btnToastSuccess://成功
                iToast.showIImgToast("发送成功", IToastImageType.SUCCESS);
                break;
            case R.id.btnToastFail://失败
                iToast.showIImgToast("发送失败", IToastImageType.FAIL);
                break;
            case R.id.btnToastTip://提示
                iToast.showIImgToast("新消息送达", IToastImageType.INFO);
                break;
            case R.id.btnToastError://错误
                iToast.showIImgToast("发生错误", IToastImageType.ERROR);
                break;
            case R.id.btnToastDefault://默认
                iToast.showIImgToast("默认图标，指定时长", 1);
                break;
            case R.id.btnSelectIcon://指定自己的资源图标
                iToast.showIImgToast("指定自己的资源图标", 0, R.drawable.itoast_ico_done_green);
                break;
            case R.id.tvFloat://悬浮
                iToast.showWXFloatToast(ToastActivity.this,floatTitle, floattext, null);
                break;
            //------------简洁自定义Toast------------------------
            case R.id.tvSimpleTextIco://简洁文本图标类型提示
                iToast.showISimpleToast("简洁文本图标类型提示(顶部)", 0, IToast.TOAST_SIMPLE_TEXT_ICO, IToast.TOAST_Gravity_TOP);
                break;
            case R.id.tvSimpleIco://简洁图标类型提示
                iToast.showISimpleToast("简洁图标类型提示(中间)", 0, IToast.TOAST_SIMPLE_ICO_ONLY, IToast.TOAST_Gravity_CENTER);
                break;
            case R.id.tvSimpleText://简洁文字类型提示
                iToast.showISimpleToast("简洁文字类型提示长时(底部)", 1, IToast.TOAST_SIMPLE_TEXT_ONLY, IToast.TOAST_Gravity_BOTTOM);
                break;
            case R.id.tvSimpleTargetIco://指定图标类型提示
                iToast.showISimpleImgResToast("指定图标类型提示",R.drawable.itoast_done_green_target);
                break;
            case R.id.tvSimpleTargetAll://全部指定类型提示
                iToast.showISimpleToast("全部指定类型提示",1,R.drawable.itoast_done_green_target,IToast.TOAST_SIMPLE_ICO_ONLY,IToast.TOAST_Gravity_RIGHT);
                break;
            case R.id.tvSimpleDefault://简洁默认类型提示
                iToast.showISimpleToast("简洁默认类型提示");
                break;

            //------------官方Toast------------------------
            case R.id.tvNoRepeat://根据显示时间不重复创建实例
                iToast.showNoRepeatToast("根据显示时间不重复创建实例");
                break;

        }
    }
}
