package com.example.paymentproject.conroller.user;

import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Enums.CardStatus;
import com.example.paymentproject.entity.Enums.UserStatus;
import com.example.paymentproject.entity.Payment;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.CardServiceImpl;
import com.example.paymentproject.service.impl.PaymentServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "payment", value = "/payment")
public class PaymentController extends HttpServlet {
    CardServiceImpl cardService = new CardServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        Cookie[] cookies = req.getCookies();
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("currentUser")) {
                login = cookie.getValue();
            }
        }
        UserServiceImpl userService = new UserServiceImpl();
        user = userService.getUserInfo(login);
        if (user != null) {
            req.getRequestDispatcher("/WEB-INF/views/user/payments/doPayment.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cardId = Integer.parseInt(req.getParameter("userCardId"));
        int paymentSum = Integer.parseInt(req.getParameter("sum"));
        String trType = req.getParameter("trType");
        Card card = cardService.searchCardByCardId(cardId);
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        if (card.getCardStatus().equals(CardStatus.ACTIVE)
                && card.getUserStatus().equals(UserStatus.ACTIVE)) {
            if (trType.equalsIgnoreCase("NEGATIVE")) {
                if (card.getCardSum() <= paymentSum) {
                    req.getRequestDispatcher("/WEB-INF/views/user/payments/doPayment.jsp").forward(req, resp);
                } else {
                    paymentService.insertPayment(Payment.createPayment(card, trType, paymentSum));
                }
            }
            if (trType.equalsIgnoreCase("POSITIVE")) {
                paymentService.insertPayment(Payment.createPayment(card, trType, paymentSum));
            }
        }
        req.getRequestDispatcher("/WEB-INF/views/user/payments/doPayment.jsp").forward(req, resp);
    }
}
