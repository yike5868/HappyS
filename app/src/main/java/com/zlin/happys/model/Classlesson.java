package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Classlesson {
  @Id
  private String lessonId;
  @Property
  private String lessonName;
  @Property
  private String unitId;
  @Property
  private int lessonLev;
  @Property
  private String lessonType;
  private int isbody;
  private String versionType;
  private String classGradeId;
  private String classId;
  private String etype;


  @Generated(hash = 1128490551)
  public Classlesson(String lessonId, String lessonName, String unitId,
          int lessonLev, String lessonType, int isbody, String versionType,
          String classGradeId, String classId, String etype) {
      this.lessonId = lessonId;
      this.lessonName = lessonName;
      this.unitId = unitId;
      this.lessonLev = lessonLev;
      this.lessonType = lessonType;
      this.isbody = isbody;
      this.versionType = versionType;
      this.classGradeId = classGradeId;
      this.classId = classId;
      this.etype = etype;
  }

  @Generated(hash = 449882017)
  public Classlesson() {
  }


  public String getLessonId() {
    return lessonId;
  }

  public void setLessonId(String lessonId) {
    this.lessonId = lessonId;
  }


  public String getLessonName() {
    return lessonName;
  }

  public void setLessonName(String lessonName) {
    this.lessonName = lessonName;
  }


  public String getUnitId() {
    return unitId;
  }

  public void setUnitId(String unitId) {
    this.unitId = unitId;
  }

  public String getLessonType() {
    return lessonType;
  }

  public void setLessonType(String lessonType) {
    this.lessonType = lessonType;
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


  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }


  public String getEtype() {
    return etype;
  }

  public void setEtype(String etype) {
    this.etype = etype;
  }

  public int getLessonLev() {
    return lessonLev;
  }

  public void setLessonLev(int lessonLev) {
    this.lessonLev = lessonLev;
  }

  public int getIsbody() {
    return isbody;
  }

  public void setIsbody(int isbody) {
    this.isbody = isbody;
  }
}
