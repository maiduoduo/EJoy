package com.module.iviews.easynavigationbar.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.module.iviews.R;
import com.module.iviews.easynavigationbar.ui.weibo.NavigationBarAddThirdFragment;
import com.module.iviews.easynavigationbar.ui.weibo.NavigationBarWBFirstFragment;
import com.module.iviews.easynavigationbar.ui.weibo.NavigationBarWBSecondFragment;
import com.module.iviews.easynavigationbar.view.KickBackAnimator;
import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.utils.NavigationUtil;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class NavigationBarWeiboActivity extends AppCompatActivity {

    private EasyNavigationBar navigationBar;

    private String[] tabText =  {"聊天", "通讯录", "发现", "我的"};

    private String[] tabText2 = {"商城", "订单", "地址"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.eicon_navigation_index, R.mipmap.eicon_navigation_find,  R.mipmap.eicon_navigation_message, R.mipmap.eicon_navigation_me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.eicon_navigation_index1, R.mipmap.eicon_navigation_find1, R.mipmap.eicon_navigation_message1, R.mipmap.eicon_navigation_me1};

    private List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> changeFragments = new ArrayList<>();


    //仿微博图片和文字集合
    private int[] menuIconItems = {R.mipmap.eicon_navigation_pic1, R.mipmap.eicon_navigation_pic2, R.mipmap.eicon_navigation_pic3, R.mipmap.eicon_navigation_pic4};
    private String[] menuTextItems = {"文字", "拍摄", "相册", "直播"};

    private LinearLayout menuLayout;
    private View cancelImageView;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_weibo);

        navigationBar = findViewById(R.id.navigationBar);

        fragments.add(new NavigationBarWBFirstFragment());
        fragments.add(new NavigationBarWBSecondFragment());
        fragments.add(new NavigationBarAddThirdFragment());
        fragments.add(new NavigationBarWBFirstFragment());

        changeFragments.add(new NavigationBarAddThirdFragment());
        changeFragments.add(new NavigationBarWBSecondFragment());
        changeFragments.add(new NavigationBarWBFirstFragment());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .centerImageRes(R.mipmap.eicon_navigation_add_image)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .centerLayoutRule(EasyNavigationBar.RULE_CENTER)
                .setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabSelectEvent(View view, int position) {
                        if (position == 3) {
                            Toast.makeText(NavigationBarWeiboActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            //return true则拦截事件、不进行页面切换
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean onTabReSelectEvent(View view, int position) {
                        return false;
                    }

                })
                .setOnCenterTabClickListener(new EasyNavigationBar.OnCenterTabSelectListener() {
                    @Override
                    public boolean onCenterTabSelectEvent(View view) {
                        //跳转页面（全民K歌）   或者   弹出菜单（微博）
                        showMunu();
                        return false;
                    }
                })
                .mode(EasyNavigationBar.NavigationMode.MODE_ADD)
                .anim(Anim.ZoomIn)
                .build();


        navigationBar.setAddViewLayout(createWeiboView());

    }

    //仿微博弹出菜单
    private View createWeiboView() {
        ViewGroup view = (ViewGroup) View.inflate(NavigationBarWeiboActivity.this, R.layout.layout_add_view, null);
        menuLayout = view.findViewById(R.id.icon_group);
        cancelImageView = view.findViewById(R.id.cancel_iv);
        cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAnimation();
            }
        });
        for (int i = 0; i < 4; i++) {
            View itemView = View.inflate(NavigationBarWeiboActivity.this, R.layout.item_navigationbar_icon, null);
            ImageView menuImage = itemView.findViewById(R.id.menu_icon_iv);
            TextView menuText = itemView.findViewById(R.id.menu_text_tv);

            menuImage.setImageResource(menuIconItems[i]);
            menuText.setText(menuTextItems[i]);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            itemView.setLayoutParams(params);
            itemView.setVisibility(View.GONE);
            menuLayout.addView(itemView);
        }
        return view;
    }

    //
    private void showMunu() {
        startAnimation();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //＋ 旋转动画
                cancelImageView.animate().rotation(90).setDuration(400);
            }
        });
        //菜单项弹出动画
        for (int i = 0; i < menuLayout.getChildCount(); i++) {
            final View child = menuLayout.getChildAt(i);
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(500);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(500);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 50 + 100);
        }
    }


    private void startAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //圆形扩展的动画
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        int x = NavigationUtil.getScreenWidth(NavigationBarWeiboActivity.this) / 2;
                        int y = (int) (NavigationUtil.getScreenHeith(NavigationBarWeiboActivity.this) - NavigationUtil.dip2px(NavigationBarWeiboActivity.this, 25));
                        Animator animator = ViewAnimationUtils.createCircularReveal(navigationBar.getAddViewLayout(), x,
                                y, 0, navigationBar.getAddViewLayout().getHeight());
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                navigationBar.getAddViewLayout().setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                //							layout.setVisibility(View.VISIBLE);
                            }
                        });
                        animator.setDuration(300);
                        animator.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 关闭window动画
     */
    private void closeAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                cancelImageView.animate().rotation(0).setDuration(400);
            }
        });

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                int x = NavigationUtil.getScreenWidth(this) / 2;
                int y = (NavigationUtil.getScreenHeith(this) - NavigationUtil.dip2px(this, 25));
                Animator animator = ViewAnimationUtils.createCircularReveal(navigationBar.getAddViewLayout(), x,
                        y, navigationBar.getAddViewLayout().getHeight(), 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //							layout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        navigationBar.getAddViewLayout().setVisibility(View.GONE);
                        //dismiss();
                    }
                });
                animator.setDuration(300);
                animator.start();
            }
        } catch (Exception e) {
        }
    }


    public EasyNavigationBar getNavigationBar() {
        return navigationBar;
    }


    public void changeStyle() {
        navigationBar
                .defaultSetting()
                .fragmentList(changeFragments)
                .fragmentManager(getSupportFragmentManager())
                .titleItems(tabText2)
                .centerLayoutRule(EasyNavigationBar.RULE_BOTTOM)
                .setOnCenterTabClickListener(new EasyNavigationBar.OnCenterTabSelectListener() {
                    @Override
                    public boolean onCenterTabSelectEvent(View view) {
                        Toast.makeText(NavigationBarWeiboActivity.this, "hhh,已经更改样式了", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .textSizeType(EasyNavigationBar.TextSizeType.TYPE_SP)
                .build();


//        navigationBar.setAddViewLayout(createWeiboView());
    }
}
