package com.uet.dictionary_java.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
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

    @FXML
    private TextField input;

    @FXML
    private TextArea translated;

    @FXML
    private void initialize() {
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
                String langFrom = "vi";
                String langTo = "en";
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
}
