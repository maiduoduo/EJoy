package com.ejoy.tool.ui.data.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.module.iviews.popup.weibo.ImageInfoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;


/**
 * CN:      IGirdViewAdapter
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/2
 * Des:    TODO:相册九宫格适配器
 */
public class IGirdViewAdapter extends BaseAdapter {

    private static DisplayImageOptions mImageItemOptions = new DisplayImageOptions.Builder()
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .considerExifParams(true)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();

    private List<ImageInfoBean> mDatas = new ArrayList<ImageInfoBean>();
    private ArrayList<ImageInfoBean> mSelectImgList = new ArrayList<ImageInfoBean>();
    private OnImgSelectListener mOnImgSelectListener;

    private Context mContext;
    private LayoutInflater layoutInflater;

    public IGirdViewAdapter(Context context, List<ImageInfoBean> data, ArrayList<ImageInfoBean> selectImgList) {
        this.mContext = context;
        this.mDatas = data;
        this.mSelectImgList = selectImgList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public ImageInfoBean getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_pic_grid_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((ImageInfoBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final ImageInfoBean imageInfo, final ViewHolder holder, final int position) {
        if (imageInfo.isSelected()) {
            holder.select_img.setImageResource(R.drawable.compose_photo_preview_right);
            holder.itemImg.setColorFilter(Color.parseColor("#77000000"));
        } else {
            holder.select_img.setImageResource(R.drawable.compose_guide_check_box_default);
            holder.itemImg.setColorFilter(null);
        }

        holder.select_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //图片被选中
                if (imageInfo.isSelected()) {
                    holder.select_img.setImageResource(R.drawable.compose_guide_check_box_default);
                    holder.itemImg.setColorFilter(null);
                    imageInfo.setIsSelected(false);
                    deleteSelectImg(mSelectImgList, imageInfo);
                    mOnImgSelectListener.OnDisSelect(mSelectImgList);
                }
                //图片没有被选中
                else {
                    if (mSelectImgList.size() >= 9) {
                        Toast.makeText(mContext,"最多选择9张图片",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    holder.select_img.setImageResource(R.drawable.compose_photo_preview_right);
                    holder.itemImg.setColorFilter(Color.parseColor("#77000000"));
                    imageInfo.setIsSelected(true);
                    addToSelectImgList(mSelectImgList, imageInfo);
                    mOnImgSelectListener.OnSelect(mSelectImgList);
                }

            }
        });
        ImageLoader.getInstance().displayImage("file:///" + imageInfo.getImageFile().getAbsolutePath(), holder.itemImg, mImageItemOptions);
    }

    protected class ViewHolder {
        private ImageView itemImg;
        private ImageView select_img;

        public ViewHolder(View view) {
            itemImg = (ImageView) view.findViewById(R.id.item_img);
            select_img = (ImageView) view.findViewById(R.id.select_img);
        }
    }

    /**
     * 监听图片勾选的事件
     */
    public interface OnImgSelectListener {
        public void OnSelect(ArrayList<ImageInfoBean> imageInfos);

        public void OnDisSelect(ArrayList<ImageInfoBean> imageInfos);
    }

    public void setOnImgSelectListener(OnImgSelectListener onImgSelectListener) {
        this.mOnImgSelectListener = onImgSelectListener;
    }

    /**
     * 第二次打开相册，搜索过后，即使同一张图片，对应的引用的地址和上一次已经发生变化，不可以直接equal来比较，要根据绝对路径名来比较才行
     *
     * @param selectImgList
     * @param imageInfo
     */
    public void deleteSelectImg(ArrayList<ImageInfoBean> selectImgList, ImageInfoBean imageInfo) {

        for (int i = 0; i < selectImgList.size(); i++) {
            if (selectImgList.get(i).getImageFile().getAbsolutePath().equals(imageInfo.getImageFile().getAbsolutePath())) {
                selectImgList.remove(i);
            }
        }
    }

    /**
     * 第二次打开相册，搜索过后，即使同一张图片，对应的引用的地址和上一次已经发生变化，不可以直接equal来比较，要根据绝对路径来比较才行
     *
     * @param selectImgList
     * @param imageInfo
     */
    public void addToSelectImgList(ArrayList<ImageInfoBean> selectImgList, ImageInfoBean imageInfo) {
        for (int i = 0; i < selectImgList.size(); i++) {
            if (selectImgList.get(i).getImageFile().getAbsolutePath().equals(imageInfo.getImageFile().getAbsolutePath())) {
                //如果selectlist中已经存在此图片，就不重复进行添加了
                return;
            }
        }
        selectImgList.add(imageInfo);

    }

}
