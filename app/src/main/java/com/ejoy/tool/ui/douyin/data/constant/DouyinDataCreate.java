package com.ejoy.tool.ui.douyin.data.constant;


import com.module.ires.bean.bean.DouyinVideoBean;

import java.util.ArrayList;


/**
 * @ClassName:  DouyinDataCreate
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/1/29
 * @des: 抖音本地数据，代替接口获取数据
 */
public class DouyinDataCreate {
    public static ArrayList<DouyinVideoBean> datas = new ArrayList<>();
    public static ArrayList<DouyinVideoBean.UserBean> userList = new ArrayList<>();

    public void initData() {


        DouyinVideoBean videoBeanZero = new DouyinVideoBean();
//        videoBeanOne.setCoverRes(R.mipmap.cover1);
        videoBeanZero.setCoverUrl("https://mvimg10.meitudata.com/5ff82dd83a849283.jpg!sthumb480?28252650");
        videoBeanZero.setContent("#美好一天 #爱情 #异地恋 #结婚 #2021我的人生态度 17岁遇见你相恋10年异国恋4年的校园爱情 我们一起走过了3047天， 从学生时代相恋，经历了多年异国，再到如今有了我们的家 我们很幸运，希望你们也是");
//        videoBeanOne.setVideoRes(R.raw.video1);
        videoBeanZero.setVideoUrl("https://mvvideoshare1.meitudata.com/5ff833ea31991H264WEB412319_H264_4_53e9e4e9419bd8.mp4?k=722b35300bdf402ebf5915f33cf5c964&t=60336a7a");
//        videoBeanZero.setVideoUrl("https://files.catbox.moe/2wt1dx.mp4");
        videoBeanZero.setDistance(7.9f);
        videoBeanZero.setFocused(false);
        videoBeanZero.setLiked(true);
        videoBeanZero.setLikeCount(226823);
        videoBeanZero.setCommentCount(3480);
        videoBeanZero.setShareCount(4252);
        videoBeanZero.setVideoOritation(1);

        DouyinVideoBean.UserBean userBeanZero = new DouyinVideoBean.UserBean();
        userBeanZero.setUid(0);
//        userBeanOne.setHead(R.mipmap.head1);
        userBeanZero.setHeadUrl("https://maavatar1.meitudata.com/5ff80947520fankjb36kem2356.jpg!thumb160");
        userBeanZero.setNickName("洪千辰");
        userBeanZero.setSign("生来偏执 如何折中");
        userBeanZero.setSubCount(119323);
        userBeanZero.setFocusCount(482);
        userBeanZero.setFansCount(32823);
        userBeanZero.setWorkCount(42);
        userBeanZero.setDynamicCount(42);
        userBeanZero.setLikeCount(821);

        userList.add(userBeanZero);
        videoBeanZero.setUserBean(userBeanZero);


        DouyinVideoBean videoBeanOne = new DouyinVideoBean();
//        videoBeanOne.setCoverRes(R.mipmap.cover1);
        videoBeanOne.setCoverUrl("https://mvimg10.meitudata.com/5b219ba2354e33471.jpg!webp");
        videoBeanOne.setContent("#手工耿 #搞笑原创 来个红烧铁蚂蚱");
//        videoBeanOne.setVideoRes(R.raw.video1);
        videoBeanOne.setVideoUrl("https://mvvideoshare1.meitudata.com/5b219ba2351896095_H264_3.mp4?k=1ac4af1ecd083c0f79433ef5e88683db&t=603369b7");
        videoBeanOne.setDistance(7.9f);
        videoBeanOne.setFocused(false);
        videoBeanOne.setLiked(true);
        videoBeanOne.setLikeCount(226823);
        videoBeanOne.setCommentCount(3480);
        videoBeanOne.setShareCount(4252);
        videoBeanOne.setVideoOritation(0);

        DouyinVideoBean.UserBean userBeanOne = new DouyinVideoBean.UserBean();
        userBeanOne.setUid(1);
//        userBeanOne.setHead(R.mipmap.head1);
        userBeanOne.setHeadUrl("https://maavatar1.meitudata.com/5b2199122a0f23872.jpg!thumb160");
        userBeanOne.setNickName("手工耿");
        userBeanOne.setSign("生来偏执 如何折中");
        userBeanOne.setSubCount(119323);
        userBeanOne.setFocusCount(482);
        userBeanOne.setFansCount(32823);
        userBeanOne.setWorkCount(42);
        userBeanOne.setDynamicCount(42);
        userBeanOne.setLikeCount(821);

        userList.add(userBeanOne);
        videoBeanOne.setUserBean(userBeanOne);

        DouyinVideoBean videoBeanTwo = new DouyinVideoBean();
        videoBeanTwo.setCoverUrl("https://mvimg10.meitudata.com/5f16e060c2c6epzlkz7dyd2382.jpg!sthumb480?28252650");
        videoBeanTwo.setContent("#朱一旦的枯燥生活 不就摸了几个小姑娘的屁股，至于开除吗？ #可怜天下父母心");
//        videoBeanTwo.setVideoUrl("http://uvideo.spriteapp.cn/video/2020/0831/f3115fca-eb5d-11ea-9763-1866daea6abd_wpd.mp4");
        videoBeanTwo.setVideoUrl("https://mvvideoshare1.meitudata.com/5f16e060ac5b3lhfdxj5j57589_H264_1_1f7211d2913126.mp4?k=8aeea1f9c416c5add0765dc9348ace05&t=603368ba");

        videoBeanTwo.setDistance(19.7f);
        videoBeanTwo.setFocused(true);
        videoBeanTwo.setLiked(false);
        videoBeanTwo.setLikeCount(1938230);
        videoBeanTwo.setCommentCount(8923);
        videoBeanTwo.setShareCount(5892);
        videoBeanTwo.setVideoOritation(0);

        DouyinVideoBean.UserBean userBeanTwo = new DouyinVideoBean.UserBean();
        userBeanTwo.setUid(2);
        userBeanTwo.setHeadUrl("https://maavatar1.meitudata.com/5e895cae8c8f56248.jpg!thumb160");
        userBeanTwo.setNickName("朱一旦的枯燥生活");
        userBeanTwo.setSign("当年我要是有这么聪明，也不至于被打死");
        userBeanTwo.setSubCount(20323234);
        userBeanTwo.setFocusCount(244);
        userBeanTwo.setFansCount(1938232);
        userBeanTwo.setWorkCount(123);
        userBeanTwo.setDynamicCount(123);
        userBeanTwo.setLikeCount(344);

        userList.add(userBeanTwo);
        videoBeanTwo.setUserBean(userBeanTwo);

        DouyinVideoBean videoBeanThree = new DouyinVideoBean();
        videoBeanThree.setCoverUrl("https://mvimg10.meitudata.com/60001405e5e87p7i9n0ii48731.jpg!sthumb480?28252650");
        videoBeanThree.setContent("#中国 #明星 有你在的省份的女明星吗？！#美女  #搞笑");
        videoBeanThree.setVideoUrl("https://mvvideoshare1.meitudata.com/600014062e717jezsqbkcj246_H264_4_55c554e0116d8a.mp4?k=c36547911ddceee5811930e997accde8&t=60336849");
        videoBeanThree.setDistance(15.9f);
        videoBeanThree.setFocused(false);
        videoBeanThree.setLiked(false);
        videoBeanThree.setLikeCount(592032);
        videoBeanThree.setCommentCount(9221);
        videoBeanThree.setShareCount(982);
        videoBeanThree.setVideoOritation(0);

        DouyinVideoBean.UserBean userBeanThree = new DouyinVideoBean.UserBean();
        userBeanThree.setUid(3);
        userBeanThree.setHeadUrl("https://maavatar1.meitudata.com/5ceb4ff9b14426348.jpg!thumb160");
        userBeanThree.setNickName("桑德测评");
        userBeanThree.setSign("吾与曹贼何异？");
        userBeanThree.setSubCount(1039232);
        userBeanThree.setFocusCount(159);
        userBeanThree.setFansCount(29232323);
        userBeanThree.setWorkCount(171);
        userBeanThree.setDynamicCount(173);
        userBeanThree.setLikeCount(1724);

        userList.add(userBeanThree);
        videoBeanThree.setUserBean(userBeanThree);

        DouyinVideoBean videoBeanFour = new DouyinVideoBean();
        videoBeanFour.setCoverUrl("https://mvimg10.meitudata.com/6008f026e798do7gakb72f215.jpg!sthumb480?28252650");
        videoBeanFour.setContent("我感觉这些毛孩子都是带着技术投胎的。 #搞笑配音 #搞笑视频 #搞笑，我要上热门");
        videoBeanFour.setVideoUrl("https://mvvideoshare2.meitudata.com/6008f024b47b6polzv9okv9578_H264_4_30093953cbe87.mp4?k=373870e5e92cae68e6689044b4548d58&t=6033677c");
        videoBeanFour.setDistance(25.2f);
        videoBeanFour.setFocused(false);
        videoBeanFour.setLiked(false);
        videoBeanFour.setLikeCount(887232);
        videoBeanFour.setCommentCount(2731);
        videoBeanFour.setShareCount(8924);
        videoBeanFour.setVideoOritation(0);


        DouyinVideoBean.UserBean userBeanFour = new DouyinVideoBean.UserBean();
        userBeanFour.setUid(4);
        userBeanFour.setHeadUrl("https://maavatar1.meitudata.com/6003dc2b8f4741lqzdr42q6389.jpg!thumb160");
        userBeanFour.setNickName("爆笑配音");
        userBeanFour.setSign("百思爆笑，接剪辑，活动拍摄，修图单\n 合作私信");
        userBeanFour.setSubCount(2689424);
        userBeanFour.setFocusCount(399);
        userBeanFour.setFansCount(360829);
        userBeanFour.setWorkCount(562);
        userBeanFour.setDynamicCount(570);
        userBeanFour.setLikeCount(4310);

        userList.add(userBeanFour);
        videoBeanFour.setUserBean(userBeanFour);

        DouyinVideoBean videoBeanFive = new DouyinVideoBean();
        videoBeanFive.setCoverUrl("https://mvimg10.meitudata.com/600e566da49d3nze4cgjac1896.jpg!sthumb480?28252650");
        videoBeanFive.setContent("在昆明市区还不知道哪里拍照最好看？一定要带闺蜜来这～ #昆明 ");
        videoBeanFive.setVideoUrl("https://mvvideoshare1.meitudata.com/600e566d727f0lrddfzs5l3019_H264_4_5910113d535079.mp4?k=a1f1898d980607b856acc7f5e962dce6&t=6033778e");

        videoBeanFive.setDistance(9.2f);
        videoBeanFive.setFocused(false);
        videoBeanFive.setLiked(false);
        videoBeanFive.setLikeCount(8293241);
        videoBeanFive.setCommentCount(982);
        videoBeanFive.setShareCount(8923);
        videoBeanFive.setVideoOritation(0);


        DouyinVideoBean.UserBean userBeanFive = new DouyinVideoBean.UserBean();
        userBeanFive.setUid(5);
        userBeanFive.setHeadUrl("https://maavatar1.meitudata.com/6012860b35b140jgvpv1l53574.jpg!thumb160");
        userBeanFive.setNickName("昆明攻略");
        userBeanFive.setSign("");
        userBeanFive.setSubCount(1002342);
        userBeanFive.setFocusCount(87);
        userBeanFive.setFansCount(520232);
        userBeanFive.setWorkCount(89);
        userBeanFive.setDynamicCount(122);
        userBeanFive.setLikeCount(9);

        userList.add(userBeanFive);
        videoBeanFive.setUserBean(userBeanFive);

        DouyinVideoBean videoBeanSix = new DouyinVideoBean();

        videoBeanSix.setCoverUrl("https://mvimg10.meitudata.com/6006b78ae56b4a2u29udrc7612.jpg!sthumb480?28252650");
        videoBeanSix.setContent("#何广智 ：自嘲不仅没扼住生活的喉咙，还被生活薅住了头发#脱口秀  #吐槽大会 ");
        videoBeanSix.setVideoUrl("https://mvvideoshare1.meitudata.com/6006b78ad227dk4d5ew3vw5289_H264_4_5749cb2c12ed2a.mp4?k=241763b9905a97c17201ee348cba6a3f&t=60335904");
        videoBeanSix.setDistance(16.4f);
        videoBeanSix.setFocused(true);
        videoBeanSix.setLiked(true);
        videoBeanSix.setLikeCount(2109823);
        videoBeanSix.setCommentCount(9723);
        videoBeanSix.setShareCount(424);
        videoBeanSix.setVideoOritation(0);

        DouyinVideoBean.UserBean userBeanSix = new DouyinVideoBean.UserBean();
        userBeanSix.setUid(6);
        userBeanSix.setHeadUrl("https://maavatar1.meitudata.com/602dc736250be9k3vkg15x2415.jpg!thumb160");
        userBeanSix.setNickName("唐彧小宝❀唐彧小宝");
        userBeanSix.setSign("一个行走在Tr与剪辑之间的人\n 有什么不懂的可以来直播间问我");
        userBeanSix.setSubCount(29342320);
        userBeanSix.setFocusCount(67);
        userBeanSix.setFansCount(7028323);
        userBeanSix.setWorkCount(5133);
        userBeanSix.setDynamicCount(5159);
        userBeanSix.setLikeCount(0);

        userList.add(userBeanSix);
        videoBeanSix.setUserBean(userBeanSix);

        DouyinVideoBean videoBeanSeven = new DouyinVideoBean();

        videoBeanSeven.setCoverUrl("https://mvimg10.meitudata.com/5f53578aa9726a2fcm25me5757.jpg!sthumb480?28252650");
        videoBeanSeven.setContent("#生活相处  虽然是跨国婆媳，也是真心换真心！总想着把好吃的留给我  ");
        videoBeanSeven.setVideoUrl("https://mvvideo10.meitudata.com/5f53578b407f8qoeas7z2n3594_H264_1_2b2e4034fbcad.mp4?k=404fa50739e54029fae9a2fa95b4f3e4&t=603355b7");
        videoBeanSeven.setDistance(16.4f);
        videoBeanSeven.setFocused(false);
        videoBeanSeven.setLiked(false);
        videoBeanSeven.setLikeCount(185782);
        videoBeanSeven.setCommentCount(2452);
        videoBeanSeven.setShareCount(3812);
        videoBeanSeven.setVideoOritation(0);

        DouyinVideoBean.UserBean userBeanSeven = new DouyinVideoBean.UserBean();
        userBeanSeven.setUid(7);
        userBeanSeven.setHeadUrl("http://img.kaiyanapp.com/482c741c06644f5566c7218096dbaf26.jpeg");
        userBeanSeven.setNickName("韩国媳妇大璐璐");
        userBeanSeven.setSign("韩国媳妇大璐璐");
        userBeanSeven.setSubCount(471932);
        userBeanSeven.setFocusCount(482);
        userBeanSeven.setFansCount(371423);
        userBeanSeven.setWorkCount(242);
        userBeanSeven.setDynamicCount(245);
        userBeanSeven.setLikeCount(839);

        userList.add(userBeanSeven);
        videoBeanSeven.setUserBean(userBeanSeven);

        DouyinVideoBean videoBeanEight = new DouyinVideoBean();
        videoBeanEight.setCoverUrl("https://mvimg10.meitudata.com/5ff99101e8c745726.jpg!webp");
        videoBeanEight.setContent("#娱乐 一生系列最后一个视频——萝卜的一生（上） #李子柒 "+
                "萝卜怎么落花成籽,埋种地下,怎么发芽,如何开花,怎样长大…\n" +
                "这是《一生系列》的最后一个视频了,一物一生");
        videoBeanEight.setVideoUrl("https://mvvideoshare1.meitudata.com/5ff997c4f04f7H264WEB106341_H264_4_cb42b946cb17d.mp4?k=2ad99dcfa80f77542de63243b7833f8a&t=60335a06");
        videoBeanEight.setDistance(8.4f);
        videoBeanEight.setFocused(false);
        videoBeanEight.setLiked(false);
        videoBeanEight.setLikeCount(1708324);
        videoBeanEight.setCommentCount(8372);
        videoBeanEight.setShareCount(982);
        videoBeanEight.setVideoOritation(1);

        DouyinVideoBean.UserBean userBeanEight = new DouyinVideoBean.UserBean();
        userBeanEight.setUid(8);
        userBeanEight.setHeadUrl("https://maavatar1.meitudata.com/57958af0dfeda4811.jpg!thumb160");
        userBeanEight.setNickName("李子柒");
        userBeanEight.setSign("#2021有你真好#");
        userBeanEight.setSubCount(1832342);
        userBeanEight.setFocusCount(397);
        userBeanEight.setFansCount(1394232);
        userBeanEight.setWorkCount(164);
        userBeanEight.setDynamicCount(167);
        userBeanEight.setLikeCount(1);

        userList.add(userBeanEight);
        videoBeanEight.setUserBean(userBeanEight);



        DouyinVideoBean videoBeanTen = new DouyinVideoBean();
        videoBeanTen.setCoverUrl("https://mvimg10.meitudata.com/5ff0004974d371314.jpg!webp");
        videoBeanTen.setContent("#中式婚礼  小八的新娘妆化好后，小朋友见了都说超漂亮，中式婚礼也正式开始");
        videoBeanTen.setVideoUrl("https://mvvideoshare2.meitudata.com/5ff0004d7faa5H264WEB511810_H264_4_5200c903e044b7.mp4?k=5bd0c5022fd2d23a3b9cb5f6fd59e179&t=60335ae4");
        videoBeanTen.setDistance(8.4f);
        videoBeanTen.setFocused(false);
        videoBeanTen.setLiked(false);
        videoBeanTen.setLikeCount(1708324);
        videoBeanTen.setCommentCount(8372);
        videoBeanTen.setShareCount(982);
        videoBeanTen.setVideoOritation(1);

        DouyinVideoBean.UserBean userBeanTen = new DouyinVideoBean.UserBean();
        userBeanTen.setUid(8);
        userBeanTen.setHeadUrl("https://maavatar1.meitudata.com/5e891cca4ecc79482.jpg!thumb160");
        userBeanTen.setNickName("尘乡居");
        userBeanTen.setSign("");
        userBeanTen.setSubCount(1832342);
        userBeanTen.setFocusCount(397);
        userBeanTen.setFansCount(1394232);
        userBeanTen.setWorkCount(164);
        userBeanTen.setDynamicCount(167);
        userBeanTen.setLikeCount(0);

        userList.add(userBeanTen);
        videoBeanTen.setUserBean(userBeanTen);



        datas.add(videoBeanZero);
        datas.add(videoBeanOne);
        datas.add(videoBeanTwo);
        datas.add(videoBeanThree);
        datas.add(videoBeanFour);
        datas.add(videoBeanFive);
        datas.add(videoBeanSix);
        datas.add(videoBeanSeven);
        datas.add(videoBeanEight);
        datas.add(videoBeanTen);


        datas.add(videoBeanZero);
        datas.add(videoBeanOne);
        datas.add(videoBeanTwo);
        datas.add(videoBeanThree);
        datas.add(videoBeanFour);
        datas.add(videoBeanFive);
        datas.add(videoBeanSix);
        datas.add(videoBeanSeven);
        datas.add(videoBeanEight);
        datas.add(videoBeanTen);

    }
}
