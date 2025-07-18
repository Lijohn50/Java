package com.example.demo.admincontroller;

import com.example.demo.adminModel.AddGame;
import com.example.demo.service.GameService;
import com.example.demo.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminAddGames implements Initializable {

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<String> gameGenre;

    @FXML
    private ChoiceBox<String> gameType;

    @FXML
    private ChoiceBox<String> gamePlatform;

    @FXML
    private DatePicker releaseDate;

    @FXML
    private TextArea gameDescription;

    @FXML
    private Slider price;

    @FXML
    private ChoiceBox<String> windowsVariant;

    @FXML
    private ChoiceBox<String> ramVariant;

    @FXML
    private ChoiceBox<String> cpuVariant;

    @FXML
    private ChoiceBox<String> gpuVariant;

    @FXML
    private TextField storageField;

    @FXML
    private ImageView gameImage;

    String imagePath;

    public void uploadImage()
    {
        FileChooser imageChooser = new FileChooser();
        File selectedFile = imageChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            gameImage.setImage(image);
            imagePath = "file:///" + selectedFile.getPath().replace("\\", "/").replace(" ", "%20");
        }

    }

    public void saveGameListEvent()
    {
        if(titleField.getText().isEmpty() || gameGenre.getValue().isEmpty() || gameType.getValue().isEmpty() || gamePlatform.getValue().isEmpty() || releaseDate.getValue() == null || price.getValue() == 0 || gameDescription.getText().isEmpty() || gameImage.getImage() == null || cpuVariant.getValue() == null || windowsVariant.getValue() == null || ramVariant.getValue() == null || gpuVariant.getValue() == null || storageField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Fields cannot be empty!");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
        else {
            AddGame addGame = new AddGame(titleField.getText(), gameGenre.getValue(), gameType.getValue(), gamePlatform.getValue(), releaseDate.getValue() + "", price.getValue(), gameDescription.getText(), imagePath, cpuVariant.getValue(), windowsVariant.getValue(), ramVariant.getValue(), gpuVariant.getValue(), storageField.getText());
            GameService gameService = new GameService();
            gameService.addGame(addGame);
            alertEvent();
        }
    }

    public void alertEvent()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText("Game Added To The List!");
        alert.showAndWait();
    }

    public void adminDashboardEvent()
    {
        HelloApplication.changeScene("admindashboard");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> platformList = FXCollections.observableArrayList();
        platformList.addAll("Windows", "XBOX", "PS4","Windows, XBOX and PS4");
        gamePlatform.setItems(platformList);

        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.addAll("Single Player", "Multiplayer", "Co-Op");
        gameType.setItems(typeList);

        ObservableList<String> genreList = FXCollections.observableArrayList();
        genreList.addAll("Action", "Adventure", "RPG", "Shooter", "Strategy", "Simulation", "Sports","Open World");
        gameGenre.setItems(genreList);

        ObservableList<String> windowsList = FXCollections.observableArrayList();
        windowsList.addAll("7", "10", "11");
        windowsVariant.setItems(windowsList);

        ObservableList<String> ramList = FXCollections.observableArrayList();
        ramList.addAll("4GB", "8GB", "12GB", "16GB", "32GB");
        ramVariant.setItems(ramList);

        ObservableList<String> cpuList = FXCollections.observableArrayList();
        cpuList.addAll("Intel Core i3", "Intel Core i5", "Intel Core i7", "AMD Ryzen 5", "AMD Ryzen 7");
        cpuVariant.setItems(cpuList);

        ObservableList<String> gpuList = FXCollections.observableArrayList();
        gpuList.addAll("NVIDIA GeForce GTX 1060", "NVIDIA GeForce GTX 1070", "NVIDIA GeForce GTX 1080", "NVIDIA GeForce GTX 1660Ti", "NVIDIA GeForce RTX 2060", "NVIDIA GeForce RTX 2080", "NVIDIA GeForce RTX 3090", "NVIDIA GeForce RTX 3080Ti");
        gpuVariant.setItems(gpuList);
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

    @FXML
    void statEvent(ActionEvent event) {
        HelloApplication.changeScene("adminstats");
    }
}
