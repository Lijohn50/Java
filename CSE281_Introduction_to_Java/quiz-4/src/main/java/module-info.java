module com.example.quiz4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.quiz4 to javafx.fxml;
    exports com.example.quiz4;
}