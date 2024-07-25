package com.uet.dictionary_java;

import lombok.Data;

@Data
public class WordEntity {
    // word: id, name, ipa, type, definition, example
    private int id;
    private String name;
    private String ipa;
    private String type;
    private String definition;
    private String example;
}
