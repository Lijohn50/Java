package com.example.demo.admincontroller;

import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.adminModel.Customer;
import com.example.demo.HelloApplication;
import com.example.demo.userModel.Library;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerInfoController implements Initializable {

    @FXML
    private TextField addressField;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> customerCol;

    @FXML
    private TableColumn<Library, String> dateCol;

    @FXML
    private TextField emailField;

    @FXML
    private TableView<Library> gameTable;

    @FXML
    private TextField genderField;

    @FXML
    private TableColumn<Library, String> genreCol;

    @FXML
    private TextField mobileField;

    @FXML
    private TableColumn<Library, String> platformCol;

    @FXML
    private TableColumn<Library, String> titleCol;

    @FXML
    private TextField usernameField;

    @FXML
    void banCustomerEvent(ActionEvent event) {
        Customer customer = customerTable.getSelectionModel().getSelectedItem();
        if (customer != null)
        {
            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                String query = "DELETE FROM userinfo WHERE username = ?;";
                String query2 = "DELETE FROM selectedgames WHERE customer_username = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement.setString(1, customer.getUsername());
                preparedStatement2.setString(1, customer.getUsername());
                preparedStatement.executeUpdate();
                preparedStatement2.executeUpdate();

                ObservableList<Customer> customerList = FXCollections.observableArrayList();
                String query3 = "select * from userinfo";
                ResultSet resultSet = connection.createStatement().executeQuery(query3);
                while(resultSet.next())
                {
                    Customer newCustomer = new Customer(resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("adress"), resultSet.getString("mobile"), resultSet.getString("gender"));
                    customerList.add(newCustomer);
                    customerCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUsername()));
                    customerTable.setItems(customerList);
                }

                ObservableList<Library>  libraryList = FXCollections.observableArrayList();
                gameTable.setItems(libraryList);
            }
            catch(SQLException ex)
            {
                System.out.println("failed to connect to database");
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void addGameEvent(ActionEvent event) {
        HelloApplication.changeScene("adminaddgames");
    }

    @FXML
    void logoutEvent(ActionEvent event) {
        HelloApplication.changeScene("adminlogin");
    }

    @FXML
    void removeGameEvent(ActionEvent event) {
        HelloApplication.changeScene("adminremovegames");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "select * from userinfo";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            ObservableList<Customer> customerList = FXCollections.observableArrayList();
            while(resultSet.next())
            {
                Customer customer = new Customer(resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("adress"), resultSet.getString("mobile"), resultSet.getString("gender"));
                customerList.add(customer);
                customerCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUsername()));
                customerTable.setItems(customerList);
            }
        }
        catch(SQLException ex)
        {
            System.out.println("failed to connect to database");
            ex.printStackTrace();
        }
    }

    @FXML
    void showCustomerDetails(MouseEvent event) {
        Customer customer = customerTable.getSelectionModel().getSelectedItem();
        if(customer != null)
        {
            usernameField.setText(customer.getUsername());
            emailField.setText(customer.getEmail());
            mobileField.setText(customer.getMobile());
            genderField.setText(customer.getGender());
            addressField.setText(customer.getAddress());

            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                String query = "select * from selectedgames where customer_username = '" +  customer.getUsername() + "'";
                ResultSet resultSet = connection.createStatement().executeQuery(query);
                ObservableList<Library>  libraryList = FXCollections.observableArrayList();
                int cnt = 0; // if customer did not buy any game
                while(resultSet.next())
                {
                    cnt++;
                    Library library = new Library(resultSet.getString("title"),  resultSet.getString("genre"), resultSet.getString("platform"), resultSet.getString("purchase_date"), resultSet.getString("image"));
                    libraryList.add(library);
                    titleCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
                    platformCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlatform()));
                    dateCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPurchaseDate()));
                    genreCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenre()));
                    gameTable.setItems(libraryList);
                }
                if(cnt == 0)
                {
                    ObservableList<Library> list = FXCollections.observableArrayList();
                    gameTable.setItems(list);
                }
            }
            catch(SQLException ex)
            {
                System.out.println("failed to connect to database");
                ex.printStackTrace();
            }
        }
    }
    @FXML
    void statEvent(ActionEvent event) {
        HelloApplication.changeScene("adminstats");
    }
}
