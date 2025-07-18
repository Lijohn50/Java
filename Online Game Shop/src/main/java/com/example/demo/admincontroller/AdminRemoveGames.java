package com.example.demo.admincontroller;

import com.example.demo.*;
import com.example.demo.adminModel.AddGame;
import com.example.demo.adminModel.Product;
import com.example.demo.service.GameService;
import com.example.demo.utility.ConnectionSingleton;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminRemoveGames implements Initializable {

    @FXML
    private TableColumn<Product, String> dateColumn;

    @FXML
    private TableColumn<Product, String> genreColumn;

    @FXML
    private TableColumn<Product, Number> priceColumn;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> titleColumn;

    @FXML
    private TableColumn<Product, String> typeColumn;

    @FXML
    private ImageView productImage;

    @FXML
    private Label titleLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label platformLabel;

    @FXML
    private Label dateLabel;


    @FXML
    private Label descriptionLabel;

    @FXML
    private Label windowsLabel;

    @FXML
    private Label cpuLabel;

    @FXML
    private Label ramLabel;

    @FXML
    private Label gpuLabel;

    @FXML
    private Label storageLabel;

    @FXML
    private TableColumn<Product, String> platformColumn;

    @FXML
    private ChoiceBox<String> cpuBox;

    @FXML
    private ChoiceBox<String> genreBox;

    @FXML
    private ChoiceBox<String> gpuBox;

    @FXML
    private ChoiceBox<String> platformBox;

    @FXML
    private ChoiceBox<String> ramBox;

    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    private ChoiceBox<String> windowsBox;

    @FXML
    private TextField priceField;

    @FXML
    private TextField storageField;

    @FXML
    private TextField titleField;

    @FXML
    private DatePicker datePick;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView updateImage;

    private String updateImagePath;


    ObservableList<Product> productList;

    public void adminDashboardEvent()
    {
        HelloApplication.changeScene("admindashboard");
    }

    public void addGameEvent()
    {
        HelloApplication.changeScene("adminaddgames");
    }
    public void logoutEvent()
    {
        HelloApplication.changeScene("adminlogin");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM gamelist;";
            ResultSet resultSet = statement.executeQuery(query);
            productList =  FXCollections.observableArrayList();
            while(resultSet.next())
            {
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String type = resultSet.getString("type");
                String platform = resultSet.getString("platform");
                String releaseDate = resultSet.getString("release_Date");
                double price = resultSet.getDouble("price");
                String imagePath = resultSet.getString("image");

                Product product = new Product(title, genre, type, platform, releaseDate, price, imagePath);
                titleColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
                genreColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenre()));
                typeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
                platformColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlatform()));
                dateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getReleaseDate()));
                priceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
                productList.add(product);
                productTable.setItems(productList);

                titleLabel.setText(title);
                genreLabel.setText(genre);
                typeLabel.setText(type);
                platformLabel.setText(platform);
                dateLabel.setText(releaseDate);
                /*priceLabel.setText(String.valueOf(price));*/
                descriptionLabel.setText(resultSet.getString("description"));
                productImage.setImage(new Image(imagePath));
                windowsLabel.setText(resultSet.getString("windows"));
                cpuLabel.setText(resultSet.getString("cpu"));
                ramLabel.setText(resultSet.getString("ram"));
                gpuLabel.setText(resultSet.getString("gpu"));
                storageLabel.setText(resultSet.getString("storage"));
            }
        }
        catch(SQLException e)
        {
            System.err.println("failed to connect to database");
            e.printStackTrace();
        }

        ObservableList<String> platformList = FXCollections.observableArrayList();
        platformList.addAll("Windows", "XBOX", "PS4","Windows, XBOX and PS4");
        platformBox.setItems(platformList);

        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.addAll("Single Player", "Multiplayer", "Co-Op");
        typeBox.setItems(typeList);

        ObservableList<String> genreList = FXCollections.observableArrayList();
        genreList.addAll("Action", "Adventure", "RPG", "Shooter", "Strategy", "Simulation", "Sports","Open World");
        genreBox.setItems(genreList);

        ObservableList<String> windowsList = FXCollections.observableArrayList();
        windowsList.addAll("7", "10", "11");
        windowsBox.setItems(windowsList);

        ObservableList<String> ramList = FXCollections.observableArrayList();
        ramList.addAll("4GB", "8GB", "12GB", "16GB", "32GB");
        ramBox.setItems(ramList);

        ObservableList<String> cpuList = FXCollections.observableArrayList();
        cpuList.addAll("Intel Core i3", "Intel Core i5", "Intel Core i7", "AMD Ryzen 5", "AMD Ryzen 7");
        cpuBox.setItems(cpuList);

        ObservableList<String> gpuList = FXCollections.observableArrayList();
        gpuList.addAll("NVIDIA GeForce GTX 1060", "NVIDIA GeForce GTX 1070", "NVIDIA GeForce GTX 1080", "NVIDIA GeForce GTX 1660Ti", "NVIDIA GeForce RTX 2060", "NVIDIA GeForce RTX 2080", "NVIDIA GeForce RTX 3090", "NVIDIA GeForce RTX 3080Ti");
        gpuBox.setItems(gpuList);


        searchField.setOnKeyPressed(e -> {
            ObservableList<Product> productList = FXCollections.observableArrayList();
            if(e.getCode().equals(KeyCode.ENTER))
            {
                try
                {
                    Connection connection = ConnectionSingleton.getConnection();
                    String query = "SELECT * FROM gamelist;";
                    ResultSet resultSet =  connection.createStatement().executeQuery(query);
                    productList.clear();
                    while(resultSet.next())
                    {
                        if(resultSet.getString("title").equalsIgnoreCase(searchField.getText()) || resultSet.getString("genre").equalsIgnoreCase(searchField.getText()) || resultSet.getString("type").equalsIgnoreCase(searchField.getText()) || resultSet.getString("platform").equalsIgnoreCase(searchField.getText()) || resultSet.getString("release_date").equalsIgnoreCase(searchField.getText()) || String.valueOf(resultSet.getDouble("price")).equalsIgnoreCase(searchField.getText()))
                        {
                            Product product = new Product(resultSet.getString("title"), resultSet.getString("genre"), resultSet.getString("type"),  resultSet.getString("platform"), resultSet.getString("release_date"), resultSet.getDouble("price"), resultSet.getString("image"));
                            productList.add(product);
                            productTable.setItems(productList);
                        }
                    }
                }
                catch(SQLException ex)
                {
                    System.out.println("failed to connect to database");
                    ex.printStackTrace();
                }

                titleColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
                genreColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenre()));
                typeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
                platformColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlatform()));
                dateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getReleaseDate()));
                priceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
            }
            else
            {
                defaultTable();
            }
        });
    }

    @FXML
    void updateImage(ActionEvent event) {
        FileChooser imageChooser = new FileChooser();
        File selectedFile = imageChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            updateImage.setImage(image);
            updateImagePath = "file:///" + selectedFile.getPath().replace("\\", "/").replace(" ", "%20");
        }
    }

    @FXML
    void searchEvent(ActionEvent event)
    {
            ObservableList<Product> productList = FXCollections.observableArrayList();
            if(!searchField.getText().isEmpty())
            {
                try
                {
                    Connection connection = ConnectionSingleton.getConnection();
                    String query = "SELECT * FROM gamelist;";
                    ResultSet resultSet =  connection.createStatement().executeQuery(query);
                    productList.clear();
                    while(resultSet.next())
                    {
                        if(resultSet.getString("title").equalsIgnoreCase(searchField.getText()) || resultSet.getString("genre").equalsIgnoreCase(searchField.getText()) || resultSet.getString("type").equalsIgnoreCase(searchField.getText()) || resultSet.getString("platform").equalsIgnoreCase(searchField.getText()) || resultSet.getString("release_date").equalsIgnoreCase(searchField.getText()) || String.valueOf(resultSet.getDouble("price")).equalsIgnoreCase(searchField.getText()))
                        {
                            Product product = new Product(resultSet.getString("title"), resultSet.getString("genre"), resultSet.getString("type"),  resultSet.getString("platform"), resultSet.getString("release_date"), resultSet.getDouble("price"), resultSet.getString("image"));
                            productList.add(product);
                            productTable.setItems(productList);
                        }
                    }
                }
                catch(SQLException ex)
                {
                    System.out.println("failed to connect to database");
                    ex.printStackTrace();
                }

                titleColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
                genreColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenre()));
                typeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
                platformColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlatform()));
                dateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getReleaseDate()));
                priceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
            }
            else
            {
                defaultTable();
            }
    }

    @FXML
    void updateEvent()
    {
        Product product = productTable.getSelectionModel().getSelectedItem();
        AddGame addGame = new AddGame(titleField.getText(), genreBox.getValue(), typeBox.getValue(), platformBox.getValue(), datePick.getValue() + "", Double.parseDouble(priceField.getText()), "", updateImagePath, cpuBox.getValue(), windowsBox.getValue(), ramBox.getValue(), gpuBox.getValue(), storageField.getText());
        GameService gameService = new GameService();
        gameService.updateGame(product, addGame);
        defaultTable();
    }

    @FXML
    void tableEvent(MouseEvent event) {

        Product product = productTable.getSelectionModel().getSelectedItem();
        titleField.setText(product.getTitle());
        priceField.setText(String.valueOf(product.getPrice()));
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM gamelist WHERE title ='" +  product.getTitle() + "';";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while(resultSet.next())
            {
                genreBox.setValue(resultSet.getString("genre"));
                platformBox.setValue(resultSet.getString("platform"));
                typeBox.setValue(resultSet.getString("type"));
                datePick.setValue(LocalDate.parse(resultSet.getString("release_date")));
                updateImage.setImage(new Image(resultSet.getString("image")));
                updateImagePath  =  resultSet.getString("image");
                windowsBox.setValue(resultSet.getString("windows"));
                cpuBox.setValue(resultSet.getString("cpu"));
                ramBox.setValue(resultSet.getString("ram"));
                gpuBox.setValue(resultSet.getString("gpu"));
                storageField.setText(resultSet.getString("storage"));

                titleLabel.setText(product.getTitle());
                genreLabel.setText(product.getGenre());
                typeLabel.setText(product.getType());
                platformLabel.setText(product.getPlatform());
                dateLabel.setText(product.getReleaseDate());
                descriptionLabel.setText(resultSet.getString("description"));
                productImage.setImage(new Image(resultSet.getString("image")));
                windowsLabel.setText(resultSet.getString("windows"));
                cpuLabel.setText(resultSet.getString("cpu"));
                ramLabel.setText(resultSet.getString("ram"));
                gpuLabel.setText(resultSet.getString("gpu"));
                storageLabel.setText(resultSet.getString("storage"));
            }
        }
        catch(SQLException e)
        {
            System.err.println("failed to connect to database");
            e.printStackTrace();
        }
    }

    @FXML
    void removeEvent() {

        Product product = productTable.getSelectionModel().getSelectedItem();
        GameService gameService = new GameService();
        gameService.removeGame(product);
        defaultTable();
    }

    void defaultTable()
    {
        productList.clear();
        productList = FXCollections.observableArrayList();
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM gamelist;";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String type = resultSet.getString("type");
                String platform = resultSet.getString("platform");
                String releaseDate = resultSet.getString("release_Date");
                double price = resultSet.getDouble("price");
                String imagePath = resultSet.getString("image");

                Product product2 = new Product(title, genre, type, platform, releaseDate, price, imagePath);
                titleColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
                genreColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenre()));
                typeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
                platformColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlatform()));
                dateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getReleaseDate()));
                priceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
                productList.add(product2);
                productTable.setItems(productList);

                titleLabel.setText(title);
                genreLabel.setText(genre);
                typeLabel.setText(type);
                platformLabel.setText(platform);
                dateLabel.setText(releaseDate);
                /*priceLabel.setText(String.valueOf(price));*/
                descriptionLabel.setText(resultSet.getString("description"));
                productImage.setImage(new Image(imagePath));
                windowsLabel.setText(resultSet.getString("windows"));
                cpuLabel.setText(resultSet.getString("cpu"));
                ramLabel.setText(resultSet.getString("ram"));
                gpuLabel.setText(resultSet.getString("gpu"));
                storageLabel.setText(resultSet.getString("storage"));
            }
        }
        catch(SQLException e)
        {
            System.err.println("failed to connect to database");
            e.printStackTrace();
        }
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
