module com.example.ct4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.ct4 to javafx.fxml;
    exports com.example.ct4;

    opens com.example.ct4.controllers to javafx.fxml;
    exports com.example.ct4.controllers;

    opens com.example.ct4.model to javafx.fxml;
    exports com.example.ct4.model;

    opens com.example.ct4.interfaces to javafx.fxml;
    exports com.example.ct4.interfaces;

    opens com.example.ct4.utility to javafx.fxml;
    exports com.example.ct4.utility;

    opens com.example.ct4.services to javafx.fxml;
    exports com.example.ct4.services;

}