package com.example.paymentproject.conroller.user;


import com.example.paymentproject.entity.Card;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("currentUser")) {
                login = cookie.getValue();
            }
        }
        UserServiceImpl userService = new UserServiceImpl();
        CardServiceImpl cardService = new CardServiceImpl();
        User user = userService.getUserInfo(login);
        List<Card> cardsList = cardService.findAllUsersCards(user.getUserId());
        req.setAttribute("cards",cardsList);
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/card/cardInformation.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cardId = Integer.parseInt(req.getParameter("hidden"));

        if (req.getParameter("button1")!=null)
        {
            cardService.blockCard(cardId);
        }
        else if (req.getParameter("button2") != null) {
            cardService.unBlockCard(cardId);
        }
        req.getRequestDispatcher("/WEB-INF/views/user/card/cardInformation.jsp").forward(req, resp);
    }
}
