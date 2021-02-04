package com.ejoy.tool.ui.douyin.fragment;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.GlideUtils;
import com.ejoy.tool.ui.base.base_fragment.BaseFragment;
import com.ejoy.tool.ui.douyin.activity.DouyinFocusActivity;
import com.ejoy.tool.ui.douyin.activity.DouyinShowImageActivity;
import com.ejoy.tool.ui.douyin.bean.DouyinCurUserBean;
import com.ejoy.tool.ui.douyin.bean.DouyinMainPageChangeEvent;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinCommPagerAdapter;
import com.ejoy.tool.ui.douyin.utils.RxBus;
import com.module.ires.bean.bean.DouyinVideoBean;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.iviews.image.DouyinCircleImageView;
import com.module.iviews.textview.IconFontTextView;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;


/**
 * @ClassName:  DouyinPersonalHomeFragment
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 个人主页fragment
 */
public class DouyinPersonalHomeFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_head)
    DouyinCircleImageView ivHead;
    @BindView(R.id.rl_dropdown)
    RelativeLayout rlDropdown;
    @BindView(R.id.ll_focus)
    LinearLayout llFocus;
    @BindView(R.id.ll_fans)
    LinearLayout llFans;
    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_focus)
    TextView tvFocus;
    @BindView(R.id.iv_more)
    IconFontTextView ivMore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    XTabLayout tabLayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.tv_getlike_count)
    TextView tvGetLikeCount;
    @BindView(R.id.tv_focus_count)
    TextView tvFocusCount;
    @BindView(R.id.tv_fans_count)
    TextView tvFansCount;
    @BindView(R.id.tv_addfocus)
    TextView tvAddfocus;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private DouyinCommPagerAdapter pagerAdapter;
    private DouyinVideoBean.UserBean curUserBean;
    private Subscription subscription;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_douyin_personal_home;
    }

    @Override
    public void intBase() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View rootView) {
        //解决toolbar左边距问题
        toolbar.setContentInsetsAbsolute(0, 0);

        setAppbarLayoutPercent();

        ivReturn.setOnClickListener(this);
        ivHead.setOnClickListener(this);
        ivBg.setOnClickListener(this);
        llFocus.setOnClickListener(this);
        llFans.setOnClickListener(this);

        setUserInfo();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }



    /**
     * 过渡动画跳转页面
     *
     * @param view
     */
    public void transitionAnim(View view, int res) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.trans));
        Intent intent = new Intent(getActivity(), DouyinShowImageActivity.class);
        intent.putExtra("res", res);
        ActivityCompat.startActivity(getActivity(), intent, compat.toBundle());
    }

    public void setUserInfo() {
        subscription = RxBus.getDefault().toObservable(DouyinCurUserBean.class).subscribe((Action1<DouyinCurUserBean>) curUserBean -> {

            coordinatorLayoutBackTop();

            this.curUserBean = curUserBean.getUserBean();

//            ivBg.setImageResource(curUserBean.getUserBean().getHead());
//            ivBg.setImageResource(curUserBean.getUserBean().getHead());
            ivHead.setImageResource(curUserBean.getUserBean().getHead());
            GlideUtils.showImage(mActivity,curUserBean.getUserBean().getHeadUrl(),ivBg);
            GlideUtils.showImage(mActivity,curUserBean.getUserBean().getHeadUrl(),ivHead);
            tvNickname.setText(curUserBean.getUserBean().getNickName());
            tvSign.setText(curUserBean.getUserBean().getSign());
            tvTitle.setText(curUserBean.getUserBean().getNickName());

            String subCount = EDensityUtils.numberFilter(curUserBean.getUserBean().getSubCount());
            String focusCount = EDensityUtils.numberFilter(curUserBean.getUserBean().getFocusCount());
            String fansCount = EDensityUtils.numberFilter(curUserBean.getUserBean().getFansCount());

            //获赞 关注 粉丝
            tvGetLikeCount.setText(subCount);
            tvFocusCount.setText(focusCount);
            tvFansCount.setText(fansCount);

            //关注状态
            if (curUserBean.getUserBean().isFocused()) {
                tvAddfocus.setText("已关注");
                tvAddfocus.setBackgroundResource(R.drawable.shape_round_halfwhite);
            } else {
                tvAddfocus.setText("关注");
                tvAddfocus.setBackgroundResource(R.drawable.shape_round_red);
            }

            setTabLayout();
        });
    }

    private void setTabLayout() {
        String[] titles = new String[]{"作品 " + curUserBean.getWorkCount(), "动态 " + curUserBean.getDynamicCount(), "喜欢 " + curUserBean.getLikeCount()};

        fragments.clear();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new DouyinWorkFragment());
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }

        pagerAdapter = new DouyinCommPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 根据滚动比例渐变view
     */
    private void setAppbarLayoutPercent() {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float percent = (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());  //滑动比例

            if (percent > 0.8) {
                tvTitle.setVisibility(View.VISIBLE);
                tvFocus.setVisibility(View.VISIBLE);

                float alpha = 1 - (1 - percent) * 5;  //渐变变换
                tvTitle.setAlpha(alpha);
                tvFocus.setAlpha(alpha);
            } else {
                tvTitle.setVisibility(View.GONE);
                tvFocus.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 自动回顶部
     */
    private void coordinatorLayoutBackTop() {
        CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
            int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
            if (topAndBottomOffset != 0) {
                appBarLayoutBehavior.setTopAndBottomOffset(0);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                RxBus.getDefault().post(new DouyinMainPageChangeEvent(0));
                break;
            case R.id.iv_head:
                transitionAnim(ivHead, curUserBean.getHead());
                break;
            case R.id.iv_bg:

                break;
            case R.id.ll_focus:
                startActivity(new Intent(getActivity(), DouyinFocusActivity.class));
                break;
            case R.id.ll_fans:
                startActivity(new Intent(getActivity(), DouyinFocusActivity.class));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

}
