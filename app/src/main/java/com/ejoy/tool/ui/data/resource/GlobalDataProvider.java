package com.ejoy.tool.ui.data.resource;


import android.content.Intent;
import android.net.Uri;


import com.ejoy.tool.R;
import com.ejoy.tool.app.App;
import com.ejoy.tool.app.AppConstant;
import com.ejoy.tool.common.api.HostType;
import com.ejoy.tool.common.bean.MainItemBean_temp;
import com.ejoy.tool.common.bean.PICData;
import com.ejoy.tool.ui.activity.IArcLayoutActivity;
import com.ejoy.tool.ui.activity.IScrollViewActivity;
import com.ejoy.tool.ui.activity.ToastActivity;
import com.ejoy.tool.ui.activity.bezer.BezierActivity;
import com.ejoy.tool.ui.activity.bottomsheet.IBottomSheetActivity;
import com.ejoy.tool.ui.activity.device.DeviceToolActviity;
import com.ejoy.tool.ui.activity.iosdialog.IDialogActivity;
import com.ejoy.tool.ui.activity.loading.ILoadingActivity;
import com.ejoy.tool.ui.activity.picker.ITimeDateOrActivity;
import com.ejoy.tool.ui.activity.popupwindow.IPopupwindowActivity;
import com.ejoy.tool.ui.activity.refresh.IRefreshActivity;
import com.module.ires.bean.CustomBottomSheetItemBean;
import com.module.iviews.popup.AdapterItem;
import com.module.iviews.popup.ExpandableItem;
import com.module.iviews.popup.bean.GalleryBean;
import com.module.iviews.popup.menu.bean.FiltrateBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @package: GlobalDataProvider
 * @author： JSYL-DCL
 * @describe： 全局配置及演示数据
 */
public class GlobalDataProvider {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_CONTENT = 1;

    public static String[] titles = new String[]{
            "伪装者:胡歌演绎'痞子特工'",
            "无心法师:生死离别!月牙遭虐杀",
            "花千骨:尊上沦为花千骨",
            "综艺饭:胖轩偷看夏天洗澡掀波澜",
            "碟中谍4:阿汤哥高塔命悬一线,超越不可能",
    };

    public static String[] urls = new String[]{//640*360 360/640=0.5625
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160323071011277.jpg",//伪装者:胡歌演绎"痞子特工"
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144158380433341332.jpg",//无心法师:生死离别!月牙遭虐杀
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160286644953923.jpg",//花千骨:尊上沦为花千骨
            "http://photocdn.sohu.com/tvmobilemvms/20150902/144115156939164801.jpg",//综艺饭:胖轩偷看夏天洗澡掀波澜
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144159406950245847.jpg",//碟中谍4:阿汤哥高塔命悬一线,超越不可能
    };

    public static String[] dpiItems = new String[]{
            "480 × 800",
            "1080 × 1920",
            "720 × 1280",
    };

    public static String[] menuItems = new String[]{
            "（双）列表可滑动",
            "筛选",
            "设置"
    };

    public static AdapterItem[] expandableChildItems = new AdapterItem[]{
            new AdapterItem(App.getAppContext(), "登陆", R.mipmap.ico_dialog_d),
            new AdapterItem(App.getAppContext(), "筛选", R.mipmap.ico_dialog_e),
            new AdapterItem(App.getAppContext(), "设置", R.mipmap.ico_dialog_f),
    };

    public static ExpandableItem[] expandableItems = new ExpandableItem[]{
            ExpandableItem.of(new AdapterItem(App.getAppContext(), "屏幕尺寸", R.mipmap.ico_dialog_a)).addChild(AdapterItem.arrayof(dpiItems)),
            ExpandableItem.of(new AdapterItem(App.getAppContext(), "设备亮度", R.mipmap.ico_dialog_b)).addChild(expandableChildItems),
            ExpandableItem.of(new AdapterItem(App.getAppContext(), "屏幕分辨率", R.mipmap.ico_dialog_c)).addChild(AdapterItem.arrayof(dpiItems))
    };


    public static List<String> qqPopItems() {
        return Arrays.asList(
                "气泡提示",
                "EasyPopup",
                "EUIPopup",
                "帮助与反馈"
        );
    }

    /**
     * 微博弹窗实现选择
     */
    public static String[] wbDialogItems = new String[]{
            "PopupWindow实现",
            "PopMenu实现",
            "其他等"
    };

    public static String[] wbPopItems = new String[]{
            "文字",
            "照片/视频",
            "头条文章",
            "签到",
            "点评",
            "更多",
            "直播",
            "好友圈",
            "音乐",
            "秒拍",
            "商品",
            "红包"
    };

