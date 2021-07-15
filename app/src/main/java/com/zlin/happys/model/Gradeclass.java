package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Gradeclass {
  @Id
  private String classId;
  @Property
  private String className;
  @Property
  private String gradeId;
  @Property
  private String remarks;
  @Property
  private int version;


  @Generated(hash = 2050682768)
  public Gradeclass(String classId, String className, String gradeId,
          String remarks, int version) {
      this.classId = classId;
      this.className = className;
      this.gradeId = gradeId;
      this.remarks = remarks;
      this.version = version;
  }

  @Generated(hash = 1732900676)
  public Gradeclass() {
  }


  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }


  public String getGradeId() {
    return gradeId;
  }

  public void setGradeId(String gradeId) {
    this.gradeId = gradeId;
  }


  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
