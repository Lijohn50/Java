package com.example.demo.usercontroller;

import com.example.demo.utility.ConnectionSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class GamesController {

    @FXML
    private ImageView gameImage;

    @FXML
    private Label genreLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label platformLabel;

    @FXML
    private Label priceLabel;

    @FXML
    void addEvent(ActionEvent event) {
        try
        {
            String username = "";
            LocalDate localDate = LocalDate.now();
            Connection connection = ConnectionSingleton.getConnection();
            String query2 = "SELECT * FROM currentuser;";
            ResultSet resultSet = connection.createStatement().executeQuery(query2);
            while (resultSet.next())
            {
                username = resultSet.getString("username");
            }

            String query = "SELECT * FROM currentusercartlist;";
            ResultSet resultSet2 = connection.createStatement().executeQuery(query);
            int cnt = 0; //checks if game already exists in cart
            while (resultSet2.next())
            {
                if(resultSet2.getString("title").equals(nameLabel.getText()))
                {
                    cnt++;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Duplicate Title!!");
                    alert.setContentText(resultSet2.getString("title") + " Already exists in cart!!");
                    alert.showAndWait();
                }
            }
            if(cnt == 0)
            {
                String query3 = "INSERT INTO currentusercartlist VALUES(?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement ps2 = connection.prepareStatement(query3);
                ps2.setString(1, username);
                ps2.setString(2, nameLabel.getText());
                ps2.setString(3, genreLabel.getText());
                ps2.setString(4, platformLabel.getText());
                ps2.setString(5, localDate + "");
                ps2.setDouble(6, Double.parseDouble(priceLabel.getText()));
                ps2.setString(7, gameImage.getImage().getUrl());
                ps2.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Game Added!");
                alert.setContentText("Game added to cart successfully!");
                alert.showAndWait();
            }
        }
        catch(SQLException ex)
        {
            System.out.println("failed to add event");
            ex.printStackTrace();
        }
    }

    void setGameData(Games games)
    {
        gameImage.setImage(new Image(games.getImage()));
        genreLabel.setText(games.getGenre());
        nameLabel.setText(games.getName());
        platformLabel.setText(games.getPlatform());
        priceLabel.setText(String.format("%.2f", games.getPrice()));
    }
}
