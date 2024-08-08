package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.SceneManager;
import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.WordService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WordFormController {
    WordService wordService = new WordService();

    @FXML private TextField nameField;
    @FXML private TextField ipaField;
    @FXML private TextField typeField;
    @FXML private TextField definitionField;
    @FXML private TextField exampleField;

    @FXML private Label errorWarning;

    private boolean createMethod = true;

    @FXML
    private void initialize() {
        WordEntity wordEntity = SceneManager.getInstance().getCachedWord();
        if (wordEntity != null) {
            createMethod = false;
            nameField.setText(wordEntity.getName());
            ipaField.setText(wordEntity.getIpa());
            typeField.setText(wordEntity.getType());
            definitionField.setText(wordEntity.getDefinition());
            exampleField.setText(wordEntity.getExample());
        }
    }

    private boolean invalidField() {
        if (nameField.getText() == null || nameField.getText().trim().equals("")) { return true; }
        if (ipaField.getText() == null || ipaField.getText().trim().equals("")) { return true; }
        if (typeField.getText() == null || typeField.getText().trim().equals("")) { return true; }
        if (definitionField.getText() == null || definitionField.getText().trim().equals("")) { return true; }
        if (exampleField.getText() == null || exampleField.getText().trim().equals("")) { return true; }
        return false;
    }

    @FXML
    private void returnToEdit() {
        SceneManager.getInstance().setSubScene("edit.fxml");
    }

    @FXML
    private void handleSaveForm() {
        if (invalidField()) { errorWarning.setText("Please fill out this form."); return; }

        WordEntity wordEntity = new WordEntity();
        wordEntity.setId(100);
        wordEntity.setName(nameField.getText());
        wordEntity.setIpa(ipaField.getText());
        wordEntity.setType(typeField.getText());
        wordEntity.setDefinition(definitionField.getText());
        wordEntity.setExample(exampleField.getText());

        if (createMethod) {
            wordService.create(wordEntity);
        }
        else {
            wordService.update(wordEntity);
        }

        returnToEdit();
    }
}
