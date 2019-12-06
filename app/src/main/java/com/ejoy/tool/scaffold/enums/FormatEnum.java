package com.ejoy.tool.scaffold.enums;
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

import com.ejoy.tool.R;

/**
 * CN:      FormatEnum
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/5
 * Des:    格式枚举
 */
public enum FormatEnum {
    //文件夹
    FOLDER("folder", R.mipmap.ico_grid_bottomsheet),

    //图片格式
    IMG("img", R.mipmap.ico_grid_bottomsheet, "jpg", "jpeg", "gif", "png", "bmp", "tiff"),

    //文本格式
    TXT("txt", R.mipmap.ico_grid_bottomsheet, "txt"),

    //文档格式
    WORD("word", R.mipmap.ico_grid_bottomsheet, "docx", "dotx", "doc", "dot", "pagers"),

    //电子表格
    EXCEL("excel", R.mipmap.ico_grid_bottomsheet, "xls", "xlsx", "xlt", "xltx"),

    //ppt
    PPT("ppt", R.mipmap.ico_grid_bottomsheet, "ppt", "pptx"),

    //pdf
    PDF("pdf", R.mipmap.ico_grid_bottomsheet, "pdf"),

    //音频格式
    MP3("mp3", R.mipmap.ico_grid_bottomsheet, "mp3", "wav", "wma"),

    //视频格式
    VIDEO("video", R.mipmap.ico_grid_bottomsheet, "avi", "flv", "mpg", "mpeg", "mp4", "3gp", "mov", "rmvb", "mkv"),

    //网页格式
    HTML("html", R.mipmap.ico_grid_bottomsheet, "html"),

    //cad
    CAD("cad", R.mipmap.ico_grid_bottomsheet, "dwg", "dxf", "dwt"),

    //ps
    PS("ps", R.mipmap.ico_grid_bottomsheet, "psd", "pdd"),

    //max
    MAX3D("3DMax", R.mipmap.ico_grid_bottomsheet, "max"),

    //压缩包
    ZIP("zip", R.mipmap.ico_grid_bottomsheet, "zip", "jar", "rar", "7z"),

    //未知格式
    UNKNOWN("unknown", R.mipmap.ico_grid_bottomsheet);


//    //文件夹
//    FOLDER("folder", R.drawable.normal_dir),
//
//    //图片格式
//    IMG("img", R.mipmap.file_icon_cad, "jpg", "jpeg", "gif", "png", "bmp", "tiff"),
//
//    //文本格式
//    TXT("txt", R.mipmap.file_icon_txt, "txt"),
//
//    //文档格式
//    WORD("word", R.mipmap.file_icon_word, "docx", "dotx", "doc", "dot", "pagers"),
//
//    //电子表格
//    EXCEL("excel", R.mipmap.file_icon_excel, "xls", "xlsx", "xlt", "xltx"),
//
//    //ppt
//    PPT("ppt", R.mipmap.file_icon_ppt, "ppt", "pptx"),
//
//    //pdf
//    PDF("pdf", R.mipmap.file_icon_pdf, "pdf"),
//
//    //音频格式
//    MP3("mp3", R.mipmap.file_icon_mp3, "mp3", "wav", "wma"),
//
//    //视频格式
//    VIDEO("video", R.mipmap.file_icon_video, "avi", "flv", "mpg", "mpeg", "mp4", "3gp", "mov", "rmvb", "mkv"),
//
//    //网页格式
//    HTML("html", R.mipmap.h5, "html"),
//
//    //cad
//    CAD("cad", R.mipmap.file_icon_cad, "dwg", "dxf", "dwt"),
//
//    //ps
//    PS("ps", R.mipmap.file_icon_psd, "psd", "pdd"),
//
//    //max
//    MAX3D("3DMax", R.mipmap.file_icon_max, "max"),
//
//    //压缩包
//    ZIP("zip", R.mipmap.file_icon_zip, "zip", "jar", "rar", "7z"),
//
//    //未知格式
//    UNKNOWN("unknown", R.mipmap.file_icon_unknown);

    private static final String TAG = "FormatEnum";
    public String TYPE;
    public int ICON;
    public String[] FORMATS;

    /**
     * @param type    文件类型
     * @param icon    对应icon
     * @param formats 包含格式
     */
    FormatEnum(String type, int icon, String... formats) {
        this.TYPE = type;
        this.ICON = icon;
        this.FORMATS = formats;
    }

    /**
     * 通过文件类型获取对应枚举
     *
     * @param extension 文件扩展名
     * @return 文件对应的枚举信息，如果没有，返回未知
     */
    public static FormatEnum getFormat(String extension) {
        for (FormatEnum format : FormatEnum.values()) {
            for (String extend : format.FORMATS) {
                if (extend.equalsIgnoreCase(extension)) {
                    return format;
                }
            }
        }
        return UNKNOWN;
    }
}

