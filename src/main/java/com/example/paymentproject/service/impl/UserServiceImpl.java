package com.example.paymentproject.service.impl;

import com.example.paymentproject.dao.impl.UserDaoImpl;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.interfaces.UserService;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDaoImpl = new UserDaoImpl();

    @Override
    public User createUser(User user) {
       return userDaoImpl.insertUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDaoImpl.deleteUser(user);
    }

    @Override
    public User getUserInfo(String login) {
        return userDaoImpl.getUser(login);
    }

    public boolean checkExistForUser(User user) {
        return userDaoImpl.checkExistForUser(user);
    }
}
