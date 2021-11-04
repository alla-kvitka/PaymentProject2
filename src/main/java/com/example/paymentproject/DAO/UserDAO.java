package com.example.paymentproject.DAO;


import com.example.paymentproject.DAO.iterfaces.UserDaoInterface;
import com.example.paymentproject.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO implements UserDaoInterface {
    DbConnection dbConnection = new DbConnection();

    @Override
    public void insertUser(User user) {
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("INSERT INTO USERS VALUES (DEFAULT, ?, ?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            if (checkExistForUser(user)) {
                pstmt.setString(1, user.getUser_login());
                pstmt.setString(2, user.getUser_password());
                pstmt.setString(3, user.getUser_email(rs.getString("email")));
                pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    long idField = rs.getLong(1);
                    user.setUser_id(idField);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
    }

    @Override
    public void deleteUser(User user) {
    }

    public boolean checkExistForUser(User user) {
        boolean isUserExists = false;
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select 1 from `Users` where `Login` = ?")) {
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
        return !isUserExists;
    }

    public User checkPassLogin(String login, String password) throws SQLException {
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from `Users` " +
                     "where `user_login` = ? and `user_password` = ?")) {
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getUser(login);
            }
        }
        return null;
    }

    public User getUser(String login) {
        User user = null;
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login=?")) {
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getLong("id"));
                user.setUser_login(rs.getString("login"));
                user.getUser_email(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return user;
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT id, login FROM users")) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString(1), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return users;
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
