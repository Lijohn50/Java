module com.example.database_connction {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.database_connction to javafx.fxml;
    exports com.example.database_connction;
}