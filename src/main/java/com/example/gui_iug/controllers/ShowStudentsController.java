package com.example.gui_iug.controllers;

import com.example.gui_iug.Navigation;
import com.example.gui_iug.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ShowStudentsController implements Initializable {
    @FXML
    private Button backBt;
    @FXML
    private ComboBox<String> cbo1;
    @FXML
    private ListView<String> lv1;
    @FXML
    private Button addStudentBt;
    @FXML
    private Button deleteBt;
    @FXML
    private TextField fullNameTf;
    @FXML
    private TextField NoTf;
    @FXML
    private TextField phoneTf;
    @FXML
    private TextField addressTf;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField imageTf;
    @FXML
    private RadioButton maleRb;
    @FXML
    private RadioButton femaleRb;
    @FXML
    private ToggleGroup maleFemaleTg;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button addBt;
    @FXML
    private ListView<String> lv2;
    private Course cboCourse;//اللواقط
    private Student currentStudent;//اللواقط
    private int currentIndex;
    private Navigation navigation = new Navigation();
    private DataModel dataModel = new DataModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //radio buttons
        maleFemaleTg = new ToggleGroup();maleRb.setToggleGroup(maleFemaleTg);femaleRb.setToggleGroup(maleFemaleTg);
        //combo box
        cbo1.setItems(FXCollections.observableArrayList(dataModel.getCoursesNmsNos()));
        lv1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //list view 1
        lv1.getSelectionModel().selectedItemProperty().addListener(observableValue -> {
            //تسطير
            currentIndex = lv1.getSelectionModel().getSelectedIndex();
                currentStudent =  cboCourse.getStudentsArr().get(currentIndex);
                //تطهير ولا تطوير
            imageView.setImage(new Image("file:/C:/Users/HP/IdeaProjects/GUI_IUG/target/classes/com/example/gui_iug/images/"+currentStudent.getImage()));
            fullNameTf.setText(currentStudent.getName());
                NoTf.setText(Long.toString(currentStudent.getNo()));
                phoneTf.setText(Long.toString(currentStudent.getPhone()));
                addressTf.setText(currentStudent.getAddress());
                lv2.setItems(FXCollections.observableArrayList(currentStudent.getAttendences(cboCourse)));
                maleRb.setSelected(Long.toString(currentStudent.getNo()).charAt(0)=='1'? true:false);
            femaleRb.setSelected(Long.toString(currentStudent.getNo()).charAt(0)=='2'? true:false);

        });
        NoTf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*")) {
                NoTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        phoneTf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*")) {
                phoneTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }
    public void cboOnAction(){

       int i =cbo1.getSelectionModel().getSelectedIndex();
        cboCourse = dataModel.getCoursess().get(i);//DataModel.coursesNmsNos().indexOf(cbo1.getValue()) مكافئة
        ArrayList<String> studentsOfCboCourse = cboCourse.getStudentsNmsNos();
        lv1.setItems(FXCollections.observableArrayList(studentsOfCboCourse));
        //absenseRatio method
        ArrayList<String> arr = new ArrayList<>();arr.add("student name | absence ration");
        for (Student student1:cboCourse.getStudentsArr()) {
            double Ratio = student1.getAbsenceRatio();
            if(Ratio<=0.25)arr.add(student1.getName()+" |"+(Ratio*100)+"%");
        }
        //
        lv2.setItems(FXCollections.observableArrayList(arr));
    }

    @FXML
    void addStudent(ActionEvent event) {
        //تسطير
        String name1 = fullNameTf.getText();
        long no1 = Long.parseLong(NoTf.getText());
        long phone1 = Long.parseLong(phoneTf.getText());
        String address1 = addressTf.getText();
        String image  = imageTf.getText();
        Student student1 = new Student(cboCourse.getNo(),"", name1,no1,phone1,address1,image);
        //تطوير خارجي
        fullNameTf.setText(null);NoTf.setText(null);phoneTf.setText(null);addressTf.setText(null);lv2.setItems(null);
        lv1.getItems().add(name1+" |"+no1);
        //تطوير داخلي
        dataModel.getStudents().add(student1);
        cboCourse.getStudentsArr().add(student1);
        //تطهير حصل انظر التطوير الخارجي
      }

    @FXML
    void deleteStudent(ActionEvent event) {
        //تسطير ورد فوق
        //تطوير
        dataModel.getStudents().remove(currentStudent);
        cboCourse.getStudentsArr().remove(currentStudent);
        //تطهير
        lv1.getItems().remove(currentIndex);
        fullNameTf.setText(null);NoTf.setText(null);phoneTf.setText(null);addressTf.setText(null);lv2.setItems(null);imageView.setImage(new Image("file:/C:/Users/HP/IdeaProjects/GUI_IUG/target/classes/com/example/gui_iug/images/Deleted_photo.png"));
    }
    @FXML
    void backOnAction(ActionEvent event) {
            navigation.navigateTo(rootPane,navigation.START_FXML);

    }

}
