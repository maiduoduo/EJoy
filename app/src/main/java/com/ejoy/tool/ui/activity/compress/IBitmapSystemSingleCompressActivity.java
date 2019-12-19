package com.ejoy.tool.ui.activity.compress;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.ImageConfig;
import com.ejoy.tool.common.bean.ImageFileBean;
import com.ejoy.tool.scaffold.utils.FileUtils;
import com.ejoy.tool.scaffold.utils.IBitmapUtils;
import com.ejoy.tool.scaffold.utils.PairHelp;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.utils.task.CompressImageTask;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.ApiResource;
import com.ejoy.tool.ui.mvp.base.BasePresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * CN:      IBitmapSystemSingleCompressActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/3
 * Des:    系统API单张图片压缩处理
 */
public class IBitmapSystemSingleCompressActivity extends BaseActivity {
    @BindView(R.id.original_choosebtn) Button mChoosebtn;
    @BindView(R.id.compressoriginal_btn) Button  mSystemCompressBtn;
    @BindView(R.id.tvSystemAlbum) TextView mTvSystemAlbum;
    @BindView(R.id.tvSystemCompress) TextView mTvSystemCompress;
    @BindView(R.id.ivSystemAlbum) ImageView mIvSystemImageview;
    @BindView(R.id.ivSystemCompress) ImageView mIvSystemCompressImageview;
    @BindView(R.id.compressBeforeText) TextView mCompressBeforeInfo;
    @BindView(R.id.compressAfterText) TextView mCompressAfterInfo;

