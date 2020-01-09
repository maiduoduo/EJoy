package com.ejoy.tool.ui.activity.popupwindow;
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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.iviews.popup.weibo.PopMenuItem;
import com.module.iviews.popup.weibo.PopMenuItemListener;
import com.module.iviews.popup.weibo.WeibPopMenu;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * CN:      IPopupWindowPopmenuActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/3
 * Des:    TODO:Popmenu微博弹出框（弹簧效果）
 */
public class IPopupWindowPopmenuActivity extends BaseActivity {
    @BindView(R.id.ivShowPopMenu)
    ImageView ivShowPopMenu;
    private WeibPopMenu mPopMenuTop;
    private WeibPopMenu mPopMenuNext;
    private String[] wbPopItems;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_weib_popmenu;
    }

    @Override
    protected void initView(View mRootView) {
        wbPopItems = GlobalDataProvider.wbPopItems;
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

    @OnClick({R.id.ivShowPopMenu})
    public void bindClick(View view) {
        switch (view.getId()) {
            case R.id.ivShowPopMenu:
                createPopMenu();
                showRotateAnim(isReverseTag);
                break;
        }
    }


    /**
     * PopupMenu
     */
    private void createPopMenu() {
        if (mPopMenuTop != null && mPopMenuTop.isShowing()) {
            mPopMenuTop.hide();
        }else if (mPopMenuNext != null && mPopMenuNext.isShowing()) {
            mPopMenuNext.hide();
        }
        else {
            mPopMenuTop = new WeibPopMenu.Builder().attachToActivity(_mActivity)
                    .addMenuItem(new PopMenuItem(wbPopItems[0], getResources().getDrawable(R.mipmap.tabbar_compose_idea)))
                    .addMenuItem(new PopMenuItem(wbPopItems[1], getResources().getDrawable(R.mipmap.tabbar_compose_photo)))
                    .addMenuItem(new PopMenuItem(wbPopItems[2], getResources().getDrawable(R.mipmap.tabbar_compose_headlines)))
                    .addMenuItem(new PopMenuItem(wbPopItems[3], getResources().getDrawable(R.mipmap.tabbar_compose_lbs)))
                    .addMenuItem(new PopMenuItem(wbPopItems[4], getResources().getDrawable(R.mipmap.tabbar_compose_review)))
                    .addMenuItem(new PopMenuItem(wbPopItems[5], getResources().getDrawable(R.mipmap.tabbar_compose_more)))
                    .setOnItemClickListener(new PopMenuItemListener() {
                        @Override
                        public void onItemClick(WeibPopMenu popMenu, int position) {
                            iToast.showISimpleToast("" + wbPopItems[position]);
                            mPopMenuTop.hide();
                            if (position == 5) {
                                mPopMenuNext.show();
                            }
                            showRotateAnim(isReverseTag);
                        }
                    })
                    .build();

            if (!mPopMenuTop.isShowing()){
                mPopMenuTop.show();
            }

            mPopMenuNext = new WeibPopMenu.Builder().attachToActivity(_mActivity)
                    .addMenuItem(new PopMenuItem(wbPopItems[6], getResources().getDrawable(R.mipmap.tabbar_compose_idea)))
                    .addMenuItem(new PopMenuItem(wbPopItems[7], getResources().getDrawable(R.mipmap.tabbar_compose_photo)))
                    .addMenuItem(new PopMenuItem(wbPopItems[8], getResources().getDrawable(R.mipmap.tabbar_compose_headlines)))
                    .addMenuItem(new PopMenuItem(wbPopItems[9], getResources().getDrawable(R.mipmap.tabbar_compose_lbs)))
                    .addMenuItem(new PopMenuItem(wbPopItems[10], getResources().getDrawable(R.mipmap.tabbar_compose_review)))
                    .addMenuItem(new PopMenuItem(wbPopItems[11], getResources().getDrawable(R.mipmap.tabbar_compose_redpack)))
                    .setOnItemClickListener(new PopMenuItemListener() {
                        @Override
                        public void onItemClick(WeibPopMenu popMenu, int position) {
                            iToast.showISimpleToast("" + wbPopItems[position + 6]);
                            mPopMenuNext.hide();
                            showRotateAnim(isReverseTag);
                        }
                    })
                    .build();
            mPopMenuNext.hide();
        }
    }

    @Override
    protected void onDestroy() {
        if (mPopMenuTop != null && mPopMenuTop.isShowing() || mPopMenuNext != null && mPopMenuNext.isShowing()) {
            mPopMenuTop.hide();
            mPopMenuNext.hide();
        }
        if (rotateAnim != null) rotateAnim = null;
        ivShowPopMenu.clearAnimation();
        super.onDestroy();
    }

    Animation rotateAnim;
    boolean isReverseTag = false;

    public void showRotateAnim(boolean isReverse) {
        isReverseTag = !isReverseTag;
        rotateAnim = new RotateAnimation(0f, isReverse ? -90 : 45f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setFillAfter(true); // 设置保持动画最后的状态
        rotateAnim.setDuration(300); // 设置动画时间
        rotateAnim.setInterpolator(new AccelerateInterpolator()); // 设置插入器
        ivShowPopMenu.startAnimation(rotateAnim);
    }
}
