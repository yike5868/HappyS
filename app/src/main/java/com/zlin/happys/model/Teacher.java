package com.zlin.happys.model;

/**
 *  老师类
 */
public class Teacher {
    /**
     * 老师id
     */
    private String tId;
    /**
     * 老师姓名
     */
    private String tName;
    /**
     * 老师性别
     */
    private String tSex;
    /**
     * 老师登录名
     */
    private String username;
    /**
     * 老师登录密码
     */
    private String password;
    /**
     * 老师电话
     */
    private String phone;
    /**
     * 老师教授学科
     * 使用-，分隔
     * 教授5年级2班 语文    3年级1班 英语
     * 5-2-1 语文  ，  3-1-3 英语
     */
    private String curriculum;
    /**
     * 班主任年级 使用,分隔
     */
    private String headmaster;

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
}
