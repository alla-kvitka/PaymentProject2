package com.example.paymentproject.conroller;

import com.example.paymentproject.DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserDAO userDAO = new UserDAO();
        try {
            if (userDAO.checkPassLogin(login, password)!=null) {
                resp.sendRedirect( "/homepage");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void destroy() {
    }

}
