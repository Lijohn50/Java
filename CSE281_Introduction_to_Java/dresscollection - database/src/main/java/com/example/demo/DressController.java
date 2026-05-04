package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.IIOParam;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DressController implements Initializable {

    @FXML
    private ComboBox<String> availableSize;

    @FXML
    private Spinner<Integer> availableQuantity;

    @FXML
    private PasswordField discountCode;

    @FXML
    private ColorPicker dressColor;

    @FXML
    private TextArea dressDetails;

    @FXML
    private TextField dressName;

    @FXML
    private ChoiceBox<String> dressType;

    @FXML
    private CheckBox enableCheck;

    @FXML
    private ToggleGroup genderPick;

    @FXML
    private Slider priceSlider;

    @FXML
    private DatePicker purchaseDate;

    @FXML
    private Label savedText;

    @FXML
    private ImageView dressImage;

    @FXML
    private Label dressAlert;

    @FXML
    private Label colorAlert;

    @FXML
    private Label customerAlert;

    @FXML
    private Label dateAlert;

    @FXML
    private Label detailsAlert;

    @FXML
    private Label discountAlert;

    @FXML
    private Label pictureAlert;

    @FXML
    private Label priceAlert;

    @FXML
    private Label quantityAlert;

    @FXML
    private Label sizeAlert;

    @FXML
    private Label typeAlert;

    private String imagePath;

    @FXML
    public void dressEvent()
    {
        HelloApplication.changeScene("dress");
    }

    public void uploadImage()
    {
        FileChooser imageChooser = new FileChooser();
        File selectedFile = imageChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            dressImage.setImage(image);
            imagePath = "file:///" + selectedFile.getPath().replace("\\", "/").replace(" ", "%20");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observeList = FXCollections.observableArrayList();
        observeList.add("Hoodie");
        observeList.add("Sweater");
        dressType.setItems(observeList);

        availableSize.getItems().addAll("M", "L", "XL", "XXL");

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        valueFactory.setValue(1);
        availableQuantity.setValueFactory(valueFactory);
    }

    public void saveEvent()
    {
        if(dressName.getText().trim().isEmpty())
        {
            dressAlert.setText("Dress name is missing");
        }
        else
        {
            dressAlert.setText("");
        }

        if(dressType.getValue() == null)
        {
            typeAlert.setText("Dress type is required");
        }
        else
        {
            typeAlert.setText("");
        }

        if(availableSize.getValue() == null)
        {
            sizeAlert.setText("Dress size is required");
        }
        else
        {
            sizeAlert.setText("");
        }

        if(dressColor.getValue() == null)
        {
            colorAlert.setText("Dress color is required");
        }
        else
        {
            colorAlert.setText("");
        }

        if(priceSlider.getValue() < 500)
        {
            priceAlert.setText("Dress price must be greater than 500");
        }
        else
        {
            priceAlert.setText("");
        }

        if(dressDetails.getLength() > 50)
        {
            detailsAlert.setText("Dress details must be less than 50 characters");
        }
        else if(dressDetails.getText().trim().isEmpty())
        {
            detailsAlert.setText("Dress details is required");
        }
        else
        {
            detailsAlert.setText("");
        }

        if(purchaseDate.getValue() == null)
        {
            dateAlert.setText("Date cannot be future date");
        }
        else
        {
            dateAlert.setText("");
        }

        if(availableQuantity.getValue() < 1)
        {
            quantityAlert.setText("Quantity cannot be less than 1");
        }
        else
        {
            quantityAlert.setText("");
        }

        if(discountCode.getText().trim().isEmpty())
        {
            discountAlert.setText("Discount code is empty");
        }
        else
        {
            discountAlert.setText("");
        }

        if(genderPick.getSelectedToggle() == null)
        {
            customerAlert.setText("Targeted customer is required");
        }
        else
        {
            customerAlert.setText("");
        }

        if(dressImage.getImage() == null)
        {
            pictureAlert.setText("Picture is required");
        }

        if(dressName.getText() == null || dressType.getValue() == null || availableSize.getValue() == null || dressColor.getValue() == null || purchaseDate.getValue() == null || availableQuantity.getValue() == null || discountCode.getText() == null || dressDetails.getText() == null || dressImage.getImage() == null || priceSlider.getValue() < 500)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please fill in all the information");
            alert.showAndWait();
        }
        else
        {
            String name = dressName.getText();
            String type = dressType.getValue();
            String size = availableSize.getValue();
            double price = priceSlider.getValue();
            String color = dressColor.getValue().toString();
            String date = purchaseDate.getValue().toString();
            int quantity = availableQuantity.getValue();
            String discount = discountCode.getText();
            String details = dressDetails.getText();
            RadioButton selectedGender = (RadioButton) genderPick.getSelectedToggle();
            String gender = selectedGender.getText();
            boolean boost = enableCheck.isSelected();
            savedText.setText("Dress Information Saved to database!...");
            Product product = new Product(name, type, color, price, date, quantity, boost, discount, details, gender, size, imagePath);
            DressService dressService = new DressService();
            dressService.insert(product);
        }

    }
}
