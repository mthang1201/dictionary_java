package com.uet.dictionary_java.repositories;

import com.uet.dictionary_java.WordEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkRepository extends WordRepository {
    public BookmarkRepository() {
        super();
    }

    public void savedWord(WordEntity wordEntity) {
        String query = "INSERT INTO savedWords (name, ipa, type, definition, example) VALUES (?, ?, ?, ?, ?)";
        connectJDBC.executeUpdate(query, wordEntity.getName(), wordEntity.getIpa(), wordEntity.getType(), wordEntity.getDefinition(), wordEntity.getExample());
    }

    public List<WordEntity> getSavedWords() {
        List<WordEntity> words = new ArrayList<WordEntity>();
        String query = "SELECT * FROM savedWords";
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

                words.add(wordEntity);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return words;
    }

    public void removeSavedWord(WordEntity wordEntity) {
        String query = "DELETE FROM savedWords WHERE id = ?";
        connectJDBC.executeUpdate(query, wordEntity.getId());
    }

    public void clearBookmark() {
        String query = "DELETE FROM savedWords";
        connectJDBC.executeUpdate(query);
    }
}
