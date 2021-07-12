package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class City {
  @Id
  private String cityId;
  @Property
  private String cityName;
  @Property
  private int version;


  @Generated(hash = 977227893)
  public City(String cityId, String cityName, int version) {
      this.cityId = cityId;
      this.cityName = cityName;
      this.version = version;
  }

  @Generated(hash = 750791287)
  public City() {
  }


  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }


  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
