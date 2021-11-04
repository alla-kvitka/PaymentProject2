package com.example.paymentproject.conroller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1 align=\"center\">" + "Login" + "</h1>");
        out.println("</body></html>");

        response.getWriter().write("<html>"+"<head><head>"
        +"<body bgcolor=\"#F5F5DC\" align=\"center\">"+"<h4>login</h4>"+"<form method = get>"+
                "<input type = 'text' name=login/>"
                + "<h4>password</h4>"+
                "<form method = get>"+
                "<input type = 'text' name=password/>"+
                "</br>"+"</br>"+
                "<form method = get>"+
                "<input type = 'submit' name=submit/>");
    }

    public void destroy() {
    }
}
