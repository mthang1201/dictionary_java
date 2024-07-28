package com.uet.dictionary_java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane contentPane;

    @FXML
    private void initialize() {
        SceneManager.getInstance().setContentPane(contentPane);
        SceneManager.getInstance().setSubScene("dictionary.fxml");
    }
}
