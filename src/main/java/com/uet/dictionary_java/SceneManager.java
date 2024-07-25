package com.uet.dictionary_java;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static SceneManager instance;
    private static Stage rootStage;

    private SceneManager() {}

    public void setStage(Stage stage) {
        SceneManager.rootStage = stage;
    }

    public void setScene(String sceneName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneName));

        Scene scene = new Scene(fxmlLoader.load(), 640, 440);
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
}
