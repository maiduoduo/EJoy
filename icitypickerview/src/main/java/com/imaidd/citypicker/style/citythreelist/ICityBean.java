package com.imaidd.citypicker.style.citythreelist;

import android.os.Parcel;
import android.os.Parcelable;

public class ICityBean implements Parcelable {
    private String id; /*110101*/
    
    private String name; /*东城区*/
    
    public String getId() {
        return id == null ? "" : id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name == null ? "" : name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }
    
    public ICityBean() {
    }
    
    protected ICityBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }
    
    public static final Parcelable.Creator<ICityBean> CREATOR = new Parcelable.Creator<ICityBean>() {
        @Override
        public ICityBean createFromParcel(Parcel source) {
            return new ICityBean(source);
        }
        
        @Override
        public ICityBean[] newArray(int size) {
            return new ICityBean[size];
        }
    };
}
