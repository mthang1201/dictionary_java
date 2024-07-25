package com.uet.dictionary_java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordRepository {
    private final String dictionaryFile;
    private final List<WordEntity> wordEntities;

    public WordRepository() throws IOException {
        this.dictionaryFile = "anhviet109K.txt";
        this.wordEntities = new ArrayList<>();
        loadDictionary();
    }

    private void loadDictionary() throws IOException {
//        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(" ");
//
//                WordEntity wordEntity = new WordEntity();
//                wordEntity.setName(parts[0].toLowerCase());
//                wordEntity.setIpa("/a/");
//                wordEntity.setType("Noun");
//                wordEntity.setDefinition(parts[1]);
//                wordEntity.setExample("Once upon a time, there's a little Def.");
//
//                wordEntities.add(wordEntity);
//            }
//        }
        int q = 5;
        while (q-- > 0) {
            WordEntity wordEntity = new WordEntity();
            wordEntity.setName("apple");
            wordEntity.setIpa("/a/");
            wordEntity.setType("Noun");
            wordEntity.setDefinition("a food to eat");
            wordEntity.setExample("Once upon a time, there's a little Def.");

            wordEntities.add(wordEntity);
        }
    }

    public List<WordEntity> findAll() {
        return wordEntities;
    }

    public Optional<WordEntity> findByName(String name) {
        for (WordEntity entity : wordEntities) {
            if (entity.getName().equals(name.toLowerCase())) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }
}
