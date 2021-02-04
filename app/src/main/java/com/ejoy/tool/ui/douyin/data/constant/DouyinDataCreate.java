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
        videoBeanZero.setCoverUrl("https://img.zcool.cn/community/0129495e4f89f7a801216518f0030f.jpg");
        videoBeanZero.setContent("#美好一天 #日子 每天早上，从发现美开始，今天要过的开心a");
//        videoBeanOne.setVideoRes(R.raw.video1);
        videoBeanZero.setVideoUrl("http://f.video.weibocdn.com/PMJxspCWlx07K6CrBrMs01041200vcvd0E010.mp4?label=mp4_720p&template=720x1280.24.0&trans_finger=c3f00996be5378650057cf237d7bfffd&ori=0&ps=1A1eh1m4ElLYfp&Expires=1612447777&ssig=iWjOR6Rfnf&KID=unistore,video");
        videoBeanZero.setDistance(7.9f);
        videoBeanZero.setFocused(false);
        videoBeanZero.setLiked(true);
        videoBeanZero.setLikeCount(226823);
        videoBeanZero.setCommentCount(3480);
        videoBeanZero.setShareCount(4252);
        videoBeanZero.setVideoOritation(0);

        DouyinVideoBean.UserBean userBeanZero = new DouyinVideoBean.UserBean();
        userBeanZero.setUid(0);
//        userBeanOne.setHead(R.mipmap.head1);
        userBeanZero.setHeadUrl("http://img.kaiyanapp.com/52f767cb73ac4ffb2fe07c4a7e90b406.png?imageMogr2/quality/60/format/jpg");
        userBeanZero.setNickName("从晴笑");
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
        videoBeanOne.setCoverUrl("https://img.zcool.cn/community/0129495e4f89f7a801216518f0030f.jpg");
        videoBeanOne.setContent("#手工耿 #搞笑原创 自制手工充电器");
//        videoBeanOne.setVideoRes(R.raw.video1);
        videoBeanOne.setVideoUrl("http://ali.cdn.kaiyanapp.com/1611855059621_e6990175.mp4?auth_key=1612445142-0-0-08623153547c7de0f9814f22be48aaa3");
        videoBeanOne.setDistance(7.9f);
        videoBeanOne.setFocused(false);
        videoBeanOne.setLiked(true);
        videoBeanOne.setLikeCount(226823);
        videoBeanOne.setCommentCount(3480);
        videoBeanOne.setShareCount(4252);
        videoBeanOne.setVideoOritation(1);

        DouyinVideoBean.UserBean userBeanOne = new DouyinVideoBean.UserBean();
        userBeanOne.setUid(1);
//        userBeanOne.setHead(R.mipmap.head1);
        userBeanOne.setHeadUrl("http://img.kaiyanapp.com/bcaf2c7c5f1f30ab7ad0d9e29233de5b.jpeg?imageMogr2/quality/60/format/jpg");
        userBeanOne.setNickName("天才少女爱我");
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
        videoBeanTwo.setCoverUrl("https://img.zcool.cn/community/0129495e4f89f7a801216518f0030f.jpg");
        videoBeanTwo.setContent("#朱一旦的枯燥生活  💎发奖金了吗？");
