package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class School {
  @Id
  private String sId;
  @Property
  private String schoolName;
  @Property
  private int version;
  @Property
  private String remark;
  @Property
  private String hamletId;


  @Generated(hash = 202866359)
  public School(String sId, String schoolName, int version, String remark,
          String hamletId) {
      this.sId = sId;
      this.schoolName = schoolName;
      this.version = version;
      this.remark = remark;
      this.hamletId = hamletId;
  }

  @Generated(hash = 1579966795)
  public School() {
  }


  public String getSId() {
    return sId;
  }

  public void setSId(String sId) {
    this.sId = sId;
  }


  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  public String getsId() {
    return sId;
  }

  public void setsId(String sId) {
    this.sId = sId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getHamletId() {
    return hamletId;
  }

  public void setHamletId(String hamletId) {
    this.hamletId = hamletId;
  }

  public int getVersion() {
      return this.version;
  }

  public void setVersion(int version) {
      this.version = version;
  }

}
