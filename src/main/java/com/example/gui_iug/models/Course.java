package com.example.gui_iug.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private String name,no,lecturer,book,hall,discription;
    private ArrayList<Lecture> lecturesArr = new ArrayList<>();
    private ArrayList<Student> studentsArr = new ArrayList<>();
    public Course(String name,String no,String lecturer,String book,String hall,String discription){
     this.name = name;
     this.no =no;
     this.lecturer  = lecturer;
     this.book = book;
     this.hall = hall;
     this.discription = discription;
    }
/////////////////////////////////////////////////////////////
    public java.lang.String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public String getNo() {
        return no;
    }

    public java.lang.String getLecturer() {
        return lecturer;
    }

    public java.lang.String getBook() {
        return book;
    }

    public java.lang.String getHall() {
        return hall;
    }

    public ArrayList<Lecture> getLecturesArr() {
        return lecturesArr;
    }

    public ArrayList<Student> getStudentsArr() {
        return studentsArr;
    }
    ///////////////////////////////////////////////////
    //initializes
    public void initializeStudentsArr() {
        for (Student student:DataModel.students)
            if (student.getCourse().equals(this.no))studentsArr.add(student);
    }
    public void initializeLecturesArr()  {
        for (Lecture lecture:DataModel.lectures)
            if (lecture.getCourse().equals(this.no))lecturesArr.add(lecture);
    }
///////////////////////////////////////////////////////

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setLecturer(java.lang.String lecturer) {
        this.lecturer = lecturer;
    }

    public void setBook(java.lang.String book) {
        this.book = book;
    }

    public void setHall(java.lang.String hall) {
        this.hall = hall;
    }

    public void setLecturesArr(ArrayList<Lecture> lecturesArr) {
        this.lecturesArr = lecturesArr;
    }

    public void setStudentsArr(ArrayList<Student> studentsArr) {
        this.studentsArr = studentsArr;
    }
    //////////////////////////////////////////////////////////////////////////////////
    public  ArrayList<String> getStudentsNmsNos(){
        ArrayList<String> arr = new ArrayList<String>();
    for (Student student:this.studentsArr)
       arr.add(student.getName()+" |"+student.getNo());return arr;
    }
    @Override
    public String toString(){
       return name+","+ no+","+ lecturer+","+book+","+hall;
    }
    public  static ArrayList<String> getLecturesNmsHalls(Course course){
        ArrayList<String> arr = new ArrayList<>();
        for (Lecture lecture:course.lecturesArr)
            arr.add("lecture_"+lecture.getLectureNO()+" |"+lecture.getHall());return arr;
    }

    ///////////////////////////////////////////////
    //اصطياد الطالب من خلال البيانة المعطاة
    public Student getStudentToAttendence(String unknown){
        int length = unknown.length();
        if(length==7 || length==10)
           return this.getPhoneStudent(unknown,length);
        if(length==9)
            return  this.getNoStudent(unknown);
        return this.getNameStudent(unknown);}
    public Student getPhoneStudent(String unknown,int length){
        if(length==10)unknown = unknown.substring(3);
        for (Student student :this.studentsArr)
            if(("0"+student.getPhone()).substring(3).equals(unknown))return student;
      return this.studentsArr.get(0);}
    public Student getNoStudent(String unknown){
        for (Student student :this.studentsArr)
            if((student.getNo()+"").equals(unknown))return student;
        return this.studentsArr.get(0);
    }
    public Student getNameStudent(String unknown){
        for (Student student :this.studentsArr)
            if(student.getName().equals(unknown))return student;
        return this.studentsArr.get(0);
    }



}
