package com.uet.dictionary_java.repositories;

public class HistoryRepository extends WordRepository {
    public HistoryRepository() {
        super();
    }

    @Override
    protected void loadDatabase() { db_table = "recent_words"; }
}
