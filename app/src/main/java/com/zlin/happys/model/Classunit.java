package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

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


  @Generated(hash = 767776824)
  public Classunit(String unitId, String unitName, String classId,
          String versionType, String classGradeId, String etype) {
      this.unitId = unitId;
      this.unitName = unitName;
      this.classId = classId;
      this.versionType = versionType;
      this.classGradeId = classGradeId;
      this.etype = etype;
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

}
