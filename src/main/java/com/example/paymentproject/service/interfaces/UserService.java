package com.example.paymentproject.service.interfaces;

import com.example.paymentproject.entity.User;

public interface UserService {
    void createUser(User user);

    void deleteUser(User user);

    User getUserInfo(String login);


}
