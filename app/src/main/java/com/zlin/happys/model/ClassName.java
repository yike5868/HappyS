package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ClassName {
    @Id
    private String id;
    @Property
    private String name;
    @Property
    private String classGradeId;

    @Generated(hash = 315867757)
    public ClassName(String id, String name, String classGradeId) {
        this.id = id;
        this.name = name;
        this.classGradeId = classGradeId;
    }

    @Generated(hash = 1876824589)
    public ClassName() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassGradeId() {
        return classGradeId;
    }

    public void setClassGradeId(String classGradeId) {
        this.classGradeId = classGradeId;
    }
}
