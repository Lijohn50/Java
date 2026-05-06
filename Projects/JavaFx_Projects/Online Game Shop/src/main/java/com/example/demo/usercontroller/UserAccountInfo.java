package com.example.demo.usercontroller;

import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserAccountInfo implements Initializable {

    @FXML
    private Label usernameLabel;

    private String deleteUser;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField mobileField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField oldPassword;

    @FXML
    void checkPasswordEvent(ActionEvent event) {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "select * from userinfo where username = '" + usernameLabel.getText() + "'";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while(resultSet.next())
            {
                if(resultSet.getString("password").equals(oldPassword.getText()))
                {
                    newPassword.setDisable(false);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Password Mismatch!");
                    alert.setContentText("Password Wrong, Provide a correct password");
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameShow();
        newPassword.setDisable(true);
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "select * from userinfo where username = '" + usernameLabel.getText() + "'";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while(resultSet.next())
            {
                nameField.setText(resultSet.getString("username"));
                emailField.setText(resultSet.getString("email"));
                mobileField.setText(resultSet.getString("mobile"));
                addressField.setText(resultSet.getString("adress"));
            }
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }
    }

    @FXML
    void buygamesEvent(ActionEvent event) {
        HelloApplication.changeScene("buygames");
    }

    @FXML
    void cartEvent(ActionEvent event) {
        HelloApplication.changeScene("usercart");
    }

    @FXML
    void dashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("userdashboard");
    }

    @FXML
    void libraryEvent(ActionEvent event) {
        HelloApplication.changeScene("userlibrary");
    }

    @FXML
    void logoutEvent(ActionEvent event) {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String queryDelete = "DELETE FROM currentuser WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setString(1, deleteUser);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("failed to delete user");
            e.printStackTrace();
        }
        HelloApplication.changeScene("userlogin");
    }

    public void userNameShow()
    {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM currentuser;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while(resultSet.next())
            {
                deleteUser = resultSet.getString("username");
                usernameLabel.setText(resultSet.getString("username"));
            }

        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }
    }

    @FXML
    void updateEvent(ActionEvent event) {
        if(!newPassword.getText().isEmpty())
        {
            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                String query = "UPDATE userinfo SET username = ?, email = ?, password = ?, mobile = ?, adress = ?, gender = ?  WHERE username = ? ;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nameField.getText());
                preparedStatement.setString(2, emailField.getText());
                preparedStatement.setString(3, newPassword.getText());
                preparedStatement.setString(4, mobileField.getText());
                preparedStatement.setString(5, addressField.getText());
                RadioButton radioButton =  (RadioButton) gender.getSelectedToggle();
                preparedStatement.setString(6, radioButton.getText());
                preparedStatement.setString(7, usernameLabel.getText());
                preparedStatement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation");
                alert.setContentText("Updated Successfully!");
                alert.showAndWait();
            }
            catch(SQLException e)
            {
                System.out.println("failed to connect to database");
                e.printStackTrace();
            }
        }
        else if(gender.getSelectedToggle() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("Please select gender");
            alert.showAndWait();
        }
        else
        {
            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                String query = "UPDATE userinfo SET username = ?, email = ?, mobile = ?, adress = ?, gender = ?  WHERE username = ? ;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nameField.getText());
                preparedStatement.setString(2, emailField.getText());
                preparedStatement.setString(3, mobileField.getText());
                preparedStatement.setString(4, addressField.getText());
                RadioButton radioButton =  (RadioButton) gender.getSelectedToggle();
                preparedStatement.setString(5, radioButton.getText());
                preparedStatement.setString(6, usernameLabel.getText());
                preparedStatement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information");
                alert.setHeaderText("Info Update!");
                alert.setContentText("Updated Successfully!");
                alert.showAndWait();
            }
            catch(SQLException e)
            {
                System.out.println("failed to connect to database");
                e.printStackTrace();
            }
        }
    }

}
