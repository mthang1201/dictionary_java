package com.uet.dictionary_java;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class WordService {
    private final WordRepository wordRepository;

    public WordService() throws IOException {
        this.wordRepository = new WordRepository();
    }

    public List<WordEntity> findAll() {
        List<WordEntity> wordEntities = wordRepository.findAll();
        return wordEntities;
    }

    public WordEntity findByName(String name) {
        Optional<WordEntity> found = wordRepository.findByName(name);
        if (found.isEmpty()) {
            return null;
        }

        WordEntity wordEntity = found.get();
        return wordEntity;
    }
}
