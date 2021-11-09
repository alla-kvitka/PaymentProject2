package com.example.paymentproject.service.interfaces;

import com.example.paymentproject.entity.User;

public interface UserService {

    User createUser(User user);

    void deleteUser(User user);

    User getUserInfo(String login);

    boolean checkExistForUser(User user);


}
