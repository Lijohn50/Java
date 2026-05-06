package com.example.login;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;

    @FXML
    public void loginEvent()
    {
        System.out.println(emailField.getText() + " " + passwordField.getText());
    }
}
