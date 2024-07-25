package com.uet.dictionary_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class DictionaryController {
    private final WordService wordService;

    @FXML
    private TextField searchBar;

    @FXML
    private TextArea definitionArea;

    @FXML
    private Label welcomeText;

    public DictionaryController() throws IOException {
        this.wordService = new WordService();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onSearchButtonClick() {
        String searchTerm = searchBar.getText();

        WordEntity wordEntity = wordService.findByName(searchTerm);
        definitionArea.setText(wordEntity != null ? wordEntity.getDefinition() : "Word not found.");
    }

    public void switchToTranslator(ActionEvent actionEvent) throws IOException {
        SceneManager.getInstance().setScene("translator.fxml");
    }
}