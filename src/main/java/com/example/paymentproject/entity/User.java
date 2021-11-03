package com.example.paymentproject.entity;

public class User {
    private long user_id;
    private String user_login;
    private String user_password;
    protected String user_email;

    public User() {
    }

    public static User createUser(String login, String password, String email) {
        User user = new User();
        user.setUser_login(login);
        user.setUser_password(password);
        user.setUser_email(email);
        return user;
    }
    public User(String user_login, String user_password, String user_email) {
        this.user_login = user_login;
        this.user_password = user_password;
        this.user_email = user_email;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}