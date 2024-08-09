package com.uet.dictionary_java.repositories;

import com.uet.dictionary_java.ConnectJDBC;
import com.uet.dictionary_java.WordEntity;

import java.sql.*;
import java.util.*;

public class WordRepository {
    protected String db_table;
    protected final ConnectJDBC connectJDBC;

    public WordRepository() {
        this.connectJDBC = new ConnectJDBC();
        loadDatabase();
    }

    protected void loadDatabase() { db_table = "words"; }

    public List<WordEntity> findAll() {
        List<WordEntity> wordEntities = new ArrayList<>();

        String query = "SELECT * FROM " + db_table;
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
        String query = "SELECT * FROM " + db_table + " LIMIT " + pageSize + " OFFSET " + offset;
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

        String query = "SELECT * FROM " + db_table + " WHERE name = ?";
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
        String query = "SELECT COUNT(*) AS total FROM " + db_table;
        int count = 0;

        try (ResultSet rs = connectJDBC.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public void create(WordEntity wordEntity) {
        String query = "INSERT INTO " + db_table + " (name, ipa, type, definition, example) VALUES (?, ?, ?, ?, ?)";
        connectJDBC.executeUpdate(query, wordEntity.getName(), wordEntity.getIpa(), wordEntity.getType(), wordEntity.getDefinition(), wordEntity.getExample());
    }

    public void update(WordEntity wordEntity) {
        String query = "UPDATE " + db_table + " SET name = ?, ipa = ?, type = ?, definition = ?, example = ? WHERE id = ?";
        connectJDBC.executeUpdate(query, wordEntity.getName(), wordEntity.getIpa(), wordEntity.getType(), wordEntity.getDefinition(), wordEntity.getExample(), wordEntity.getId());
    }

    public void delete(WordEntity wordEntity) {
        String query = "DELETE FROM " + db_table + " WHERE id = ?";
        connectJDBC.executeUpdate(query, wordEntity.getId());
    }

    public void deleteAll() {
        String query = "DELETE FROM " + db_table;
        connectJDBC.executeUpdate(query);
    }
}