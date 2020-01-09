package com.module.iviews.popup.weibo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.view.EDividerItemDecoration;
import com.module.iviews.R;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * CN:      ImgFolderPopWindow
 * Author： DINGCL
 * Date:   2016-5-8
 * Des:    TODO:相册页的弹出界面
 */
public class ImgFolderPopWindow extends PopupWindow {

    private View mView;
    private RecyclerView mRecyclerView;
    private int mWidth;
    private int mHeight;
    private Context mContext;
    private ArrayList<AlbumFolderInfo> mFolderList;
    public OnFolderClickListener onFolderClickListener;
    private int mCurrentFolder;
    private BaseQuickAdapter mAdapter;


    public ImgFolderPopWindow(Context context, int width, int height, ArrayList<AlbumFolderInfo> folderList, int currentFolder) {
        this.mContext = context;
        this.mWidth = width;
        this.mHeight = height;
        this.mFolderList = folderList;
        this.mCurrentFolder = currentFolder;
        mView = LayoutInflater.from(context).inflate(R.layout.compose_picfolder_pop, null);
        setContentView(mView);
        initPopWindow();
        initListView();
        setUpListener();
    }

    private void initListView() {
        mRecyclerView = mView.findViewById(R.id.listview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new EDividerItemDecoration(mContext,1,1));
        mRecyclerView.setAdapter(mAdapter = new BaseQuickAdapter<AlbumFolderInfo, BaseViewHolder>(R.layout.compose_picfolder_pop_item, mFolderList) {
                                     @Override
                                     protected void convert(BaseViewHolder helper, AlbumFolderInfo item) {
                                         int adapterPosition = helper.getAdapterPosition();
                                         if (mCurrentFolder == adapterPosition){
                                             helper.getView(R.id.folderlayout).setBackgroundColor(Color.parseColor("#f2f2f2"));
                                         } else {
                                             helper.getView(R.id.folderlayout).setBackgroundColor(Color.parseColor("#ffffff"));
                                         }

                                         helper.setText(R.id.foldername,item.getFolderName());
                                         helper.setText(R.id.num,"(" + item.getImageInfoList().size() + ")");

                                         Glide.with(mContext).load("file:///" + item.getFrontCover().getAbsolutePath()).bitmapTransform(new CropCircleTransformation(mContext))
                                                 .into((ImageView) helper.getView(R.id.firstpic));
                                     }
                                 }
          );
        mRecyclerView.getLayoutParams().height = mHeight;

    }


    public interface OnFolderClickListener {
        void OnFolderClick(int position);
    }

    public void setOnFolderClickListener(OnFolderClickListener onFolderClickListener) {
        this.onFolderClickListener = onFolderClickListener;
    }

    private void setUpListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onFolderClickListener.OnFolderClick(position);
            }
        });
    }


    private void initPopWindow() {
        this.setWidth(mWidth);
        this.setHeight(EDensityUtils.getScreenHeight(mContext));
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        this.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                Rect rect = new Rect(mRecyclerView.getLeft(), mRecyclerView.getTop(), mRecyclerView.getRight(), mRecyclerView.getBottom());
                if (!rect.contains((int) x, (int) y)) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }
}

