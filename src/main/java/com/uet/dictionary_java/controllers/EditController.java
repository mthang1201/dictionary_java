package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.WordService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;

import java.util.List;

public class EditController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Pagination pagination;

    private final WordService wordService = new WordService();
    private static final int PAGE_SIZE = 10;

    @FXML
    private void initialize() {
        pagination.setPageCount(calculatePageCount());
        pagination.setPageFactory(this::createPage);
    }

    private int calculatePageCount() {
        //int totalWords = wordService.countAll();
        int totalWords = 176023;
        return (int) Math.ceil((double) totalWords / PAGE_SIZE);
    }

    private GridPane createPage(int pageIndex) {
        GridPane pageGrid = new GridPane();
        pageGrid.setPrefWidth(700);
        pageGrid.setPrefHeight(380);
//        pageGrid.setStyle("-fx-border-color: red; -fx-border-width: 2;");

        reloadPage(pageIndex, pageGrid);
        return pageGrid;
    }

    private void reloadPage(int pageIndex, GridPane pageGrid) {
        gridPane.getChildren().clear();
        List<WordEntity> wordEntities = wordService.findAllByPage(pageIndex + 1, PAGE_SIZE);

        int columnIndex = 0;
        for (WordEntity wordEntity : wordEntities) {
            //addLabelToGridPane(String.valueOf(wordEntity.getId()), 0, columnIndex);
            addLabelToGridPane(pageGrid, wordEntity.getName(), 1, columnIndex);
            addLabelToGridPane(pageGrid, wordEntity.getIpa(), 2, columnIndex);
            addLabelToGridPane(pageGrid, wordEntity.getDefinition(), 3, columnIndex);
            addLabelToGridPane(pageGrid, wordEntity.getExample(), 4, columnIndex);
            columnIndex++;
        }
    }

    private void addLabelToGridPane(GridPane pageGrid, String text, int rowIndex, int columnIndex) {
        Label label = new Label(text);
        switch (rowIndex) {
            case 1, 2:
                label.setPrefWidth(100);
                break;
            case 3, 4:
                label.setPrefWidth(250);
                break;
        }
        label.setPrefHeight(38);
        pageGrid.add(label, rowIndex, columnIndex);
    }
}
