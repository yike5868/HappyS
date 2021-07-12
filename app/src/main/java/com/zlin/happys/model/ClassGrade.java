package com.zlin.happys.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 课程年级
 * 六年级上
 * 六年级下
 * 五年级上
 */
@Entity
public class ClassGrade {
    @Id
    private String classGradeId;
    @Property
    private String classGradeName;

    @Generated(hash = 1610318413)
    public ClassGrade(String classGradeId, String classGradeName) {
        this.classGradeId = classGradeId;
        this.classGradeName = classGradeName;
    }

    @Generated(hash = 736830918)
    public ClassGrade() {
    }

    public String getClassGradeId() {
        return classGradeId;
    }

    public void setClassGradeId(String classGradeId) {
        this.classGradeId = classGradeId;
    }

    public String getClassGradeName() {
        return classGradeName;
    }

    public void setClassGradeName(String classGradeName) {
        this.classGradeName = classGradeName;
    }
}
