package com.example.paymentproject.conroller;

import com.example.paymentproject.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBManager {
    private static DBManager dbManager;
    private final Connection conn ;
    private static final Lock CONNECTION_LOCK = new ReentrantLock();

    public DBManager() throws SQLException {
        conn = getConnection();
    }

    public static DBManager getInstance() throws SQLException {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public Connection getConnection() {
        try (InputStream inputStream = new FileInputStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String connUrl = properties.getProperty("jdbc:mysql://127.0.0.1:3306/Payments");
            String user = properties.getProperty("root");
            String password = properties.getProperty("AK1@gmail.com");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Payments?ssMode=DISABLE&serverTimezone=UTC", "root", "AK1@gmail.com");
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void insertUser(User user) {
        ResultSet rs = null;
        try (PreparedStatement pstmt = conn.prepareStatement
                ("INSERT INTO USERS VALUES (DEFAULT ,?,?,?)")) {
           if (!checkExistForUser(user)) {
                pstmt.setString(1, user.getUser_login());
                pstmt.setString(2, user.getUser_password());
                pstmt.setString(3, user.getUser_email());
                pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    long idField = rs.getLong(1);
                    user.setUser_id(idField);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
    }

    public Boolean checkExistForUser(User user) {
        boolean isUserExists = false;
        ResultSet rs = null;
        try (PreparedStatement ps = conn.prepareStatement("select 1 from `Users` where `Login` = ?")) {
            ps.setString(1, user.getUser_login());
            rs = ps.executeQuery();
            if (rs.next()) {
                isUserExists = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return isUserExists;
    }

    private static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        }
    }
}
