package com.example.gui_iug.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lecture {
    private String course;
    private   int lectureNO;
    private String hall;
    private ArrayList<Student> studentsArr = new ArrayList<>();
public Lecture (String course,int lectureNO,String hall){
  this.course = course;
  this.lectureNO = lectureNO;
  this.hall = hall;
 }

    public String getCourse() {
        return course;
    }

    public int getLectureNO() {
        return lectureNO;
    }

    public String getHall() {
        return hall;
    }

    public ArrayList<Student> getStudentsArr() {
        return studentsArr;
    }

    /////////////////////////////////////////////////////////////
    public void initializeStudentsArr() {
    for (Student student:DataModel.students)//الشرط الثاني الشرط الرجائي يعني الطالب في منو رجا ولا لأ
        if (student.getCourse().equals(this.course) && student.getLecturesCode().length()>=this.lectureNO
                && student.getLecturesCode().charAt(lectureNO - 1) == '1')
                    studentsArr.add(student);
    }

///////////////////////////////////////////////////////

    public void setCourse(String course) {
        this.course = course;
    }

    public void setLectureNO(int lectureNO) {
        this.lectureNO = lectureNO;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public void setStudentsArr(ArrayList<Student> studentsArr) {
        this.studentsArr = studentsArr;
    }

/////////////////////////////////////////////////////////

    @Override
    public String toString(){
        return course+","+lectureNO+","+ hall;
    }
    public ArrayList<String> getStudentsNmaNos() {
        ArrayList<String> arr = new ArrayList<>();
        for (Student student:this.studentsArr)
            arr.add(student.getName()+" |"+student.getNo());return arr;
    }


}
