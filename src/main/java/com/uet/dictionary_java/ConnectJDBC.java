package com.uet.dictionary_java;

import java.sql.*;

public class ConnectJDBC {

    public Connection connect() {
        Connection conn;
        try {
            String url = "jdbc:mysql://localhost:3306/dictionary_db";
            String username = "root";
            String password = "1201";
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public ResultSet executeQuery(String query) {
        Statement statement;
        ResultSet rs;
        try {
            statement = connect().createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
}
