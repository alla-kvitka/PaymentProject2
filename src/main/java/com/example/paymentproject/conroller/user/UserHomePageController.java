package com.example.paymentproject.conroller.user;

import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.CardServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;
import com.example.paymentproject.utils.Utils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "homepage", value = "/homepage")
public class UserHomePageController extends HttpServlet {
    CardServiceImpl cardService = new CardServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
    private static final Logger LOGGER = Logger.getLogger(UserHomePageController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/homePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        Cookie[] cookies = req.getCookies();
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("currentUser")) {
                login = cookie.getValue();
            }
        }
        user = userService.getUserInfo(login);
        try {
            if (req.getParameter("button1") != null &&
                    userService.loginCheck(login,
                            Utils.encryptPassword(req.getParameter("password")))) {
                try {
                    cardService.insertCard(cardService.createCard(user));
                    LOGGER.info("User" + user.getUserId() + "created new card ");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}