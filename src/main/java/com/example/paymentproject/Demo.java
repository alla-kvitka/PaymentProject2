package com.example.paymentproject;

import com.example.paymentproject.DAO.UserDAO;
import com.example.paymentproject.entity.User;

import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) throws SQLException {

        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(User.createUser("arh", "234", "xtrjx"));

    }

}
