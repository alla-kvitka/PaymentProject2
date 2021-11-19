package com.example.paymentproject.conroller.admin;

import com.example.paymentproject.entity.Card;
import com.example.paymentproject.service.impl.CardServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "allCards", value = "/allCards")
public class AdminAllCardsController extends HttpServlet {
    CardServiceImpl cardService = new CardServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Card> allCards = cardService.findAllCards();
        req.setAttribute("allCards", allCards);
        getServletContext().getRequestDispatcher("/WEB-INF/views/admin/adminAllCardsWithStatus.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cardId = Integer.parseInt(req.getParameter("hidden"));
        if (req.getParameter("button3") != null) {
            cardService.blockCard(cardId);
        } else if (req.getParameter("button4") != null) {
            cardService.unBlockCard(cardId);
        }
    }
}
