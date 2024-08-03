package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.WordService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.util.List;

public class EditController {
    @FXML
    public GridPane gridPane;

    @FXML
    public ScrollPane scrollPane;

    @FXML
    public TextField pageTextField;

    private int currentPage = 1;

    private void addLabelToGridPane(String text, int rowIndex, int columnIndex) {
        Label label = new Label(text);
        gridPane.add(label, rowIndex, columnIndex);
    }

    private void setCurrentPage() {
        currentPage = Integer.parseInt(pageTextField.getText());
        reloadPage();
    }

    private void reloadPage() {
        gridPane.getChildren().clear();
        WordService wordService = new WordService();
        List<WordEntity> wordEntities = wordService.findAllByPage(currentPage, 20);
        pageTextField.setText(String.valueOf(currentPage));
        int columnIndex = 0;
        for (WordEntity wordEntity : wordEntities) {
            //addLabelToGridPane(String.valueOf(wordEntity.getId()), 0, columnIndex);
            addLabelToGridPane(wordEntity.getName(), 1, columnIndex);
            addLabelToGridPane(wordEntity.getIpa(), 2, columnIndex);
            addLabelToGridPane(wordEntity.getDefinition(), 3, columnIndex);
            addLabelToGridPane(wordEntity.getExample(), 4, columnIndex);
            columnIndex++;
        }
    }

    @FXML
    private void initialize() {
        pageTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                setCurrentPage();
            }
        });

        pageTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean wasFocused, Boolean isNowFocused) {
                if (!isNowFocused) {
                    setCurrentPage();
                }
            }
        });

        reloadPage();
    }

    @FXML
    private void decreasePage() {
        if (currentPage == 1) return;
        currentPage--;
        reloadPage();
    }

    @FXML
    private void increasePage() {
        if (currentPage == 1000) return;
        currentPage++;
        reloadPage();
    }

}
