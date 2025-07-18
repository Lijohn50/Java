package com.example.ct4.controllers;

import com.example.ct4.model.Book;
import com.example.ct4.services.BookService;
import com.example.ct4.utility.ConnectionSingleton;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> numberColumn;

    @FXML
    private TextField numberField;

    @FXML
    private TableColumn<Book, Number> priceColumn;

    @FXML
    private TextField priceField;

    @FXML
    private TableColumn<Book, Number> quantityColumn;

    @FXML
    private TextField quantityField;

    @FXML
    private Label saveLabel;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TextField titleField;

    BookService insert = new BookService();

    @FXML
    void saveEvent() {

        Book book = new Book(numberField.getText(), titleField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(quantityField.getText()));
        insert.insert(book);
        bookTable.setItems(insert.getBooks());
        saveLabel.setText("Book Saved Successfully!");

    }

    public void searchEvent()
    {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM bookinfo";
            ResultSet resultSet = statement.executeQuery(query);

            ObservableList<Book> books = FXCollections.observableArrayList();
            while(resultSet.next())
            {
                if(resultSet.getString("number").equals(searchField.getText()) || resultSet.getString("title").equals(searchField.getText()) || (resultSet.getString("price") + "").equals(searchField.getText()) || (resultSet.getString("quantity") + "").equals(searchField.getText()))
                {

                    Book book = new Book(resultSet.getString("number"), resultSet.getString("title"), Double.parseDouble(resultSet.getString("price")), Integer.parseInt(resultSet.getString("quantity")));
                    books.add(book);
                    bookTable.setItems(books);


                }
            }
            numberColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumber()));
            titleColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
            priceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
            quantityColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getQuantity()));
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bookTable.setItems(insert.getBooks());
        numberColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumber()));
        titleColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
        priceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
        quantityColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getQuantity()));
        searchField.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.ENTER))
            {
                searchEvent();
            }
        });
    }
}
