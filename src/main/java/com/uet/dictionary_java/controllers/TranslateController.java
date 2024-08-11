package com.uet.dictionary_java.controllers;

import com.uet.dictionary_java.SceneManager;
import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.services.BookmarkService;
import com.uet.dictionary_java.services.HistoryService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
import java.util.HashMap;
import java.util.Map;

public class TranslateController {
    private String langFrom;
    private String langTo;
    private boolean isSwapping = false; // Flag to prevent interference beetween handleSwapLanguages and handleOverlapLanguages
    private Map<String, String> langMap = new HashMap<>();

    private void setLangMap()
    {
        langMap.put("English","en");
        langMap.put("French","fr");
        langMap.put("Vietnamese","vi");
        langMap.put("Japanese", "ja");
        langMap.put("Chinese", "zh");
    }

    @FXML private ComboBox<String> langFromBox;
    @FXML private ComboBox<String> langToBox;
    private ObservableList<String> langList = FXCollections.observableArrayList("Vietnamese", "English", "French", "Chinese", "Japanese");

    @FXML
    private TextField input;

    @FXML
    private TextField translated;

    @FXML
    private void initialize() {
        setLangMap();
        langFromBox.setItems(langList);
        langFromBox.setValue("Vietnamese");
        langToBox.setItems(langList);
        langToBox.setValue("English");
        langFrom = "vi";
        langTo = "en";


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

        handleOverlapLanguages();
    }

    @FXML
    private void handleLangFrom(){}

    @FXML
    private void handleLangTo(){}

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
                langTo = langMap.get(langToBox.getValue());
                langFrom = langMap.get(langFromBox.getValue());
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

        WordEntity wordEntity = filterEnVi();

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
        isSwapping = true;
        String langFrom = langFromBox.getValue();
        String langTo = langToBox.getValue();
        langFromBox.setValue(langTo);
        langToBox.setValue(langFrom);
        isSwapping = false;
    }


    public void handleOverlapLanguages() {
        // Listener for langFromBox
        langFromBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(isSwapping) return;
                if (newValue != null && newValue.equals(langToBox.getValue())) {
                    langFromBox.setValue(oldValue); // remain old language if similar to langTo
                }
            }
        });

        // Listener for langToBox
        langToBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(isSwapping) return;
                if (newValue != null && newValue.equals(langFromBox.getValue())) {
                    langToBox.setValue(oldValue); // remain old language if similar to langFrom
                }
            }
        });
    }

    public void handleHistory() {
        SceneManager.getInstance().setSubScene("history.fxml");
    }

    public void handleBookmark() {
        if (input.getText().isEmpty()) return;

        BookmarkService service = new BookmarkService();

        WordEntity wordEntity = filterEnVi();

        service.create(wordEntity);
    }

    private WordEntity filterEnVi() {
        WordEntity wordEntity = new WordEntity();
        if (langFrom.equals("vi")) {
            wordEntity.setName(translated.getText());
            wordEntity.setDefinition(input.getText());
        }
        else {
            wordEntity.setName(input.getText());
            wordEntity.setDefinition(translated.getText());
        }
        return wordEntity;
    }
}
