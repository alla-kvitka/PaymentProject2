package com.example.paymentproject.conroller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class LogOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        eraseCookie(req);
        resp.sendRedirect(req.getContextPath() + "/index");
    }

    private void eraseCookie(HttpServletRequest req)  {
        req.getSession().invalidate();
    }
}
