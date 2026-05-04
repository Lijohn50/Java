package com.example.labpractice;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Book, Number> numberCol;

    @FXML
    private TextField numberField;

    @FXML
    private TableColumn<Book, Number> priceCol;

    @FXML
    private TextField priceField;

    @FXML
    private TableColumn<Book, Number> quantityCol;

    @FXML
    private TextField quantityField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Book, String> titleCol;

    @FXML
    private TextField titleField;

    @FXML
    private Button updateButton;

    private ObservableList<Book> observeBook = FXCollections.observableArrayList();;

    @FXML
    void deleteEvent(ActionEvent event) {

        Book book = new Book(Integer.parseInt(numberField.getText()), titleField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(quantityField.getText()));
        BookService bookService = new BookService();
        bookService.delete(book);
        bookTable.setItems(observeBook);
        observeBook.setAll(bookService.getBook());
    }

    @FXML
    void saveEvent(ActionEvent event) {

        Book book = new Book(Integer.parseInt(numberField.getText()), titleField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(quantityField.getText()));
        BookService bookService = new BookService();
        bookService.insert(book);
        bookTable.setItems(observeBook);
        observeBook.setAll(bookService.getBook());
    }

    @FXML
    void updateEvent(ActionEvent event) {
        Book book = new Book(Integer.parseInt(numberField.getText()), titleField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(quantityField.getText()));
        BookService bookService = new BookService();
        bookService.update(book);
        bookTable.setItems(observeBook);
        observeBook.setAll(bookService.getBook());
        saveButton.setDisable(false);
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        titleField.clear();
        priceField.clear();
        numberField.clear();
        quantityField.clear();
    }

    @FXML
    void tableEvent(MouseEvent event) {
        saveButton.setDisable(true);
        updateButton.setDisable(false);
        deleteButton.setDisable(false);
        numberField.setText(String.valueOf(bookTable.getSelectionModel().getSelectedItem().getNumber()));
        titleField.setText(bookTable.getSelectionModel().getSelectedItem().getTitle());
        priceField.setText(String.valueOf(bookTable.getSelectionModel().getSelectedItem().getPrice()));
        quantityField.setText(String.valueOf(bookTable.getSelectionModel().getSelectedItem().getQuantity()));
    }

    @FXML
    void searchEvent(KeyEvent event) {
        searchField.setOnKeyReleased(key -> {
            if(key.getCode() == KeyCode.ENTER)
            {
                search();
            }
        });
    }

    void search()
    {
        List<Book> searchBook = observeBook.stream().filter(s -> String.valueOf(s.getTitle()).toLowerCase().contains(searchField.getText().toLowerCase()) || String.valueOf(s.getPrice()).toLowerCase().contains(searchField.getText().toLowerCase()) || String.valueOf(s.getQuantity()).toLowerCase().contains(searchField.getText().toLowerCase())).toList();
        ObservableList<Book> bookList = FXCollections.observableArrayList(searchBook);
        bookTable.setItems(bookList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        BookService bookService = new BookService();
        bookTable.setItems(observeBook);
        observeBook.setAll(bookService.getBook());
        numberCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getNumber()));
        priceCol.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
        quantityCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getQuantity()));
        titleCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
        updateButton.setDisable(true);
        deleteButton.setDisable(true);

    }
}
