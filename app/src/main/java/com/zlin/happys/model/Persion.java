package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Persion {
  @Id
  private long id;
  @Property
  private String age;
  @Property
  private String name;
  @Property
  private String password;


  @Generated(hash = 939975325)
  public Persion(long id, String age, String name, String password) {
      this.id = id;
      this.age = age;
      this.name = name;
      this.password = password;
  }

  @Generated(hash = 274083291)
  public Persion() {
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
