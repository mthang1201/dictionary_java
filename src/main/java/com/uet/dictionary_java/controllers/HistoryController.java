package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.SearchEngine;
import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.services.HistoryService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.List;

public class HistoryController {
    @FXML
    private VBox vboxList;

    private final HistoryService historyService = new HistoryService();

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
        checkList();

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

    private void checkList() {
        List<WordEntity> wordEntities = historyService.findAll();

        for (WordEntity wordEntity : wordEntities) {
            Label label = new Label(wordEntity.getName());
            label.getStyleClass().add("vbox-label");
            label.setOnMouseClicked(mouseEvent -> {
                nameLabel.setText(wordEntity.getName());
                ipaLabel.setText(wordEntity.getIpa());
                typeLabel.setText(wordEntity.getType());
                definitionLabel.setText(wordEntity.getDefinition());
                exampleLabel.setText(wordEntity.getExample());
            });

            vboxList.getChildren().add(label);
        }
    }

    private void handleSearch() {
        String searchTerm = searchBar.getText();

        WordEntity wordEntity = SearchEngine.getInstance().search(searchTerm, historyService);

        if (wordEntity == null) {
            nameLabel.setText("No Results Found\n" +
                    "No results were found for this search");
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
