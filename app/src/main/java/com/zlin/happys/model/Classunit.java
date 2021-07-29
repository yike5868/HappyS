package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

@Entity
public class Classunit {
  @Id
  private String unitId;
  @Property
  private String unitName;
  @Property
  private String classId;
  @Property
  private String versionType;
  @Property
  private String classGradeId;
  @Property
  private String etype;
  @Property
  private int orderId;
  @Transient
  private List<Classlesson> classlessonList;


  @Generated(hash = 1180881130)
  public Classunit(String unitId, String unitName, String classId,
          String versionType, String classGradeId, String etype, int orderId) {
      this.unitId = unitId;
      this.unitName = unitName;
      this.classId = classId;
      this.versionType = versionType;
      this.classGradeId = classGradeId;
      this.etype = etype;
      this.orderId = orderId;
  }

  @Generated(hash = 973449164)
  public Classunit() {
  }


  public String getUnitId() {
    return unitId;
  }

  public void setUnitId(String unitId) {
    this.unitId = unitId;
  }


  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }


  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }


  public String getVersionType() {
    return versionType;
  }

  public void setVersionType(String versionType) {
    this.versionType = versionType;
  }


  public String getClassGradeId() {
    return classGradeId;
  }

  public void setClassGradeId(String classGradeId) {
    this.classGradeId = classGradeId;
  }


  public String getEtype() {
    return etype;
  }

  public void setEtype(String etype) {
    this.etype = etype;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public List<Classlesson> getClasslessonList() {
    return classlessonList;
  }

  public void setClasslessonList(List<Classlesson> classlessonList) {
    this.classlessonList = classlessonList;
  }
}
