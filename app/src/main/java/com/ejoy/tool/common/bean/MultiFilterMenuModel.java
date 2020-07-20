package com.ejoy.tool.common.bean;

/**
 * @author DCL
 * @des 多类型筛选菜单实体
 */
public class MultiFilterMenuModel {
    private String name;
    private String date;
    private String position;
    private String description;
    private String stationposition;
    private int status;

    public MultiFilterMenuModel(String name, String date, String position, String description, String stationposition, int status) {
        this.name = name;
        this.date = date;
        this.position = position;
        this.description = description;
        this.stationposition = stationposition;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStationposition() {
        return stationposition;
    }

    public void setStationposition(String stationposition) {
        this.stationposition = stationposition;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}