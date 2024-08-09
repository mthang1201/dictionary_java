package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.services.HistoryService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HistoryController {
    private final HistoryService historyService = new HistoryService();

    public Button helloButton;
    @FXML
    private Label welcomeText;

    @FXML
    private void initialize() {
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
