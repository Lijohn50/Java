package com.example.demo.othercontroller;

import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ToggleGroup genderRadio;

    @FXML
    private TextField mobileField;

    @FXML
    private TextField addressField;

    @FXML
    public void welcomeEvent()
    {
        HelloApplication.changeScene("userlogin");
    }
    @FXML
    public void saveUserInfo()
    {
        RadioButton radioButton = (RadioButton) genderRadio.getSelectedToggle();
        if(usernameField.getText().isEmpty() ||  emailField.getText().isEmpty() || passwordField.getText().isEmpty() || addressField.getText().isEmpty() || mobileField.getText().isEmpty() || !genderRadio.getSelectedToggle().isSelected())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields");
            alert.showAndWait();
        }
        else
        {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String address = addressField.getText();
            String gender = radioButton.getText();
            String mobile = mobileField.getText();

            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM userinfo;";
                ResultSet resultSet = statement.executeQuery(query);
                int cnt = 0;
                while(resultSet.next())
                {
                    if(username.equals(resultSet.getString("username")))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Username already exists");
                        alert.showAndWait();
                        cnt++;
                        break;
                    }
                    else if(email.equals(resultSet.getString("email")))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Email already exists");
                        alert.showAndWait();
                        cnt++;
                        break;
                    }
                }
                if(cnt == 0)
                {
                    String queryInsert = "INSERT INTO userinfo VALUES (?,?,?,?,?,?);";
                    PreparedStatement preparedStatement = connection.prepareStatement(queryInsert);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, password);
                    preparedStatement.setString(4, address);
                    preparedStatement.setString(5, mobile);
                    preparedStatement.setString(6, gender);
                    preparedStatement.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("SignUp Successful!");
                    alert.showAndWait();
                }
            }
            catch(SQLException ex)
            {
                System.err.println("failed to connect to database");
                ex.printStackTrace();
            }
        }

    }
}
