package com.uet.dictionary_java.repositories;

public class BookmarkRepository extends WordRepository {
    public BookmarkRepository() {
        super();
    }

    @Override
    protected void loadDatabase() { db_table = "saved_words"; }
}
