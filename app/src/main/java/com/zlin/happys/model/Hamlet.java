package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Hamlet {
  @Id
  private String hamletId;
  @Property
  private String hamletName;
  @Property
  private String villageId;
  @Property
  private int version;


  @Generated(hash = 348429591)
  public Hamlet(String hamletId, String hamletName, String villageId,
          int version) {
      this.hamletId = hamletId;
      this.hamletName = hamletName;
      this.villageId = villageId;
      this.version = version;
  }

  @Generated(hash = 1152788864)
  public Hamlet() {
  }


  public String getHamletId() {
    return hamletId;
  }

  public void setHamletId(String hamletId) {
    this.hamletId = hamletId;
  }


  public String getHamletName() {
    return hamletName;
  }

  public void setHamletName(String hamletName) {
    this.hamletName = hamletName;
  }


  public String getVillageId() {
    return villageId;
  }

  public void setVillageId(String villageId) {
    this.villageId = villageId;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
