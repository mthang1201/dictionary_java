package com.uet.dictionary_java.services;

import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.repositories.WordRepository;

import java.util.List;
import java.util.Optional;

public class WordService {
    protected final WordRepository repository;

    public WordService(WordRepository repository) {
        this.repository = repository;
    }

    public WordService() {
        this.repository = new WordRepository();
    }

    public List<WordEntity> findAll() {
        List<WordEntity> wordEntities = repository.findAll();
        return wordEntities;
    }

    public List<WordEntity> findAllByPage(int page, int pageSize) {
        List<WordEntity> wordEntities = repository.findAllByPage(page, pageSize);
        return wordEntities;
    }

    public WordEntity findByName(String name) {
        Optional<WordEntity> found = repository.findByName(name);
        if (found.isEmpty()) {
            return null;
        }

        WordEntity wordEntity = found.get();
        return wordEntity;
    }

    public int countAll() {
        return repository.countAll();
    }

    public void create(WordEntity wordEntity) {
        repository.create(wordEntity);
    }

    public void update(WordEntity wordEntity) {
        repository.update(wordEntity);
    }

    public void delete(WordEntity wordEntity) {
        repository.delete(wordEntity);
    }
}
