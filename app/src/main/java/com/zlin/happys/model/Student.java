package com.zlin.happys.model;

import java.util.Date;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {
  @Id
  private String sId;
  @Property
  private String userName;
  @Property
  private String password;
  @Property
  private String sName;
  @Property
  private String sSex;
  @Property
  private String sIdCard;
  @Property
  private Date sRxsj;
  @Property
  private String sClass;
  @Property
  private String sGrade;
  @Property
  private String school;

  @Generated(hash = 1171313715)
  public Student(String sId, String userName, String password, String sName,
          String sSex, String sIdCard, Date sRxsj, String sClass, String sGrade,
          String school) {
      this.sId = sId;
      this.userName = userName;
      this.password = password;
      this.sName = sName;
      this.sSex = sSex;
      this.sIdCard = sIdCard;
      this.sRxsj = sRxsj;
      this.sClass = sClass;
      this.sGrade = sGrade;
      this.school = school;
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

  public Date getsRxsj() {
    return sRxsj;
  }

  public void setsRxsj(Date sRxsj) {
    this.sRxsj = sRxsj;
  }

  public String getsClass() {
    return sClass;
  }

  public void setsClass(String sClass) {
    this.sClass = sClass;
  }

  public String getsGrade() {
    return sGrade;
  }

  public void setsGrade(String sGrade) {
    this.sGrade = sGrade;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
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

  public Date getSRxsj() {
      return this.sRxsj;
  }

  public void setSRxsj(Date sRxsj) {
      this.sRxsj = sRxsj;
  }

  public String getSClass() {
      return this.sClass;
  }

  public void setSClass(String sClass) {
      this.sClass = sClass;
  }

  public String getSGrade() {
      return this.sGrade;
  }

  public void setSGrade(String sGrade) {
      this.sGrade = sGrade;
  }
}
