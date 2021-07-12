package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Educationtype {
  @Id
  private String ename;
  @Property
  private String eid;
  @Property
  private int version;


  @Generated(hash = 356285925)
  public Educationtype(String ename, String eid, int version) {
      this.ename = ename;
      this.eid = eid;
      this.version = version;
  }

  @Generated(hash = 66445635)
  public Educationtype() {
  }


  public String getEname() {
    return ename;
  }

  public void setEname(String ename) {
    this.ename = ename;
  }


  public String getEid() {
    return eid;
  }

  public void setEid(String eid) {
    this.eid = eid;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
