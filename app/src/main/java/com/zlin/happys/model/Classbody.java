package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Classbody {
  @Id
  private String bodyid;
  @Property
  private String lessonid;
  @Property
  private String title;
  @Property
  private String titlepy;
  @Property
  private String body;
  @Property
  private String bodypy;
  @Property
  private String newword;
  @Generated(hash = 68402591)
  public Classbody(String bodyid, String lessonid, String title, String titlepy,
          String body, String bodypy, String newword) {
      this.bodyid = bodyid;
      this.lessonid = lessonid;
      this.title = title;
      this.titlepy = titlepy;
      this.body = body;
      this.bodypy = bodypy;
      this.newword = newword;
  }
  @Generated(hash = 1691380399)
  public Classbody() {
  }
  public String getBodyid() {
      return this.bodyid;
  }
  public void setBodyid(String bodyid) {
      this.bodyid = bodyid;
  }
  public String getLessonid() {
      return this.lessonid;
  }
  public void setLessonid(String lessonid) {
      this.lessonid = lessonid;
  }
  public String getTitle() {
      return this.title;
  }
  public void setTitle(String title) {
      this.title = title;
  }
  public String getTitlepy() {
      return this.titlepy;
  }
  public void setTitlepy(String titlepy) {
      this.titlepy = titlepy;
  }
  public String getBody() {
      return this.body;
  }
  public void setBody(String body) {
      this.body = body;
  }
  public String getBodypy() {
      return this.bodypy;
  }
  public void setBodypy(String bodypy) {
      this.bodypy = bodypy;
  }
  public String getNewword() {
      return this.newword;
  }
  public void setNewword(String newword) {
      this.newword = newword;
  }

  

}
