package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.WordService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.List;

public class EditController {
    @FXML
    public GridPane gridPane;

    @FXML
    public ScrollPane scrollPane;

    @FXML
    public Button pageBtn;

    private int currentPage = 1;

    private void addLabelToGridPane(String text, int rowIndex, int columnIndex) {
        Label label = new Label(text);
        gridPane.add(label, rowIndex, columnIndex);
    }

    @FXML
    private void initialize() {
        WordService wordService = new WordService();
        List<WordEntity> wordEntities = wordService.findAllByPage(currentPage, 20);
        pageBtn.setText(String.valueOf(currentPage));
        int columnIndex = 0;
        for (WordEntity wordEntity : wordEntities) {
            addLabelToGridPane(String.valueOf(wordEntity.getId()), 0, columnIndex);
            addLabelToGridPane(wordEntity.getName(), 1, columnIndex);
            addLabelToGridPane(wordEntity.getIpa(), 2, columnIndex);
            addLabelToGridPane(wordEntity.getDefinition(), 3, columnIndex);
            addLabelToGridPane(wordEntity.getExample(), 4, columnIndex);
            columnIndex++;
        }
    }

    @FXML
    private void decreasePage() {
        if (currentPage == 1) return;
        currentPage--;
        initialize();
    }

    @FXML
    private void increasePage() {
        if (currentPage == 1000) return;
        currentPage++;
        initialize();
    }

}