    private File mImageFile;
    // private boolean mIsCompress;
    private int mClickPosition;
    private File mCompressImageFile;
    private List<String> mFilePathData;
    private StringBuilder oldOriginalImgBuilder,newOriginalImgBuilder;


    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_system_sing_compress_image;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(_mActivity, true);
        StatusBarTool.setStatusBarColor(_mActivity, Color.parseColor("#FFCF47"));
        oldOriginalImgBuilder = new StringBuilder();
        newOriginalImgBuilder = new StringBuilder();
    }

    @Override
    protected void initData() {
        mFilePathData = new ArrayList<>();
    }

    @Override
    protected void addListener() {
        mChoosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearTextInfo();
                clickChooseView();
                openPhoto(true);
            }
        });
        mSystemCompressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCompressView();

            }
        });
        mIvSystemImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickPosition = 0;
                clickRawImage(view);
            }
        });
        mIvSystemCompressImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickPosition = 1;
                clickCompressImage(view);
            }
        });
        ActivityCompat.setExitSharedElementCallback(this, new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                super.onMapSharedElements(names, sharedElements);
                switch (PairHelp.PREVIEW_POSITION) {
                    case 0:
                        sharedElements.put(PairHelp.transitionName(), mIvSystemImageview);
                        break;
                    case 1:
                        sharedElements.put(PairHelp.transitionName(), mIvSystemCompressImageview);
                        break;
                }


            }
        });
    }

    private void clearTextInfo() {
        mTvSystemAlbum.setText("");
        mTvSystemCompress.setText("");
        mCompressBeforeInfo.setText("");
        mCompressAfterInfo.setText("");
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    private void clickCompressImage(View view) {
        PairHelp.setPreviewPosition(1);
        toPreviewActivity(view, mIvSystemCompressImageview, mCompressImageFile);
    }


    private void clickRawImage(View view) {
        PairHelp.setPreviewPosition(0);
        toPreviewActivity(view, mIvSystemImageview, mImageFile);
    }

    private void toPreviewActivity(View view, ImageView imageView, File imageFile) {
        if (imageView.getDrawable() != null && FileUtils.isImageFile(imageFile)) {
            Intent intent = new Intent(this, PreviewImageActivity.class);
            intent.putStringArrayListExtra(ApiResource.IMAGE_PATH_KEY, (ArrayList<String>) mFilePathData);
            intent.putExtra(ApiResource.CLICK_IMAGE_POSITION_KEY, mClickPosition);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this
                        , PairHelp.addPair(view)).toBundle();
                startActivity(intent, bundle);
            } else {
                startActivity(intent);
            }
        }
    }

    private void clickCompressView() {
        if (mImageFile == null) {
            iToast.showIImgToast("请先选择图片");
        } else {
            if (FileUtils.isImageFile(mImageFile)) {
                //压缩：默认压缩质量为：60
                File compressFinishFile = new File(IBitmapUtils.compressImage(mImageFile.getAbsolutePath()));
                mCompressImageFile = compressFinishFile;
                mTvSystemCompress.setText("Size:" + FileUtils.imageSize(compressFinishFile.length()));
                if (!_mActivity.isFinishing()) {
                   Glide.with(_mActivity).load(compressFinishFile).into(mIvSystemCompressImageview);
                }
                newOriginalImgBuilder.setLength(0);
                newOriginalImgBuilder.append("compressed success！压缩后： \n" +
                        ">>>  path :\n");
                newOriginalImgBuilder.append(compressFinishFile.getAbsolutePath() + "\n\n");
                newOriginalImgBuilder.append(String.format(">>>  Size :    %s", FileUtils.getReadableFileSize(compressFinishFile.length())) + "\n");
                newOriginalImgBuilder.append(">>>  FileName :    " + compressFinishFile.getName() + "\n");
                mCompressAfterInfo.setText(newOriginalImgBuilder.toString());

//                if (!CompressImageTask.get().isCompressImage()) {
//                    final ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
//                    final View inflate = LayoutInflater.from(_mActivity).inflate(R.layout.item_loading_view, viewGroup, false);
//
//                    CompressImageTask.get().compressImage(_mActivity, ImageConfig.getDefaultConfig(mImageFile.getAbsolutePath()), new CompressImageTask.OnImageResult() {
//                        @Override
//                        public void startCompress() {
//                            viewGroup.addView(inflate);
//                        }
//
//                        @Override
//                        public void resultFileSucceed(File file) {
//                            mCompressImageFile = file;
//                            mFilePathData.add(file.getAbsolutePath());
//                            if (!_mActivity.isFinishing()) {
//                                Glide.with(_mActivity).load(file).into(mIvSystemCompressImageview);
//                            }
//                            mTvSystemCompress.setText("Size:" + FileUtils.imageSize(file.length()));
//                            if (viewGroup.indexOfChild(inflate) != -1) {
//                                viewGroup.removeView(inflate);
//                            }
//
//
//                            StringBuilder newInfo = new StringBuilder();
//                            newInfo.append("compressed success！压缩后： \n" +
//                                    ">>>  path :\n");
//                            newInfo.append(file.getAbsolutePath() + "\n\n");
//                            newInfo.append(String.format(">>>  Size :    %s", FileUtils.getReadableFileSize(file.length())) + "\n");
//                            newInfo.append(">>>  FileName :    " + file.getName() + "\n");
//                            mCompressAfterInfo.setText(newInfo.toString());
//                        }
//
//                        @Override
//                        public void resultFileError() {
//                            if (viewGroup.indexOfChild(inflate) != -1) {
//                                viewGroup.removeView(inflate);
//                            }
//                        }
//                    });
//                } else {
//                    iToast.showIImgToast("正在压缩，请勿重复压缩");
//                }
            } else {
                iToast.showIImgToast("该文件不是图片");
            }
        }
    }


    private void clickChooseView() {
        if (mIvSystemImageview.getDrawable() != null) {
            mIvSystemImageview.setImageDrawable(null);
        }
        if (mImageFile != null) {
            mTvSystemAlbum.setText(null);
            mImageFile = null;
        }
        if (mCompressImageFile != null) {
            mTvSystemCompress.setText(null);
        }
        if (mIvSystemCompressImageview.getDrawable() != null) {
            mIvSystemCompressImageview.setImageDrawable(null);
        }
        mFilePathData.clear();

    }


    @Override
    protected void imageFileResult(ImageFileBean bean) {
        super.imageFileResult(bean);
        if (bean != null) {
            mImageFile = bean.imageFile;
            mFilePathData.add(bean.imageFile.getAbsolutePath());
            Glide.with(this).load(bean.imageFile).into(mIvSystemImageview);
            mTvSystemAlbum.setText("Size:" + FileUtils.imageSize(mImageFile.length()));

            oldOriginalImgBuilder.setLength(0);
            oldOriginalImgBuilder.append("choose success！压缩前： \n\n" +
                    ">>> path :\n");
            oldOriginalImgBuilder.append(mImageFile.getAbsolutePath() + "\n\n");
            oldOriginalImgBuilder.append(String.format(">>> Size : %s", FileUtils.getReadableFileSize(mImageFile.length())) + "\n");
            oldOriginalImgBuilder.append(">>>  FileName :    " + mImageFile.getName() + "\n");
            mCompressBeforeInfo.setText(oldOriginalImgBuilder.toString());
        }
    }

    @Override
    protected void onDestroy() {
        CompressImageTask.get().deathCompress();
        super.onDestroy();

    }

    public void singleBack(View view) {
        finish();
    }

}
