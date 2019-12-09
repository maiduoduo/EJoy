package com.ejoy.tool.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.ejoy.tool.common.bean.ImageFileBean;
import com.ejoy.tool.common.helper.dialog.PhotoDialog;
import com.ejoy.tool.scaffold.utils.FileUtils;
import com.ejoy.tool.scaffold.utils.IToast;
import com.ejoy.tool.scaffold.utils.IToastImageType;
import com.ejoy.tool.scaffold.utils.LogUtils;
import com.ejoy.tool.ui.activity.permission.OnPermissionsResult;
import com.ejoy.tool.ui.activity.permission.PermissionActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * CN:      ICameraActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/2
 * Des:    图片处理
 */
public abstract class ICameraActivity extends PermissionActivity {

    public static final int REQUEST_CODE_CHOOSE = 1000;
    private PhotoDialog mPhotoDialog;
    private static final int PICK_IMAGE_REQUEST_CODE = 100;
    private static final int OPEN_CAMERA_REQUEST_CODE = 101;
    private File mCameraFile;
    public static final int maxSelectable = 9;

    private void openCamera() {
        requestPermission(new OnPermissionsResult() {
            @Override
            public void onAllow(List<String> allowPermissions) {
                onCamera();
            }

            @Override
            public void onNoAllow(List<String> noAllowPermissions) {
                Toast.makeText(ICameraActivity.this,"相机为必须权限!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onForbid(List<String> noForbidPermissions) {
                showForbidPermissionDialog("相机权限");
            }

            @Override
            public void onLowVersion() {
                onCamera();
            }
        }, Manifest.permission.CAMERA);
    }

    private void onCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            mCameraFile = FileUtils.resultImageFile();
            Uri cameraUri = FileUtils.fileToUri(this, mCameraFile, cameraIntent);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
            startActivityForResult(cameraIntent, OPEN_CAMERA_REQUEST_CODE);
        }
    }

    private void openAlbum() {
        requestPermission(new OnPermissionsResult() {
            @Override
            public void onAllow(List<String> allowPermissions) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
            }

            @Override
            public void onNoAllow(List<String> noAllowPermissions) {
                Toast.makeText(ICameraActivity.this,"存储为必须权限!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onForbid(List<String> noForbidPermissions) {
                showForbidPermissionDialog("存储权限");
            }

            @Override
            public void onLowVersion() {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, ICameraActivity.PICK_IMAGE_REQUEST_CODE);
            }
        },Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume--", "onResume");
    }

    protected void openPhoto(final boolean isSingChoice) {
        if (mPhotoDialog == null) {
            mPhotoDialog = new PhotoDialog(this);
        }
        if (!mPhotoDialog.isShowing()) {
            mPhotoDialog.show();
        }
        mPhotoDialog.setOnPhotoDialogItemClickListener(new PhotoDialog.OnPhotoDialogItemClickListener() {
            @Override
            public void onClickCamera(View view) {
                mPhotoDialog.dismiss();
                openCamera();
            }

            @Override
            public void onClickAlbum(View view) {
                mPhotoDialog.dismiss();
                if (isSingChoice) {
                    openAlbum();
                } else {
                    openZhiHuAlbum();
                }
            }

            @Override
            public void onClickCancel(View view) {
                mPhotoDialog.dismiss();
            }
        });
    }

    protected void openZhiHuAlbum() {
        requestPermission(new OnPermissionsResult() {
            @Override
            public void onAllow(List<String> allowPermissions) {
                Matisse.from(ICameraActivity.this)
                        .choose(MimeType.ofImage())
                        .countable(true)
                        .maxSelectable(maxSelectable)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
            }

            @Override
            public void onNoAllow(List<String> noAllowPermissions) {
                Toast.makeText(ICameraActivity.this,"存储为必须权限!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onForbid(List<String> noForbidPermissions) {
                showForbidPermissionDialog("存储权限");
            }

            @Override
            public void onLowVersion() {
                Matisse.from(ICameraActivity.this)
                        .choose(MimeType.ofImage())
                        .countable(true)
                        .maxSelectable(maxSelectable)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
            }
        },Manifest.permission.READ_EXTERNAL_STORAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == PICK_IMAGE_REQUEST_CODE) {
                    if (data == null) {
                        Toast.makeText(this,"获取图片异常!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        ImageFileBean bean = new ImageFileBean();
                        bean.imageFile = FileUtils.from(this, data.getData());
                        if (bean.imageFile != null && FileUtils.isImageFile(bean.imageFile)) {
                            bean.imageSize = FileUtils.imageSize(bean.imageFile.length());
                        }
                        imageFileResult(bean);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (requestCode == OPEN_CAMERA_REQUEST_CODE) {
                    if (FileUtils.isImageFile(mCameraFile)) {
                        FileUtils.scanImage(this, mCameraFile);
                        ImageFileBean bean = new ImageFileBean();
                        bean.imageFile = mCameraFile;
                        bean.isImage = true;
                        if (bean.imageFile != null && FileUtils.isImageFile(bean.imageFile)) {
                            bean.imageSize = FileUtils.imageSize(bean.imageFile.length());
                        }
                        imageFileResult(bean);

                    }
                } else if (requestCode == REQUEST_CODE_CHOOSE) {
                    List<String> pathData = Matisse.obtainPathResult(data);
                    if (pathData == null || pathData.size() == 0)
                        return;
                    List<ImageFileBean> imageFileBeanList = new ArrayList<>();
                    for (String path : pathData) {
                        if (path == null) {
                            return;
                        }
                        File file = new File(path);
                        if (FileUtils.isImageFile(file)) {
                            ImageFileBean imageFileBean = new ImageFileBean();
                            imageFileBean.isImage = true;
                            imageFileBean.imageSize = FileUtils.imageSize(file.length());
                            imageFileBean.imageFile = file;
                            imageFileBeanList.add(imageFileBean);
                        }
                    }

                    imageFilesResult(imageFileBeanList);
                }
                break;
            default:
                break;
        }
    }


    protected void imageFileResult(ImageFileBean bean) {

    }

    protected void imageFilesResult(List<ImageFileBean> data) {
    }

}
