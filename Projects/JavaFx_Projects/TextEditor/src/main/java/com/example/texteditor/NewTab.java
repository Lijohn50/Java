package com.example.texteditor;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class NewTab extends Tab {

    private final AnchorPane pane;
    private final TextArea textArea;
    private File file;

    public NewTab(String text) {

        setText(text);
        pane = new AnchorPane();
        textArea = new TextArea();
        textArea.setWrapText(true);
        AnchorPane.setBottomAnchor(textArea,0.0 );
        AnchorPane.setLeftAnchor(textArea,0.0 );
        AnchorPane.setRightAnchor(textArea,0.0 );
        AnchorPane.setTopAnchor(textArea,0.0 );
        pane.getChildren().add(textArea);
        setContent(pane);
    }

    public TextArea getTextArea() {

        return textArea;
    }

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
}
