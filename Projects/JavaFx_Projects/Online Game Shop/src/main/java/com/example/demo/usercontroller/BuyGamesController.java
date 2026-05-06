package com.example.demo.usercontroller;

import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BuyGamesController implements Initializable {


    @FXML
    private GridPane gameContainer;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField searchField;

    String deleteUser;

    @FXML
    void dashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("userdashboard");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        userNameShow();
        defaultGameList();
            searchField.setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ENTER)
                {
                        try
                        {
                            Connection connection = ConnectionSingleton.getConnection();
                            String query = "SELECT * FROM gamelist;";
                            ResultSet resultset = connection.createStatement().executeQuery(query);
                            List<Games> gamelist = new ArrayList<>();
                            while(resultset.next())
                            {
                                if(resultset.getString("title").equalsIgnoreCase(searchField.getText()) || resultset.getString("genre").equalsIgnoreCase(searchField.getText()) || resultset.getString("platform").equalsIgnoreCase(searchField.getText()))
                                {
                                    Games games = new Games(resultset.getString("title"), resultset.getString("genre"), resultset.getString("platform"), resultset.getDouble("price"), resultset.getString("image"));
                                    gamelist.add(games);
                                }
                            }
                            int column = 0;
                            int row = 1;
                            gameContainer.getChildren().clear();
                            for(Games games : gamelist)
                            {
                                try
                                {
                                    FXMLLoader fxmlLoader = new FXMLLoader(BuyGamesController.class.getResource("games.fxml"));
                                    VBox cardBox = fxmlLoader.load();
                                    GamesController gamesController = fxmlLoader.getController();
                                    gamesController.setGameData(games);

                                    if(column == 5)
                                    {
                                        column = 0;
                                        row++;
                                    }
                                    gameContainer.add(cardBox, column++, row);
                                    GridPane.setMargin(cardBox, new Insets(10));
                                }
                                catch(IOException ex)
                                {
                                    System.out.println("failed to load data");
                                    ex.printStackTrace();
                                }
                            }
                        }
                        catch(SQLException ex)
                        {
                            System.out.println("failed to connect to database");
                            ex.printStackTrace();
                        }
                }
                else
                {
                    defaultGameList();
                }

            });
    }

    @FXML
    void searchEvent(ActionEvent event) {

    }

    public void defaultGameList()
    {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM gamelist";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            int column = 0;
            int row = 1;
            while(resultSet.next())
            {
                try
                {
                    Games games = new Games(resultSet.getString("title"), resultSet.getString("genre"), resultSet.getString("platform"), Double.parseDouble(resultSet.getString("price")), resultSet.getString("image"));
                    FXMLLoader fxmlLoader = new FXMLLoader(BuyGamesController.class.getResource("games.fxml"));
                    VBox cardBox = fxmlLoader.load();
                    GamesController gamesController = fxmlLoader.getController();
                    gamesController.setGameData(games);

                    if(column == 5)
                    {
                        column = 0;
                        row++;
                    }

                    gameContainer.add(cardBox, column++, row);
                    GridPane.setMargin(cardBox, new Insets(15));
                }
                catch(IOException ex)
                {
                    System.out.println("failed to load data");
                    ex.printStackTrace();
                }

            }
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }
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
    void libraryEvent3(ActionEvent event) {
        HelloApplication.changeScene("userlibrary");
    }

    @FXML
    void accouninfoEvent(ActionEvent event) {
        HelloApplication.changeScene("useraccountinfo");
    }
}
