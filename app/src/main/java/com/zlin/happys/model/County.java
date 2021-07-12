package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class County {
  @Id
  private String countyId;
  @Property
  private String countyName;
  @Property
  private String cityId;
  @Property
  private int version;


  @Generated(hash = 1688721586)
  public County(String countyId, String countyName, String cityId, int version) {
      this.countyId = countyId;
      this.countyName = countyName;
      this.cityId = cityId;
      this.version = version;
  }

  @Generated(hash = 1991272252)
  public County() {
  }


  public String getCountyId() {
    return countyId;
  }

  public void setCountyId(String countyId) {
    this.countyId = countyId;
  }


  public String getCountyName() {
    return countyName;
  }

  public void setCountyName(String countyName) {
    this.countyName = countyName;
  }


  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
