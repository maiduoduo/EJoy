package com.ejoy.tool.ui.douyin.fragment;

import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.view.dialog.DouyinCommentDialog;
import com.ejoy.tool.scaffold.view.dialog.DouyinShareDialog;
import com.ejoy.tool.ui.base.base_fragment.BaseFragment;
import com.ejoy.tool.ui.douyin.activity.DouyinMainActivity;
import com.ejoy.tool.ui.douyin.activity.DouyinPlayListActivity;
import com.ejoy.tool.ui.douyin.bean.DouyinCurUserBean;
import com.ejoy.tool.ui.douyin.bean.DouyinMainPageChangeEvent;
import com.ejoy.tool.ui.douyin.bean.DouyinPauseVideoEvent;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinVideoAdapter;
import com.ejoy.tool.ui.douyin.data.constant.DouyinDataCreate;
import com.ejoy.tool.ui.douyin.data.constant.OnVideoControllerListener;
import com.ejoy.tool.ui.douyin.data.constant.OnViewPagerListener;
import com.ejoy.tool.ui.douyin.utils.RxBus;
import com.ejoy.tool.ui.douyin.view.DouyinFullScreenVideoView;
import com.ejoy.tool.ui.douyin.view.ViewPagerLayoutManager;
import com.module.ires.bean.bean.DouyinVideoBean;
import com.module.iviews.view.DouyinLikeView;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * @ClassName:  DouyinRecommendFragment
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 推荐播放页
 */
public class DouyinRecommendFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private DouyinVideoAdapter adapter;
    private ViewPagerLayoutManager viewPagerLayoutManager;
    /** 当前播放视频位置 */
    private int curPlayPos = -1;
    private DouyinFullScreenVideoView videoView;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;
    private ImageView ivCurCover;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_douyin_recommend;
    }

    @Override
    public void intBase() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View rootView) {
        adapter = new DouyinVideoAdapter(R.layout.item_douyin_video, DouyinDataCreate.datas,getActivity());
        recyclerView.setAdapter(adapter);
        videoView = new DouyinFullScreenVideoView(getActivity());
        setViewPagerLayoutManager();
        setRefreshEvent();

        //监听播放或暂停事件
        RxBus.getDefault().toObservable(DouyinPauseVideoEvent.class)
                .subscribe((Action1<DouyinPauseVideoEvent>) event -> {
                    if (event.isPlayOrPause()) {
                        videoView.start();
                    } else {
                        videoView.pause();
                    }
                });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }



    @Override
    public void onResume() {
        super.onResume();
        //返回时，推荐页面可见，则继续播放视频
        if (DouyinMainActivity.curMainPage == 0 && DouyinMainFragment.curPage == 1) {
            videoView.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();

        videoView.stopPlayback();
    }

    private void setViewPagerLayoutManager() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewPagerLayoutManager);
        recyclerView.scrollToPosition(DouyinPlayListActivity.initPos);

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                playCurVideo(DouyinPlayListActivity.initPos);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (ivCurCover != null) {
                    ivCurCover.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                playCurVideo(position);
            }
        });
    }

    private void setRefreshEvent() {
        refreshLayout.setColorSchemeResources(R.color.color_douyin_link);
        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
            }
        }.start());
    }

    private void playCurVideo(int position) {
        if (position == curPlayPos) {
            return;
        }

        View itemView = viewPagerLayoutManager.findViewByPosition(position);
        if (itemView == null) {
            return;
        }

        ViewGroup rootView = itemView.findViewById(R.id.rl_container);
        DouyinLikeView likeView = rootView.findViewById(R.id.likeview);
//        DouyinControllerView controllerView = rootView.findViewById(R.id.controller);
        ImageView ivPlay = rootView.findViewById(R.id.iv_play);
        ImageView ivCover = rootView.findViewById(R.id.iv_cover);
        ivPlay.setAlpha(0.4f);

        //播放暂停事件
        likeView.setOnPlayPauseListener(() -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                ivPlay.setVisibility(View.VISIBLE);
            } else {
                videoView.start();
                ivPlay.setVisibility(View.GONE);
            }

        });

        //评论点赞事件
//        likeShareEvent(controllerView);
        likeShareEvent();

        //切换播放视频的作者主页数据
        RxBus.getDefault().post(new DouyinCurUserBean(DouyinDataCreate.datas.get(position).getUserBean()));

        curPlayPos = position;

        //切换播放器位置
        dettachParentView(rootView);

        autoPlayVideo(curPlayPos, ivCover);
    }

    /**
     * 移除videoview父view
     */
    private void dettachParentView(ViewGroup rootView) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view
        ViewGroup parent = (ViewGroup) videoView.getParent();
        if (parent != null) {
            parent.removeView(videoView);
        }
        rootView.addView(videoView, 0);
    }

    /**
     * 自动播放视频
     */
    private void autoPlayVideo(int position, ImageView ivCover) {
//        String bgVideoPath = "android.resource://" + getActivity().getPackageName() + "/" + DouyinDataCreate.datas.get(position).getVideoRes();
        DouyinVideoBean douyinVideoBean = DouyinDataCreate.datas.get(position);
        String bgVideoPath = DouyinDataCreate.datas.get(position).getVideoUrl();
        if (douyinVideoBean.getVideoOritation() == 1) {//宽屏视频
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,400,0,0);
            videoView.setLayoutParams(lp);
            videoView.setMeasure(700, 400);

        }else {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,0,0);
            videoView.setLayoutParams(lp);
            videoView.setMeasure(1000, 1000);
        }
            videoView.setVideoPath(bgVideoPath);
            videoView.start();
            videoView.setOnPreparedListener(mp -> {
                mp.setLooping(true);

                //延迟取消封面，避免加载视频黑屏
                new CountDownTimer(200, 200) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        ivCover.setVisibility(View.GONE);
                        ivCurCover = ivCover;
                    }
                }.start();


//
//                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//                        //FixMe 获取视频资源的宽度
//                        int mVideoWidth = mp.getVideoWidth();
//                        //FixMe 获取视频资源的高度
//                        int mVideoHeight = mp.getVideoHeight();
//
//                        float scale = (float) mVideoWidth / (float) mVideoHeight;
//                        refreshPortraitScreen(showVideoHeight == 0 ? EDensityUtils.dp2px(mActivity, 300) : showVideoHeight);
//                    }
//                });

            });



    }


    /**
     * 用户操作事件
     */
    private void likeShareEvent() {
        adapter.setListener(new OnVideoControllerListener() {
            @Override
            public void onHeadClick() {
                RxBus.getDefault().post(new DouyinMainPageChangeEvent(1));
            }

            @Override
            public void onLikeClick() {

            }

            @Override
            public void onCommentClick() {
                DouyinCommentDialog commentDialog = new DouyinCommentDialog();
                commentDialog.show(getChildFragmentManager(), "");
            }

            @Override
            public void onShareClick() {
                new DouyinShareDialog().show(getChildFragmentManager(), "");
            }
        });
    }

}
