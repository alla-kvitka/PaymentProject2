package com.example.paymentproject.DAO;


import com.example.paymentproject.DAO.iterfaces.UserDaoInterface;
import com.example.paymentproject.entity.User;

import java.sql.*;


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
                pstmt.setString(3, user.getUser_email());
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

  public boolean  checkPassLogin (String login, String password) throws SQLException {
      ResultSet rs = null;
      try (Connection connection = DBConnection.getInstance().getConnection();
           PreparedStatement ps = connection.prepareStatement("select * from `Users` " +
                   "where `user_login` = ? and `user_password` = ?")) {
          ps.setString(1, login);
          ps.setString (2,password);
           rs = ps.executeQuery();
           if (rs.next()) {
             return true;
           }
      }
        return false;
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
