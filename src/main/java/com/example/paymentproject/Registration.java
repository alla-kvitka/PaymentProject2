package com.example.paymentproject;

import com.example.paymentproject.conroller.DBManager;
import com.example.paymentproject.entity.User;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "registration", value = "/registration")
public class Registration extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String login = request.getParameter("login");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1 align=\"center\">" + "Registration" + "</h1>");
        out.println("</body></html>");

        response.getWriter().write("<html>"+"<head><head>"
                +"<body bgcolor=\"#F5F5DC\" align=\"center\">"
                +"<h4>login</h4>"
                +"<form method = post>"+
                "<input type = 'text' name='login'/>"
                + "<h4>password</h4>"+
                "<input type = 'text' name='password'/>"+
                 "<h4>email</h4>"+
                "<input type = 'text' name='email'/>"+
                "</br>"+"</br>"+
                "<input type = 'submit' name='submit'/>"
                +"<h4>dhsgswgsgbhswghw<h4>"+login+
                "</form></body></html>");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
        DBManager dbManager = null;
        try {
             dbManager = DBManager.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbManager.insertUser(User.createUser(req.getParameter("name"),req.getParameter("password"),
                req.getParameter("email")));

    }

    public void destroy() {
    }
}