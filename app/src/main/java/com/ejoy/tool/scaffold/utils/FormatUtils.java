package com.ejoy.tool.scaffold.utils;
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

import android.text.TextUtils;
import android.widget.ImageView;

import com.ejoy.tool.scaffold.enums.FormatEnum;

import java.io.File;

/**
 * CN:      FormatUtils
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/5
 * Des:    格式工具类，通过文件名获取文件格式
 */
public class FormatUtils {
    /**
     * 获取文件格式名
     */
    public static String getFormatName(String fileName) {
        //去掉首尾的空格
        fileName = fileName.trim();
        String s[] = fileName.split("\\.");
        if (s.length > 2) {
            return s[s.length - 1];
        }
        return "";
    }

    /**
     * 获取文件对应icon
     */
    public static int getFileIcon(String fileName) {
        File file = new File(fileName);
        if (file.isDirectory()) {
            return FormatEnum.FOLDER.ICON;
        }
        //获取扩展名并且全部转小写
        String extension = getFormatName(fileName).toLowerCase();
        if (TextUtils.isEmpty(extension)) {
            return FormatEnum.UNKNOWN.ICON;
        }
        FormatEnum format = FormatEnum.getFormat(extension);
        return format.ICON;
    }

    /**
     * 获取文件对应类型
     */
    public static String getFileType(String fileName) {
        //获取扩展名并且全部转小写
        String extension = getFormatName(fileName).toLowerCase();
        if (TextUtils.isEmpty(extension)) {
            return FormatEnum.FOLDER.TYPE;
        }
        FormatEnum format = FormatEnum.getFormat(extension);
        return format.TYPE;
    }

    /**
     * 通过文件获取icon
     */
    public static int getFileIcon(File file) {
        return getFileIcon(file.getName());
    }

    /**
     * 通过文件获取格式名
     */
    public static String getForamtName(File file) {
        return getFormatName(file.getName());
    }

    /**
     * 直接设置icon
     *
     * @param iv       需要设置icon的View
     * @param fileName 文件名
     */
    public static void initIcon(ImageView iv, String fileName) {
        //如果是图片类型的文件，还需要直接展示图片
        if (getFileType(fileName).equalsIgnoreCase(FormatEnum.IMG.TYPE)) {
            iv.setImageResource(FormatEnum.IMG.ICON);
//            ImageLoader.load(iv.getContext(), fileName, iv);
        } else {
            iv.setImageResource(getFileIcon(fileName));
        }
    }

}

