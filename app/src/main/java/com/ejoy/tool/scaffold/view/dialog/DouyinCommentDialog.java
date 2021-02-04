package com.ejoy.tool.scaffold.view.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.douyin.bean.DouyinCommentBean;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinCommentAdapter;
import com.ejoy.tool.ui.douyin.data.constant.DouyinDataCreate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @ClassName:  DouyinCommentDialog
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/4
 * @des: 评论弹框
 */
public class DouyinCommentDialog extends BaseBottomSheetDialog {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private DouyinCommentAdapter commentAdapter;
    private ArrayList<DouyinCommentBean> datas = new ArrayList<>();
    private View view;
    private int[] likeArray = new int[]{4919, 334, 121, 423, 221, 23};
    private String[] commentArray = new String[]{"我就说左脚踩右脚可以上天你们还不信！", "全是评论点赞，没人关注吗", "哈哈哈哈", "像谁，没看出来", "你这西安话真好听"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_douyin_comment, container);
        ButterKnife.bind(this, view);

        init();

        return view;
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentAdapter = new DouyinCommentAdapter(R.layout.item_douyin_comment,datas,getContext());
        recyclerView.setAdapter(commentAdapter);

        loadData();
    }

    private void loadData() {
        for (int i = 0; i < DouyinDataCreate.userList.size(); i++) {
            DouyinCommentBean commentBean = new DouyinCommentBean();
            commentBean.setUserBean(DouyinDataCreate.userList.get(i));
            commentBean.setContent(commentArray[(int) (Math.random() * commentArray.length)]);
            commentBean.setLikeCount(likeArray[(int) (Math.random() * likeArray.length)]);
            datas.add(commentBean);
        }
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getHeight() {
        return getResources().getDisplayMetrics().heightPixels - 400;
    }
}
