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

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.OptionalDouble;
import java.util.ResourceBundle;

import static com.example.demo.HelloApplication.changeScene;

public class DressController_2 extends DressController implements Initializable{

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
        if(searchField.getText().trim().isEmpty())
        {
            try
            {
                Connection connection = ConnectionSingleton.getConnection();
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM dressdata";
                ResultSet resultset =  statement.executeQuery(query);
                while(resultset.next())
                {
                    String name = resultset.getString("name");
                    String type = resultset.getString("type");
                    String color = resultset.getString("color");
                    double price = resultset.getDouble("price");
                    String date = resultset.getString("date");
                    int quantity = resultset.getInt("quantity");
                    boolean boost = resultset.getBoolean("boost");
                    String image = resultset.getString("image");
                    int discountCode = resultset.getInt("discount");
                    String details = resultset.getString("details");
                    String customer = resultset.getString("gender");
                    String size = resultset.getString("size");

                    Product product = new Product(name, type, color, price, date, quantity, boost, discountCode + "", details, customer, size, image);
                    productTable.setItems(observe);
                    observe.add(product);
                    nameColumn.setCellValueFactory( c -> new SimpleStringProperty(c.getValue().getName()));
                    typeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
                    colorColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getColor()));
                    priceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
                    lastPurchaseColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLastPurchase()));
                    quantityColumn.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getQuantity()));
                    boostedColumn.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().getBoosted()));
                    dressNameLabel.setText(name);
                    dressTypeLabel.setText(type);
                    colorLabel.setText(color);
                    priceLabel.setText(price + "");
                    dateField.setText(date);
                    if(quantity <= 10)
                    {
                        redQuantity.setText(quantity + "");
                    }
                    else
                    {
                        quantityField.setText(quantity + "");
                    }
                    boostField.setText(boost + "");
                    discountField.setText("****");
                    discount = discountCode + "";
                    detailsField.setText(details);
                    customerField.setText(customer);
                    sizeLabel.setText(size);
                    Image image1 = new Image(image);
                    image2.setImage(image1);
                }
            }
            catch(SQLException e)
            {
                System.out.println("failed to connect");
                e.printStackTrace();
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
            image2.setImage(new Image(newValue.getImage()));
        }
        });

        OptionalDouble od = observe.stream().filter(s -> s.getType().equals("Hoodie")).mapToDouble(Product::getPrice).average();
        if(od.isPresent())
        {
            double avg = od.getAsDouble();
            System.out.println("Average price of Hoodie: " + avg);
        }

        int total = observe.stream().mapToInt(Product::getQuantity).sum();
        System.out.println("Total available product " + total);

        List<String> list = observe.stream().filter(s -> s.getPrice() > 1000).map(Product::getName).toList();
        System.out.println("List of names: " + list);

        for(int i = 0; i < observe.size(); i++)
        {
            for(int j = 0; j < observe.size() - i - 1; j++)
            {
                if(observe.get(j).getPrice() > observe.get(j + 1).getPrice())
                {
                    Product temp = observe.get(j);
                    observe.set(j, observe.get(j + 1));
                    observe.set(j + 1, temp);
                }
            }
        }
        for(Product product : observe)
        {
            System.out.println(product.getName() + ", " + product.getType() + ", " + product.getColor() + ", " + product.getPrice() + ", " + product.getLastPurchase() + ", " + product.getQuantity() + ", " + product.getBoosted());
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
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM dressdata";
            ResultSet resultset =  statement.executeQuery(query);
            ObservableList<Product>observe2;
            while(resultset.next())
            {
                String name = resultset.getString("name");
                String type = resultset.getString("type");
                String color = resultset.getString("color");
                double price = resultset.getDouble("price");
                String date = resultset.getString("date");
                int quantity = resultset.getInt("quantity");
                boolean boost = resultset.getBoolean("boost");
                String image = resultset.getString("image_path");
                int discountCode = resultset.getInt("discount");
                String details = resultset.getString("details");
                String customer = resultset.getString("gender");
                String size = resultset.getString("size");

                if(searchField.getText().equals(name) || searchField.getText().equals(type) || searchField.getText().equals(color) || searchField.getText().equals(price + "") || searchField.getText().equals(date) || searchField.getText().equals(quantity + ""))
                {
                    observe2 = FXCollections.observableArrayList();
                    Product product = new Product(name, type, color, price, date, quantity, boost, discountCode + "", details, customer, size, image);
                    productTable.setItems(observe2);
                    observe2.add(product);
                    dressNameLabel.setText(name);
                    dressTypeLabel.setText(type);
                    colorLabel.setText(color);
                    priceLabel.setText(price + "");
                    dateField.setText(date);
                    if(quantity <= 10)
                    {
                        redQuantity.setText(quantity + "");
                    }
                    else
                    {
                        quantityField.setText(quantity + "");
                    }
                    boostField.setText(boost + "");
                    discountField.setText("****");
                    discount = discountCode + "";
                    detailsField.setText(details);
                    customerField.setText(customer);
                    sizeLabel.setText(size);
                    image2.setImage(new Image(image));
                    break;
                }

            }
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect");
            e.printStackTrace();
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
