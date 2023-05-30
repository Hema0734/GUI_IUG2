module com.example.gui_iug {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.gui_iug to javafx.fxml;
    exports com.example.gui_iug;
    exports com.example.gui_iug.controllers;
    opens com.example.gui_iug.controllers to javafx.fxml;
}