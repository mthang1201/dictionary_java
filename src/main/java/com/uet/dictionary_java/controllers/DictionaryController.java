package com.uet.dictionary_java.controllers;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.uet.dictionary_java.SearchEngine;
import com.uet.dictionary_java.VoiceGenerator;
import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.services.WordService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.List;

public class DictionaryController {
    private final WordService wordService = new WordService();

    @FXML private Label welcomeText;

    @FXML
    private Label nameLabel;
    @FXML
    private Label ipaLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label definitionLabel;
    @FXML
    private Label exampleLabel;

    @FXML
    private TextField searchBar;


    @FXML
    private void initialize() {
        welcomeText.setText("Welcome!\nEnter your search in the box above");

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

        List<WordEntity> wordEntities = SearchEngine.getInstance().search(searchTerm, wordService);

        WordEntity wordEntity = wordEntities.get(0);
        if (wordEntity == null) {
            welcomeText.setText("No Results Found\n" +
                    "No results were found for this search");
        }
        else {
            welcomeText.setText("");

            nameLabel.setText(wordEntity.getName());
            ipaLabel.setText(wordEntity.getIpa());
            typeLabel.setText(wordEntity.getType());
            definitionLabel.setText(wordEntity.getDefinition());
            exampleLabel.setText(wordEntity.getExample());
        }
    }

    @FXML
    private void handleSpeak() {
        VoiceGenerator.getInstance().speak(nameLabel.getText());
    }
}