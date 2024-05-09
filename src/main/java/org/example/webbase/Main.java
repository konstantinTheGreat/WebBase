package org.example.webbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/WebServer";
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(url, "postgres", "gustavofabelo2169");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
