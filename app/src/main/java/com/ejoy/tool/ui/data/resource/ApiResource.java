package com.ejoy.tool.ui.data.resource;


import android.net.Uri;


import com.ejoy.tool.R;
import com.ejoy.tool.app.AppConstant;
import com.ejoy.tool.common.api.HostType;
import com.ejoy.tool.common.bean.MainItemBean;
import com.ejoy.tool.common.bean.PICData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @package: ApiResource
 * @author： JSYL-DCL
 * @describe： 全局配置
 * @email： 11442865
 */
public class ApiResource {
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

    public static String[] title = { ApiResource.HOT, ApiResource.XING_GAN};

    static {
        URL_COMMITCACHEPATROL = AppConstant.Baseurl + "patrolAction/cacheUploadPatrolRecord";
        //版本信息
        URL_UpdateVersionURL = AppConstant.Baseurl+"conmmonData/version";
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
    public static final String LATEST_DB_NAME ="china_cities_v2.db" ;
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

    public static List<MainItemBean> getMainItemData() {
        List<MainItemBean> dataList = new ArrayList<>();

        dataList.add(new MainItemBean("Toast", R.mipmap.ico_grid_toast));
        dataList.add(new MainItemBean("仿IOS Dialog", R.mipmap.ico_grid_ios_dialog));
        dataList.add(new MainItemBean("Loading Dialog", R.mipmap.ico_grid_load_dialog));
        dataList.add(new MainItemBean("ScrollView", R.mipmap.ico_grid_detail));
        dataList.add(new MainItemBean("FloatDragButton", R.mipmap.ico_grid_floatdrag_button));
        dataList.add(new MainItemBean("版本更新", R.mipmap.ico_grid_update));
        dataList.add(new MainItemBean("详情页", R.mipmap.ico_grid_detail));
        dataList.add(new MainItemBean("Popupwindow筛选", R.mipmap.ico_grid_popupwindow));
        dataList.add(new MainItemBean("ArcLayout", R.mipmap.ico_grid_a));
        dataList.add(new MainItemBean("设备信息", R.mipmap.ico_grid_b));
        //10
        dataList.add(new MainItemBean("图片处理", R.mipmap.ico_grid_c));
        dataList.add(new MainItemBean("BottomSheet", R.mipmap.ico_grid_d));
        dataList.add(new MainItemBean("日期等选择器", R.mipmap.ico_grid_e));
        dataList.add(new MainItemBean("下拉刷新", R.mipmap.ico_grid_f));


        return dataList;
    }


}
