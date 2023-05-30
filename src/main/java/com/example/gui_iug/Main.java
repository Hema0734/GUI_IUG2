package com.example.gui_iug;

import com.example.gui_iug.models.DataModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = new FXMLLoader(Main.class.getResource("views/start.fxml")).load();
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("The program will be closed");
            alert.setHeaderText("Do you want to save the entered data?");//الرأسية
            alert.setContentText(null);//المحتويية

            ButtonType save = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.FINISH);
            ButtonType do_not_save = new ButtonType("Don't Save", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(save, cancel, do_not_save);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == save) {
                DataModel model = new DataModel();
                model.save_Students();model.save_Courses();model.save_Lectures();model.save_tutors();
            } else if (result.get() == cancel) {
                event.consume();
            }
        });
    }


}