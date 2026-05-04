package com.example.demo.admincontroller;

import com.example.demo.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class AdminLoginController {

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void welcomeEvent()
    {
        HelloApplication.changeScene("welcome");
    }

    public void loginEvent()
    {
        String name = nameField.getText();
        String password = passwordField.getText();
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamerange", "root", "1210");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM admininfo;";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                if(resultSet.getString("username").equals(name) && resultSet.getString("password").equals(password))
                {
                    HelloApplication.changeScene("admindashboard");
                }
                else if(nameField.getText().isEmpty() || passwordField.getText().isEmpty())
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Empty Fields");
                    alert.setContentText("Please fill in all fields");
                    alert.showAndWait();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Credentials");
                    alert.setContentText("Please try again");
                    alert.showAndWait();
                }
            }
        }
        catch(SQLException e)
        {
            System.err.println("failed to connect to database");
            e.printStackTrace();
        }

    }
}
