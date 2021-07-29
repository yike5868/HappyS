package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Classexercises {
    @Id
    private String eid;
    @Property
    private String ename;
    @Property
    private String eanswer;
    @Property
    private String esubname;
    @Property
    private String lessonId;
    @Property
    private int orderid;

    @Generated(hash = 823923993)
    public Classexercises(String eid, String ename, String eanswer, String esubname,
            String lessonId, int orderid) {
        this.eid = eid;
        this.ename = ename;
        this.eanswer = eanswer;
        this.esubname = esubname;
        this.lessonId = lessonId;
        this.orderid = orderid;
    }

    @Generated(hash = 1228404172)
    public Classexercises() {
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEanswer() {
        return eanswer;
    }

    public void setEanswer(String eanswer) {
        this.eanswer = eanswer;
    }

    public String getEsubname() {
        return esubname;
    }

    public void setEsubname(String esubname) {
        this.esubname = esubname;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
}
