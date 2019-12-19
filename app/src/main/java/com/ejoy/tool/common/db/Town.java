/*
 * Copyright (c) 2017. CMRI PRIVATE LIMITED. All rights reserved
 * Created by WangBo on 17-6-23 上午11:48
 *
 * Last modified 17-6-23 上午11:48
 */

package com.ejoy.tool.common.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Entity mapped to table "NOTE".
 */
@Entity(
        nameInDb = "town",
        createInDb = false
)
public class Town {

    @Id
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "name")
    private String name;
    @Property(nameInDb = "code")
    private int code;
@Generated(hash = 62109782)
public Town(Long id, String name, int code) {
    this.id = id;
    this.name = name;
    this.code = code;
}
@Generated(hash = 2030923556)
public Town() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getName() {
    return this.name;
}
public void setName(String name) {
    this.name = name;
}
public int getCode() {
    return this.code;
}
public void setCode(int code) {
    this.code = code;
}


}
