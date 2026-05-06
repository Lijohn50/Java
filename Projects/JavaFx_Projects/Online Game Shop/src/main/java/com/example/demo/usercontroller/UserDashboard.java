package com.example.demo.usercontroller;

import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.HelloApplication;
import com.example.demo.model.RecentActivity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDashboard implements Initializable {

    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView achiveImage;

    @FXML
    private Label achiveLabel;

    @FXML
    private Label dateLabel1;

    @FXML
    private Label dateLabel2;

    @FXML
    private Label dateLabel3;

    @FXML
    private Label gameCountLabel;

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
    private ImageView image3;

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
    private Label topgnereLabel;

    @FXML
    private Label totalspentLabel;

    String deleteUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameShow();

        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM selectedgames WHERE customer_username = '" + usernameLabel.getText() + "'";
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            int gameCount = 0;
            double totalSpent = 0;
            int topgenreCount = 0;
            String topGenre = "";

            List<RecentActivity> list = new ArrayList<>();
            while(resultSet.next())
            {
                gameCount++;
                totalSpent += resultSet.getDouble("price");
                RecentActivity recentActivity = new RecentActivity(resultSet.getString("title"), resultSet.getString("genre"), resultSet.getString("purchase_date"), resultSet.getDouble("price"), resultSet.getString("image"));
                list.add(recentActivity);

                String query2 = "SELECT * FROM selectedgames WHERE customer_username = '" + usernameLabel.getText() + "'";
                ResultSet resultSet2 = connection.createStatement().executeQuery(query2);

                int cnt = 0;//for finding out top genre
                while(resultSet2.next())
                {
                    if(resultSet.getString("genre").equals(resultSet2.getString("genre")))
                    {
                        cnt++;
                    }
                }

                if(topgenreCount <= cnt)
                {
                    topgenreCount = cnt;
                    topGenre = resultSet.getString("genre");
                }
            }

            gameCountLabel.setText(gameCount + " Copies");
            totalspentLabel.setText(totalSpent + " BDT");
            if(topgenreCount == 1)
            {
                topgnereLabel.setText("None");
            }
            else
            {
                topgnereLabel.setText(topGenre);
            }

            if(list.size() >= 3)
            {
                RecentActivity recent = list.get(list.size()-1);
                nameLabel1.setText(recent.getName());
                genreLabel1.setText(recent.getGenre());
                dateLabel1.setText(recent.getDate());
                priceLabel1.setText(recent.getPrice() + " BDT");
                image1.setImage(new Image(recent.getImage()));

                RecentActivity recentActivity1 = list.get(list.size()- 2);
                nameLabel2.setText(recentActivity1.getName());
                genreLabel2.setText(recentActivity1.getGenre());
                dateLabel2.setText(recentActivity1.getDate());
                priceLabel2.setText(recentActivity1.getPrice() + " BDT");
                image2.setImage(new Image(recentActivity1.getImage()));

                RecentActivity recentActivity2 = list.get(list.size()-3);
                nameLabel3.setText(recentActivity2.getName());
                genreLabel3.setText(recentActivity2.getGenre());
                dateLabel3.setText(recentActivity2.getDate());
                priceLabel3.setText(recentActivity2.getPrice() + " BDT");
                image3.setImage(new Image(recentActivity2.getImage()));
            }
            else if(list.size() == 2)
            {
                RecentActivity recent = list.get(list.size()-1);
                nameLabel1.setText(recent.getName());
                genreLabel1.setText(recent.getGenre());
                dateLabel1.setText(recent.getDate());
                priceLabel1.setText(recent.getPrice() + " BDT");
                image1.setImage(new Image(recent.getImage()));

                RecentActivity recentActivity1 = list.get(list.size()- 2);
                nameLabel2.setText(recentActivity1.getName());
                genreLabel2.setText(recentActivity1.getGenre());
                dateLabel2.setText(recentActivity1.getDate());
                priceLabel2.setText(recentActivity1.getPrice() + " BDT");
                image2.setImage(new Image(recentActivity1.getImage()));
            }
            else if(list.size() == 1)
            {
                RecentActivity recent = list.get(list.size()-1);
                nameLabel1.setText(recent.getName());
                genreLabel1.setText(recent.getGenre());
                dateLabel1.setText(recent.getDate());
                priceLabel1.setText(recent.getPrice() + " BDT");
                image1.setImage(new Image(recent.getImage()));
            }

            if(gameCount >= 5 && gameCount < 10)
            {
                achiveLabel.setText("Rookie Buyer");
                achiveImage.setImage(new Image("file:/D:/Online%20Gameshop/src/main/resources/com/example/demo/product%20images/1.png"));
            }
            else if(gameCount >= 10 && gameCount < 15)
            {
                achiveLabel.setText("Casual Collector");
                achiveImage.setImage(new Image("file:/D:/Online%20Gameshop/src/main/resources/com/example/demo/product%20images/2.png"));
            }
            else if(gameCount >= 15 && gameCount < 20)
            {
                achiveLabel.setText("Hardcore Gamer");
                achiveImage.setImage(new Image("file:/D:/Online%20Gameshop/src/main/resources/com/example/demo/product%20images/3.png"));
            }
            else if(gameCount >= 20)
            {
                achiveLabel.setText("Ultimate Gamer");
                achiveImage.setImage(new Image("file:/D:/Online%20Gameshop/src/main/resources/com/example/demo/product%20images/4.png"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }

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

    @FXML
    void buygamesEvent(ActionEvent event) {
        HelloApplication.changeScene("buygames");
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
    void cartEvent(ActionEvent event) {
        HelloApplication.changeScene("usercart");
    }

    @FXML
    void libraryEvent(ActionEvent event) {
        HelloApplication.changeScene("userlibrary");
    }

    @FXML
    void accouninfoEvent(ActionEvent event) {
        HelloApplication.changeScene("useraccountinfo");
    }
}
