package com.zlin.happys.model;

import java.util.Date;

public class Student {
    /**
     * id
     */
    private String sId;
    /**
     * 学生登录名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 学生姓名
     */
    private String sName;

    /**
     * 学生性别
     */
    private String sSex;
    /**
     * 学生身份证号
     */
    private String sIdCard;
    /**
     * 学生入学时间
     */
    private Date sRXSJ;
    /**
     * 学生年级
     */
    private String sGrade;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    public String getsIdCard() {
        return sIdCard;
    }

    public void setsIdCard(String sIdCard) {
        this.sIdCard = sIdCard;
    }

    public Date getsRXSJ() {
        return sRXSJ;
    }

    public void setsRXSJ(Date sRXSJ) {
        this.sRXSJ = sRXSJ;
    }

    public String getsGrade() {
        return sGrade;
    }

    public void setsGrade(String sGrade) {
        this.sGrade = sGrade;
    }
}
