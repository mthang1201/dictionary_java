package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainController {
    @FXML
    private AnchorPane contentPane;

    @FXML
    private void initialize() {
        SceneManager.getInstance().setContentPane(contentPane);
        SceneManager.getInstance().setSubScene("dictionary.fxml");
    }
}
