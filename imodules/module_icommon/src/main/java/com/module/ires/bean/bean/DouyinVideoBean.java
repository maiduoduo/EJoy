package com.module.ires.bean.bean;


/**
 * @ClassName:  DouyinVideoBean
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/1/26
 * @des:  抖音视频实体类
 */
public class DouyinVideoBean {
    private int videoId;
    /** 视频播放资源 */
    private int videoRes;
    /**视频网络资源**/
    private String videoUrl;
    /** 封面图片资源 */
    private int coverRes;
    /**视频封面网络资源**/
    private String coverUrl;
    /** 视频文案内容 */
    private String content;
    /** 作者 */
    private UserBean userBean;
    /** 是否已点赞 */
    private boolean isLiked;
    /** 与视频发布距离 */
    private float distance;
    /** 是否已关注 */
    private boolean isFocused;
    /** 点赞数 */
    private int likeCount;
    /** 评论数 */
    private int commentCount;
    /** 转发数 */
    private int shareCount;
    /**
     * 视频方向
     * 0：竖屏视频
     * 1：横屏视频
     */
    private int videoOritation;

    public int getVideoOritation() {
        return videoOritation;
    }

    public void setVideoOritation(int videoOritation) {
        this.videoOritation = videoOritation;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getVideoRes() {
        return videoRes;
    }

    public void setVideoRes(int videoRes) {
        this.videoRes = videoRes;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public int getCoverRes() {
        return coverRes;
    }

    public void setCoverRes(int coverRes) {
        this.coverRes = coverRes;
    }
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public boolean isFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public static class UserBean {
        private int uid;
        private String nickName;
        private int head;
        /**网络资源头像**/
        private String headUrl;
        /** 座右铭 */
        private String sign;
        /** 是否已关注 */
        private boolean isFocused;
        /** 获赞数量 */
        private int subCount;
        /** 关注数量 */
        private int focusCount;
        /** 粉丝数量 */
        private int fansCount;
        /** 作品数量 */
        private int workCount;
        /** 动态数量 */
        private int dynamicCount;
        /** 喜欢数量 */
        private int likeCount;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getNickName() {
            return nickName == null ? "" : nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getHead() {
            return head;
        }

        public void setHead(int head) {
            this.head = head;
        }
        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public String getSign() {
            return sign == null ? "" : sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public boolean isFocused() {
            return isFocused;
        }

        public void setFocused(boolean focused) {
            isFocused = focused;
        }

        public int getSubCount() {
            return subCount;
        }

        public void setSubCount(int subCount) {
            this.subCount = subCount;
        }

        public int getFocusCount() {
            return focusCount;
        }

        public void setFocusCount(int focusCount) {
            this.focusCount = focusCount;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        public int getWorkCount() {
            return workCount;
        }

        public void setWorkCount(int workCount) {
            this.workCount = workCount;
        }

        public int getDynamicCount() {
            return dynamicCount;
        }

        public void setDynamicCount(int dynamicCount) {
            this.dynamicCount = dynamicCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }
    }
}
