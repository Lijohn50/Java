package com.example.demo.usercontroller;

import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.HelloApplication;
import com.example.demo.userModel.Library;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserLibraryController implements Initializable {
    @FXML
    private ImageView gameImage;

    @FXML
    private TableView<Library> gameTable;

    @FXML
    private TableColumn<Library, String> genreCol;

    @FXML
    private TableColumn<Library, String> platformCol;

    @FXML
    private TableColumn<Library, String> titleCol;

    @FXML
    private TableColumn<Library, String> dateCol;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField searchField;

    @FXML
    private Label titleLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label platformLabel;

    private String deleteUser;

    private ObservableList<Library> libraryList;

    @FXML
    void accountInfoEvent(ActionEvent event) {
        HelloApplication.changeScene("useraccountinfo");
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
    void dashboarEvent(ActionEvent event) {
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
    void removeGameEvent(ActionEvent event) {
        if(gameTable.getSelectionModel().getSelectedItem() != null)
        {
            Library selectedGame = gameTable.getSelectionModel().getSelectedItem();
            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                String query = "DELETE FROM selectedgames WHERE customer_username = ? and title = ? ";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, usernameLabel.getText());
                preparedStatement.setString(2, selectedGame.getTitle());
                preparedStatement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation!");
                alert.setHeaderText("Selected Game Has Been Removed!");
                alert.showAndWait();

                titleLabel.setText("--");
                genreLabel.setText("--");
                platformLabel.setText("--");
                typeLabel.setText("--");
                dateLabel.setText("--");
                descriptionLabel.setText("--");
                gameImage.setImage(null);
            }
            catch(SQLException e)
            {
                System.out.println("failed to delete game");
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Select a game from the table");
            alert.showAndWait();
        }
        defalutLibrary();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        userNameShow();
        defalutLibrary();
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
            {
                try
                {
                    Connection connection = ConnectionSingleton.getConnection();
                    String queryLibrary = "SELECT * FROM selectedgames WHERE customer_username = '" +  usernameLabel.getText() + "';";
                    ResultSet resultSetLibrary = connection.createStatement().executeQuery(queryLibrary);
                    ObservableList<Library> libraryList2 = FXCollections.observableArrayList();
                    gameTable.setItems(null);
                    while(resultSetLibrary.next())
                    {
                        if(searchField.getText().equalsIgnoreCase(resultSetLibrary.getString("title")) || searchField.getText().equalsIgnoreCase(resultSetLibrary.getString("purchase_date")) || searchField.getText().equalsIgnoreCase(resultSetLibrary.getString("genre")) || searchField.getText().equalsIgnoreCase(resultSetLibrary.getString("platform")))
                        {
                            Library library2 = new Library(resultSetLibrary.getString("title"), resultSetLibrary.getString("genre"), resultSetLibrary.getString("platform"), resultSetLibrary.getString("purchase_date"), resultSetLibrary.getString("image"));
                            libraryList2.add(library2);
                            gameTable.setItems(libraryList2);
                            titleCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
                            genreCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenre()));
                            platformCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlatform()));
                            dateCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPurchaseDate()));
                        }
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
                defalutLibrary();
            }
        });
    }

    public void defalutLibrary()
    {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "Select * FROM selectedgames WHERE customer_username = '" + usernameLabel.getText() + "';";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            libraryList = FXCollections.observableArrayList();
            while(resultSet.next())
            {
                Library library = new Library(resultSet.getString("title"), resultSet.getString("genre"), resultSet.getString("platform"), resultSet.getString("purchase_date"), resultSet.getString("image"));
                libraryList.add(library);
                titleCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
                genreCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenre()));
                platformCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlatform()));
                dateCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPurchaseDate()));
                gameTable.setItems(libraryList);
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
    void showDetails(MouseEvent event) {

        Library selectedGame = gameTable.getSelectionModel().getSelectedItem();
        if(selectedGame != null)
        {
            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                String query = "SELECT * FROM gamelist WHERE title = '" + selectedGame.getTitle() + "';";
                ResultSet resultSet = connection.createStatement().executeQuery(query);
                while(resultSet.next())
                {
                    genreLabel.setText(resultSet.getString("genre"));
                    titleLabel.setText(resultSet.getString("title"));
                    typeLabel.setText(resultSet.getString("type"));
                    dateLabel.setText(resultSet.getString("release_date"));
                    descriptionLabel.setText(resultSet.getString("description"));
                    gameImage.setImage(new Image(resultSet.getString("image")));
                    platformLabel.setText(resultSet.getString("platform"));
                }
            }
            catch(SQLException ex)
            {
                System.out.println("failed to connect to database");
                ex.printStackTrace();
            }
        }
    }
}
