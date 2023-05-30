package com.example.gui_iug;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    public static boolean isAdmin = false,isTut=false;
    public final String START_FXML = "views/start.fxml"
        ,STUDENTS_FXML = "views/showStudents.fxml"
        ,COURSES_FXML = "views/showCourses.fxml"
        ,LECTURES_FXML = "views/showLectures.fxml";
    public void navigateTo(Parent rootPane, String path) {//اللوحة الجذعية
        try {

            rootPane.getScene().setRoot( FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
