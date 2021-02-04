package com.ejoy.tool.ui.douyin.data.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.GlideUtils;
import com.ejoy.tool.ui.douyin.data.constant.AutoLinkHerfManager;
import com.ejoy.tool.ui.douyin.data.constant.OnVideoControllerListener;
import com.module.ires.bean.bean.DouyinVideoBean;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.EResUtils;
import com.module.iviews.image.DouyinCircleImageView;
import com.module.iviews.textview.AutoLinkTextView;
import com.module.iviews.textview.IconFontTextView;
import com.module.iviews.view.DouyinLikeView;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static android.view.animation.Animation.INFINITE;

public class DouyinVideoAdapter extends BaseQuickAdapter<DouyinVideoBean, BaseViewHolder> {
    private static final String TAG = "adapter";
    private Context mContext;
    private OnVideoControllerListener listener;

    public DouyinVideoAdapter(int layoutResId, List<DouyinVideoBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    public void setListener(OnVideoControllerListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, DouyinVideoBean item) {
        int position = helper.getAdapterPosition();
        DouyinLikeView likeView = helper.getView(R.id.likeview);
//        DouyinControllerView controllerView = helper.getView(R.id.controller);
        ImageView ivCover = helper.getView(R.id.iv_cover);
        ivCover.setImageResource(item.getCoverRes());

        //播放
        RelativeLayout rlCotrollerView = helper.getView(R.id.rlCotrollerView);
        AutoLinkTextView autoLinkTextView = helper.getView(R.id.tv_content);
        DouyinCircleImageView ivHead = helper.getView(R.id.iv_head);
        LottieAnimationView animationView = helper.getView(R.id.lottie_anim);
        RelativeLayout rlLike = helper.getView(R.id.rl_like);
        IconFontTextView ivComment = helper.getView(R.id.iv_comment);
        IconFontTextView ivShare = helper.getView(R.id.iv_share);
        ImageView ivRecord = helper.getView(R.id.iv_record);
        RelativeLayout rlRecord = helper.getView(R.id.rl_record);
        TextView tvNickname = helper.getView(R.id.tv_nickname);
        DouyinCircleImageView ivHeadAnim = helper.getView(R.id.iv_head_anim);
        IconFontTextView ivLike = helper.getView(R.id.iv_like);
        TextView tvLikecount = helper.getView(R.id.tv_likecount);
        TextView tvCommentcount = helper.getView(R.id.tv_commentcount);
        TextView tvSharecount = helper.getView(R.id.tv_sharecount);
        ImageView ivFocus = helper.getView(R.id.iv_focus);


        /**
         * 循环旋转动画
         */
        setRotateAnim(rlRecord);

        /**
         * 设置视频数据
         */
//        ivHead.setImageResource(item.getUserBean().getHead());

        GlideUtils.showImage(mContext,item.getUserBean().getHeadUrl(),ivHead);
        tvNickname.setText("@" + item.getUserBean().getNickName());
        AutoLinkHerfManager.setContent(item.getContent(), autoLinkTextView);
//        ivHeadAnim.setImageResource(item.getUserBean().getHead());
        GlideUtils.showImage(mContext,item.getUserBean().getHeadUrl(),ivHeadAnim);
        tvLikecount.setText(EDensityUtils.numberFilter(item.getLikeCount()));
        tvCommentcount.setText(EDensityUtils.numberFilter(item.getCommentCount()));
        tvSharecount.setText(EDensityUtils.numberFilter(item.getShareCount()));

        animationView.setAnimation("like.json");

        //点赞状态
        if (item.isLiked()) {
            ivLike.setTextColor(EResUtils.getColor(mContext, R.color.color_douyin_FF0041));
        } else {
            ivLike.setTextColor(EResUtils.getColor(mContext, R.color.white));
        }

        //关注状态
        if (item.isFocused()) {
            ivFocus.setVisibility(GONE);
        } else {
            ivFocus.setVisibility(VISIBLE);
        }

        likeView.setOnLikeListener(() -> {
            /**
             * 点赞动作
             */
            if (!item.isLiked()) {//未点赞，会有点赞效果，否则无
                //点赞
                animationView.setVisibility(VISIBLE);
                animationView.playAnimation();
                ivLike.setTextColor(EResUtils.getColor(mContext,R.color.color_douyin_FF0041));
            } else {
                //取消点赞
                animationView.setVisibility(INVISIBLE);
                ivLike.setTextColor(EResUtils.getColor(mContext,R.color.white));
            }

            item.setLiked(!item.isLiked());

        });



        /**
         * 监听事件
         */
        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHeadClick();
            }
        });
        ivComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCommentClick();
            }
        });
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onShareClick();
            }
        });
        rlLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLikeClick();
                /**
                 * 点赞动作
                 */
                if (!item.isLiked()) {//未点赞，会有点赞效果，否则无
                    //点赞
                    animationView.setVisibility(VISIBLE);
                    animationView.playAnimation();
                    ivLike.setTextColor(EResUtils.getColor(mContext,R.color.color_douyin_FF0041));
                } else {
                    //取消点赞
                    animationView.setVisibility(INVISIBLE);
                    ivLike.setTextColor(EResUtils.getColor(mContext,R.color.white));
                }

                item.setLiked(!item.isLiked());
            }
        });
        ivFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!item.isFocused()) {
                    item.setLiked(true);
                    ivFocus.setVisibility(GONE);
                }
            }
        });


    }

    /**
     * 循环旋转动画
     *
     * @param rlRecord
     */
    private void setRotateAnim(RelativeLayout rlRecord) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(INFINITE);
        rotateAnimation.setDuration(8000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rlRecord.startAnimation(rotateAnimation);
    }



}
