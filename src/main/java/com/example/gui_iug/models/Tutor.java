package com.example.gui_iug.models;

public class Tutor {
    private String course,name,passward;

    public Tutor( String course,String name, String passward) {
        this.course = course;
        this.name = name;
        this.passward = passward;
    }

    public String getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public String getPassward() {
        return passward;
    }
///////////////////////////
    public void setCourse(String course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    @Override
    public String toString() {
        return this.course+","+this.name+","+this.passward;
    }
}
