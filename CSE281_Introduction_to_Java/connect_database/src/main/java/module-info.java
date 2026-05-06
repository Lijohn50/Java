module com.example.connect_database {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.connect_database to javafx.fxml;
    exports com.example.connect_database;
}