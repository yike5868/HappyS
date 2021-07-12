package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Village {
  @Id
  private String villageId;
  @Property
  private String villageName;
  @Property
  private String countyId;
  @Property
  private int version;


  @Generated(hash = 1898840130)
  public Village(String villageId, String villageName, String countyId,
          int version) {
      this.villageId = villageId;
      this.villageName = villageName;
      this.countyId = countyId;
      this.version = version;
  }

  @Generated(hash = 861187015)
  public Village() {
  }


  public String getVillageId() {
    return villageId;
  }

  public void setVillageId(String villageId) {
    this.villageId = villageId;
  }


  public String getVillageName() {
    return villageName;
  }

  public void setVillageName(String villageName) {
    this.villageName = villageName;
  }


  public String getCountyId() {
    return countyId;
  }

  public void setCountyId(String countyId) {
    this.countyId = countyId;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
