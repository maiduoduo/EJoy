package com.ejoy.tool.app;
import android.widget.LinearLayout;

import com.ejoy.tool.common.api.HostType;

/**
 * CN:      AppConstant
 * Author： JSYL-DINGCL (1144286501@qq.com)
 * Date:   2019/10/14
 * Des:    全局文件
 */
public class AppConstant {
    public static int HTTP_TIME = 20000;
    public static int URL_CODE = 0;
    public static String Baseurl = "";
    static {
        switch (URL_CODE){
            case 0:
                //xb-Local
                Baseurl = "http://192.168.40.190:8888/";
                break;
            default:
                break;

        }
    }
    /**
     * xb-Local
     */
    public static String GRID_LOCAL1 = "http://192.168.40.190:8888/";
    public static final String HOT = "hot";
    public static final String XING_GAN = "xinggan";
    public static final String JAPAN = "japan";
    public static final String TAIWAN = "taiwan";
    public static final String MM = "mm";
    public static final String ZIPAI = "zipai";
    public static final String JIEPAI = "jiepai";
    public static final String BEST = "best";
    public static final String TODAY = GRID_LOCAL1 + "today";
    public static final int COUNT = 20;

    //FloatTab
    public static int CONTEXT_HEADER = 0;
    public static LinearLayout MY_HEAD_LAYOUT = null;
    public static int MY_INDICATORTOP = 0;
    public static int offsetHeight = 0;
    public static int ScrollY = 0;
    public static boolean RET;
    public static int DisplayWidth = 0;


    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String configHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.TYPE_HOST_STANDARD:
                host = GRID_LOCAL1;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }

    public static final String[] url = new String[]{
            "http://c.hiphotos.baidu.com/smart_image/pic/item/5bafa40f4bfbfbed91fbb0837ef0f736aec31faf.jpg",
            "http://h.hiphotos.baidu.com/smart_image/pic/item/4ec2d5628535e5dd2820232370c6a7efce1b623a.jpg",
            "http://a.hiphotos.baidu.com/smart_image/pic/item/9c16fdfaaf51f3de6cee3f9892eef01f3b2979ea.jpg",
            "http://smart_image.tianjimedia.com/uploadImages/2011/253/5SGVFD0KYZW2.jpg",
            "http://h.hiphotos.baidu.com/smart_image/pic/item/203fb80e7bec54e7f14e9ce2bf389b504ec26aa8.jpg",
            "http://c.hiphotos.baidu.com/smart_image/pic/item/5bafa40f4bfbfbed91fbb0837ef0f736aec31faf.jpg",
            "http://g.hiphotos.baidu.com/smart_image/pic/item/4b90f603738da977772000d7b651f8198618e33b.jpg",
            "http://h.hiphotos.baidu.com/smart_image/pic/item/6609c93d70cf3bc798e14b10d700baa1cc112a6c.jpg",
            "http://f.hiphotos.baidu.com/smart_image/h%3D200/sign=368c40c7cbfc1e17e2bf8b317a91f67c/6c224f4a20a446237cd252b39c22720e0df3d7c3.jpg",
            "http://h.hiphotos.baidu.com/smart_image/pic/item/203fb80e7bec54e7f14e9ce2bf389b504ec26aa8.jpg",
            "http://c.hiphotos.baidu.com/smart_image/pic/item/5bafa40f4bfbfbed91fbb0837ef0f736aec31faf.jpg",
            "http://g.hiphotos.baidu.com/smart_image/pic/item/4b90f603738da977772000d7b651f8198618e33b.jpg",
            "http://h.hiphotos.baidu.com/smart_image/pic/item/6609c93d70cf3bc798e14b10d700baa1cc112a6c.jpg",
            "http://f.hiphotos.baidu.com/smart_image/h%3D200/sign=368c40c7cbfc1e17e2bf8b317a91f67c/6c224f4a20a446237cd252b39c22720e0df3d7c3.jpg",
            "http://h.hiphotos.baidu.com/smart_image/pic/item/203fb80e7bec54e7f14e9ce2bf389b504ec26aa8.jpg",
            "http://c.hiphotos.baidu.com/smart_image/pic/item/5bafa40f4bfbfbed91fbb0837ef0f736aec31faf.jpg",
            "http://g.hiphotos.baidu.com/smart_image/pic/item/4b90f603738da977772000d7b651f8198618e33b.jpg",
            "http://h.hiphotos.baidu.com/smart_image/pic/item/6609c93d70cf3bc798e14b10d700baa1cc112a6c.jpg",
            "http://f.hiphotos.baidu.com/smart_image/h%3D200/sign=368c40c7cbfc1e17e2bf8b317a91f67c/6c224f4a20a446237cd252b39c22720e0df3d7c3.jpg",
            "http://h.hiphotos.baidu.com/smart_image/pic/item/203fb80e7bec54e7f14e9ce2bf389b504ec26aa8.jpg",
            "http://img.taopic.com/uploads/allimg/140119/234926-14011ZU35340.jpg",
            "http://img1.imgtn.bdimg.com/it/u=78892727,3063927320&fm=23&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=957679189,3516093661&fm=23&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=890350663,1927931620&fm=23&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3959292123,1789415419&fm=23&gp=0.jpg"};

    public static final String[] url2 = new String[]{
            "http://img0.imgtn.bdimg.com/it/u=3268904404,921223304&fm=11&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2423893232,4154036355&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3377406333,2742840292&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3683810524,2759589095&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=4241281044,538566628&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3673048821,2452061959&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3268904404,921223304&fm=11&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2423893232,4154036355&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3377406333,2742840292&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3683810524,2759589095&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=4241281044,538566628&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3673048821,2452061959&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3268904404,921223304&fm=11&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2423893232,4154036355&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3377406333,2742840292&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3683810524,2759589095&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=4241281044,538566628&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3673048821,2452061959&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3268904404,921223304&fm=11&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2423893232,4154036355&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3377406333,2742840292&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3683810524,2759589095&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=4241281044,538566628&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3673048821,2452061959&fm=21&gp=0.jpg",
    };
    public static final String[] url3 = new String[]{
            "http://img5.imgtn.bdimg.com/it/u=2718404076,3031639911&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1826501988,4073826302&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=4195055044,282761109&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3108661666,496287233&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1573812686,2257210520&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2037395612,2080510986&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3860973773,801763570&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3610196237,4019772089&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=216802935,594365367&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=81944964,3944943751&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1647378912,3447288460&fm=21&gp=0.jpg",
    };


    //4种常用背景色
    public static final int color_info = 0XFF2094F3;        //信息
    public static final int color_confirm = 0XFF4CB04E;     //确认
    public static final int color_warning = 0XFFFEC005;     //警告
    public static final int color_danger = 0XFFF44336;      //危险
    //中国传统36色
    public static final int color_cn_zhuqing = 0XFF7D9768;          //竹青
    public static final int color_cn_wan = 0XFFBE968E;              //绾
    public static final int color_cn_li = 0XFF76664D;               //黎
    public static final int color_cn_huanglu = 0XFFE3A335;          //黄栌
    public static final int color_cn_ou = 0XFFEED0D8;               //藕
    public static final int color_cn_chabai = 0XFFEFF4ED;           //茶白
    public static final int color_cn_zitan = 0XFF6A2C1F;            //紫檀
    public static final int color_cn_cang = 0XFF8EA7AB;             //苍
    public static final int color_cn_dingxiang = 0XFFD6B5D4;        //丁香
    public static final int color_cn_yuebai = 0XFFEEF6F8;           //月白
    public static final int color_cn_su = 0XFFE1EEE7;               //素
    public static final int color_cn_lanhui = 0XFF9CAAC4;           //蓝灰
    public static final int color_cn_yaluanqing = 0XFFEAF5F1;       //鸭卵青
    public static final int color_cn_xiangyabai = 0XFFFEFEF6;       //象牙白
    public static final int color_cn_ya = 0XFFE9DDB5;               //牙
    public static final int color_cn_xue = 0XFFF8FCFD;              //雪
    public static final int color_cn_dailan = 0XFF566177;           //黛蓝
    public static final int color_cn_shuilv = 0XFFD3EAE2;           //水绿
    public static final int color_cn_wuhei = 0XFF392E3F;            //乌黑
    public static final int color_cn_shuang = 0XFFE9F0F8;           //霜
    public static final int color_cn_fei = 0XFFED5736;              //妃
    public static final int color_cn_mohui = 0XFF748A98;            //墨灰
    public static final int color_cn_xuehui = 0XFFEDEDEF;           //雪灰
    public static final int color_cn_yanzhi = 0XFF97282F;           //胭脂
    public static final int color_cn_yaqing = 0XFF424B50;           //鸦青
    public static final int color_cn_zi = 0XFF493131;               //淄
    public static final int color_cn_qihei = 0XFF131522;            //漆黑
    public static final int color_cn_haitanghong = 0XFFD13658;      //海棠红
    public static final int color_cn_mo = 0XFF272923;               //墨
    public static final int color_cn_liuhuang = 0XFFC9DD23;         //柳黄
    public static final int color_cn_tuoyan = 0XFFF99070;           //酡颜
    public static final int color_cn_pinhong = 0XFFF00057;          //品红
    public static final int color_cn_yan = 0XFFD36870;              //嫣
    public static final int color_cn_xueqing = 0XFFA49BC8;          //雪青
    public static final int color_cn_feicui = 0XFF5BB995;           //翡翠
    public static final int color_cn_hupo = 0XFFCA6924;             //琥珀
    //flyme6中7配色
    public static final int color_flyme_blue_sky = 0XFF198DED;      //天蓝
    public static final int color_flyme_blue_green = 0XFF04C0CF;    //蓝绿
    public static final int color_flyme_green = 0XFF3BC06B;         //绿
    public static final int color_flyme_yellow = 0XFFFFBE26;        //黄
    public static final int color_flyme_orange = 0XFFFC5B23;        //橙
    public static final int color_flyme_red_dark = 0XFFD33A2A;      //深红
    public static final int color_flyme_red_bright = 0XFFF12528;    //鲜红

    private AppConstant(){
        throw new RuntimeException("禁止创建实例!");
    }


}
