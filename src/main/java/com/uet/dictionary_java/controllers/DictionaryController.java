package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.WordService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class DictionaryController {
    private final WordService wordService = new WordService();
    @FXML
    public Label nameLabel;
    @FXML
    public Label ipaLabel;
    @FXML
    public Label typeLabel;
    @FXML
    private Label definitionLabel;
    @FXML
    public Label exampleLabel;

    @FXML
    private TextField searchBar;


    @FXML
    private void initialize() {
        searchBar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSearch();
            }
        });

        searchBar.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean wasFocused, Boolean isNowFocused) {
                if (!isNowFocused) {
                    handleSearch();
                }
            }
        });
    }

    private void handleSearch() {
        String searchTerm = searchBar.getText();

        WordEntity wordEntity = wordService.findByName(searchTerm);
        if (wordEntity == null) {
            nameLabel.setText("No Results Found\n" +
                    "No results were found for this search query");
        }
        else {
            nameLabel.setText(wordEntity.getName());
            ipaLabel.setText(wordEntity.getIpa());
            typeLabel.setText(wordEntity.getType());
            definitionLabel.setText(wordEntity.getDefinition());
            exampleLabel.setText(wordEntity.getExample());
        }
    }
}