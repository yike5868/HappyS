package com.zlin.happys.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Classgrade {
  @Property
  private String classGradeName;
  @Id
  private String classGradeId;
  @Property
  private String ename;
  @Property
  private String etype;
  @Property
  private int version;
  @Property
  private String versionname;
  @Property
  private String versionType;
  @Property
  private int orderId;


  @Generated(hash = 288764015)
  public Classgrade(String classGradeName, String classGradeId, String ename,
          String etype, int version, String versionname, String versionType,
          int orderId) {
      this.classGradeName = classGradeName;
      this.classGradeId = classGradeId;
      this.ename = ename;
      this.etype = etype;
      this.version = version;
      this.versionname = versionname;
      this.versionType = versionType;
      this.orderId = orderId;
  }

  @Generated(hash = 1925341873)
  public Classgrade() {
  }


  public String getClassGradeName() {
    return classGradeName;
  }

  public void setClassGradeName(String classGradeName) {
    this.classGradeName = classGradeName;
  }


  public String getClassGradeId() {
    return classGradeId;
  }

  public void setClassGradeId(String classGradeId) {
    this.classGradeId = classGradeId;
  }


  public String getEname() {
    return ename;
  }

  public void setEname(String ename) {
    this.ename = ename;
  }


  public String getEtype() {
    return etype;
  }

  public void setEtype(String etype) {
    this.etype = etype;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getVersionname() {
    return versionname;
  }

  public void setVersionname(String versionname) {
    this.versionname = versionname;
  }


  public String getVersionType() {
    return versionType;
  }

  public void setVersionType(String versionType) {
    this.versionType = versionType;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
}
