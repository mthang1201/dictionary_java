package com.uet.dictionary_java;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HistoryController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
