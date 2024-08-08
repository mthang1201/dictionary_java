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

        String query = "SELECT * FROM words";
        ResultSet rs = connectJDBC.executeQuery(query);
        while (true) {
            try {
                if (!rs.next()) break;
                WordEntity wordEntity = new WordEntity();
                wordEntity.setId(rs.getInt("id"));
                wordEntity.setName(rs.getString("name"));
                wordEntity.setIpa(rs.getString("ipa"));
                wordEntity.setType(rs.getString("type"));
                wordEntity.setDefinition(rs.getString("definition"));
                wordEntity.setExample(rs.getString("example"));

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
        String query = "SELECT * FROM words LIMIT " + pageSize + " OFFSET " + offset;
//        String query = "SELECT * FROM entries LIMIT " + pageSize + " OFFSET " + offset;
        ResultSet rs = connectJDBC.executeQuery(query);
        while (true) {
            try {
                if (!rs.next()) break;
                WordEntity wordEntity = new WordEntity();
                wordEntity.setId(rs.getInt("id"));
                wordEntity.setName(rs.getString("name"));
                wordEntity.setIpa(rs.getString("ipa"));
                wordEntity.setType(rs.getString("type"));
                wordEntity.setDefinition(rs.getString("definition"));
                wordEntity.setExample(rs.getString("example"));

                wordEntities.add(wordEntity);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return wordEntities;
    }

    public Optional<WordEntity> findByName(String name) {
        List<WordEntity> wordEntities = new ArrayList<>();

        String query = "SELECT * FROM words WHERE name = ?";
        ResultSet rs = connectJDBC.executeQueryWithParams(query, name);
        while (true) {
            try {
                if (!rs.next()) break;
                WordEntity wordEntity = new WordEntity();
                wordEntity.setId(rs.getInt("id"));
                wordEntity.setName(rs.getString("name"));
                wordEntity.setIpa(rs.getString("ipa"));
                wordEntity.setType(rs.getString("type"));
                wordEntity.setDefinition(rs.getString("definition"));
                wordEntity.setExample(rs.getString("example"));

                wordEntities.add(wordEntity);
                return Optional.of(wordEntity);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
//        return wordEntities;
        return Optional.empty();
    }

    public int countAll() {
        return 45556;
    }

    public void create(WordEntity wordEntity) {
//        String query = "INSERT INTO words (name, ipa, type, definition, example) VALUES (?, ?, ?, ?, ?)";
//        connectJDBC.executeUpdate(query, wordEntity.getName(), wordEntity.getIpa(), wordEntity.getType(), wordEntity.getDefinition(), wordEntity.getExample());
        String query = "INSERT INTO words (name, definition) VALUES (?, ?)";
        connectJDBC.executeUpdate(query, wordEntity.getName(), wordEntity.getDefinition());
    }

    public void update(WordEntity wordEntity) {
        String query = "UPDATE words SET name = ?, ipa = ?, type = ?, definition = ?, example = ? WHERE id = ?";
        connectJDBC.executeUpdate(query, wordEntity.getName(), wordEntity.getIpa(), wordEntity.getType(), wordEntity.getDefinition(), wordEntity.getExample(), wordEntity.getId());
    }

    public void delete(WordEntity wordEntity) {
        String query = "DELETE FROM words WHERE id = ?";
        connectJDBC.executeUpdate(query, wordEntity.getId());
    }
}
