package com.example.texteditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class EditorController {

    @FXML
    private MenuItem closeFile;

    @FXML
    private Menu menu;

    @FXML
    private MenuItem newFile;

    @FXML
    private MenuItem openFile;

    @FXML
    private MenuItem saveFile;

    @FXML
    private TabPane tabOpen;

    @FXML
    void close(ActionEvent event) {

        Stage stage = (Stage) tabOpen.getScene().getWindow();
        stage.close();
    }

    @FXML
    void create() {
        NewTab tab = new NewTab("untitled");
        tabOpen.getTabs().add(tab);
    }

    @FXML
    void open() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Open File");
        Stage stage = (Stage)tabOpen.getScene().getWindow();
        File selectedFile = fc.showOpenDialog(stage);
        if (selectedFile != null) {

                try {
                    String content = Files.readString(selectedFile.toPath(), StandardCharsets.UTF_8);
                    NewTab selectedTab = new NewTab(selectedFile.getName());
                    selectedTab.getTextArea().setText(content);
                    selectedTab.setFile(selectedFile);
                    tabOpen.getTabs().add(selectedTab);
            }catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    @FXML
    void save() {

        NewTab selectedTab = (NewTab) tabOpen.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            File f;
            if(selectedTab.getFile() != null){

                f = selectedTab.getFile();
            }else{
                FileChooser fc = new FileChooser();
                fc.setTitle("Save File");
                Stage stage = (Stage)tabOpen.getScene().getWindow();
                f = fc.showSaveDialog(stage);
            }
            if (f != null) {
                try {
                    FileWriter fw = new FileWriter(f);
                    fw.write(selectedTab.getTextArea().getText());
                    fw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                selectedTab.setText(f.getName());
            }else{

                System.out.println("file not selected");
            }
        }else{

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No File");
            alert.setContentText("File Not Created");
            alert.showAndWait();
        }
    }

}
