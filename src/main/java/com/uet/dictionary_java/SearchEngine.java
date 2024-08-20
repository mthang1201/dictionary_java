package com.uet.dictionary_java;

import com.uet.dictionary_java.services.WordService;

import java.util.List;

public class SearchEngine {
    private static SearchEngine instance;

    private SearchEngine() {}

    public static SearchEngine getInstance() {
        if (instance == null) {
            instance = new SearchEngine();
        }
        return instance;
    }

    public List<WordEntity> search(String word, WordService service) {
        if (word.isEmpty()) return null;

        return service.findByName(word);
    }
}
