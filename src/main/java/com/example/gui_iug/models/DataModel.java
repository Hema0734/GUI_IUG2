package com.example.gui_iug.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class DataModel {
    public static ArrayList<Course> courses = null;
    public static ArrayList<Student> students=null;
    public static ArrayList<Lecture> lectures = null;
    public static ArrayList<Tutor> tutors = null;
    public ArrayList<Course> getCoursess() {
        return courses;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public ArrayList<Lecture> getLectures() {
        return lectures;
    }
    public ArrayList<Tutor> getTutors() {
        return tutors;
    }


    public DataModel() {
        initializeSt();
        initializeLec();
        initializeCor();
        initializeTut();
    }
    public static void initializeSt() {
        if (students== null) {
            try (Scanner scanner = new Scanner( new File("students.csv"))) {
                //read header line
                String line;students = new ArrayList<Student>();
                while (scanner.hasNext()) {
                    line = scanner.nextLine(); String[] strings = line.split(",");
                    students.add(new Student(strings[0],strings[1],strings[2], Long.parseLong(strings[3]),
                            Long.parseLong(strings[4]),strings[5],strings[6]));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static void initializeLec() {
        if (lectures == null) {
            try (Scanner scanner = new Scanner( new File("lectures.csv"))) {
                //read header line
                String line;lectures = new ArrayList<Lecture>();
                while (scanner.hasNext()) {
                    line = scanner.nextLine();String[] strings = line.split(",");
                    Lecture lecture1 = new Lecture(strings[0],Integer.parseInt(strings[1]),strings[2]);
                    lectures.add(lecture1);
                    lecture1.initializeStudentsArr();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static void initializeCor() {//هذه الميثود من اسمها خاصة بتعريف الارري وملئها
        if (courses == null) {
            try (Scanner scanner = new Scanner( new File("courses.csv"))) {
                //read header line
                String line;courses = new ArrayList<Course>();
                while (scanner.hasNext()) {
                    line = scanner.nextLine();String[] strings = line.split(",");
                    Course course1 = new Course(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5]);
                    course1.initializeStudentsArr();
                    course1.initializeLecturesArr();
                    courses.add(course1 );
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static void initializeTut() {//هذه الميثود من اسمها خاصة بتعريف الارري وملئها
        if (tutors == null) {
            try (Scanner scanner = new Scanner( new File("tutors.csv"))) {
                //read header line
                String line;tutors = new ArrayList<Tutor>();
                while (scanner.hasNext()) {
                    line = scanner.nextLine();String[] strings = line.split(",");
                    tutors.add(new Tutor(strings[0],strings[1],strings[2])  );
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void save_Courses() {
        try (PrintWriter pw = new PrintWriter("courses.csv")) {
            //print header line
            for (Course c : courses) {
                //print each student object as string
                pw.println(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void save_Students() {
        try (PrintWriter pw = new PrintWriter("students.csv")) {
            //print header line
            for (Student s : students) {
                //print each student object as string
                pw.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void save_Lectures() {
        try (PrintWriter pw = new PrintWriter("lectures.csv")) {
            //print header line
            for (Lecture l : lectures) {
                //print each student object as string
                pw.println(l);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void save_tutors() {
        try (PrintWriter pw = new PrintWriter("tutors.csv")) {
            //print header line
            for (Tutor t : tutors) {
                //print each student object as string
                pw.println(t);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  ArrayList<String> getCoursesNmsNos(){
        ArrayList<String> arr = new ArrayList<>();
        for (Course course:courses)
            arr.add(course.getName()+"  "+course.getNo());return arr;
        }
    public  ArrayList<String> getTutorsCrsNmsPs(){
        ArrayList<String> arr = new ArrayList<>();
        for (Tutor tutor:tutors)
            arr.add(tutor.getName()+"  |"+tutor.getPassward()+" |"+tutor.getCourse());return arr;
    }




}
