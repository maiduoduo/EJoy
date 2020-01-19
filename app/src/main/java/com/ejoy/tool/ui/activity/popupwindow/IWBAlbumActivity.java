package com.ejoy.tool.ui.activity.popupwindow;


import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.adapter.IGirdViewAdapter;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.iviews.image.EImageScan;
import com.module.iviews.popup.weibo.AlbumFolderInfo;
import com.module.iviews.popup.weibo.EImgFolderPopWindow;
import com.module.iviews.popup.weibo.ImageInfoBean;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * CN:      IWBAlbumActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/2
 * Des:    TODO:发表图片/视频界面
 */
public class IWBAlbumActivity extends BaseActivity implements IGirdViewAdapter.OnImgSelectListener {


    @BindView(R.id.cancal)
    TextView mCancal;
    @BindView(R.id.foldername)
    TextView foldername;
    @BindView(R.id.folder_arrow)
    ImageView mArrow;
    @BindView(R.id.folder)
    LinearLayout folderLayout;
    @BindView(R.id.next)
    TextView mNext;
    @BindView(R.id.toolbarLayout)
    RelativeLayout toolbarLayout;
    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.preview)
    TextView preview;
    @BindView(R.id.originpic)
    TextView originpic;


    private ArrayList<AlbumFolderInfo> mFolderList;
    private ArrayList<ImageInfoBean> mSelectImgList;
    private IGirdViewAdapter mAdapter;
    private EImgFolderPopWindow mPopWindow;
    private int mCurrentFolder;

    @Override
    protected Object registSatusbarBgcolor() {
        return baseWhite;
    }

    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_compose_pic_album;
    }

    @Override
    protected void initView(View mRootView) {
        mSelectImgList = getIntent().getParcelableArrayListExtra("selectedImglist");
        if (mSelectImgList == null)mSelectImgList = new ArrayList<>();
        mCurrentFolder = 0;

        //开始扫描图片
        EImageScan imageScan = new EImageScan(_mActivity, getLoaderManager()) {
            @Override
            public void onScanFinish(ArrayList<AlbumFolderInfo> folderList) {
                mFolderList = folderList;
                setAlreadySelectFile(mSelectImgList, mFolderList);
                Message message = Message.obtain();
                mHandler.sendMessage(message);
            }
        };
        changeSendButtonBg(mNext, mSelectImgList.size());
        setUpListener();
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


    private void setAlreadySelectFile(ArrayList<ImageInfoBean> selectImgList, ArrayList<AlbumFolderInfo> folderList) {

        if (selectImgList == null || selectImgList.size() == 0) {
            return;
        }
        String selectPath = "";
        ArrayList<ImageInfoBean> allImg = (ArrayList<ImageInfoBean>) folderList.get(0).getImageInfoList();

        for (ImageInfoBean selectInfo : selectImgList) {
            //拿到绝对路径之后
            selectPath = selectInfo.getImageFile().getAbsolutePath();
            //根据绝对路路径取出对应的imageinfor，修改select的值
            for (int i = 0; i < allImg.size(); i++) {
                if (selectPath.equals(allImg.get(i).getImageFile().getAbsolutePath())) {
                    allImg.get(i).setIsSelected(true);
                }
            }
        }
    }


    private void setUpListener() {
        folderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow = new EImgFolderPopWindow(_mActivity, EDensityUtils.getScreenWidth(_mActivity), (int) (EDensityUtils.getScreenHeight(_mActivity) * 0.6), mFolderList, mCurrentFolder);
                mPopWindow.setOnFolderClickListener(new EImgFolderPopWindow.OnFolderClickListener() {
                    @Override
                    public void OnFolderClick(int position) {
                        foldername.setText(mFolderList.get(position).getFolderName());
                        mCurrentFolder = position;
                        initGridView(position);
                        mPopWindow.dismiss();
                    }
                });
//                mPopWindow.showAsDropDown(toolbarLayout, 0, EDensityUtils.dp2px(_mActivity,45));
//                mPopWindow.showAtLocation(toolbarLayout,Gravity.TOP,0,0);
                mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mArrow.setImageResource(R.drawable.navigationbar_arrow_down);
                    }
                });
                mArrow.setImageResource(R.drawable.navigationbar_arrow_up);

                mPopWindow.showAsDropDown(toolbarLayout);
//                mPopWindow.showAtLocation(toolbarLayout,Gravity.TOP,0,0);
            }
        });
        mCancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("selectImgList", mSelectImgList);
                setResult(1, intent);
                finish();
            }
        });
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            initGridView(mCurrentFolder);
        }
    };


    private void initGridView(int position) {
        mAdapter = new IGirdViewAdapter(_mActivity, mFolderList.get(position).getImageInfoList(), mSelectImgList);
        mAdapter.setOnImgSelectListener(this);
        gridview.setAdapter(mAdapter);

    }


    @Override
    public void OnSelect(ArrayList<ImageInfoBean> imageInfos) {
        mSelectImgList = imageInfos;
        changeSendButtonBg(mNext, mSelectImgList.size());
    }

    @Override
    public void OnDisSelect(ArrayList<ImageInfoBean> imageInfos) {
        mSelectImgList = imageInfos;
        changeSendButtonBg(mNext, mSelectImgList.size());
    }


    /**
     * 根据输入的文本数量，决定发送按钮的背景
     *
     * @param textView
     * @param length
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void changeSendButtonBg(TextView textView, int length) {
        if (length > 0) {
            textView.setBackground(getResources().getDrawable(R.drawable.compose_send_corners_highlight_bg));
            textView.setTextColor(Color.parseColor("#fbffff"));
            textView.setText("下一步(" + length + ")");
            textView.setEnabled(true);
        } else {
            textView.setBackground(getResources().getDrawable(R.drawable.compose_send_corners_bg));
            textView.setTextColor(Color.parseColor("#b3b3b3"));
            textView.setText("下一步");
            textView.setEnabled(false);
        }
    }
}
