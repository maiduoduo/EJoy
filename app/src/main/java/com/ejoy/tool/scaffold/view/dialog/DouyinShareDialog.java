package com.ejoy.tool.scaffold.view.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ejoy.tool.R;
import com.ejoy.tool.ui.douyin.bean.DouyinShareBean;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinPrivateLetterAdapter;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinShareAdapter;
import com.ejoy.tool.ui.douyin.data.constant.DouyinDataCreate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @ClassName:  DouyinShareDialog
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/4
 * @des: 分享弹框
 */
public class DouyinShareDialog extends BaseBottomSheetDialog {
    @BindView(R.id.rv_private_letter)
    RecyclerView rvPrivateLetter;
    @BindView(R.id.rv_share)
    RecyclerView rvShare;
    private DouyinPrivateLetterAdapter privateLetterAdapter;
    private DouyinShareAdapter shareAdapter;
    private View view;
    private ArrayList<DouyinShareBean> shareBeans = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_douyin_share, container);
        ButterKnife.bind(this, view);
        init();

        return view;
    }

    private void init() {

        rvPrivateLetter.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        privateLetterAdapter = new DouyinPrivateLetterAdapter(R.layout.item_douyin_private_letter,DouyinDataCreate.userList,getContext());
        rvPrivateLetter.setAdapter(privateLetterAdapter);

        rvShare.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        shareAdapter = new DouyinShareAdapter(R.layout.item_douyin_share,shareBeans,getContext());
        rvShare.setAdapter(shareAdapter);

        setShareDatas();

    }

    private void setShareDatas() {
        shareBeans.add(new DouyinShareBean(R.string.icon_friends, "朋友圈", R.color.color_douyin_wechat_iconbg));
        shareBeans.add(new DouyinShareBean(R.string.icon_wechat, "微信", R.color.color_douyin_wechat_iconbg));
        shareBeans.add(new DouyinShareBean(R.string.icon_qq, "QQ", R.color.color_douyin_qq_iconbg));
        shareBeans.add(new DouyinShareBean(R.string.icon_qq_space, "QQ空间", R.color.color_douyin_qqzone_iconbg));
        shareBeans.add(new DouyinShareBean(R.string.icon_weibo, "微博", R.color.color_douyin_weibo_iconbg));
        shareBeans.add(new DouyinShareBean(R.string.icon_comment, "私信好友", R.color.color_douyin_FF0041));

        shareAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getHeight() {
        return dp2px(getContext(), 355);
    }

}
