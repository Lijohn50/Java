package com.example.demo.othercontroller;

import com.example.demo.HelloApplication;
import javafx.fxml.FXML;

public class WelcomeController {

    @FXML
    public void adminEvent()
    {
        HelloApplication.changeScene("adminlogin");
    }
    public void userEvent()
    {
        HelloApplication.changeScene("userlogin");
    }

}
