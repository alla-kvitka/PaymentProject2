package com.example.paymentproject.DAO.iterfaces;

import com.example.paymentproject.entity.User;

import java.util.List;

public interface UserDaoInterface {
    void insertUser(User user);

    void deleteUser(User user);

    User getUser(String login);

    List<User> findAllUsers();

}
