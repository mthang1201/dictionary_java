package com.uet.dictionary_java.services;

import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.repositories.WordRepository;

import java.util.List;
import java.util.Optional;

public class WordService {
    protected WordRepository repository;

    public WordService() {
        loadRepository();
    }

    protected void loadRepository() { repository = new WordRepository(); }

    public List<WordEntity> findAll() {
        return repository.findAll();
    }

    public List<WordEntity> findAllByPage(int page, int pageSize) {
        return repository.findAllByPage(page, pageSize);
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

    public void deleteAll() {
        repository.deleteAll();
    }
}
