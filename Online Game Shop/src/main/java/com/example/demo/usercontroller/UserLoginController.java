package com.example.demo.usercontroller;

import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class UserLoginController {

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    private String username;

    @FXML
    public void welcomeEvent()
    {
        HelloApplication.changeScene("welcome");
    }
    @FXML
    public void registrationEvent()
    {
        HelloApplication.changeScene("registration");
    }

    public void userLoginEvent()
    {
        if(nameField.getText().isEmpty() || passwordField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
        }
        else
        {
            username = nameField.getText();
            String password = passwordField.getText();
            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM userinfo;";
                ResultSet resultSet = statement.executeQuery(query);
                int cnt = 0;
                while(resultSet.next())
                {
                    if(resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password))
                    {
                        cnt++;
                        try
                        {
                            String queryInsert = "INSERT INTO currentuser VALUES(?);";
                            PreparedStatement preparedStatement = connection.prepareStatement(queryInsert);
                            preparedStatement.setString(1, username);
                            preparedStatement.executeUpdate();
                        }
                        catch(SQLException e)
                        {
                            System.out.println("failed to connect to database");
                            e.printStackTrace();
                        }

                        HelloApplication.changeScene("userdashboard");
                        break;
                    }
                }
                if(cnt == 0)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid username or password");
                    alert.setContentText("Please enter correct username and password");
                    alert.showAndWait();
                }
            }
            catch(SQLException e)
            {
                System.err.println("failed to connect to database");
                e.printStackTrace();
            }
        }

    }
    public String getUsername()
    {
        return username;
    }
}
