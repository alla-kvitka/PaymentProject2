package com.example.paymentproject.service.impl;

import com.example.paymentproject.dao.impl.UserDAO;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.interfaces.UserService;

public class UserServiceImpl implements UserService {
    UserDAO userDAO = new UserDAO();

    @Override
    public void createUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User getUserInfo(String login) {
        return userDAO.getUser(login);
    }

    public boolean checkExistForUser(User user) {
        return userDAO.checkExistForUser(user);
    }
}
