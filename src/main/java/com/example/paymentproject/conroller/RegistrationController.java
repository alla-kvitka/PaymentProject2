package com.example.paymentproject.conroller;

import com.example.paymentproject.dao.impl.UserDAO;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.UserServiceImpl;
import com.example.paymentproject.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "registration", value = "/registration")
public class RegistrationController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserServiceImpl userService = new UserServiceImpl();

        if (userService.checkExistForUser(User.createUser(login, password, email))) {
            userService.createUser(User.createUser(login, password, email));
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("message", "User ia already exist");
            req.setAttribute("login", login);
            req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
        }
    }


    public void destroy() {
    }
}