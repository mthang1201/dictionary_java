package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.WordService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DictionaryController {
    private final WordService wordService;
    public Button searchButton;

    @FXML
    private TextField searchBar;

    @FXML
    private TextArea definitionArea;

    public DictionaryController() {
        this.wordService = new WordService();
    }

    @FXML
    protected void onSearchButtonClick() {
        String searchTerm = searchBar.getText();

        WordEntity wordEntity = wordService.findByName(searchTerm);
        definitionArea.setText(wordEntity != null ? wordEntity.getDefinition() : "Word not found.");
    }
}