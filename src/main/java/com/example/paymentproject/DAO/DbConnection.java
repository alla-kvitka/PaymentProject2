package com.example.paymentproject.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private DbConnection dbManager;
    private final Connection conn;


    public DbConnection() {
        conn = getConnection();
    }

    public DbConnection getInstance() {
        if (dbManager == null) {
            dbManager = new DbConnection();
        }
        return dbManager;
    }

    public Connection getConnection() {
        try (InputStream inputStream = new FileInputStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String connUrl = properties.getProperty("connection.url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            return DriverManager.getConnection(connUrl, user, password);
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
