package com.example.paymentproject.conroller.admin;

import com.example.paymentproject.service.impl.CardServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "block", value = "/block")
public class AdminBlockController extends HttpServlet {

    CardServiceImpl cardService = new CardServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin/adminBlock.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int cardId = Integer.parseInt(req.getParameter("cardBlock"));
            cardService.blockCard(cardId);
            req.getRequestDispatcher("/WEB-INF/views/admin/adminBlock.jsp").forward(req, resp);
    }
}
