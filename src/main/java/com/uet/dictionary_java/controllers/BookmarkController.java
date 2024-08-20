package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.SearchEngine;
import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.services.BookmarkService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.List;

public class BookmarkController {
    @FXML
    public Button deleteBtn;
    @FXML
    private VBox vboxList;

    private final BookmarkService bookmarkService = new BookmarkService();

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
        vboxList.getChildren().clear();
        List<WordEntity> wordEntities = bookmarkService.findAll();

        for (WordEntity wordEntity : wordEntities) {
            Label label = new Label(wordEntity.getName());
            label.getStyleClass().add("vbox-label");
            label.setOnMouseClicked(mouseEvent -> {
                nameLabel.setText(wordEntity.getName());
                ipaLabel.setText(wordEntity.getIpa());
                typeLabel.setText(wordEntity.getType());
                definitionLabel.setText(wordEntity.getDefinition());
                exampleLabel.setText(wordEntity.getExample());

                deleteBtn.setOnAction(event -> handleDelete(wordEntity));
            });

            vboxList.getChildren().add(label);
        }
    }

    private void handleSearch() {
        String searchTerm = searchBar.getText();

        List<WordEntity> wordEntities = SearchEngine.getInstance().search(searchTerm, bookmarkService);

        WordEntity wordEntity = wordEntities.get(0);
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

        vboxList.getChildren().clear();

        for (WordEntity word : wordEntities) {
            Label label = new Label(wordEntity.getName());
            label.getStyleClass().add("vbox-label");
            label.setOnMouseClicked(mouseEvent -> {
                nameLabel.setText(word.getName());
                ipaLabel.setText(word.getIpa());
                typeLabel.setText(word.getType());
                definitionLabel.setText(word.getDefinition());
                exampleLabel.setText(word.getExample());

                deleteBtn.setOnAction(event -> handleDelete(word));
            });

            vboxList.getChildren().add(label);
        }
    }

    private void handleDelete(WordEntity wordEntity) {
        bookmarkService.delete(wordEntity);
        checkList();
    }
}
