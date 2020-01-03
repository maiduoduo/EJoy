package com.module.iviews.popup.weibo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: dcl
 * Method/Class Name:ImageInfoBean
 * Date: 2016-12-06
 * About:图片信息
 * param:
 * FIXME
 */
public class ImageInfoBean implements Parcelable {

    private static final long serialVersionUID = -3753345306395582567L;
    /**
     * 图片文件
     */
    private File imageFile;
    /**
     * 是否被选中
     */
    private boolean isSelected = false;

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageInfoBean imageInfo = (ImageInfoBean) o;

        if (isSelected() != imageInfo.isSelected()) return false;
        return getImageFile().equals(imageInfo.getImageFile());

    }

    @Override
    public int hashCode() {
        int result = getImageFile().hashCode();
        result = 31 * result + (isSelected() ? 1 : 0);
        return result;
    }

    /**
     * @param imageFileList
     * @return
     */
    public static List<ImageInfoBean> buildFromFileList(List<File> imageFileList) {
        if (imageFileList != null) {
            List<ImageInfoBean> imageInfoArrayList = new ArrayList<>();
            for (File imageFile : imageFileList) {
                ImageInfoBean imageInfo = new ImageInfoBean();
                imageInfo.imageFile = imageFile;
                imageInfo.isSelected = false;
                imageInfoArrayList.add(imageInfo);
            }
            return imageInfoArrayList;
        } else {
            return null;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.imageFile);
        dest.writeByte(isSelected ? (byte) 1 : (byte) 0);
    }

    public ImageInfoBean() {
    }

    protected ImageInfoBean(Parcel in) {
        this.imageFile = (File) in.readSerializable();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<ImageInfoBean> CREATOR = new Creator<ImageInfoBean>() {
        @Override
        public ImageInfoBean createFromParcel(Parcel source) {
            return new ImageInfoBean(source);
        }

        @Override
        public ImageInfoBean[] newArray(int size) {
            return new ImageInfoBean[size];
        }
    };
}

