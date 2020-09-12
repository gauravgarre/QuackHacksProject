package com.example.quackhacksproject;

public class TeacherClasses {
    public String className;
    public TeacherClasses(){};

    public TeacherClasses(String clssName){
        className = clssName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
