package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.SceneManager;
import javafx.fxml.FXML;

public class MenuController {
    @FXML
    private void handleDictionaryMenu() {
        SceneManager.getInstance().setSubScene("dictionary.fxml");
    }

    @FXML
    private void handleTranslateMenu() {
        SceneManager.getInstance().setSubScene("translate.fxml");
    }

    @FXML
    private void handleFavoritesMenu() {
        SceneManager.getInstance().setSubScene("favorites.fxml");
    }

    @FXML
    private void handleHistoryMenu() {
        SceneManager.getInstance().setSubScene("history.fxml");
    }

    @FXML
    private void handleEditMenu() {
        SceneManager.getInstance().setSubScene("edit.fxml");
    }

    @FXML
    public void handleGameMenu() {
        SceneManager.getInstance().setSubScene("game.fxml");
    }
}
