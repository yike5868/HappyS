package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 *  老师类
 */
@Entity
public class Teacher {
    /**
     * 老师id
     */
    @Id
    private String tId;
    /**
     * 老师姓名
     */
    @Property
    private String tName;
    /**
     * 老师性别
     */
    @Property
    private String tSex;
    /**
     * 老师登录名
     */
    @Property
    private String username;
    /**
     * 老师登录密码
     */
    @Property
    private String password;
    /**
     * 老师电话
     */
    @Property
    private String phone;
    /**
     * 老师教授学科
     * 使用-，分隔
     * 教授5年级2班 语文    3年级1班 英语
     * 5-2-1 语文  ，  3-1-3 英语
     */
    @Property
    private String curriculum;
    /**
     * 班主任年级 使用,分隔
     */
    @Property
    private String headmaster;

    @Generated(hash = 925336789)
    public Teacher(String tId, String tName, String tSex, String username,
            String password, String phone, String curriculum, String headmaster) {
        this.tId = tId;
        this.tName = tName;
        this.tSex = tSex;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.curriculum = curriculum;
        this.headmaster = headmaster;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettSex() {
        return tSex;
    }

    public void settSex(String tSex) {
        this.tSex = tSex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(String headmaster) {
        this.headmaster = headmaster;
    }

    public String getTId() {
        return this.tId;
    }

    public void setTId(String tId) {
        this.tId = tId;
    }

    public String getTName() {
        return this.tName;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public String getTSex() {
        return this.tSex;
    }

    public void setTSex(String tSex) {
        this.tSex = tSex;
    }
}
