package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.SceneManager;
import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.services.BookmarkService;
import com.uet.dictionary_java.services.HistoryService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TranslateController {
    private String langFrom;
    private String langTo;

    @FXML private Label langFromLabel;
    @FXML private Label langToLabel;

    @FXML
    private TextField input;

    @FXML
    private TextField translated;

    @FXML
    private void initialize() {
        langFrom = "vi";
        langTo = "en";
        langFromLabel.setText("Vietnamese");
        langToLabel.setText("English");

        input.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                translateText();
            }
        });

        input.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean wasFocused, Boolean isNowFocused) {
                if (!isNowFocused) {
                    translateText();
                }
            }
        });
    }

    private String decodeHtmlEntities(String input) {
        if (input == null) {
            return null;
        }
        return input
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replace("&#39;", "'"); // Handle apostrophe
    }

    private void translateText() {
        String textToTranslate = input.getText().trim();
        if (!textToTranslate.isEmpty()) {
            try {
                String urlStr = "https://script.google.com/macros/s/AKfycbwY5vf3-rWnkbgv3cO5n1wfAQ3KfqnFz54Bt8cJbSfkfe81nzsVK-Tfxt1INO91bX931A/exec" +
                        "?q=" + URLEncoder.encode(textToTranslate, StandardCharsets.UTF_8) +
                        "&target=" + langTo +
                        "&source=" + langFrom;
                String translatedText = getTranslatedText(urlStr);

                // Decode HTML entities manually
                translatedText = decodeHtmlEntities(translatedText);

                translated.setText(translatedText);
            } catch (Exception e) {
                //e.printStackTrace();
                translated.setText("Translation error: " + e.getMessage());
            }
        }

        saveToHistory();
    }

    private void saveToHistory() {
        HistoryService service = new HistoryService();

        WordEntity wordEntity = new WordEntity();
        wordEntity.setName(translated.getText());
        wordEntity.setDefinition(input.getText());

        service.create(wordEntity);
    }

    private static String getTranslatedText(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void handleSwapLanguages() {
        String tmp = langFrom;
        langFrom = langTo;
        langTo = tmp;
        if (langFrom.equals("vi")) {
            langFromLabel.setText("Vietnamese");
            langToLabel.setText("English");
        }
        else {
            langFromLabel.setText("English");
            langToLabel.setText("Vietnamese");
        }
    }

    public void handleHistory() {
        SceneManager.getInstance().setSubScene("history.fxml");
    }

    public void handleBookmark() {
        BookmarkService service = new BookmarkService();

        WordEntity wordEntity = new WordEntity();
        wordEntity.setName(translated.getText());
        wordEntity.setDefinition(input.getText());

        service.create(wordEntity);
    }
}
