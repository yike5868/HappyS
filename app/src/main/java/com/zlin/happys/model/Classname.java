package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Classname {
  @Id
  private String id;
  @Property
  private String name;
  @Property
  private String classGradeId;
  @Property
  private int version;
  @Property
  private String versionType;
  @Property
  private String etype;


  @Generated(hash = 157027638)
  public Classname(String id, String name, String classGradeId, int version,
          String versionType, String etype) {
      this.id = id;
      this.name = name;
      this.classGradeId = classGradeId;
      this.version = version;
      this.versionType = versionType;
      this.etype = etype;
  }

  @Generated(hash = 1934583420)
  public Classname() {
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getClassGradeId() {
    return classGradeId;
  }

  public void setClassGradeId(String classGradeId) {
    this.classGradeId = classGradeId;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getVersionType() {
    return versionType;
  }

  public void setVersionType(String versionType) {
    this.versionType = versionType;
  }


  public String getEtype() {
    return etype;
  }

  public void setEtype(String etype) {
    this.etype = etype;
  }

}
