module com.example.labpractice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.labpractice to javafx.fxml;
    exports com.example.labpractice;
}