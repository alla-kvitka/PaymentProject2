package com.example.paymentproject;

import com.example.paymentproject.conroller.DBManager;
import com.example.paymentproject.entity.User;

import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) throws SQLException {
        DBManager dbManager = DBManager.getInstance();

       dbManager.insertUser(User.createUser("ajj","343","stj"));
    }

}
