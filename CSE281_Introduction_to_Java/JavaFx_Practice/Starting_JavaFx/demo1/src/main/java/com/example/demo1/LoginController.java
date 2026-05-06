package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.util.Objects;

public class LoginController {

    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordfield;

    @FXML
    public void loginEvent()
    {
        System.out.println("button clicked");
    }
}
