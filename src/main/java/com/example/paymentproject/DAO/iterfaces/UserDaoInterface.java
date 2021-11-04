package com.example.paymentproject.DAO.iterfaces;

import com.example.paymentproject.entity.User;

public interface UserDaoInterface {
     void insertUser(User user) ;
     void deleteUser(User user) ;
     boolean checkExistForUser (User user);

}
