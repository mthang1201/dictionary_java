package com.uet.dictionary_java;

import java.sql.*;
import java.util.*;

public class WordRepository {

    private final ConnectJDBC connectJDBC;

    public WordRepository() {
        this.connectJDBC = new ConnectJDBC();
    }

    public List<WordEntity> findAll() {
        List<WordEntity> wordEntities = new ArrayList<>();

        String query = "SELECT * FROM entries";
        ResultSet rs = connectJDBC.executeQuery(query);
        while (true) {
            try {
                if (!rs.next()) break;
                WordEntity wordEntity = new WordEntity();
                wordEntity.setName(rs.getString("word"));
                wordEntity.setIpa("/a/");
                wordEntity.setType(rs.getString("wordtype"));
                wordEntity.setDefinition(rs.getString("definition"));
                wordEntity.setExample("Once upon a time, there's a little Def.");

                wordEntities.add(wordEntity);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return wordEntities;
    }

    public List<WordEntity> findAllByPage(int page, int pageSize) {
        List<WordEntity> wordEntities = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        String query = "SELECT * FROM entries LIMIT " + pageSize + " OFFSET " + offset;
        ResultSet rs = connectJDBC.executeQuery(query);
        while (true) {
            try {
                if (!rs.next()) break;
                WordEntity wordEntity = new WordEntity();
                wordEntity.setName(rs.getString("word"));
                wordEntity.setIpa("/a/");
                wordEntity.setType(rs.getString("wordtype"));
                wordEntity.setDefinition(rs.getString("definition"));
                wordEntity.setExample("Once upon a time, there's a little Def.");

                wordEntities.add(wordEntity);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return wordEntities;
    }

    public Optional<WordEntity> findByName(String name) {
        List<WordEntity> wordEntities = new ArrayList<>();

        String query = "SELECT * FROM entries where word = ?";
        ResultSet rs = connectJDBC.executeQueryWithParams(query, name);
        while (true) {
            try {
                if (!rs.next()) break;
                WordEntity wordEntity = new WordEntity();
                wordEntity.setName(rs.getString("word"));
                wordEntity.setIpa("/a/");
                wordEntity.setType(rs.getString("wordtype"));
                wordEntity.setDefinition(rs.getString("definition"));
                wordEntity.setExample("Once upon a time, there's a little Def.");

                wordEntities.add(wordEntity);
                return Optional.of(wordEntity);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
//        return wordEntities;
        return Optional.empty();
    }

    public void create(WordEntity wordEntity) {
        String query = "INSERT INTO entries (word, wordtype, definition) VALUES (?, ?, ?)";
        connectJDBC.executeUpdate(query, wordEntity.getName(), wordEntity.getIpa(), wordEntity.getDefinition());
    }

    public void update(WordEntity wordEntity) {
        //UPDATE persons SET name = ?, age = ? WHERE id = ?
        String query = "UPDATE entries SET wordtype = ?, definition = ? WHERE word = ?";
        connectJDBC.executeUpdate(query, wordEntity.getIpa(), wordEntity.getDefinition(), wordEntity.getName());
//        connectJDBC.executeUpdate(query, wordEntity.getId(), wordEntity.getName(), wordEntity.getIpa(), wordEntity.getType(), wordEntity.getDefinition(), wordEntity.getExample());
    }

    public void delete(WordEntity wordEntity) {
        String query = "DELETE FROM entries where word = ?";
        connectJDBC.executeUpdate(query, wordEntity.getName());
    }
}
