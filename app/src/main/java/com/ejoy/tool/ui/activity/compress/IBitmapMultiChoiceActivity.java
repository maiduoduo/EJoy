package com.ejoy.tool.ui.activity.compress;
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

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.ImageConfig;
import com.ejoy.tool.common.bean.ImageFileBean;
import com.ejoy.tool.scaffold.utils.FileUtils;
import com.ejoy.tool.scaffold.utils.PairHelp;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.utils.task.CompressImageTask;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.adapter.PictureAdapter;
import com.ejoy.tool.ui.data.resource.ApiResource;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * CN:      IBitmapMultiChoiceActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/2
 * Des:    图片处理
 */
public class IBitmapMultiChoiceActivity extends BaseActivity {

    @BindView(R.id.ry_original)
    RecyclerView mRlOriginal;
    @BindView(R.id.ry_compress)
    RecyclerView mRlCompress;
    @BindView(R.id.btn_compress)
    Button mBtnCompress;


    private List<ImageFileBean> mOriginalPictureList;
    private PictureAdapter mOriginalAdapter;
    private List<ImageFileBean> mCompressPictureList;
    private PictureAdapter mCompressAdapter;
    List<String> mPreviewOriginalData;
    private int mPreviewStatus;//0、代表原图预览；1、代表也缩图预览
    private List<String> mPreviewCompressData;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_multiple_choice_image;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(this,true);
        StatusBarTool.setStatusBarColor(this,Color.parseColor("#FFCF47"));
        mRlOriginal.setLayoutManager(new GridLayoutManager(this, 3));
        mRlCompress.setLayoutManager(new GridLayoutManager(this, 3));
        try {
            ActivityCompat.setExitSharedElementCallback(this, new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    super.onMapSharedElements(names, sharedElements);
                    showLog(PairHelp.PREVIEW_POSITION + "");
                    switch (mPreviewStatus) {
                        case 0:
                            sharedElements.put(PairHelp.transitionName(), mRlOriginal.findViewHolderForAdapterPosition(PairHelp.PREVIEW_POSITION).itemView);
                            break;
                        case 1:
                            sharedElements.put(PairHelp.transitionName(), mRlCompress.findViewHolderForAdapterPosition(PairHelp.PREVIEW_POSITION).itemView);
                            break;
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    protected void initData() {
        mPreviewOriginalData = new ArrayList<>();
        mPreviewCompressData = new ArrayList<>();

        mOriginalPictureList = new ArrayList<>();
        mOriginalPictureList.add(new ImageFileBean());

        mOriginalAdapter = new PictureAdapter(this, mOriginalPictureList);
        mRlOriginal.setAdapter(mOriginalAdapter);

        mCompressPictureList = new ArrayList<>();
        mCompressAdapter = new PictureAdapter(this, mCompressPictureList);
        mRlCompress.setAdapter(mCompressAdapter);
    }

    @Override
    protected void addListener() {
        mOriginalAdapter.setOnItemClickListener(new PictureAdapter.OnItemClickListener() {
            @Override
            public void onAddItemClick(View view, int position) {
                if (!CompressImageTask.get().isCompressImage()) {
                    IBitmapMultiChoiceActivity.this.notifyOriginalAndCompressData();
                    openPhoto(false);
                } else {
                    iToast.showIImgToast("正在压缩！请等待！");
                }
            }

            @Override
            public void onPictureItemClick(View view, int position) {
                mPreviewStatus = 0;
                toPreviewActivity(view, position, mPreviewOriginalData);
            }
        });

        mCompressAdapter.setOnItemClickListener(new PictureAdapter.OnItemClickListener() {
            @Override
            public void onAddItemClick(View view, int position) {

            }

            @Override
            public void onPictureItemClick(View view, int position) {
                mPreviewStatus = 1;
                toPreviewActivity(view, position, mPreviewCompressData);
            }
        });


        mBtnCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOriginalPictureList.size() == 1) {
                    iToast.showIImgToast("请先选择照片");
                    return;
                }
                if (CompressImageTask.get().isCompressImage()) {
                    iToast.showIImgToast("正在压缩,请等待！");
                    return;
                }
                List<ImageConfig> data = new ArrayList<>();
                for (ImageFileBean imageFileBean : mOriginalPictureList) {
                    if (imageFileBean.isImage) {
                        ImageConfig imageConfig = ImageConfig.getDefaultConfig(imageFileBean.imageFile.getAbsolutePath());
                        data.add(imageConfig);
                    }
                }
                final ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
                final View inflate = LayoutInflater.from(IBitmapMultiChoiceActivity.this).inflate(R.layout.item_loading_view, viewGroup, false);
                CompressImageTask.get().compressImages(IBitmapMultiChoiceActivity.this,data, new CompressImageTask.OnImagesResult() {
                    @Override
                    public void startCompress() {
                        viewGroup.addView(inflate);
                    }

                    @Override
                    public void resultFilesSucceed(List<File> fileList) {
                        if (mCompressPictureList.size() > 0) {
                            mCompressPictureList.clear();
                            mCompressAdapter.notifyDataSetChanged();
                        }
                        if (fileList != null && fileList.size() > 0) {
                            mPreviewCompressData.clear();
                            for (File file : fileList) {
                                ImageFileBean imageFileBean = new ImageFileBean();
                                imageFileBean.imageFile = file;
                                imageFileBean.imageSize = FileUtils.imageSize(file.length());
                                imageFileBean.isImage = true;
                                mCompressPictureList.add(imageFileBean);
                                mPreviewCompressData.add(file.getAbsolutePath());
                            }
                            mCompressAdapter.notifyDataSetChanged();

                        }
                        showLog("[压缩后]fileList:-----\n"+new Gson().toJson(fileList));
                        showLog("[压缩后]mCompressPictureList:-----\n"+new Gson().toJson(mCompressPictureList));
                        if (viewGroup.indexOfChild(inflate) != -1) {
                            viewGroup.removeView(inflate);
                        }
                        iToast.showIImgToast("压缩成功");
                    }

                    @Override
                    public void resultFilesError() {
                        if (viewGroup.indexOfChild(inflate) != -1) {
                            viewGroup.removeView(inflate);
                        }
                    }
                });
            }
        });
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void toPreviewActivity(View view, int position, List<String> list) {
        Intent intent = new Intent(this, PreviewImageActivity.class);
        intent.putStringArrayListExtra(ApiResource.IMAGE_PATH_KEY, (ArrayList<String>) list);
        intent.putExtra(ApiResource.CLICK_IMAGE_POSITION_KEY, position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PairHelp.setPreviewPosition(position);
            showLog(PairHelp.PREVIEW_POSITION + "---" + mPreviewOriginalData.size());
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this
                    , PairHelp.addPair(view)).toBundle();
            startActivity(intent, bundle);
        } else {
            startActivity(intent);
        }
    }


    public void notifyOriginalAndCompressData() {
        if (mOriginalPictureList.size() > 1) {
            Iterator<ImageFileBean> iterator = mOriginalPictureList.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().isImage) {
                    iterator.remove();
                }
            }
            mOriginalAdapter.notifyDataSetChanged();
        }
        if (mCompressPictureList.size() > 0) {
            mCompressPictureList.clear();
            mCompressAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void imageFileResult(ImageFileBean bean) {
        super.imageFileResult(bean);
        if (mOriginalPictureList.size() > 0) {
            mOriginalPictureList.add(mOriginalPictureList.size() - 1, bean);
            mOriginalAdapter.notifyDataSetChanged();
        }
        mPreviewOriginalData.add(mPreviewOriginalData.size(), bean.imageFile.getAbsolutePath());

    }


    @Override
    protected void imageFilesResult(List<ImageFileBean> data) {
        super.imageFilesResult(data);
        mOriginalPictureList.addAll(0, data);
        mOriginalAdapter.notifyDataSetChanged();
        mPreviewOriginalData.clear();
        for (ImageFileBean imageFileBean : data) {
            mPreviewOriginalData.add(imageFileBean.imageFile.getAbsolutePath());
        }
    }

    @Override
    protected void onDestroy() {
        CompressImageTask.get().deathCompress();
        super.onDestroy();
    }

    public void multiBack(View view) {
        finish();
    }
}
