module com.example.texteditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.texteditor to javafx.fxml;
    exports com.example.texteditor;
}