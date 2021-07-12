package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Student {
    /**
     * id
     */
    @Id
    private String sId;
    /**
     * 学生登录名
     */
    @Property
    private String userName;
    /**
     * 密码
     */
    @Property
    private String password;
    /**
     * 学生姓名
     */
    @Property
    private String sName;

    /**
     * 学生性别
     */
    @Property
    private String sSex;
    /**
     * 学生身份证号
     */
    @Property
    private String sIdCard;
    /**
     * 学生入学时间
     */
    @Property
    private Date sRXSJ;
    /**
     * 学生年级
     */
    @Property
    private String sGrade;

    @Generated(hash = 1735080471)
    public Student(String sId, String userName, String password, String sName,
            String sSex, String sIdCard, Date sRXSJ, String sGrade) {
        this.sId = sId;
        this.userName = userName;
        this.password = password;
        this.sName = sName;
        this.sSex = sSex;
        this.sIdCard = sIdCard;
        this.sRXSJ = sRXSJ;
        this.sGrade = sGrade;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

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

    public String getSId() {
        return this.sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

    public String getSName() {
        return this.sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSSex() {
        return this.sSex;
    }

    public void setSSex(String sSex) {
        this.sSex = sSex;
    }

    public String getSIdCard() {
        return this.sIdCard;
    }

    public void setSIdCard(String sIdCard) {
        this.sIdCard = sIdCard;
    }

    public Date getSRXSJ() {
        return this.sRXSJ;
    }

    public void setSRXSJ(Date sRXSJ) {
        this.sRXSJ = sRXSJ;
    }

    public String getSGrade() {
        return this.sGrade;
    }

    public void setSGrade(String sGrade) {
        this.sGrade = sGrade;
    }
}
