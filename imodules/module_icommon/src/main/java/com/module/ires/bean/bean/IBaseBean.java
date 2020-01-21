package com.module.ires.bean.bean;

/**
 * 父类bean
 */
public class IBaseBean {

    public String title;
    public String subTile;
    public String tipTile;
    public int ico;
    public String urlIco;

    public IBaseBean(String title, String subTile, String tipTile, String urlIco) {
        this.title = title;
        this.subTile = subTile;
        this.tipTile = tipTile;
        this.urlIco = urlIco;
    }

    public IBaseBean(String title, String subTile, int ico, String urlIco) {
        this.title = title;
        this.subTile = subTile;
        this.ico = ico;
        this.urlIco = urlIco;
    }

    public IBaseBean(String title, String subTile, String urlIco) {
        this.title = title;
        this.subTile = subTile;
        this.urlIco = urlIco;
    }

    public IBaseBean(String title, String subTile, int ico) {
        this.title = title;
        this.subTile = subTile;
        this.ico = ico;
    }

    public IBaseBean(String title, String subTile) {
        this.title = title;
        this.subTile = subTile;
    }

    public IBaseBean(String title) {
        this.title = title;
    }

    public IBaseBean(String title, int ico) {
        this.title = title;
        this.ico = ico;
    }
}
