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
        addLabelToGridPane(pageGrid, "Definition", 3, 0);
        addLabelToGridPane(pageGrid, "Example", 4, 0);

        int rowIndex = 1;
        for (WordEntity wordEntity : wordEntities) {
            addLabelToGridPane(pageGrid, wordEntity.getName(), 1, rowIndex);
            addLabelToGridPane(pageGrid, wordEntity.getIpa(), 2, rowIndex);
            addLabelToGridPane(pageGrid, wordEntity.getDefinition(), 3, rowIndex);
            addLabelToGridPane(pageGrid, wordEntity.getExample(), 4, rowIndex);

            Button editBtn = new Button("Edit");
            editBtn.getStyleClass().add("edit-button");
            editBtn.setOnAction(event -> openWordForm(wordEntity));
            pageGrid.add(editBtn, 5, rowIndex);

            Button deleteBtn = new Button("Delete");
            deleteBtn.getStyleClass().add("delete-button");
            deleteBtn.setOnAction(event -> handleDelete(wordEntity));
            pageGrid.add(deleteBtn, 6, rowIndex);

            rowIndex++;
        }
    }

    private void addLabelToGridPane(GridPane pageGrid, String text, int rowIndex, int columnIndex) {
        Label label = new Label(text);
        switch (rowIndex) {
            case 1:
                label.setPrefWidth(120);
                break;
            case 2:
                label.setPrefWidth(40);
                break;
            case 3, 4:
                label.setPrefWidth(150);
                break;
        }
        label.setPrefHeight(38);
        pageGrid.add(label, rowIndex, columnIndex);
    }

    private void openWordForm(WordEntity wordEntity) {
        SceneManager.getInstance().setSubScene("wordForm.fxml");
        SceneManager.getInstance().setCachedWord(wordEntity);
    }

    @FXML
    private void handleCreate() {
        openWordForm(null);
    }

    private void handleDelete(WordEntity wordEntity) {
        wordService.delete(wordEntity.getName());
        pagination.setPageFactory(this::createPage);
    }
}
