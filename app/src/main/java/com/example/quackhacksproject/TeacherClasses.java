package com.example.quackhacksproject;

public class TeacherClasses {
    public String className;
    public int classCount;
    public TeacherClasses(){};

    public TeacherClasses(String className,int classCount){
        this.className = className;
        this.classCount = classCount;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
