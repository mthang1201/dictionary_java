package com.uet.dictionary_java.services;

import com.uet.dictionary_java.repositories.BookmarkRepository;

public class BookmarkService extends WordService {
    public BookmarkService() {
        super();
    }

    @Override
    protected void loadRepository() { repository = new BookmarkRepository(); }
}