package com.example.paymentproject.entity;

import com.example.paymentproject.entity.Enums.Role;
import com.example.paymentproject.entity.Enums.UserStatus;

public class User {
    private int userId;
    private String userLogin;
    private String userPassword;
    private String userEmail;
    private Role role;
    private long userBill;
    private UserStatus userStatus;
    public User() {
    }


    public static User createUser(String login, String password, String email) {
        User user = new User();
        user.setUserLogin(login);
        user.setUserPassword(password);
        user.setUserEmail(email);
        user.setRole(Role.USER);
        user.setUserStatus(UserStatus.ACTIVE);
        return user;
    }

    public User(int userId, String userLogin, String userPassword,
                String userEmail, Role role, long userBill, UserStatus userStatus) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.role = role;
        this.userBill = userBill;
        this.userStatus=userStatus;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getUserBill() {
        return userBill;
    }

    public void setUserBill(long userBill) {
        this.userBill = userBill;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

}