    /**
     * ListPopupwindow数据
     *
     * @return
     */
    public static List<AdapterItem> addListPopData() {
        return Arrays.asList(
                new AdapterItem(App.getAppContext(), "屏幕尺寸", R.mipmap.ico_ptitle_a),
                new AdapterItem(App.getAppContext(), "设备亮度", R.mipmap.ico_ptitle_b),
                new AdapterItem(App.getAppContext(), "屏幕分辨率", R.mipmap.ico_ptitle_c),
                new AdapterItem(App.getAppContext(), "运输事件", R.mipmap.ico_ptitle_d),
                new AdapterItem(App.getAppContext(), "供销质量", R.mipmap.ico_ptitle_e),
                new AdapterItem(App.getAppContext(), "裁定单价", R.mipmap.ico_ptitle_f),
                new AdapterItem(App.getAppContext(), "限定价格", R.mipmap.ico_ptitle_g)
        );
    }

    public static List<String> addListPopStringData() {
        return Arrays.asList(
                "屏幕尺寸",
                "设备亮度",
                "屏幕分辨率",
                "运输事件",
                "供销质量",
                "裁定单价",
                "限定价格",
                "品种",
                "种类数量",
                "技术工种"
        );
    }

    private static List<FiltrateBean> dictList;

    public static List<FiltrateBean> getMultiMenuData() {
        if (dictList != null) dictList.clear();
        else dictList = new ArrayList<>();
        String[] brand = {"屏幕尺寸", "设备亮度", "屏幕分辨率", "运输事件", "供销质量", "裁定单价", "限定价格"};
        String[] type = {"品种", "种类数量", "技术工种", "平方价格", "作业效率", "时效长度"};

        FiltrateBean filtrateBean_child = new FiltrateBean();
        filtrateBean_child.setTypeName("材料分析");
        List<FiltrateBean.Children> childrenList = new ArrayList<>();
        for (String aBrand : brand) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aBrand);
            childrenList.add(cd);
        }
        filtrateBean_child.setChildren(childrenList);

        FiltrateBean filtrateBean_child2 = new FiltrateBean();
        filtrateBean_child2.setTypeName("质量把关");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (String aType : type) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aType);
            childrenList2.add(cd);
        }
        filtrateBean_child2.setChildren(childrenList2);
        dictList.add(filtrateBean_child);
        dictList.add(filtrateBean_child2);

        return dictList;
    }


    /**
     * 相册
     *
     * @return
     */
    public static List<GalleryBean> getAlbumInfo() {
        return Arrays.asList(
                new GalleryBean("最近项目", true, R.mipmap.img_a),
                new GalleryBean("微博时光", false, R.mipmap.img_j),
                new GalleryBean("微博动图", false, R.mipmap.img_k),
                new GalleryBean("Video Joiner", false, R.mipmap.img_l),
                new GalleryBean("动图", false, R.mipmap.img_d),
                new GalleryBean("全景照片", false, R.mipmap.img_e),
                new GalleryBean("今日头条", false, R.mipmap.img_nopic),
                new GalleryBean("慢动作", false, R.mipmap.img_f),
                new GalleryBean("视频", false, R.mipmap.img_nopic),
                new GalleryBean("长曝光", false, R.mipmap.img_nopic),
                new GalleryBean("iQIYI", false, R.mipmap.img_d),
                new GalleryBean("QQ浏览器", false, R.mipmap.img_g),
                new GalleryBean("QQ", false, R.mipmap.img_i),
                new GalleryBean("截屏", false, R.mipmap.img_b),
                new GalleryBean("延时摄影", false, R.mipmap.img_nopic),
                new GalleryBean("抖音", false, R.mipmap.img_g),
                new GalleryBean("个人收藏", false, R.mipmap.img_nopic),
                new GalleryBean("缩略图", false, R.mipmap.img_f),
                new GalleryBean("手机淘宝", false, R.mipmap.img_b)
        );
    }


    public static String[] bottomsheetItems = new String[]{
            "RecyclerView",
            "ScrollView",
            "Recycler View in DialogBtmSheet"
    };

    public static List<String> likeBottomsheetData() {
        return Arrays.asList(
                "From Xml", "Without Icon", "Dark Theme", "Grid", "Style", "Style from Theme", "ShareAction", "FullScreen", "Menu Manipulate", "HeaderLayout"
        );
    }


    public static final String HOT = "热门";
    public static final String XING_GAN = "新鲜";
    public static final String MM = "mm";
    public static final String ZIPAI = "zipai";
    public static final String JIEPAI = "jiepai";
    public static final String BEST = "best";
    public static final int COUNT = 20;
    public static final boolean FIRST_LOAD = true;
    public static final String BUNDLE_PROBLEM_DETAIL_ID = "DETAIL_ID";
    public static final String BUNDLE_PROBLEM_DETAIL_STATE = "DETAIL_STATE";
    public static final String BUNDLE_NOTICE_DETAIL_ID = "NOTICE_ID";
    public static final String BUNDLE_CANCEL_DETAIL_PROJECT = "CANCEL_DETAIL_PROJECT";
    public static final String BUNDLE_CANCEL_DETAIL_ID = "CANCEL_DETAIL_ID";
    public static final String BUNDLE_CANCEL_MAIN_FLAG = "CANCEL_MAIN_FLAG";
    public static final String BUNDLE_CANCEL_MAIN_EVENTID = "CANCEL_MAIN_EVENTID";
    public static final String BUNDLE_CANCEL_MAIN_WHICH_PAGE = "CANCEL_MAIN_WHICH_PAGE";
    public static final String BUNDLE_MANAGE_POSTID = "103";
    public static final String BUNDLE_PLAYCARD_NFC = "PNFCCode";
    public static final String LOGIN_TYPE = "10";
    public static String NFCCodeOfMain = "";
    public static String URL_COMMITCACHEPATROL;
    public static String URL_UpdateVersionURL;
    public static String IMAGE_PATH_KEY = "image_path";
    public static String CLICK_IMAGE_POSITION_KEY = "click_image_position_key";

    public static String[] title = {GlobalDataProvider.HOT, GlobalDataProvider.XING_GAN};

    static {
        URL_COMMITCACHEPATROL = AppConstant.Baseurl + "patrolAction/cacheUploadPatrolRecord";
        //版本信息
        URL_UpdateVersionURL = AppConstant.Baseurl + "conmmonData/version";
    }

    //======================省市区============================================
    public static final String DB_NAME_V2 = "china_cities_v2.db";
    public static final String DB_NAME_V3 = "china_cities_v3.db";
    public static final String DB_NAME_ZONE = "zone.db";
    public static final String DB_NAME = "cities";

    public static final String TABLE_NAME = "cities";
    public static final String COLUMN_C_NAME = "c_name";
    public static final String COLUMN_C_PROVINCE = "c_province";
    public static final String COLUMN_C_PINYIN = "c_pinyin";
    public static final String COLUMN_C_CODE = "c_code";
    public static final String COLUMN_C_TIP = "c_tip";
    public static final String LATEST_DB_NAME = "china_cities_v2.db";
    //========================================================================


    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String configHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.TYPE_HOST_STANDARD://XB
                host = AppConstant.GRID_LOCAL1;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }


    public static class PROBLEM_LIST {
        //USERID
        public static final String PROBLEM_LIST_ID = "";
    }


    /**
     * channel代码
     *
     * @param channel 名称
     * @return iddaima
     */
    public static String getIdParam(String channel) {
        switch (channel) {
            case "problem":
                return "SYLB10,SYDT10,SYRECOMMEND";
            case "":
                return "";
            default:
                break;
        }
        return "SYLB10,SYDT10,SYRECOMMEND";
    }


    public static PICData getGif() {
        List<PICData> dataList = new ArrayList<>();

        PICData data1 = new PICData();
        data1.avatar = "http://b162.photo.store.qq.com/psb?/V14EhGon4cZvmh/z2WukT5EhNE76WtOcbqPIgwM2Wxz4Tb7Nub.rDpsDgo!/b/dOaanmAaKQAA";
        data1.nickname = "萌新-lpe";
        data1.createTime = "昨天 11:21";
        data1.content = "开司还是那么帅";
        data1.pictureList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168009921&di=fe2e9e5bc508130558a9954c2a8bd28f&imgtype=0&src=http%3A%2F%2Fxuexi.leawo.cn%2Fuploads%2Fallimg%2F160926%2F134225K60-img_b.gif"),
                Uri.parse("https://b-ssl.duitang.com/uploads/item/201206/29/20120629140234_QWAsX.thumb.700_0.gif"),
                Uri.parse("http://pic2.52pk.com/files/allimg/150324/104923F49-12.jpg"),
                Uri.parse("https://f12.baidu.com/it/u=3294379970,949120920&fm=72"),
                Uri.parse("https://f12.baidu.com/it/u=3294379970,949120920&fm=72")
//                Uri.parse("http://imgsrc.baidu.com/forum/w=580/sign=852e30338435e5dd902ca5d746c7a7f5/bd3eb13533fa828b6cf24d82fc1f4134960a5afa.jpg"),
//                Uri.parse("http://pic.3h3.com/up/2014-3/20143314140858312456.gif"),
//                Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542168118542&di=437ba348dfe4bd91afa5e5761f318cee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201410%2F17%2F20141017094107_VdNJu.gif"),
//                Uri.parse("http://img0.ph.126.net/deotqlphOfncBJA8SnbWUQ==/2008605433907336946.gif"),
//                Uri.parse("https://f12.baidu.com/it/u=3294379970,949120920&fm=72")
        );
        data1.pictureThumbList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168009921&di=fe2e9e5bc508130558a9954c2a8bd28f&imgtype=0&src=http%3A%2F%2Fxuexi.leawo.cn%2Fuploads%2Fallimg%2F160926%2F134225K60-img_b.gif"),
                Uri.parse("https://b-ssl.duitang.com/uploads/item/201206/29/20120629140234_QWAsX.thumb.700_0.gif"),
                Uri.parse("http://pic2.52pk.com/files/allimg/150324/104923F49-12.jpg"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168279697&di=dcd2b62878ad6c2c92e5bd7facfe6c3c&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20151126%2Fmp44425938_1448498418499_2.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168279697&di=dcd2b62878ad6c2c92e5bd7facfe6c3c&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20151126%2Fmp44425938_1448498418499_2.gif")
//                Uri.parse("http://upfile.asqql.com/2009pasdfasdfic2009s305985-ts/2014-9/201491520352362114.gif"),
//                Uri.parse("http://imgsrc.baidu.com/forum/w=580/sign=852e30338435e5dd902ca5d746c7a7f5/bd3eb13533fa828b6cf24d82fc1f4134960a5afa.jpg"),
//                Uri.parse("http://pic.3h3.com/up/2014-3/20143314140858312456.gif"),
//                Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542168118542&di=437ba348dfe4bd91afa5e5761f318cee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201410%2F17%2F20141017094107_VdNJu.gif"),
//                Uri.parse("http://img0.ph.126.net/deotqlphOfncBJA8SnbWUQ==/2008605433907336946.gif"),
//                Uri.parse("http://imglf3.ph.126.net/0oF3rJPVSPm2ugnX6xSMXw==/1106478133466928383.gif"),
//                Uri.parse("http://s4.sinaimg.cn/bmiddle/b4d49f27tdfe1a9907393&690"),
//                Uri.parse("http://media.giphy.com/media/4a6cioskxhvuU/giphy-tumblr.gif"),
//                Uri.parse("https://f12.baidu.com/it/u=3294379970,949120920&fm=72")
        );


        PICData data2 = new PICData();
        data2.avatar = "http://b167.photo.store.qq.com/psb?/V14EhGon2OmAUI/hQN450lNoDPF.dO82PVKEdFw0Qj5qyGeyN9fByKgWd0!/m/dJWKmWNZEwAAnull";
        data2.nickname = "苦涩";
        data2.createTime = "昨天 09:59";
        data2.content = "唐僧还是厉害啊。为什么？有宝马";
        data2.pictureList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168240132&di=226605ee54960b3135cbeebf12d1e219&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20150928%2Fmp33561543_1443397655340_1.gif")
        );
        data2.pictureThumbList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168240132&di=226605ee54960b3135cbeebf12d1e219&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20150928%2Fmp33561543_1443397655340_1.gif")
        );

        PICData data3 = new PICData();
        data3.avatar = "http://b167.photo.store.qq.com/psb?/V14EhGon2OmAUI/hQN450lNoDPF.dO82PVKEdFw0Qj5qyGeyN9fByKgWd0!/m/dJWKmWNZEwAAnull";
        data3.nickname = "empty";
        data3.createTime = "昨天 08:12";
        data3.content = "各种眼神";
        data3.pictureList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168792516&di=b3ae4a05909fd4c6b903851553649fb0&imgtype=0&src=http%3A%2F%2Fwww.people.com.cn%2Fmediafile%2Fpic%2F20171013%2F33%2F16097621616938606825.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168265734&di=6dbf0daade2a0126fa6118ec3a185205&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160917%2Fb8b605c1f286482b8e748f37528ccfd5.jpg"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168279697&di=dcd2b62878ad6c2c92e5bd7facfe6c3c&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20151126%2Fmp44425938_1448498418499_2.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168288781&di=ae03ef1c5b30330f0fb8b8f0e955a737&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20161101%2F8f0f344ee011413297830dfd3dbb18f1.jpg")
        );
        data3.pictureThumbList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168792516&di=b3ae4a05909fd4c6b903851553649fb0&imgtype=0&src=http%3A%2F%2Fwww.people.com.cn%2Fmediafile%2Fpic%2F20171013%2F33%2F16097621616938606825.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168265734&di=6dbf0daade2a0126fa6118ec3a185205&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160917%2Fb8b605c1f286482b8e748f37528ccfd5.jpg"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168279697&di=dcd2b62878ad6c2c92e5bd7facfe6c3c&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20151126%2Fmp44425938_1448498418499_2.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168288781&di=ae03ef1c5b30330f0fb8b8f0e955a737&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20161101%2F8f0f344ee011413297830dfd3dbb18f1.jpg")
        );

        PICData data4 = new PICData();
        data4.avatar = "http://b167.photo.store.qq.com/psb?/V14EhGon2OmAUI/hQN450lNoDPF.dO82PVKEdFw0Qj5qyGeyN9fByKgWd0!/m/dJWKmWNZEwAAnull";
        data4.nickname = "empty";
        data4.createTime = "昨天 06:00";
        data4.content = "人与人间的信任，就像是纸片，一旦破损，就不会再回到原来的样子。";
        data4.pictureList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168299317&di=620d6bcb76de1d70e00ca4ed9aca58db&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F20164_12_16%2Fa8e9ix85375805036596.jpg"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168316681&di=840b4d0440543ae3f72c6c81270556b6&imgtype=0&src=http%3A%2F%2Fupfile.asqql.com%2F2009pasdfasdfic2009s305985-ts%2F2015-9%2F20159202352355149.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168810214&di=24078bdf9e747555965ead78f40f38af&imgtype=0&src=http%3A%2F%2Fs1.trueart.com%2F20180110%2F223107721_640.gif")
        );
        data4.pictureThumbList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168299317&di=620d6bcb76de1d70e00ca4ed9aca58db&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F20164_12_16%2Fa8e9ix85375805036596.jpg"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168316681&di=840b4d0440543ae3f72c6c81270556b6&imgtype=0&src=http%3A%2F%2Fupfile.asqql.com%2F2009pasdfasdfic2009s305985-ts%2F2015-9%2F20159202352355149.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168810214&di=24078bdf9e747555965ead78f40f38af&imgtype=0&src=http%3A%2F%2Fs1.trueart.com%2F20180110%2F223107721_640.gif")
        );

        PICData data5 = new PICData();
        data5.avatar = "http://qlogo3.store.qq.com/qzone/383559698/383559698/100?1416542262";
        data5.nickname = "越线龙马";
        data5.createTime = "前天 14:61";
        data5.content = "雪.触之即化..愿永久飘零";
        data5.pictureList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168338032&di=498a6e41472a6f49e3f95aa16a3c2402&imgtype=0&src=http%3A%2F%2Fwww.dahepiao.com%2Fuploads%2Fallimg%2F170630%2F26008-1F6301543125B.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168353920&di=ac499e09eec05c86871d6df539748445&imgtype=0&src=http%3A%2F%2Fimg1.ph.126.net%2FWmNQ-hcRrqIDTYBtms1W3A%3D%3D%2F6619465719002444731.gif")
        );
        data5.pictureThumbList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168338032&di=498a6e41472a6f49e3f95aa16a3c2402&imgtype=0&src=http%3A%2F%2Fwww.dahepiao.com%2Fuploads%2Fallimg%2F170630%2F26008-1F6301543125B.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168353920&di=ac499e09eec05c86871d6df539748445&imgtype=0&src=http%3A%2F%2Fimg1.ph.126.net%2FWmNQ-hcRrqIDTYBtms1W3A%3D%3D%2F6619465719002444731.gif")
        );

        PICData data6 = new PICData();
        data6.avatar = "http://b162.photo.store.qq.com/psb?/V14EhGon4cZvmh/z2WukT5EhNE76WtOcbqPIgwM2Wxz4Tb7Nub.rDpsDgo!/b/dOaanmAaKQAA";
        data6.nickname = "顺子要不起";
        data6.createTime = "圣诞节";
        data6.content = "颜宇扬的期末总结\nimg_a、有本事冲我来，别再家长会上吓唬我爸\nimg_b、期末考试成绩出来了，我觉得我妈生二胎是非常明智的选择\n3、这场考试对于我的意义就是知道了班上到底有多少人\nimg_d、期末考试不给老师们露一手，他们还真以为自己教的好";
        data6.pictureList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168389150&di=2fd5c826af5394b62777fd132dff7d8f&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201701%2F17%2F20170117112406_zixk5.thumb.700_0.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168397716&di=909dcfb1bf7a7fe37041ec5914c34c4a&imgtype=0&src=http%3A%2F%2Fs7.rr.itc.cn%2Fg%2FwapChange%2F20159_11_19%2Fa4m9779610717481352.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168437965&di=f91b9c858eecf75799af00df525eab9a&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F201510_31_11%2Fa6cjhv9612585370352.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168451881&di=805580bb76614eba5dcc4668253b9749&imgtype=0&src=http%3A%2F%2Fs8.rr.itc.cn%2Fr%2FwapChange%2F201510_31_11%2Fa979a69612629324352.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168465415&di=8c7e9f70a33c4e442427f5e4bd21db1e&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F20162_24_14%2Fa69tdw8577863829596.gif")
        );
        data6.pictureThumbList = Arrays.asList(
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168389150&di=2fd5c826af5394b62777fd132dff7d8f&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201701%2F17%2F20170117112406_zixk5.thumb.700_0.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168397716&di=909dcfb1bf7a7fe37041ec5914c34c4a&imgtype=0&src=http%3A%2F%2Fs7.rr.itc.cn%2Fg%2FwapChange%2F20159_11_19%2Fa4m9779610717481352.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168437965&di=f91b9c858eecf75799af00df525eab9a&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F201510_31_11%2Fa6cjhv9612585370352.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168451881&di=805580bb76614eba5dcc4668253b9749&imgtype=0&src=http%3A%2F%2Fs8.rr.itc.cn%2Fr%2FwapChange%2F201510_31_11%2Fa979a69612629324352.gif"),
                Uri.parse("https://timgsa.baidu.com/timg?smart_image&quality=80&size=b9999_10000&sec=1542168465415&di=8c7e9f70a33c4e442427f5e4bd21db1e&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F20162_24_14%2Fa69tdw8577863829596.gif")
        );

        if (System.currentTimeMillis() % 3 == 0) {
            dataList.add(data1);
            dataList.add(data2);
            dataList.add(data3);
            dataList.add(data4);
            dataList.add(data6);
        } else if (System.currentTimeMillis() % 3 == 1) {
            dataList.add(data5);
            dataList.add(data6);
            dataList.add(data2);
            dataList.add(data4);
            dataList.add(data3);
        } else {
            dataList.add(data1);
            dataList.add(data3);
            dataList.add(data4);
            dataList.add(data5);
            dataList.add(data6);
        }

        return data1;
    }


    public static List<MainItemBean_temp> getMainItemData() {
        return Arrays.asList(
                new MainItemBean_temp(TYPE_TITLE, "弹窗"),
                new MainItemBean_temp(TYPE_CONTENT, Arrays.asList(
                        new MainItemBean_temp.ContentBean("Toast", "吐司，支持图标，文本时长等", 0, ToastActivity.class, ""),
                        new MainItemBean_temp.ContentBean("Dialog", "系统对话框解决方案，仿IOS样式", 1, IDialogActivity.class, ""),
                        new MainItemBean_temp.ContentBean("Popupwindow", "Popupwindow、ECookieBar、SnackBar等", 2, IPopupwindowActivity.class, ""),
                        new MainItemBean_temp.ContentBean("BottomSheetDialog", "自定义BottomSheetDialog及官方示例", 3, IBottomSheetActivity.class, ""),
                        new MainItemBean_temp.ContentBean("日期等选择器", "日期选择器，省市区级联选择", 4, ITimeDateOrActivity.class, "")
                )),
                new MainItemBean_temp(TYPE_TITLE, "长文本"),
                new MainItemBean_temp(TYPE_CONTENT, Arrays.asList(
                        new MainItemBean_temp.ContentBean("ScrollView", "ScrollView显示内容量大计算合理高度显示内容", 5, IScrollViewActivity.class, "")
                )),
                new MainItemBean_temp(TYPE_TITLE, "菜单"),
                new MainItemBean_temp(TYPE_CONTENT, Arrays.asList(
                        new MainItemBean_temp.ContentBean("详情页", "商城类详情页示例", 6, null, ""),
                        new MainItemBean_temp.ContentBean("FloatDragButton", "可展开可拖拽的菜单", 7, BezierActivity.class, "")
                )),
                new MainItemBean_temp(TYPE_TITLE, "Banner"),
                new MainItemBean_temp(TYPE_CONTENT, Arrays.asList(
                        new MainItemBean_temp.ContentBean("ArcLayout", "广告条弧度，广告轮播条", 8, IArcLayoutActivity.class, "")
                )),
                new MainItemBean_temp(TYPE_TITLE, "设备"),
                new MainItemBean_temp(TYPE_CONTENT, Arrays.asList(
                        new MainItemBean_temp.ContentBean("设备信息", "设备工具，设备信息等", 9, DeviceToolActviity.class, "")
                )),
                new MainItemBean_temp(TYPE_TITLE, "图片处理"),
                new MainItemBean_temp(TYPE_CONTENT, Arrays.asList(
                        new MainItemBean_temp.ContentBean("图片处理", "图片选择，单张压缩/批量压缩及信息", 10, null, "BITMAP")
                )),
                new MainItemBean_temp(TYPE_TITLE, "刷新"),
                new MainItemBean_temp(TYPE_CONTENT, Arrays.asList(
                        new MainItemBean_temp.ContentBean("下拉刷新", "界面数据下拉刷新样式示例", 11, IRefreshActivity.class, "")
                )),
                new MainItemBean_temp(TYPE_TITLE, "标题栏"),
                new MainItemBean_temp(TYPE_CONTENT, Arrays.asList(
                        new MainItemBean_temp.ContentBean("通用标题栏", "通用封装的标题栏", 12, null, "")
                )),
                new MainItemBean_temp(TYPE_TITLE, "文本"),
                new MainItemBean_temp(TYPE_CONTENT, Arrays.asList(
                        new MainItemBean_temp.ContentBean("通用TextView", "通用的文本，支持通用需求", 13, null, "")
                )),
                new MainItemBean_temp(TYPE_TITLE, "应用更新"),
                new MainItemBean_temp(TYPE_CONTENT, Arrays.asList(
                        new MainItemBean_temp.ContentBean("版本更新", "应用检查更新下载并安装封装", 14, null, "")
                ))
        );


    }


    /**
     * Bottomsheet自定义（仿网易云弹窗）
     *
     * @return
     */
    private static List<CustomBottomSheetItemBean> bsList;

    public static List<CustomBottomSheetItemBean> getBsData() {
        if (bsList != null) bsList.clear();
        else bsList = new ArrayList<>();
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_zan, "鼓励一下", "好的专辑离不开每一个爱好者去孜孜的追求"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_play, "下一首播放", "直接切换到下一曲播放，音乐就是这么自然"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_collect, "收藏到歌单", "好听就先收藏，改日再听"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_downloadit, "下载(vip)", "下载下来，他就是你的生活伴侣，注意VIP才可以哦"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_comment, "评论(67045)", "觉得好给个讯息吧"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_share, "分享", "分享给你的盆友，一起嗨皮吧"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_songger, "歌手：柏松", "喜欢歌曲，看看作者吧"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_album, "专辑：听闻余生", "歌曲收录在哪张专辑中，信息可以查看"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_ling, "设置铃声或彩铃", "设置你的手机铃声吧，无时无地都可以听到1"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_buyalbum, "购买单曲", "购买这张单曲，就可以一直拥有它"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_video_watch, "查看视频", "看看视频，MV对歌曲的诠释"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_rqbyy, "人气榜应援", "为你喜欢的歌曲应援吧"));
        bsList.add(new CustomBottomSheetItemBean(R.mipmap.ico_unintrest, "不感兴趣", "好遗憾，没能让你满意。"));
        return bsList;
    }


    public static List<CustomBottomSheetItemBean> getBsRightData() {
        return Arrays.asList(
                new CustomBottomSheetItemBean(R.mipmap.ico_zan, "", "好的专辑离不开每一个爱好者去孜孜的追求"),
                new CustomBottomSheetItemBean(R.mipmap.ico_play, "下一首播放", "直接切换到下一曲播放，音乐就是这么自然"),
                new CustomBottomSheetItemBean(R.mipmap.ico_collect, "收藏到歌单", "好听就先收藏，改日再听"),
                new CustomBottomSheetItemBean(R.mipmap.ico_downloadit, "下载(vip)", "下载下来，他就是你的生活伴侣，注意VIP才可以哦"),
                new CustomBottomSheetItemBean(R.mipmap.ico_comment, "评论(67045)", "觉得好给个讯息吧"),
                new CustomBottomSheetItemBean(R.mipmap.ico_share, "分享", "分享给你的盆友，一起嗨皮吧"),
                new CustomBottomSheetItemBean(R.mipmap.ico_songger, "歌手：柏松", "喜欢歌曲，看看作者吧"),
                new CustomBottomSheetItemBean(R.mipmap.ico_album, "专辑：听闻余生", "歌曲收录在哪张专辑中，信息可以查看"),
                new CustomBottomSheetItemBean(R.mipmap.ico_ling, "设置铃声或彩铃", "设置你的手机铃声吧，无时无地都可以听到1"),
                new CustomBottomSheetItemBean(R.mipmap.ico_buyalbum, "购买单曲", "购买这张单曲，就可以一直拥有它"),
                new CustomBottomSheetItemBean(R.mipmap.ico_video_watch, "查看视频", "看看视频，MV对歌曲的诠释"),
                new CustomBottomSheetItemBean(R.mipmap.ico_rqbyy, "人气榜应援", "为你喜欢的歌曲应援吧"),
                new CustomBottomSheetItemBean(R.mipmap.ico_unintrest, "不感兴趣", "好遗憾，没能让你满意。")
        );
    }


    /*private static List<ImageViewInfo> getVideos() {
        List<ImageViewInfo> videos = new ArrayList<>();
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
                "http://pic.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
                "http://pic.vjshi.com/2017-05-25/b146e104069c2bd0590bb919269193c4/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-05-07/d0bbfc4ac4dd173cc93873ed4eb0be53.mp4",
                "http://pic.vjshi.com/2017-05-07/d0bbfc4ac4dd173cc93873ed4eb0be53/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));

        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-07-18/80d08ce1a84adfbaed5c7067b73d19ed.mp4",
                "http://pic.vjshi.com/2017-07-18/80d08ce1a84adfbaed5c7067b73d19ed/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://img0.imgtn.bdimg.com/it/u=556618733,1205300389&fm=21&gp=0.jpg"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
                "http://pic.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://img0.imgtn.bdimg.com/it/u=556618733,1205300389&fm=21&gp=0.jpg"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2018-06-07/cf673556cce54ab9cf4633fd7d9d0d46.mp4",
                "http://pic.vjshi.com/2018-06-06/caa296729c8e6e41e6aff2aadf4feff3/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://img44.photophoto.cn/20170730/0018090594006661_s.jpg"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
                "http://pic.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2018-01-27/5169bb7bdd7386ce7bd4ce1739229424.mp4",
                "http://pic.vjshi.com/2018-01-27/5169bb7bdd7386ce7bd4ce1739229424/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-27/9a6e69f7c257ff7b7832e8bac6fddf82.mp4",
                "http://pic.vjshi.com/2017-09-27/9a6e69f7c257ff7b7832e8bac6fddf82/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png"));
        return videos;
    }*/

    private static List<String> getUrls() {
        List<String> urls = new ArrayList<>();
        urls.add("http://img4.duitang.com/uploads/item/201307/02/20130702113059_UEGL2.jpeg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=985035006,79865976&fm=21&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=1774291582,2563335167&fm=21&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=1511364704,3337189105&fm=21&gp=0.jpg");
        urls.add("http://pic.qiantucdn.com/58pic/11/90/83/96a58PICrRx.jpg");
        urls.add("http://pic.qiantucdn.com/58pic/13/09/97/26W58PICKNk_1024.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3272030875,860665188&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=2237658959,3726297486&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3016675040,1510439865&fm=21&gp=0.jpg");
        urls.add("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png");

        urls.add("http://img0.imgtn.bdimg.com/it/u=556618733,1205300389&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3272030875,860665188&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=2237658959,3726297486&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3016675040,1510439865&fm=21&gp=0.jpg");
        urls.add("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png");
        urls.add("http://d040779c2cd49.scdn.itc.cn/s_big/pic/20161213/184474627873966848.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/07915a0154ac4a64.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/9ec4bc44bfaf07ed.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/fa85037f97e8191f.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/de13315600ba1cff.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/9ec4bc44bfaf07ed.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/fa85037f97e8191f.jpg");
        urls.add("ttp://ac-QYgvX1CC.clouddn.com/de13315600ba1cff.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/ad99de83e1e3f7d4.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/15c5c50e941ba6b0.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/eaf1c9d55c5f9afd.jpg");
        urls.add("http://pic44.photophoto.cn/20170802/0017030376585114_b.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0847085702814554_s.jpg");
        urls.add("http://img44.photophoto.cn/20170802/0017030319134956_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0838084023987260_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0838084009134421_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0838084002855326_s.jpg");

        urls.add("http://img44.photophoto.cn/20170731/0847085207211178_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0017030319740534_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0838084002855326_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085969586424_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0014105802293676_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0847085242661101_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0886088744582079_s.jpg");
        urls.add("http://img44.photophoto.cn/20170801/0017029514287804_s.jpg");
        urls.add("http://img44.photophoto.cn/20170730/0018090594006661_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085848134910_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085581124963_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg");

        urls.add("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085200668628_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085246003750_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085012707934_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0005018303330857_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085231427344_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085236829578_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085729490157_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0847085751995287_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085729043617_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085786392651_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085761440022_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0847085275244570_s.jpg");


        urls.add("http://img44.photophoto.cn/20170722/0847085858434984_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085781987193_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085707961800_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085716198074_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085769259426_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085717385169_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085789079771_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085265005650_s.jpg");
        urls.add("http://img44.photophoto.cn/20170730/0008118269110532_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0008118203762697_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0008118269666722_s.jpg");

        urls.add("http://img44.photophoto.cn/20170722/0847085858434984_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085781987193_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085707961800_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085716198074_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085769259426_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085717385169_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085789079771_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085265005650_s.jpg");
        urls.add("http://img44.photophoto.cn/20170730/0008118269110532_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0008118203762697_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0008118269666722_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085858434984_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085781987193_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085707961800_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085716198074_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085769259426_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085717385169_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085789079771_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085265005650_s.jpg");
        urls.add("http://img44.photophoto.cn/20170730/0008118269110532_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0008118203762697_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0008118269666722_s.jpg");

        urls.add("http://img44.photophoto.cn/20170731/0847085207211178_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0017030319740534_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0838084002855326_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085969586424_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0014105802293676_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0847085242661101_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0886088744582079_s.jpg");
        urls.add("http://img44.photophoto.cn/20170801/0017029514287804_s.jpg");
        urls.add("http://img44.photophoto.cn/20170730/0018090594006661_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085848134910_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085581124963_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg");

        return urls;
    }


    /*public static List<ImageViewInfo> getGifUrls() {
        List<ImageViewInfo> userViewInfos = new ArrayList<>();
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/8Q8Vy8jh6wEYCT4bYiEAOZdmzIf7GrLQ.gif_s400x0"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/yCPIVl3icfbIhZ1rjKKU6Kl6lCKkC8Wq.gif_s400x0"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/mQK3vlOYVOIpnhNYKg6XuWqpc3yAg9hR.gif_s400x0"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/mESQBeZn5V8Xzke0XPsnEEXUF9MaU3sA.gif_s400x0"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/HFuVvydFj7dgIEcbEBMA9ccGcGOFdEsx.gif_s400x0"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/SH0FB6FnTNgoCsVtxcAMtSNfV7XxXmo8.gif"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/KkB9WARG3PFrz9EEX4DJdiy6Vyg95fGl.gif"));
        return userViewInfos;
    }*/

    /**
     * 拆分集合
     *
     * @param <T>
     * @param resList 要拆分的集合
     * @param count   每个集合的元素个数
     * @return 返回拆分后的各个集合
     */
    public static <T> List<List<T>> split(List<T> resList, int count) {
        if (resList == null || count < 1) {
            return null;
        }
        List<List<T>> ret = new ArrayList<>();
        int size = resList.size();
        if (size <= count) { //数据量不足count指定的大小
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            //前面pre个集合，每个大小都是count个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<>();
                for (int j = 0; j < count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            //last的进行处理
            if (last > 0) {
                List<T> itemList = new ArrayList<>();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }


}
