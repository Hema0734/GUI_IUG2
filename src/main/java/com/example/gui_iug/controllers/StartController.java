package com.example.gui_iug.controllers;

import com.example.gui_iug.Navigation;
import com.example.gui_iug.models.DataModel;
import com.example.gui_iug.models.Tutor;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    @FXML
    private Button addBt;

    @FXML
    private TextField courseTf;

    @FXML
    private Button coursesBt;

    @FXML
    private Button delBt;

    @FXML
    private Button enterBt;

    @FXML
    private ImageView imageV;

    @FXML
    private Button lecturesBt;

    @FXML
    private ListView<String> lv1;

    @FXML
    private TextField nameTf;

    @FXML
    private PasswordField passwordTf;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button studentBt;
    private int lvIndex;
    private Navigation navigation = new Navigation();
    private DataModel dataModel = new DataModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lv1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lv1.setOrientation(Orientation.VERTICAL);
        initializer();
        enterBt.setOnAction(actionEvent -> {
            setAdminOrTut();initializer();
        });
        lv1.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            //تسطير
           lvIndex =lv1.getSelectionModel().getSelectedIndex();
        });


    }
    void initializer(){if(Navigation.isAdmin)
        lv1.setItems(FXCollections.observableArrayList(dataModel.getTutorsCrsNmsPs()));
        if(Navigation.isAdmin||Navigation.isTut)
            imageV.setImage(new
                    Image("file:/C:/Users/HP/IdeaProjects/GUI_IUG/target/classes/com/example/gui_iug/images/green-check-mark-icon-circle-260nw-1033217617.jpg"));
    }
    @FXML
    void addTut(ActionEvent event) {
        if(Navigation.isAdmin){
            //تسطير
            String tutName = nameTf.getText();
            String tutPass = passwordTf.getText();
            String tutCourse = courseTf.getText();
            //تطوير
            dataModel.getTutors().add(new Tutor(tutCourse,tutName,tutPass));
            lv1.getItems().add(tutName+" |"+tutPass+" |"+tutCourse);


        }

    }

    @FXML
    void delTut(ActionEvent event) {
        if(Navigation.isAdmin){
            //تطوير
            dataModel.getTutors().remove(lvIndex);
            lv1.getItems().remove(lvIndex);


        }

    }

    @FXML
    void courses(ActionEvent event) {
        setAdminOrTut();
        if(Navigation.isAdmin||Navigation.isTut){
            navigation.navigateTo(rootPane,navigation.COURSES_FXML);

        }

    }

    @FXML
    void lectures(ActionEvent event) {
        setAdminOrTut();
        if(Navigation.isAdmin||Navigation.isTut){
        navigation.navigateTo(rootPane,navigation.LECTURES_FXML);

        }
    }

    @FXML
    void students(ActionEvent event) {
        setAdminOrTut();
        if(Navigation.isAdmin||Navigation.isTut){
            navigation.navigateTo(rootPane,navigation.STUDENTS_FXML);

        }

    }
    void setAdminOrTut(){
        String adminName = "Iam",adminPass = "amI";
        if(nameTf.getText().equals(adminName)&&passwordTf.getText().equals(adminPass)){Navigation.isAdmin=true;}
        else {
            for (Tutor tutor : dataModel.getTutors())
                if (nameTf.getText().equals(tutor.getName()) && passwordTf.getText().equals(tutor.getPassward())) {Navigation.isTut = true;}
        }

        }




}
