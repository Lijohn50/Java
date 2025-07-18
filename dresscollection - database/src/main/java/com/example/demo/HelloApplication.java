package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dress1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        this.stage = stage;
        stage.setTitle("Dress collection program");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String fxml)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setScene(scene);
        }
        catch(IOException ex)
        {
            System.out.println("failed to change scene");
            ex.printStackTrace();
        }

    }
}