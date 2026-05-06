package com.example.demo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo.HelloApplication.changeScene;

public class DressController2 extends DressController implements Initializable{

    @FXML
    private Label colorLabel;

    @FXML
    private Label dressNameLabel;

    @FXML
    private Label dressTypeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label sizeLabel;

    @FXML
    private Label detailsField;

    @FXML
    private Label dateField;

    @FXML
    private Label discountField;

    @FXML
    private Label quantityField;

    @FXML
    private Label customerField;

    @FXML
    private Label boostField;

    @FXML
    private TextField searchField;

    @FXML
    private Label redQuantity;

    @FXML
    private ImageView image2;

    @FXML
    private String imagePath;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> typeColumn;

    @FXML
    private TableColumn<Product, String> colorColumn;

    @FXML
    private TableColumn<Product, Number> priceColumn;

    @FXML
    private TableColumn<Product, String>lastPurchaseColumn;

    @FXML
    private TableColumn<Product, Number>quantityColumn;

    @FXML
    private TableColumn<Product, Boolean>boostedColumn;

    ObservableList<Product>observe = FXCollections.observableArrayList();

    private String discount;

    @FXML
    public void backEvent()
    {
        changeScene("dress1");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            if(searchField.getText().trim().isEmpty())
            {
                RandomAccessFile raf = new RandomAccessFile("dressCollection.txt", "r");
                String line;
                while((line = raf.readLine()) != null)
                {
                    String[] data = line.split(",");

                    Product product = new Product(data[0], data[1], data[2], Double.parseDouble(data[3]), data[4], Integer.parseInt(data[5]), Boolean.parseBoolean(data[6]), data[11]);
                    productTable.setItems(observe);
                    observe.add(product);
                    nameColumn.setCellValueFactory( c -> new SimpleStringProperty(c.getValue().getName()));
                    typeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
                    colorColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getColor()));
                    priceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
                    lastPurchaseColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLastPurchase()));
                    quantityColumn.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getQuantity()));
                    boostedColumn.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().getBoosted()));
                    dressNameLabel.setText(data[0]);
                    dressTypeLabel.setText(data[1]);
                    colorLabel.setText(data[2]);
                    priceLabel.setText(data[3]);
                    dateField.setText(data[4]);
                    if(Integer.parseInt(data[5]) <= 10)
                    {
                        redQuantity.setText(data[5]);
                    }
                    else
                    {
                        quantityField.setText(data[5]);
                    }
                    boostField.setText(data[6]);
                    discountField.setText("****");
                    discount = data[7];
                    detailsField.setText(data[8]);
                    customerField.setText(data[9]);
                    sizeLabel.setText(data[10]);
                    imagePath = data[11];
                    String formattedPath = "file:///" + imagePath.replace("\\", "/").replace(" ", "%20");
                    image2.setImage(new Image(formattedPath));

                }
            }
            else
            {
                searchField.setOnKeyPressed(e -> {
                    if (e.getCode().equals(KeyCode.ENTER))
                    {
                        searchDress();
                    }
                });
            }
            productTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue != null)
                {
                    dressNameLabel.setText(newValue.getName());
                    dressTypeLabel.setText(newValue.getType());
                    colorLabel.setText(newValue.getColor());
                    priceLabel.setText(newValue.getPrice() + "");
                    dateField.setText(newValue.getLastPurchase());
                    if(newValue.getQuantity() <= 10)
                    {
                        redQuantity.setText(newValue.getQuantity() + "");
                    }
                    String formattedPath = "file:///" + imagePath.replace("\\", "/").replace(" ", "%20");
                    image2.setImage(new Image(newValue.getImage()));
                }
            });
        }
        catch(IOException ex)
        {
            System.out.println("failed to read");
            ex.printStackTrace();
        }


    }
    public void showDiscount()
    {
        discountField.setText(discount);
    }
    public void hideDiscount()
    {
        discountField.setText("****");
    }
    public void searchDress()
    {
        try
        {
            ObservableList<Product>observe2;
            RandomAccessFile raf = new RandomAccessFile("dressCollection.txt", "r");
            String line;
            while ((line = raf.readLine()) != null)
            {
                observe2 = FXCollections.observableArrayList();
                String[] data = line.split(",");
                if (searchField.getText().trim().equals(data[0]) || searchField.getText().trim().equals(data[1]) || searchField.getText().trim().equals(data[2]) || searchField.getText().trim().equals(data[8])) {
                    Product product = new Product(data[0], data[1], data[2], Double.parseDouble(data[3]), data[4], Integer.parseInt(data[5]), Boolean.parseBoolean(data[6]), data[11]);
                    productTable.setItems(observe2);
                    observe2.add(product);

                    dressNameLabel.setText(data[0]);
                    dressTypeLabel.setText(data[1]);
                    colorLabel.setText(data[2]);
                    priceLabel.setText(data[3]);
                    dateField.setText(data[4]);
                    if(Integer.parseInt(data[5]) <= 10)
                    {
                        redQuantity.setText(data[5]);
                    }
                    else
                    {
                        quantityField.setText(data[5]);
                    }
                    boostField.setText(data[6]);
                    discountField.setText("****");
                    discount = data[7];
                    detailsField.setText(data[8]);
                    customerField.setText(data[9]);
                    sizeLabel.setText(data[10]);
                    imagePath = data[11];
                    String formattedPath = "file:///" + imagePath.replace("\\", "/").replace(" ", "%20");
                    image2.setImage(new Image(formattedPath));
                    break;
                }
            }
        }
        catch(IOException ex)
        {
            System.out.println("failed to read");
            ex.printStackTrace();
        }
    }
    public void comingSoonEvent()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText("Coming Soon!!");
        alert.setContentText("This feature will be available soon!!");
        alert.showAndWait();
    }
}
