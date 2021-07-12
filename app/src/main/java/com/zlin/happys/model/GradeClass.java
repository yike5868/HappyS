package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 班
 */
@Entity
public class GradeClass {
    /**
     * 班id
     */
    @Id
    private String classId;
    /**
     * 班名称
     */
    @Property
    private String className;
    /**
     *  年级id
     */
    @Property
    private String gradeId;
    /**
     * 班说明
     *
     */
    @Property
    private String remarks;

    @Generated(hash = 721169955)
    public GradeClass(String classId, String className, String gradeId,
            String remarks) {
        this.classId = classId;
        this.className = className;
        this.gradeId = gradeId;
        this.remarks = remarks;
    }

    @Generated(hash = 531897728)
    public GradeClass() {
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
