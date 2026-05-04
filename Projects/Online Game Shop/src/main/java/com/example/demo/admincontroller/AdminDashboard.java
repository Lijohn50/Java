package com.example.demo.admincontroller;

import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.HelloApplication;
import com.example.demo.model.RecentActivity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminDashboard implements Initializable {

    @FXML
    private PasswordField newPassword;

    @FXML
    private TextField newUsername;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private TextField oldUsername;

    @FXML
    private Button proceedButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label dateLabel1;

    @FXML
    private Label dateLabel2;

    @FXML
    private Label dateLabel3;

    @FXML
    private Label genreLabel1;

    @FXML
    private Label genreLabel2;

    @FXML
    private Label genreLabel3;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private Label nameLabel1;

    @FXML
    private Label nameLabel2;

    @FXML
    private Label nameLabel3;

    @FXML
    private Label priceLabel1;

    @FXML
    private Label priceLabel2;

    @FXML
    private Label priceLabel3;

    @FXML
    private Label topearningLabel;

    @FXML
    private Label totalgameLabel;

    @FXML
    private ImageView image3;

    @FXML
    private Label topgamegenreLabel;

    @FXML
    private Label toptotalLabel;


    @FXML
    void oldAdminInfoEvent(ActionEvent event) {
        if(oldUsername.getText().isEmpty() || oldPassword.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Fields");
            alert.setContentText("Please fill out all the fields");
            alert.showAndWait();
        }
        else
        {
            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                String query = "select * from admininfo";
                ResultSet resultSet = connection.createStatement().executeQuery(query);
                while(resultSet.next())
                {
                    if(oldUsername.getText().equals(resultSet.getString("username")) && oldPassword.getText().equals(resultSet.getString("password")))
                    {
                        newUsername.setDisable(false);
                        newPassword.setDisable(false);
                        saveButton.setDisable(false);
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Wrong Credentials");
                        alert.setContentText("Please provide valid username and password");
                        alert.showAndWait();
                    }
                }
            }
            catch(SQLException e)
            {
                System.out.println("failed to connect to database");
                e.printStackTrace();
            }
        }

    }

    @FXML
    void saveNewAdminInfoEvent(ActionEvent event) {

        if(newUsername.getText().isEmpty() || newPassword.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Fields");
            alert.setContentText("Please fill out all the fields");
            alert.showAndWait();
        }
        else if(newUsername.getText().equals(oldUsername.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username Already Exists");
            alert.setContentText("Provide a new username");
            alert.showAndWait();
        }
        else
        {
            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                String query = "update admininfo set username = ?, password = ? where username = ? and password = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newUsername.getText());
                preparedStatement.setString(2, newPassword.getText());
                preparedStatement.setString(3, oldUsername.getText());
                preparedStatement.setString(4, oldPassword.getText());
                preparedStatement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("New Admin Info");
                alert.setContentText("New Admin Info has been saved successfully");
                alert.showAndWait();
            }
            catch(SQLException e)
            {
                System.out.println("failed to connect to database");
                e.printStackTrace();
            }
        }

    }

    public void addGameEvent()
    {
        HelloApplication.changeScene("adminaddgames");
    }
    public void removeGameEvent()
    {
        HelloApplication.changeScene("adminremovegames");
    }

    public void logoutEvent()
    {
        HelloApplication.changeScene("adminlogin");
    }

    @FXML
    void customerInfoEvent()
    {
        HelloApplication.changeScene("customerinfo");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newUsername.setDisable(true);
        newPassword.setDisable(true);
        saveButton.setDisable(true);

        RecentActivity recentActivity;

        List<RecentActivity> list = new ArrayList<>();
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "select * from gamelist";
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            double topEarn = 0;
            int totalGames = 0;//determines total games in stock
            while(resultSet.next())
            {
                totalGames++;
                recentActivity = new RecentActivity(resultSet.getString("title"), resultSet.getString("genre"), resultSet.getString("release_date"), resultSet.getDouble("price"), resultSet.getString("image"));
                list.add(recentActivity);

                String query2 = "SELECT * FROM selectedgames WHERE title = '" +resultSet.getString("title") + "'";
                ResultSet resultSet2 = connection.createStatement().executeQuery(query2);

                double totalPrice = 0;
                while (resultSet2.next())
                {
                    totalPrice+=resultSet2.getDouble("price");
                }
                if (topEarn < totalPrice)
                {
                    topEarn = totalPrice;
                    topearningLabel.setText(resultSet.getString("title"));
                    topgamegenreLabel.setText(resultSet.getString("genre"));
                }
            }

            totalgameLabel.setText(totalGames + " Available");
            toptotalLabel.setText(topEarn + " BDT");
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }

        RecentActivity recent = list.get(list.size()-1);
        nameLabel1.setText(recent.getName());
        genreLabel1.setText(recent.getGenre());
        dateLabel1.setText(recent.getDate());
        priceLabel1.setText(String.format("%.2f", recent.getPrice()) + " BDT");
        image1.setImage(new Image(recent.getImage()));

        RecentActivity recentActivity1 = list.get(list.size()- 2);
        nameLabel2.setText(recentActivity1.getName());
        genreLabel2.setText(recentActivity1.getGenre());
        dateLabel2.setText(recentActivity1.getDate());
        priceLabel2.setText(String.format("%.2f", recentActivity1.getPrice()) + " BDT");
        image2.setImage(new Image(recentActivity1.getImage()));

        RecentActivity recentActivity2 = list.get(list.size()-3);
        nameLabel3.setText(recentActivity2.getName());
        genreLabel3.setText(recentActivity2.getGenre());
        dateLabel3.setText(recentActivity2.getDate());
        priceLabel3.setText(String.format("%.2f", recentActivity1.getPrice()) + " BDT");
        image3.setImage(new Image(recentActivity2.getImage()));
    }

    @FXML
    void statEvent(ActionEvent event) {
        HelloApplication.changeScene("adminstats");
    }
}