//        videoBeanTwo.setVideoUrl("http://uvideo.spriteapp.cn/video/2020/0831/f3115fca-eb5d-11ea-9763-1866daea6abd_wpd.mp4");
        videoBeanTwo.setVideoUrl("http://f.video.weibocdn.com/003SCVWFgx07K3BuwjXN01041200MTnO0E010.mp4?label=mp4_720p&template=720x1280.24.0&trans_finger=c3f00996be5378650057cf237d7bfffd&ori=0&ps=1A1eh1m4ElLYfp&Expires=1612447777&ssig=0Gw5J3Y83d&KID=unistore,video");

        videoBeanTwo.setDistance(19.7f);
        videoBeanTwo.setFocused(true);
        videoBeanTwo.setLiked(false);
        videoBeanTwo.setLikeCount(1938230);
        videoBeanTwo.setCommentCount(8923);
        videoBeanTwo.setShareCount(5892);
        videoBeanTwo.setVideoOritation(0);

        DouyinVideoBean.UserBean userBeanTwo = new DouyinVideoBean.UserBean();
        userBeanTwo.setUid(2);
        userBeanTwo.setHeadUrl("http://img.kaiyanapp.com/6da6ebd197c408a6d0193c58c00583f4.png?imageMogr2/quality/60/format/jpg");
        userBeanTwo.setNickName("遇一狗白首");
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
        videoBeanThree.setCoverUrl("https://img.zcool.cn/community/0129495e4f89f7a801216518f0030f.jpg");
        videoBeanThree.setContent("#宠物猫  这jio是通电了吗？一碰就摇起来！哈哈");
        videoBeanThree.setVideoUrl("http://ali.cdn.kaiyanapp.com/1612336880853_f3941eb5.mp4?auth_key=1612446207-0-0-f401adf644de5ca554ffe1a7da19f933");
        videoBeanThree.setDistance(15.9f);
        videoBeanThree.setFocused(false);
        videoBeanThree.setLiked(false);
        videoBeanThree.setLikeCount(592032);
        videoBeanThree.setCommentCount(9221);
        videoBeanThree.setShareCount(982);
        videoBeanThree.setVideoOritation(1);

        DouyinVideoBean.UserBean userBeanThree = new DouyinVideoBean.UserBean();
        userBeanThree.setUid(3);
        userBeanThree.setHeadUrl("http://img.kaiyanapp.com/afa27b9c52d2ed2f5f8b5f8c12992fcf.png?imageMogr2/quality/60/format/jpg");
        userBeanThree.setNickName("林深时见鹿");
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
        videoBeanFour.setCoverUrl("https://img.zcool.cn/community/0129495e4f89f7a801216518f0030f.jpg");
        videoBeanFour.setContent("#篮球 #CBA  不顾教练席大喊外援！张宁三分打进 ");
        videoBeanFour.setVideoUrl("http://f.video.weibocdn.com/hJeV48v7lx07JTI75Vy0010412007fjM0E010.mp4?label=mp4_720p&template=720x1280.24.0&trans_finger=c3f00996be5378650057cf237d7bfffd&ori=0&ps=1A1eh1m4ElLYfp&Expires=1612423304&ssig=5X%2B373u025&KID=unistore,video");
        videoBeanFour.setDistance(25.2f);
        videoBeanFour.setFocused(false);
        videoBeanFour.setLiked(false);
        videoBeanFour.setLikeCount(887232);
        videoBeanFour.setCommentCount(2731);
        videoBeanFour.setShareCount(8924);
        videoBeanFour.setVideoOritation(1);


        DouyinVideoBean.UserBean userBeanFour = new DouyinVideoBean.UserBean();
        userBeanFour.setUid(4);
        userBeanFour.setHeadUrl("http://img.kaiyanapp.com/a17745312139694dc1f0c40984533328.png?imageMogr2/quality/60/format/jpg");
        userBeanFour.setNickName("看了个球");
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
        videoBeanFive.setCoverUrl("https://img.zcool.cn/community/0129495e4f89f7a801216518f0030f.jpg");
        videoBeanFive.setContent("#萌娃  警察叔叔遇到史上最小年纪的“自首”  #神奇 ");
        videoBeanFive.setVideoUrl("http://f.video.weibocdn.com/oeg72jyvlx07JLWJFBhu01041200j3VH0E010.mp4?label=mp4_720p&template=720x1270.24.0&trans_finger=c3f00996be5378650057cf237d7bfffd&ori=0&ps=1A1eh1m4ElLYfp&Expires=1612423435&ssig=zEi40kZgdK&KID=unistore,video");

        videoBeanFive.setDistance(9.2f);
        videoBeanFive.setFocused(false);
        videoBeanFive.setLiked(false);
        videoBeanFive.setLikeCount(8293241);
        videoBeanFive.setCommentCount(982);
        videoBeanFive.setShareCount(8923);
        videoBeanFive.setVideoOritation(1);


        DouyinVideoBean.UserBean userBeanFive = new DouyinVideoBean.UserBean();
        userBeanFive.setUid(5);
        userBeanFive.setHeadUrl("http://img.kaiyanapp.com/d7186edff72b6a6ddd03eff166ee4cd8.jpeg");
        userBeanFive.setNickName("搞笑二货");
        userBeanFive.setSign("变化就是理发前别人说你杀币，理发后自己觉得自己杀币");
        userBeanFive.setSubCount(1002342);
        userBeanFive.setFocusCount(87);
        userBeanFive.setFansCount(520232);
        userBeanFive.setWorkCount(89);
        userBeanFive.setDynamicCount(122);
        userBeanFive.setLikeCount(9);

        userList.add(userBeanFive);
        videoBeanFive.setUserBean(userBeanFive);

        DouyinVideoBean videoBeanSix = new DouyinVideoBean();

        videoBeanSix.setCoverUrl("https://img.zcool.cn/community/0129495e4f89f7a801216518f0030f.jpg");
        videoBeanSix.setContent("#中国正能量   平凡英雄！孩子掉进水里，路人毫不犹豫的跳水里救孩子...   ");
        videoBeanSix.setVideoUrl("http://f.video.weibocdn.com/40IX6KDUlx07JM1dcI0g010412007Yjj0E010.mp4?label=mp4_720p&template=720x1280.24.0&trans_finger=c3f00996be5378650057cf237d7bfffd&ori=0&ps=1A1eh1m4ElLYfp&Expires=1612423435&ssig=jbT%2BKDw5LM&KID=unistore,video");
        videoBeanSix.setDistance(16.4f);
        videoBeanSix.setFocused(true);
        videoBeanSix.setLiked(true);
        videoBeanSix.setLikeCount(2109823);
        videoBeanSix.setCommentCount(9723);
        videoBeanSix.setShareCount(424);
        videoBeanSix.setVideoOritation(1);

        DouyinVideoBean.UserBean userBeanSix = new DouyinVideoBean.UserBean();
        userBeanSix.setUid(6);
        userBeanSix.setHeadUrl("http://img.kaiyanapp.com/0117b9108c7cff43700db8af5e24f2bf.jpeg");
        userBeanSix.setNickName("人民正能量");
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

        videoBeanSeven.setCoverUrl("https://img.zcool.cn/community/0129495e4f89f7a801216518f0030f.jpg");
        videoBeanSeven.setContent("#铭记历史  1945年嘉陵江拖船的纤夫为前线运送抗战物资帧规历史修复视频。大家加过纤夫吗？小学上下血路上，我都能看到纤夫，很辛苦。  ");
        videoBeanSeven.setVideoUrl("http://f.video.weibocdn.com/jIQKetm5lx07JIKKhIda01041200mFKm0E010.mp4?label=mp4_720p&template=720x1270.24.0&trans_finger=c3f00996be5378650057cf237d7bfffd&ori=0&ps=1A1eh1m4ElLYfp&Expires=1612423768&ssig=3stbTIcZzu&KID=unistore,video");
        videoBeanSeven.setDistance(16.4f);
        videoBeanSeven.setFocused(false);
        videoBeanSeven.setLiked(false);
        videoBeanSeven.setLikeCount(185782);
        videoBeanSeven.setCommentCount(2452);
        videoBeanSeven.setShareCount(3812);
        videoBeanSeven.setVideoOritation(1);

        DouyinVideoBean.UserBean userBeanSeven = new DouyinVideoBean.UserBean();
        userBeanSeven.setUid(7);
        userBeanSeven.setHeadUrl("http://img.kaiyanapp.com/482c741c06644f5566c7218096dbaf26.jpeg");
        userBeanSeven.setNickName("历史档案馆");
        userBeanSeven.setSign("纤夫的历史岁月");
        userBeanSeven.setSubCount(471932);
        userBeanSeven.setFocusCount(482);
        userBeanSeven.setFansCount(371423);
        userBeanSeven.setWorkCount(242);
        userBeanSeven.setDynamicCount(245);
        userBeanSeven.setLikeCount(839);

        userList.add(userBeanSeven);
        videoBeanSeven.setUserBean(userBeanSeven);

        DouyinVideoBean videoBeanEight = new DouyinVideoBean();
        videoBeanEight.setCoverUrl("https://img.zcool.cn/community/0129495e4f89f7a801216518f0030f.jpg");
        videoBeanEight.setContent("#娱乐  跟着李子柒万物，李子柒加油  #李子柒");
        videoBeanEight.setVideoUrl("http://f.video.weibocdn.com/CMXAtndxlx07K6aZSbRC010412004sqC0E010.mp4?label=mp4_720p&template=720x1280.24.0&trans_finger=c3f00996be5378650057cf237d7bfffd&ori=0&ps=1A1eh1m4ElLYfp&Expires=1612425515&ssig=A4ZG1T81rL&KID=unistore,video");
        videoBeanEight.setDistance(8.4f);
        videoBeanEight.setFocused(false);
        videoBeanEight.setLiked(false);
        videoBeanEight.setLikeCount(1708324);
        videoBeanEight.setCommentCount(8372);
        videoBeanEight.setShareCount(982);
        videoBeanEight.setVideoOritation(1);

        DouyinVideoBean.UserBean userBeanEight = new DouyinVideoBean.UserBean();
        userBeanEight.setUid(8);
        userBeanEight.setHeadUrl("http://img.kaiyanapp.com/afb9e7d7f061d10ade5ebcb524dc8679.jpeg?imageMogr2/quality/60/format/jpg");
        userBeanEight.setNickName("看娱乐");
        userBeanEight.setSign("#2021有你真好#");
        userBeanEight.setSubCount(1832342);
        userBeanEight.setFocusCount(397);
        userBeanEight.setFansCount(1394232);
        userBeanEight.setWorkCount(164);
        userBeanEight.setDynamicCount(167);
        userBeanEight.setLikeCount(0);

        userList.add(userBeanEight);
        videoBeanEight.setUserBean(userBeanEight);





        DouyinVideoBean videoBeanTen = new DouyinVideoBean();
        videoBeanTen.setCoverUrl("https://img.zcool.cn/community/0129495e4f89f7a801216518f0030f.jpg");
        videoBeanTen.setContent("看糯米如何把这个家管理的井井有条...");
        videoBeanTen.setVideoUrl("http://f.video.weibocdn.com/0013p9yZgx07JrsTN85901041200ew480E010.mp4?label=mp4_720p&template=720x1280.24.0&trans_finger=c3f00996be5378650057cf237d7bfffd&ori=0&ps=1A1eh1m4ElLYfp&Expires=1612424083&ssig=FqxdFpYBkH&KID=unistore,video");
        videoBeanTen.setDistance(8.4f);
        videoBeanTen.setFocused(false);
        videoBeanTen.setLiked(false);
        videoBeanTen.setLikeCount(1708324);
        videoBeanTen.setCommentCount(8372);
        videoBeanTen.setShareCount(982);
        videoBeanTen.setVideoOritation(1);

        DouyinVideoBean.UserBean userBeanTen = new DouyinVideoBean.UserBean();
        userBeanTen.setUid(8);
        userBeanTen.setHeadUrl("http://img.kaiyanapp.com/349cbd33cdf71fc74d5e9c7a00b444fd.jpeg?imageMogr2/quality/60/format/jpg");
        userBeanTen.setNickName("您以顶会笑喷");
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
