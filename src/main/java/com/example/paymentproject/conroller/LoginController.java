package com.example.paymentproject.conroller;

import com.example.paymentproject.entity.Enums.Role;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.exeption.UserNotFoundException;
import com.example.paymentproject.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.equals("") || password.equals("")) {
            resp.sendRedirect(req.getContextPath() + "/login");
            LOGGER.error("Error message: login/password cannot be empty");
        }
        try {
            if (userService.loginCheck(login, password)) {
                LOGGER.info("INFO message: Login process starts with login: "
                        + login + " password " + password);
                HttpSession newSession = req.getSession(true);
                newSession.setMaxInactiveInterval(5 * 60);
                User user = userService.getUserInfo(login);
                newSession.setAttribute("user", user);
                Cookie message = new Cookie("currentUser", login);
                resp.addCookie(message);
                if (user.getRole().equals(Role.USER)) {
                    LOGGER.info("Login as USER" + user.getUserId() + "Success");
                    resp.sendRedirect(req.getContextPath() + "/homepage");
                } else if (user.getRole().equals(Role.ADMIN)) {
                    LOGGER.info("Login as ADMIN" + user.getUserId() + " Success");
                    resp.sendRedirect(req.getContextPath() + "/adminHomepage");
                }
            } else {
                LOGGER.error("Error message: Cannot find user with such login/password");
                throw new UserNotFoundException(login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        super.destroy();
    }
}