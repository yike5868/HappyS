package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Classpoints {
    @Id
    private String pid;
    @Property
    private String lessonid;
    @Property
    private String pbody;

    @Generated(hash = 1425997956)
    public Classpoints(String pid, String lessonid, String pbody) {
        this.pid = pid;
        this.lessonid = lessonid;
        this.pbody = pbody;
    }

    @Generated(hash = 1678440884)
    public Classpoints() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLessonid() {
        return lessonid;
    }

    public void setLessonid(String lessonid) {
        this.lessonid = lessonid;
    }

    public String getPbody() {
        return pbody;
    }

    public void setPbody(String pbody) {
        this.pbody = pbody;
    }
}
