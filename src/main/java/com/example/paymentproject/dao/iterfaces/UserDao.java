package com.example.paymentproject.dao.iterfaces;

import com.example.paymentproject.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void unBlockUser(int userId);

    void blockUser(int userId);

    User insertUser(User user);

    void deleteUser(User user);

    User getUser(String login);

    boolean checkExistForUser(User user);

    boolean checkPassLogin(String login, String password) throws SQLException;

    List<User> findAllUsers(int pageNumber, int size);

    User getUserById(int userID);

    int countOfAllUsers ();

    int countOfAllUserCards(int userid);
}
