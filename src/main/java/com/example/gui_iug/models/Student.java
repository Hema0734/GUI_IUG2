package com.example.gui_iug.models;

import java.util.ArrayList;

public class Student {
    private String course,lecturesCode,name;//اسم الطالب رباعيا ب 3 سبيسس
    private long no,phone;
    private String address,image;
    public Student(String course,String lectureCode,String name,long no,long phone,String address,String image){
this.course = course;
this.lecturesCode=lectureCode;
this.name = name;
this.no = no;
this.phone = phone;
this.address = address;
this.image = image;
}

    public String getCourse() {
        return course;
    }

    public String getImage() {
        return image;
    }

    public String getLecturesCode() {
        return lecturesCode;
    }

    public String getName() {
        return name;
    }

    public long getNo() {
        return no;
    }

    public long getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
//////////////////////////////////////////////////////

    public void setCourse(String course) {
        this.course = course;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLecturesCode(String lecturesCode) {
        this.lecturesCode = lecturesCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
///////////////////////////////////////////////////////
    @Override
    public String toString(){
    return course+","+lecturesCode+","+name+","+no+","+phone+","+address;
}
    public ArrayList<String> getAttendences(Course cboCourse){
    ArrayList<String> arr = new ArrayList<>();
    String code = this.lecturesCode;
        for (int i =0;i<cboCourse.getLecturesArr().size();i++){
            arr.add("lecture_"+(i+1)+"  |  "+(code.length()>=(i+1)&&code.charAt(i)=='1'?("attended"):("didn't")));
        }
        return arr;
    }
    public double getAbsenceRatio(){
        int total=0;int yes = 0;

        for (Course course1:DataModel.courses) {

        if(course1.getNo().equals(this.course)){
            total = course1.getLecturesArr().size();
        }
        }
        for (int i = 1; i <= total; i++) {
            String code = this.getLecturesCode();
            if(code.length()>=i && code.charAt(i - 1) == '1'){++yes;
                }

        }
    return (yes/(total*1.0));}



}
