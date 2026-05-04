module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.othercontroller;
    opens com.example.demo.othercontroller to javafx.fxml;
    exports com.example.demo.admincontroller;
    opens com.example.demo.admincontroller to javafx.fxml;
    exports com.example.demo.usercontroller;
    opens com.example.demo.usercontroller to javafx.fxml;
    exports com.example.demo.service;
    opens com.example.demo.service to javafx.fxml;
    exports com.example.demo.utility;
    opens com.example.demo.utility to javafx.fxml;
    exports com.example.demo.userModel;
    opens com.example.demo.userModel to javafx.fxml;
    exports com.example.demo.adminModel;
    opens com.example.demo.adminModel to javafx.fxml;
    exports com.example.demo.model;
    opens com.example.demo.model to javafx.fxml;
}