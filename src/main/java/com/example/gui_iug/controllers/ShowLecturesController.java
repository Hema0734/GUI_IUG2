package com.example.gui_iug.controllers;

import com.example.gui_iug.Main;
import com.example.gui_iug.Navigation;
import com.example.gui_iug.models.Course;
import com.example.gui_iug.models.DataModel;
import com.example.gui_iug.models.Lecture;
import com.example.gui_iug.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ShowLecturesController implements Initializable {
    @FXML
    private Button backBt;
    @FXML
    private Label attendenceRatioLb;
    @FXML
    private Button searchBt;
    @FXML
    private Button deleteStudentBt;
    @FXML
    private Button addLectureBt;
    @FXML
    private Button addStudentBt;
    @FXML
    private ComboBox<String> cbo1;
    @FXML
    private TextField hallTf;
    @FXML
    private ListView<String> lv1;
    @FXML
    private ListView<String> lv2;
    @FXML
    private TextField noTf;
    @FXML
    private TextField studentTf;
    private Course cboCourse;
    @FXML
    private AnchorPane rootPane;
    private Lecture currentLecture;
    private int currentIndex1;
    private Integer currentIndex2 ;
    private Navigation navigation = new Navigation();
    private DataModel dataModel=new DataModel();
    private int count[] = new int[dataModel.getCoursess().size()];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbo1.setItems(FXCollections.observableArrayList(dataModel.getCoursesNmsNos()));
        lv1.getSelectionModel().selectedItemProperty().addListener(observableValue1 -> {
            //تسطير
            currentIndex1 =lv1.getSelectionModel().getSelectedIndex();
            currentLecture =  cboCourse.getLecturesArr().get(currentIndex1);
            //تطوير خارجي داخلي
            noTf.setText("lecture_"+currentLecture.getLectureNO());
            hallTf.setText(currentLecture.getHall());
            attendenceRatioLb.setText("Lecture attendance/ratio "+(currentLecture.getStudentsArr().size())/(1.0*cboCourse.getStudentsArr().size())*100+"%");
            lv2.setItems(FXCollections.observableArrayList(currentLecture.getStudentsNmaNos()));
            lv2.getSelectionModel().selectedItemProperty().addListener(observableValue2 -> {
                currentIndex2=lv2.getSelectionModel().getSelectedIndex();
                deleteStudentBt.setOnAction(actionEvent -> {
                    lv2.getItems().remove((int)(currentIndex2));
                    //تسطير
                    Student studentsAttended = cboCourse.getLecturesArr().get(currentIndex1).getStudentsArr().get(currentIndex2);
                    //تطوير
                    String code = studentsAttended.getLecturesCode();
                    int lectureNo = currentLecture.getLectureNO();
                    studentsAttended.setLecturesCode(code.substring(0, lectureNo - 1) + "0" + code.substring(lectureNo));
                    //تطهير
                    currentLecture.getStudentsArr().remove((int)currentIndex2);
                    //تطوير خارجي
                    studentTf.setText(null);

                        }
                );
            });

        });
    }

    @FXML
    public void cboOnAction(ActionEvent event) {
        int i = cbo1.getSelectionModel().getSelectedIndex();
       cboCourse = dataModel.getCoursess().get(i);
        //getLecturesNmsHalls method
        ArrayList<String> lecturesOfCboCourse = new ArrayList<>();//Course.getLecturesNmsHalls(cboCourse);
        for (Lecture lecture:cboCourse.getLecturesArr())
            lecturesOfCboCourse.add("lecture_"+lecture.getLectureNO()+" |"+lecture.getHall());
        //
        lv1.setItems(null);
        lv1.setItems(FXCollections.observableArrayList(lecturesOfCboCourse));
    }
    @FXML
    void addStudent(ActionEvent event) {
        //تسطير
        String studentData = studentTf.getText();
        //تطوير
        Student studentsAttended = cboCourse.getStudentToAttendence(studentData);
        studentsAttended.setLecturesCode(studentsAttended.getLecturesCode()+'1');
        currentLecture.getStudentsArr().add(studentsAttended);
        //نطوير خارجي
        lv2.getItems().add(studentsAttended.getName()+" |"+studentsAttended.getNo());
        studentTf.setText(null);

    }

    @FXML
    void deleteStudent(ActionEvent event) {
        if(currentIndex2==null) {
            //تسطير
            String studentData = studentTf.getText();
            //تطوير
            Student studentsAttended = cboCourse.getStudentToAttendence(studentData);
            String code = studentsAttended.getLecturesCode();
            int lectureNo = currentLecture.getLectureNO();
            studentsAttended.setLecturesCode(code.substring(0, lectureNo - 1) + "0" + code.substring(lectureNo));
            currentLecture.getStudentsArr().remove(studentsAttended);
            //تطوير خارجي
            lv2.getItems().remove(studentsAttended.getName() + " |" + studentsAttended.getNo());
            studentTf.setText(null);
        }
    }
    @FXML
    void addLecture(ActionEvent event) {
        //تسطير
        int lectureNumber = lv1.getItems().size();
        ++lectureNumber;
        String hall = hallTf.getText();
        //تطوير داخلي
        currentLecture = new Lecture(cboCourse.getNo(),lectureNumber,hall);
        dataModel.getLectures().add(currentLecture);
        cboCourse.getLecturesArr().add(currentLecture);
        //تطويؤ خارجي
        lv1.getItems().add("lecture_"+lectureNumber+" |"+hall);
        studentTf.setText(null);noTf.setText(null);hallTf.setText(null);

    }
    @FXML
    void findLecture(ActionEvent event) {
        String title = noTf.getText();
        int index = Integer.parseInt(title.substring(title.length()-1));--index;
        //تسطير
        currentLecture =  cboCourse.getLecturesArr().get(index);
        //تطوير خارجي داخلي
        noTf.setText("lecture_"+currentLecture.getLectureNO());
        hallTf.setText(currentLecture.getHall());
        attendenceRatioLb.setText("Lecture attendance/ratio "+(currentLecture.getStudentsArr().size())/(1.0*cboCourse.getStudentsArr().size())*100+"%");
        lv2.setItems(FXCollections.observableArrayList(currentLecture.getStudentsNmaNos()));
        lv2.getSelectionModel().selectedItemProperty().addListener(observableValue2 -> {
            currentIndex2=lv2.getSelectionModel().getSelectedIndex();
            deleteStudentBt.setOnAction(actionEvent -> {
                        lv2.getItems().remove((int)(currentIndex2));
                        //تسطير
                        Student studentsAttended = cboCourse.getLecturesArr().get(currentIndex1).getStudentsArr().get(currentIndex2);
                        //تطوير
                        String code = studentsAttended.getLecturesCode();
                        int lectureNo = currentLecture.getLectureNO();
                        studentsAttended.setLecturesCode(code.substring(0, lectureNo - 1) + "0" + code.substring(lectureNo));
                        //تطهير
                        currentLecture.getStudentsArr().remove((int)currentIndex2);
                        //تطوير خارجي
                        studentTf.setText(null);

                    }
            );
        });

    }
    @FXML
    void backOnAction(ActionEvent event) {
        navigation.navigateTo(rootPane,navigation.START_FXML);

    }




}
