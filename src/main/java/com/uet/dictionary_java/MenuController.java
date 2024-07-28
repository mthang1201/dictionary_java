package com.uet.dictionary_java;

import javafx.fxml.FXML;

public class MenuController {
    @FXML
    private void handleDictionaryMenu() {
        SceneManager.getInstance().setSubScene("dictionary.fxml");
    }

    @FXML
    private void handleTranslateMenu() {
        SceneManager.getInstance().setSubScene("translator.fxml");
    }

    @FXML
    private void handleFavoritesMenu() {
        SceneManager.getInstance().setSubScene("favorites.fxml");
    }

    @FXML
    private void handleHistoryMenu() {
        SceneManager.getInstance().setSubScene("history.fxml");
    }
}
