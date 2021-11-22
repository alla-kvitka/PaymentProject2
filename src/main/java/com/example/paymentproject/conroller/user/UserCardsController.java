package com.example.paymentproject.conroller.user;


import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Enums.CardStatus;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.CardServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "user-cards", value = "/user-cards")
public class UserCardsController extends HttpServlet {

    CardServiceImpl cardService = new CardServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        Cookie[] cookies = req.getCookies();
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("currentUser")) {
                login = cookie.getValue();
            }
        }
        User user = userService.getUserInfo(login);
        List<Card> cardsList = cardService.findAllUserCards(user.getUserId(), page, size);
        int noOfRecords = userService.countOfAllUserCards(user.getUserId());
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / size);
        req.setAttribute("cards", cardsList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/card/cardInformation.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cardId = Integer.parseInt(req.getParameter("hidden"));
        Card card = cardService.searchCardByCardId(cardId);
        if (req.getParameter("button1") != null && card.getCardStatus().equals(CardStatus.ACTIVE)) {
            cardService.blockCard(cardId);
        } else if (req.getParameter("button2") != null && card.getCardStatus().equals(CardStatus.BLOCKED)) {
            cardService.requestToUnblock(cardId);
        }
        req.getRequestDispatcher("/WEB-INF/views/user/card/cardInformation.jsp").forward(req, resp);
    }
}
