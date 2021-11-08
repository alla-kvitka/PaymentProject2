package com.example.paymentproject.dao.iterfaces;

import com.example.paymentproject.entity.User;

import java.util.List;

public interface UserDaoInterface {
    void insertUser(User user);

    void deleteUser(User user);

    User getUser(String login);

    boolean checkExistForUser(User user);

    List<User> findAllUsers();

}
