package com.zlin.happys.model;

import java.util.List;

public class ResultModel<T>  {

    private List<Classgrade> classgradeList;
    private List<Classname> classnameList;
    private List<Classlesson> classlessons;

    public List<Classgrade> getClassgradeList() {
        return classgradeList;
    }

    public void setClassgradeList(List<Classgrade> classgradeList) {
        this.classgradeList = classgradeList;
    }

    public List<Classname> getClassnameList() {
        return classnameList;
    }

    public void setClassnameList(List<Classname> classnameList) {
        this.classnameList = classnameList;
    }

    public List<Classlesson> getClasslessons() {
        return classlessons;
    }

    public void setClasslessons(List<Classlesson> classlessons) {
        this.classlessons = classlessons;
    }
}
