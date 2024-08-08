package com.uet.dictionary_java;

import java.util.List;
import java.util.Optional;

public class WordService {
    private final WordRepository wordRepository;

    public WordService() {
        this.wordRepository = new WordRepository();
    }

    public List<WordEntity> findAll() {
        List<WordEntity> wordEntities = wordRepository.findAll();
        return wordEntities;
    }

    public List<WordEntity> findAllByPage(int page, int pageSize) {
        List<WordEntity> wordEntities = wordRepository.findAllByPage(page, pageSize);
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

    public int countAll() {
        return 176023;
    }

    public void create(WordEntity wordEntity) {
        wordRepository.create(wordEntity);
    }

    public void update(WordEntity wordEntity) {
        wordRepository.update(wordEntity);
    }

    public void delete(WordEntity wordEntity) {
        wordRepository.delete(wordEntity);
    }
}
