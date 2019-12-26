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


}
