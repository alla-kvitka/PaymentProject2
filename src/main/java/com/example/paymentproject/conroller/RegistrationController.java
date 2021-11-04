package com.example.paymentproject.conroller;

import com.example.paymentproject.DAO.UserDAO;
import com.example.paymentproject.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.System.out;

@WebServlet(name = "registration", value = "/registration")
public class RegistrationController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserDAO userDAO = new UserDAO();
        if (userDAO.checkExistForUser(User.createUser(login, password, email))) {
            userDAO.insertUser(User.createUser(login, password, email));
            resp.sendRedirect("/WEB-INF/login.jsp");
        } else {
            req.setAttribute("message", "User ia already exist");
            req.setAttribute("login", login);
            req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
        }
    }


    public void destroy() {
    }
}