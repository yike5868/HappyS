package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Grade {
  @Id
  private String gradeId;
  @Property
  private String gradeName;
  @Property
  private int version;
  @Property
  private String schoolId;


  @Generated(hash = 243439227)
  public Grade(String gradeId, String gradeName, int version, String schoolId) {
      this.gradeId = gradeId;
      this.gradeName = gradeName;
      this.version = version;
      this.schoolId = schoolId;
  }

  @Generated(hash = 2042976393)
  public Grade() {
  }


  public String getGradeId() {
    return gradeId;
  }

  public void setGradeId(String gradeId) {
    this.gradeId = gradeId;
  }


  public String getGradeName() {
    return gradeName;
  }

  public void setGradeName(String gradeName) {
    this.gradeName = gradeName;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getSchoolId() {
    return schoolId;
  }

  public void setSchoolId(String schoolId) {
    this.schoolId = schoolId;
  }

}
