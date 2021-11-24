package com.example.paymentproject.conroller;

import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.CardServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;
import com.example.paymentproject.utils.Utils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "registration", value = "/registration")
public class RegistrationController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");
        String email = req.getParameter("email");
        LOGGER.info("INFO message: Registration process starts with login: "
                + login + " password " + password + " password-repeat " + passwordRepeat
                + " email" + email);
        UserServiceImpl userService = new UserServiceImpl();
        CardServiceImpl cardService = new CardServiceImpl();
        if (login.equals("") || password.equals("") || passwordRepeat.equals("")
                || email.equals("") || !Utils.checkEmail(email)) {
            LOGGER.error("One of registration parameters is null!");
            req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);

        } else if (!password.equals(passwordRepeat)) {
            LOGGER.error("Password and password-repeat are not equals");
        } else if (userService.checkExistForUser(userService.createUser(login,
                Utils.encryptPassword(password), email))) {
            User user = userService.createUser(userService.createUser(login,
                    Utils.encryptPassword(password), email));
            LOGGER.info("User was created successful");
            try {
                cardService.insertCard(cardService.createCard(user));
                LOGGER.info("Card was created successful");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
        }
    }

    public void destroy() {
        super.destroy();
    }
}