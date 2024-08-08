package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.SceneManager;
import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.WordService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private static final int PAGE_SIZE = 6;

    @FXML
    private void initialize() {
        pagination.setPageCount(calculatePageCount());
        pagination.setPageFactory(this::createPage);
    }

    private int calculatePageCount() {
        int totalWords = wordService.countAll();
        return (int) Math.ceil((double) totalWords / PAGE_SIZE);
    }

    private GridPane createPage(int pageIndex) {
        GridPane pageGrid = new GridPane();
        pageGrid.setPrefWidth(700);
        pageGrid.setPrefHeight(280);
        pageGrid.setHgap(10);
//        pageGrid.setStyle("-fx-border-color: red; -fx-border-width: 2;");

        reloadPage(pageIndex, pageGrid);
        return pageGrid;
    }

    private void reloadPage(int pageIndex, GridPane pageGrid) {
        gridPane.getChildren().clear();
        List<WordEntity> wordEntities = wordService.findAllByPage(pageIndex + 1, PAGE_SIZE);

        addLabelToGridPane(pageGrid, "Name", 1, 0);
        addLabelToGridPane(pageGrid, "Ipa", 2, 0);
        addLabelToGridPane(pageGrid, "Type", 3, 0);
        addLabelToGridPane(pageGrid, "Definition", 4, 0);
        addLabelToGridPane(pageGrid, "Example", 5, 0);

        int rowIndex = 1;
        for (WordEntity wordEntity : wordEntities) {
            addLabelToGridPane(pageGrid, wordEntity.getName(), 1, rowIndex);
            addLabelToGridPane(pageGrid, wordEntity.getIpa(), 2, rowIndex);
            addLabelToGridPane(pageGrid, wordEntity.getType(), 3, rowIndex);
            addLabelToGridPane(pageGrid, wordEntity.getDefinition(), 4, rowIndex);
            addLabelToGridPane(pageGrid, wordEntity.getExample(), 5, rowIndex);

            Button editBtn = new Button("Edit");
            editBtn.getStyleClass().add("edit-button");
            editBtn.setOnAction(event -> openWordForm(wordEntity));
            pageGrid.add(editBtn, 6, rowIndex);

            Button deleteBtn = new Button("Delete");
            deleteBtn.getStyleClass().add("delete-button");
            deleteBtn.setOnAction(event -> handleDelete(wordEntity));
            pageGrid.add(deleteBtn, 7, rowIndex);

            rowIndex++;
        }
    }

    private void addLabelToGridPane(GridPane pageGrid, String text, int columnIndex, int rowIndex) {
        Label label = new Label(text);
        switch (columnIndex) {
            case 1:
                label.setPrefWidth(80);
                break;
            case 2, 3:
                label.setPrefWidth(30);
                break;
            case 4:
                label.setPrefWidth(240);
                break;
            case 5:
                label.setPrefWidth(100);
                break;
        }
        label.setPrefHeight(38);
        pageGrid.add(label, columnIndex, rowIndex);
    }

    private void openWordForm(WordEntity wordEntity) {
        SceneManager.getInstance().setCachedWord(wordEntity);
        SceneManager.getInstance().setSubScene("wordForm.fxml");
    }

    @FXML
    private void handleCreate() {
        openWordForm(null);
    }

    private void handleDelete(WordEntity wordEntity) {
        wordService.delete(wordEntity);
        pagination.setPageFactory(this::createPage);
    }
}
