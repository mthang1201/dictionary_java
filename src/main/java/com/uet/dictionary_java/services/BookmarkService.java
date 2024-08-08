package com.uet.dictionary_java.services;

import com.uet.dictionary_java.WordEntity;
import com.uet.dictionary_java.repositories.BookmarkRepository;

import java.util.List;

public class BookmarkService extends WordService {
    public BookmarkService() {
        super(new BookmarkRepository());
    }

    public void savedWord(WordEntity wordEntity) {
        ((BookmarkRepository) repository).savedWord(wordEntity);
    }

    public List<WordEntity> getSavedWords() {
        return ((BookmarkRepository) repository).getSavedWords();
    }

    public void removeSavedWord(WordEntity wordEntity) {
        ((BookmarkRepository) repository).removeSavedWord(wordEntity);
    }

    public void clearBookmark() {
        ((BookmarkRepository) repository).clearBookmark();
    }
}