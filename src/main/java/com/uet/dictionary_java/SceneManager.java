package com.uet.dictionary_java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    @FXML
    private AnchorPane contentPane;

    private static SceneManager instance;
    private static Stage rootStage;
    private WordEntity cachedWord;

    private SceneManager() {}

    public void setStage(Stage stage) {
        SceneManager.rootStage = stage;
    }

    public void setScene(String sceneName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneName));

        Scene scene = new Scene(fxmlLoader.load(), 800, 480);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        rootStage.setTitle("Dictionary Application");
        rootStage.setScene(scene);
        rootStage.show();
    }

    public static SceneManager getInstance() {
        if (instance == null)
            instance = new SceneManager();
        return instance;
    }

    public void setContentPane(AnchorPane contentPane) {
        this.contentPane = contentPane;
    }

    public void setSubScene(String sceneName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            AnchorPane pageContent = loader.load();
            contentPane.getChildren().setAll(pageContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WordEntity getCachedWord() { return this.cachedWord; }

    public void setCachedWord(WordEntity wordEntity) { this.cachedWord = wordEntity; }
}
