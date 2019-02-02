package com.gdd.tolittlepart.entity;

import android.support.annotation.Nullable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class StudentBean {
    @Id
    @Index
    String _id;

    @Nullable
    String name;

    @Generated(hash = 302447737)
    public StudentBean(String _id, String name) {
        this._id = _id;
        this.name = name;
    }

    @Generated(hash = 2097171990)
    public StudentBean() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }
}
