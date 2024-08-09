package com.uet.dictionary_java.services;

import com.uet.dictionary_java.repositories.HistoryRepository;

public class HistoryService extends WordService {
    public HistoryService() {
        super();
    }

    @Override
    protected void loadRepository() { repository = new HistoryRepository(); }
}
