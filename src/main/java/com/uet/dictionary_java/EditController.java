package com.uet.dictionary_java;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class EditController {
    @FXML
    public GridPane gridPane;

    private void addLabelToGridPane(String text, int rowIndex, int columnIndex) {
        Label label = new Label(text);
        gridPane.add(label, rowIndex, columnIndex);
    }

    @FXML
    private void initialize() throws IOException {
        WordService wordService = new WordService();
        List<WordEntity> wordEntities = wordService.findAll();
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

}
