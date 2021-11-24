package com.example.paymentproject.conroller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class LogOutController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LogOutController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        LOGGER.info("LogOut successful");
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        eraseCookie(req);
        doGet(req, resp);
    }

    private void eraseCookie(HttpServletRequest req) {
        req.getSession().invalidate();
    }
}