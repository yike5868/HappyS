package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Classversion {
  @Id
  private String classversionId;
  @Property
  private String classversionName;
  @Property
  private String version;


  @Generated(hash = 66143776)
  public Classversion(String classversionId, String classversionName,
          String version) {
      this.classversionId = classversionId;
      this.classversionName = classversionName;
      this.version = version;
  }

  @Generated(hash = 1377905369)
  public Classversion() {
  }


  public String getClassversionId() {
    return classversionId;
  }

  public void setClassversionId(String classversionId) {
    this.classversionId = classversionId;
  }


  public String getClassversionName() {
    return classversionName;
  }

  public void setClassversionName(String classversionName) {
    this.classversionName = classversionName;
  }


  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

}
