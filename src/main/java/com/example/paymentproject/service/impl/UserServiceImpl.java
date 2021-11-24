package com.example.paymentproject.service.impl;

import com.example.paymentproject.dao.impl.UserDaoImpl;
import com.example.paymentproject.entity.Enums.Role;
import com.example.paymentproject.entity.Enums.UserStatus;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.interfaces.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDaoImpl = new UserDaoImpl();


    public  User createUser(String login, String password, String email) {
        User user = new User();
        user.setUserLogin(login);
        user.setUserPassword(password);
        user.setUserEmail(email);
        user.setRole(Role.USER);
        user.setUserStatus(UserStatus.ACTIVE);
        return user;
    }

    @Override
    public User createUser(User user) {
        return userDaoImpl.insertUser(user);
    }

    public void blockUser(int userId) {
        userDaoImpl.blockUser(userId);
    }

    public void unBlockUser(int userId) {
        userDaoImpl.unBlockUser(userId);
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

    @Override
    public boolean loginCheck(String login, String password) throws SQLException {
        return userDaoImpl.checkPassLogin(login, password);
    }

    @Override
    public List<User> findUsers(int pageNumber, int size) {
        return userDaoImpl.findAllUsers(pageNumber,size);
    }

    @Override
    public User getUserById(int userID) {
        return userDaoImpl.getUserById(userID);
    }

    @Override
    public int countOfAllUsersCards() {
        return userDaoImpl.countOfAllUsers();
    }

    @Override
    public int countOfAllUserCards(int userid) {
        return userDaoImpl.countOfAllUserCards(userid);
    }
}
