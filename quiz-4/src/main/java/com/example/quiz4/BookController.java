package com.example.quiz4;

import com.mysql.cj.protocol.Resultset;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BookController implements Initializable {

        @FXML
        private TableColumn<Book, String> NumberColumn;

        @FXML
        private TableView<Book> bookTable;

        @FXML
        private TextField numberField;

        @FXML
        private TableColumn<Book, String> priceColumn;

        @FXML
        private TextField priceField;

        @FXML
        private TableColumn<Book, String> quantityColumn;

        @FXML
        private TextField quantityField;

        @FXML
        private TextField searchField;

        @FXML
        private TableColumn<Book, String> titleColumn;

        @FXML
        private TextField titleField;

        @FXML
        private Label successLabel;

        ObservableList<Book> bookList = FXCollections.observableArrayList();;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        NumberColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumber()));
        priceColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPrice()));
        quantityColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getQuantity()));
        titleColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
        bookTable.setItems(bookList);
    }

    public void saveEvent()
    {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO bookinfo VALUES('" + numberField.getText() + "','" + titleField.getText() + "'," + Double.parseDouble(priceField.getText()) + "," + Integer.parseInt(quantityField.getText()) + ");";
            statement.execute(query);

            bookTable.setItems(bookList);
            Book book = new Book(numberField.getText(), titleField.getText(), priceField.getText(), quantityField.getText());
            bookList.add(book);
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }
        successLabel.setText("Book saved successfully!");
    }

}
