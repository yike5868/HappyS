package com.zlin.happys.model;

import java.util.List;

public class ResultModel<T>  {

    private List<Classgrade> classgradeList;
    private List<Classname> classnameList;
    private List<Classlesson> classlessonList;
    private List<Classunit> classunitList;
    private List<Classbody> classbodyList;
    private List<Classexercises> classexercisesList;
    private List<Classpoints> classpointsList;

    public List<Classbody> getClassbodyList() {
        return classbodyList;
    }

    public void setClassbodyList(List<Classbody> classbodyList) {
        this.classbodyList = classbodyList;
    }

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

    public List<Classlesson> getClasslessonList() {
        return classlessonList;
    }

    public void setClasslessonList(List<Classlesson> classlessonList) {
        this.classlessonList = classlessonList;
    }

    public List<Classunit> getClassunitList() {
        return classunitList;
    }

    public void setClassunitList(List<Classunit> classunitList) {
        this.classunitList = classunitList;
    }

    public List<Classexercises> getClassexercisesList() {
        return classexercisesList;
    }

    public void setClassexercisesList(List<Classexercises> classexercisesList) {
        this.classexercisesList = classexercisesList;
    }

    public List<Classpoints> getClasspointsList() {
        return classpointsList;
    }

    public void setClasspointsList(List<Classpoints> classpointsList) {
        this.classpointsList = classpointsList;
    }
}
