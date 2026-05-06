module bd.edu.seu.dress_collection {
    requires javafx.controls;
    requires javafx.fxml;


    opens bd.edu.seu.dress_collection to javafx.fxml;
    exports bd.edu.seu.dress_collection;
}