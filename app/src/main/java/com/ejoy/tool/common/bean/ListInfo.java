package com.ejoy.tool.common.bean;

public class ListInfo  {
    public String name;
    public String url;

    public ListInfo(String name, String icon) {
        this.name = name;
        this.url = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return url;
    }

    public void setIcon(String icon) {
        this.url = icon;
    }
}
