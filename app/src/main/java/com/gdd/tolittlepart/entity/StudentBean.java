package com.gdd.tolittlepart.entity;

import javax.inject.Inject;

public class StudentBean {

    @Inject
    public StudentBean(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    private String name;
    private String _id;
}
