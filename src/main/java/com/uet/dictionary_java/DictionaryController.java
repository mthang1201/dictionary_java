package com.uet.dictionary_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DictionaryController {
    private final WordService wordService;

    @FXML
    private TextField searchBar;

    @FXML
    private TextArea definitionArea;

    public DictionaryController() throws IOException {
        this.wordService = new WordService();
    }

    @FXML
    protected void onSearchButtonClick() {
        String searchTerm = searchBar.getText();

        WordEntity wordEntity = wordService.findByName(searchTerm);
        definitionArea.setText(wordEntity != null ? wordEntity.getDefinition() : "Word not found.");
    }

    public void switchToTranslator(ActionEvent actionEvent) throws IOException {
        SceneManager.getInstance().setScene("translator.fxml");
    }
}