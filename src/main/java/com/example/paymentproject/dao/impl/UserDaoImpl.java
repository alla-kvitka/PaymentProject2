package com.example.paymentproject.dao.impl;


import com.example.paymentproject.dao.iterfaces.UserDao;
import com.example.paymentproject.entity.Enums.Role;
import com.example.paymentproject.entity.Enums.UserStatus;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {

    @Override
    public void unBlockUser(int userId) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("UPDATE USERS SET user_status=? WHERE user_id=?")) {
            pstmt.setString(1, "ACTIVE");
            pstmt.setLong(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("UPDATE CARDS SET user_status=? WHERE user_id=?")) {
            pstmt.setString(1, "ACTIVE");
            pstmt.setLong(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void blockUser(int userId) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("UPDATE USERS SET user_status=? WHERE user_id=?")) {
            pstmt.setString(1, "BLOCKED");
            pstmt.setLong(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("UPDATE CARDS SET user_status=? WHERE user_id=?")) {
            pstmt.setString(1, "BLOCKED");
            pstmt.setLong(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User insertUser(User user) {
        long randomBill = Utils.randomLong();
        int userIdRandom = Utils.randomInt();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("INSERT INTO USERS VALUES (?, ?, ?, ?,?,?,?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            if (checkExistForUser(user)) {
                user.setUserId(userIdRandom);
                user.setUserBill(randomBill);
                pstmt.setLong(1, user.getUserId());
                pstmt.setString(2, user.getUserLogin());
                pstmt.setString(3, user.getUserPassword());
                pstmt.setString(4, user.getUserEmail());
                pstmt.setString(5, user.getRole().toString());
                pstmt.setLong(6, user.getUserBill());
                pstmt.setString(7,user.getUserStatus().toString());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUser(User user) {


    }

    @Override
    public boolean checkExistForUser(User user) {
        boolean isUserExists = false;
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from `Users` where `Login` = ?")) {
            ps.setString(1, user.getUserLogin());
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

    @Override
    public boolean checkPassLogin(String login, String password) throws SQLException {
        ResultSet rs;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from `Users` " +
                     "where `user_login` = ? and `user_password` = ?")) {
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUser(String login) {
        User user = null;
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM `users` WHERE `user_login`= ?")) {
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserPassword(rs.getString("user_password"));
                user.setUserLogin(rs.getString("user_login"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserBill(rs.getLong("user_bill"));
                user.setRole(Role.valueOf(rs.getString("user_role")));
                user.setUserStatus(UserStatus.valueOf(rs.getString("user_status")));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return user;
    }

    @Override
    public User getUserById(int userID) {
        User user = null;
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM `users` WHERE `user_id`= ?")) {
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserPassword(rs.getString("user_password"));
                user.setUserLogin(rs.getString("user_login"));
                user.setUserEmail(rs.getString("user_email"));
                user.setRole(Role.valueOf(rs.getString("user_role")));
                user.setUserStatus(UserStatus.valueOf(rs.getString("user_status")));

            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT user_id, user_login, user_email,user_status FROM users " +
                     "WHERE user_role like 'USER'")) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user= new User();
                user.setUserId(rs.getInt(1));
                user.setUserLogin(rs.getString(2));
                user.setUserEmail(rs.getString(3));
                user.setUserStatus(UserStatus.valueOf(rs.getString(4)));
                users.add(user);
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
