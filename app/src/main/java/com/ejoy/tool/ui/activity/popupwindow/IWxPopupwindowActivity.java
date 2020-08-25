package com.ejoy.tool.ui.activity.popupwindow;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.utils.EBlurHelper;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.IAnimUtil;
import com.module.iviews.popup.AdapterItem;
import com.module.iviews.popup.EUISimpleAdapter;
import com.module.iviews.popup.EUISimpleExpandableListAdapter;
import com.module.iviews.popup.EUISimpleExpandablePopup;
import com.module.iviews.popup.EUISimplePopup;
import com.module.iviews.popup.ExpandableItem;
import com.module.iviews.popup.baseuse.IListPopupwindow;
import com.module.iviews.popup.baseuse.IPopupwindowUse;
import com.module.iviews.popup.bean.GalleryBean;
import com.module.iviews.popup.blurPop.BlurPopupWindow;
import com.module.iviews.popup.menu.IScreenMenuPopWindow;
import com.module.iviews.popup.menu.bean.FiltrateBean;
import com.module.iviews.popup.qq.IQQPopupWindow;
import com.module.iviews.popup.weibo.AlertDesignViewDialog;
import com.module.iviews.popup.weibo.ImageInfoBean;
import com.module.iviews.popup.weibo.WeiboPopupWindow;
import com.module.iviews.view.widget.IExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;

import static com.module.iviews.popup.baseuse.IPopupwindowUse.LocationType.BOTTOM_CENTER;

/**
 * CN:      IWxPopupwindowActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/08/05
 * Des:    微信右上角添加按钮弹窗
 */
public class IWxPopupwindowActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ibnWxPopupshow)
    ImageButton mIbnWxPopupshow;
    private RelativeLayout rlFeedBack,rlPopScan,rlAddFriends,rlChatGroup;
    private IAnimUtil ianimUtil;
    private PopupWindow mWxPopupWindow;
    private float bgAlpha = 1f;
    private boolean bright = false;

    private static final long DURATION = 500;
    private static final float START_ALPHA = 0.7f;
    private static final float END_ALPHA = 1f;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_popupwindow_wx;
    }

    @Override
    protected Object registSatusbarBgcolor() {
        return baseTransparent;
    }

    @Override
    protected void initView(View mRootView) {
        mWxPopupWindow = new PopupWindow(_mActivity);
        ianimUtil = new IAnimUtil();
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


    @OnClick({
            R.id.ibnWxPopupshow,
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.ibnWxPopupshow://仿微信的右上角选项弹窗
                showWxPop();
                toggleBright();
                break;
            default:
                break;
        }
    }



    public void ivBack(View view) {
        finish();
    }




    Animation rotateAnim;
    boolean isReverseTag = false;
    private void showWxPop() {
        showRotateAnim(isReverseTag);


        // 设置布局文件
        mWxPopupWindow.setContentView(LayoutInflater.from(_mActivity).inflate(R.layout.layout_wx_popupwindow_add, null));
        // 为了避免部分机型不显示，我们需要重新设置一下宽高
        mWxPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mWxPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置pop透明效果
        mWxPopupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop出入动画
        mWxPopupWindow.setAnimationStyle(R.style.menu_wxpop_add);
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        mWxPopupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        mWxPopupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        mWxPopupWindow.setOutsideTouchable(true);
        // 相对于 + 号正下面，同时可以设置偏移量
        mWxPopupWindow.showAsDropDown(mIbnWxPopupshow, -100, 0);
        // 设置pop关闭监听，用于改变背景透明度
        mWxPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
                showRotateAnim(isReverseTag);
            }

        });

        rlChatGroup = mWxPopupWindow.getContentView().findViewById(R.id.rlChatGroup);
        rlAddFriends = mWxPopupWindow.getContentView().findViewById(R.id.rlAddFriends);
        rlPopScan = mWxPopupWindow.getContentView().findViewById(R.id.rlPopScan);
        rlFeedBack = mWxPopupWindow.getContentView().findViewById(R.id.rlFeedBack);

        rlChatGroup.setOnClickListener(this);
        rlAddFriends.setOnClickListener(this);
        rlPopScan.setOnClickListener(this);
        rlFeedBack.setOnClickListener(this);
    }

    public void showRotateAnim(boolean isReverse) {
        isReverseTag = !isReverseTag;
        rotateAnim = new RotateAnimation(0f, isReverse ? -90 : 45f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setFillAfter(true); // 设置保持动画最后的状态
        rotateAnim.setDuration(300); // 设置动画时间
        rotateAnim.setInterpolator(new AccelerateInterpolator()); // 设置插入器
        mIbnWxPopupshow.startAnimation(rotateAnim);
    }

    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        ianimUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        ianimUtil.addUpdateListener(new IAnimUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        ianimUtil.addEndListner(new IAnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        ianimUtil.startAnimator();
    }

    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = _mActivity.getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        _mActivity.getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        _mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlChatGroup:
                mWxPopupWindow.dismiss();
                iToast.showIImgToast("发起群聊");
                break;
            case R.id.rlAddFriends:
                mWxPopupWindow.dismiss();
                iToast.showIImgToast("添加好友");
                break;
            case R.id.rlPopScan:
                mWxPopupWindow.dismiss();
                iToast.showIImgToast("扫一扫");
                break;
            case R.id.rlFeedBack:
                mWxPopupWindow.dismiss();
                iToast.showIImgToast("我有意见");
                Toast.makeText(_mActivity, "我有意见", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}
