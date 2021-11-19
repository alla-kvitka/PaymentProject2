package com.example.paymentproject.conroller.user;

import com.example.paymentproject.entity.Transaction;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.PaymentServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "transactions", value = "/transactions")
public class TransactionsController extends HttpServlet {

    PaymentServiceImpl paymentService = new PaymentServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //замінити витгування логіна на сесію
        Cookie[] cookies = req.getCookies();
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("currentUser")) {
                login = cookie.getValue();
            }
        }
        User user = userService.getUserInfo(login);
        List<Transaction> transactionList = paymentService.searchAllUserTransaction(user.getUserId());
        req.setAttribute("transaction", transactionList);
        RequestDispatcher view = getServletContext().getRequestDispatcher("/WEB-INF/views/user/payments/transactionsHistory.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
