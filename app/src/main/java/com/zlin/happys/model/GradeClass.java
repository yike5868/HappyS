package com.zlin.happys.model;

/**
 * 班
 */
public class GradeClass {
    /**
     * 班id
     */
    private String classId;
    /**
     * 班名称
     */
    private String className;
    /**
     *  年级id
     */
    private String gradeId;
    /**
     * 班说明
     *
     */
    private String remarks;

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
