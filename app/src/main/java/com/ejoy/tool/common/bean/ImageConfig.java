package com.ejoy.tool.common.bean;

import android.graphics.Bitmap;

import com.ejoy.tool.scaffold.utils.FileUtils;
import com.ejoy.tool.scaffold.utils.task.CompressPicker;

import java.util.UUID;


/**
 * 类  名 :  ImageConfig
 * 描  述 :  ${TODO} 图片处理bean类
 */
public class ImageConfig {
    /**
     * 压缩默认宽为720px
     */
    public int compressWidth = 720;
    /**
     * 压缩默认长为1280px
     */
    public int compressHeight = 1280;
    public String imagePath;


    private ImageConfig(String imagePath) {
        this.imagePath = imagePath;
    }

    public ImageConfig() {
    }

    /**
     * 图片格式
     */
    public Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;

    /**
     * 图片质量
     */
    public Bitmap.Config config = Bitmap.Config.ARGB_8888;


    /**
     * 默认缓存的目录
     */
    public String cachePathDirectory = FileUtils.FILE_DIRECTOR_NAME;

    /**
     * 默认的缓存图片名字
     */
    public String imageName = "/ejoy_" + String.valueOf(System.currentTimeMillis()) + UUID.randomUUID().toString().replaceAll("-", "").trim() + ".jpg";

    /**
     * 调用默认压缩的图片配置属性
     */

    public int compressSize = CompressPicker.COMPRESS_SIZE;

        public static ImageConfig getDefaultConfig(String filePath){
            return new ImageConfig(filePath);
        }

}
