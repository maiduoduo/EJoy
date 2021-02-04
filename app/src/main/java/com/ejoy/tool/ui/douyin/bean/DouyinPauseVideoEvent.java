package com.ejoy.tool.ui.douyin.bean;

public class DouyinPauseVideoEvent {
    private boolean playOrPause;

    public DouyinPauseVideoEvent(boolean playOrPause) {
        this.playOrPause = playOrPause;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
