package com.example.gui_iug.controllers;

import com.example.gui_iug.Navigation;
import com.example.gui_iug.models.Course;
import com.example.gui_iug.models.DataModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowCoursesController implements Initializable {
    @FXML
    private Button addBt;
    @FXML
    private Button deleteBt;
    @FXML
    private Button backBt;
    @FXML
    private TextField bookTf;
    @FXML
    private TextArea discTa;
    @FXML
    private TextField hallTf;
    @FXML
    private TextField lecturerTf;
    @FXML
    private ListView<String> lv1;
    @FXML
    private TextField lecturesTf;
    @FXML
    private TextField studentsTf;
    @FXML
    private TextArea ta1;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private AnchorPane rootPane;
    private Navigation navigation = new Navigation();
    private DataModel dataModel=new DataModel();
    private Course currentCourse;
    private int currentIndex;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lv1.setItems(FXCollections.observableArrayList(dataModel.getCoursesNmsNos()));discTa.setWrapText(true);
        lv1.getSelectionModel().selectedItemProperty().addListener(observableValue -> {
            //تسطير
           currentIndex = lv1.getSelectionModel().getSelectedIndex();
            currentCourse = dataModel.getCoursess().get(currentIndex);
                //تطهير ولا تطوير
            studentsTf.setText(currentCourse.getStudentsArr().size()+"");
                lecturesTf.setText(currentCourse.getLecturesArr().size()+"");
                lecturerTf.setText(currentCourse.getLecturer());
                bookTf.setText(currentCourse.getBook());
                hallTf.setText(currentCourse.getHall());
                discTa.setText(currentCourse.getDiscription());
                  });
       }

    @FXML
    void addCourse(ActionEvent event) {
        //تسطير
        String name = studentsTf.getText();
        String no = lecturesTf.getText();
        String lucturer = lecturerTf.getText();
        String book =bookTf.getText();
        String hall = hallTf.getText();
        String dicription = discTa.getText();
        //
        Course course =new Course(name,no,lucturer,book,hall,dicription);
        dataModel.getCoursess().add(course);
        //
        lv1.getItems().add(course.getName()+" |"+course.getNo());
        studentsTf.setText(null);lecturesTf.setText(null);lecturerTf.setText(null);bookTf.setText(null);hallTf.setText(null);discTa.setText(null);



    }

    @FXML
    void deleteCourse(ActionEvent event) {
        //تسطير
        //تطوير
        dataModel.getCoursess().remove(currentCourse);
        dataModel.getLectures().removeAll(currentCourse.getLecturesArr());
        dataModel.getStudents().removeAll(currentCourse.getStudentsArr());
        //تطهير
        lv1.getItems().remove(currentIndex);
        studentsTf.setText(null);lecturesTf.setText(null);lecturerTf.setText(null);bookTf.setText(null);hallTf.setText(null);discTa.setText(null);

    }

    @FXML
    void backOnAction(ActionEvent event) {
        navigation.navigateTo(rootPane,navigation.START_FXML);

    }



}
