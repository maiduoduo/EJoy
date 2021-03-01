package com.module.iviews.keyboard.safe_keyboard;


public class SafeViewPoint {
    private int coo_x;
    private int coo_y;
    private long coo_time;

    public SafeViewPoint() {
        this.coo_x = 0;
        this.coo_y = 0;
        this.coo_time = 0;
    }

    public SafeViewPoint(int coo_x, int coo_y, long coo_time) {
        this.coo_x = coo_x;
        this.coo_y = coo_y;
        this.coo_time = coo_time;
    }

    public int getCoo_x() {
        return coo_x;
    }

    public void setCoo_x(int coo_x) {
        this.coo_x = coo_x;
    }

    public int getCoo_y() {
        return coo_y;
    }

    public void setCoo_y(int coo_y) {
        this.coo_y = coo_y;
    }

    public long getCoo_time() {
        return coo_time;
    }

    public void setCoo_time(long coo_time) {
        this.coo_time = coo_time;
    }

    public void clearPoint() {
        this.coo_x = 0;
        this.coo_y = 0;
        this.coo_time = 0;
    }
}
