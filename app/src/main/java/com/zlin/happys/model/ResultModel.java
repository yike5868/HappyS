package com.zlin.happys.model;

import java.util.List;

public class ResultModel<T>  {

    private List<ClassGrade> classgradeList;
    private List<ClassName> classnameList;

    public List<ClassGrade> getClassgradeList() {
        return classgradeList;
    }

    public void setClassgradeList(List<ClassGrade> classgradeList) {
        this.classgradeList = classgradeList;
    }

    public List<ClassName> getClassnameList() {
        return classnameList;
    }

    public void setClassnameList(List<ClassName> classnameList) {
        this.classnameList = classnameList;
    }
}
