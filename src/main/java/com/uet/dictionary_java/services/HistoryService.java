package com.uet.dictionary_java.services;

import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.repositories.HistoryRepository;

import java.util.List;

public class HistoryService extends WordService {
    public HistoryService() {
        super(new HistoryRepository());
    }

    public List<WordEntity> getRecentWords() {
        return ((HistoryRepository) repository).getRecentWords();
    }

    public void removeSavedWord(WordEntity wordEntity) {
        ((HistoryRepository) repository).removeSavedWord(wordEntity);
    }

    public void clearHistory() {
        ((HistoryRepository) repository).clearHistory();
    }
}
