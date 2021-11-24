package com.example.paymentproject.conroller.admin;

import com.example.paymentproject.entity.Card;
import com.example.paymentproject.service.impl.CardServiceImpl;
import org.apache.log4j.Logger;

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
    private static final Logger LOGGER = Logger.getLogger(AdminAllCardsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int page = 1;
        int size = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int noOfRecords = cardService.countOfAllUsersCards();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / size);
        List<Card> allCards = cardService.findAllCards(page, size);
        req.setAttribute("allCards", allCards);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        getServletContext().getRequestDispatcher
                        ("/WEB-INF/views/admin/adminAllCardsWithStatus.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int cardId = Integer.parseInt(req.getParameter("hidden"));
        if (req.getParameter("button3") != null) {
            cardService.blockCard(cardId);
            LOGGER.info("ADMIN blocked card: "+cardId);

        } else if (req.getParameter("button4") != null) {
            cardService.unBlockCard(cardId);
            LOGGER.info("ADMIN unblocked card: "+cardId);
        }
    }
}
