package com.example.demo.usercontroller;

import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.HelloApplication;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UserCartController implements Initializable {

    @FXML
    private Label genreLabel;

    @FXML
    private Label gpuLabel;

    @FXML
    private Label platformLabel;

    @FXML
    private ImageView productImage;

    @FXML
    private Label ramLabel;

    @FXML
    private Label storageLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label windowsLabel;

    @FXML
    private Label cpuLabel;


    @FXML
    private TableView<Games> gameList;

    @FXML
    private TableColumn<Games, Number> priceCol;

    @FXML
    private TableColumn<Games, String> titleCol;

    @FXML
    private TableColumn<Games, Number> totalCol;

    @FXML
    private Label usernameLabel;

    private String deleteUser;

    private ObservableList<Games> gamesList =  FXCollections.observableArrayList();

    private double totalPrice = 0;
    @FXML
    void accountinfoEvent(ActionEvent event) {
        HelloApplication.changeScene("useraccountinfo");
    }

    @FXML
    void buyGamesEvent(ActionEvent event) {
        HelloApplication.changeScene("buygames");
    }

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

    @FXML
    void confirmPurchaseEvent(ActionEvent event) {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String querycheck = "SELECT * FROM currentusercartlist;";
            ResultSet resultSet = connection.createStatement().executeQuery(querycheck);
            int cnt = 0; //checks if the game exists in library
            while(resultSet.next())
            {
                String queryCheck2 = "SELECT * FROM selectedgames WHERE customer_username = '" +  resultSet.getString("customer_username") + "';";
                ResultSet resultSet2 = connection.createStatement().executeQuery(queryCheck2);
                while(resultSet2.next())
                {
                    if(resultSet2.getString("title").equals(resultSet.getString("title")))
                    {
                        cnt++;
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error!");
                        alert.setHeaderText(resultSet2.getString("title") + " Already exists in cart!!");
                        alert.setContentText("Delete " + resultSet2.getString("title") + " from the cart first!!");
                        alert.showAndWait();
                    }
                }
            }
            if(cnt == 0)
            {
                String queryInsert2 = "SELECT * FROM currentusercartlist;";
                ResultSet resultSet3 = connection.createStatement().executeQuery(queryInsert2);
                while(resultSet3.next())
                {
                    String queryInsert = "INSERT INTO selectedgames VALUES(?, ?, ?, ?, ?, ?, ?);";
                    PreparedStatement preparedStatement = connection.prepareStatement(queryInsert);
                    preparedStatement.setString(1, resultSet3.getString("customer_username"));
                    preparedStatement.setString(2, resultSet3.getString("title"));
                    preparedStatement.setString(3, resultSet3.getString("genre"));
                    preparedStatement.setString(4, resultSet3.getString("platform"));
                    preparedStatement.setString(5, resultSet3.getString("purchase_date"));
                    preparedStatement.setDouble(6, resultSet3.getDouble("price"));
                    preparedStatement.setString(7, resultSet3.getString("image"));
                    preparedStatement.executeUpdate();
                }

                String query = "DELETE FROM currentusercartlist;";
                Statement statement = connection.createStatement();
                statement.execute(query);
                gamesList.clear();

                titleLabel.setText("--");
                genreLabel.setText("--");
                platformLabel.setText("--");
                typeLabel.setText("--");
                windowsLabel.setText("--");
                cpuLabel.setText("--");
                ramLabel.setText("--");
                gpuLabel.setText("--");
                storageLabel.setText("--");
                productImage.setImage(null);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Complete!");
                alert.setContentText("Your Purchase is Complete!");
                alert.showAndWait();

            }
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }
    }

    @FXML
    void removeGameEvent(ActionEvent event) {

        if(gameList.getSelectionModel().getSelectedItem() != null)
        {
            Games selectedGame = gameList.getSelectionModel().getSelectedItem();
            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                String query = "DELETE FROM currentusercartlist WHERE title = ? and price = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, selectedGame.getName());
                preparedStatement.setDouble(2, selectedGame.getPrice());
                preparedStatement.executeUpdate();

                String query3 = "SELECT * FROM currentusercartlist;";
                ResultSet resultSet = connection.createStatement().executeQuery(query3);
                ObservableList<Games> newList = FXCollections.observableArrayList();
                double totalPrice2 = 0;
                while(resultSet.next())
                {
                    Games games = new Games(resultSet.getString("title"), resultSet.getString("genre"), resultSet.getString("platform"), resultSet.getDouble("price"), resultSet.getString("image"));
                    newList.add(games);
                    gameList.setItems(newList);
                    titleCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
                    priceCol.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
                    totalPrice2 += resultSet.getDouble("price");
                    games.setTotalPrice(totalPrice2);
                    totalCol.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getTotalPrice()));

                    titleLabel.setText("--");
                    genreLabel.setText("--");
                    platformLabel.setText("--");
                    typeLabel.setText("--");
                    windowsLabel.setText("--");
                    cpuLabel.setText("--");
                    ramLabel.setText("--");
                    gpuLabel.setText("--");
                    storageLabel.setText("--");
                    productImage.setImage(null);
                }
            }
            catch(SQLException e)
            {
                System.out.println("failed to connect to database");
                e.printStackTrace();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Game not selected");
            alert.setContentText("Please select a game to remove");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        userNameShow();
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM currentusercartlist;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while(resultSet.next())
            {
                Games games = new Games(resultSet.getString("title"), resultSet.getString("genre"), resultSet.getString("platform"), resultSet.getDouble("price"), resultSet.getString("image"));
                gamesList.add(games);
                gameList.setItems(gamesList);
                titleCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
                priceCol.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
                totalPrice += resultSet.getDouble("price");
                games.setTotalPrice(totalPrice);
                totalCol.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getTotalPrice()));
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
    void libraryEvent2(ActionEvent event) {
        HelloApplication.changeScene("userlibrary");
    }

    @FXML
    void detailsEvent() {
         Games selectedGame = gameList.getSelectionModel().getSelectedItem();
         if(selectedGame != null)
         {
             try
             {
                 Connection connection = ConnectionSingleton.getConnection();
                 String query = "SELECT * FROM gamelist WHERE title = '" +  selectedGame.getName() + "';";
                 ResultSet resultSet = connection.createStatement().executeQuery(query);
                 while(resultSet.next())
                 {
                     titleLabel.setText(resultSet.getString("title"));
                     genreLabel.setText(resultSet.getString("genre"));
                     platformLabel.setText(resultSet.getString("platform"));
                     typeLabel.setText(resultSet.getString("type"));
                     windowsLabel.setText(resultSet.getString("windows"));
                     cpuLabel.setText(resultSet.getString("cpu"));
                     ramLabel.setText(resultSet.getString("ram"));
                     gpuLabel.setText(resultSet.getString("gpu"));
                     storageLabel.setText(resultSet.getString("storage"));
                     productImage.setImage(new Image(resultSet.getString("image")));
                 }
             }
             catch(SQLException e)
             {
                 System.out.println("failed to connect to database");
                 e.printStackTrace();
             }

         }
    }
}
