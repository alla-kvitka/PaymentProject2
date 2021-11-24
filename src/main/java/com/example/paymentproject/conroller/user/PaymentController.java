package com.example.paymentproject.conroller.user;

import com.example.paymentproject.conroller.admin.AdminAllCardsController;
import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Enums.CardStatus;
import com.example.paymentproject.entity.Enums.UserStatus;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.CardServiceImpl;
import com.example.paymentproject.service.impl.PaymentServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

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
    private static final Logger LOGGER = Logger.getLogger(AdminAllCardsController.class);
    User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
            req.getRequestDispatcher("/WEB-INF/views/user/payments/doPayment.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int cardId = Integer.parseInt(req.getParameter("userCardId"));
        int paymentSum = Integer.parseInt(req.getParameter("sum"));
        String trType = req.getParameter("trType");
        Card card = cardService.searchCardByCardId(cardId);
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        if (card.getUserId() != user.getUserId()) {
            LOGGER.error("User " + user.getUserId() + " Do not have this card" + cardId);
        }
        if (card.getCardStatus().equals(CardStatus.BLOCKED)) {
            LOGGER.error("Card " + card.getCardId() + " is BLOCKED" + " user " + user.getUserId());
        } else if (card.getUserStatus().equals(UserStatus.BLOCKED)) {
            LOGGER.error("User " + card.getUserId() + " is BLOCKED" + " user " + user.getUserId());
        }
        if (card.getCardStatus().equals(CardStatus.ACTIVE)
                && card.getUserStatus().equals(UserStatus.ACTIVE)
                && card.getUserId() == user.getUserId()) {
            if (trType.equalsIgnoreCase("NEGATIVE")) {
                if (card.getCardSum() <= paymentSum) {
                    LOGGER.error("Payment FAILED " + user.getUserId() + " LOW BALANCE!");
                    req.getRequestDispatcher("/WEB-INF/views/user/payments/doPayment.jsp")
                            .forward(req, resp);
                } else {
                    paymentService.insertPayment(paymentService.createPayment(card, trType, paymentSum));
                    LOGGER.info("Payment negative success for card " + card.getCardId()
                            + " Sum = " + paymentSum + " user " + user.getUserId());
                }
            }
            if (trType.equalsIgnoreCase("POSITIVE")) {
                paymentService.insertPayment(paymentService.createPayment(card, trType, paymentSum));
                LOGGER.info("Payment positive success for card " + card.getCardId()
                        + " Sum = " + paymentSum + " user " + user.getUserId());
            }
        }
        req.getRequestDispatcher("/WEB-INF/views/user/payments/doPayment.jsp")
                .forward(req, resp);
    }
}