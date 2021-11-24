package com.example.paymentproject.conroller.user;

import com.example.paymentproject.entity.Transaction;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.PaymentServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

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
    private static final Logger LOGGER = Logger.getLogger(TransactionsController.class);


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
        List<Transaction> transactionList = paymentService.searchAllUserTransaction(page, size, user.getUserId());
        int noOfRecords = paymentService.getNoOfRecords(user.getUserId());
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / size);
        req.setAttribute("transaction", transactionList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        LOGGER.info("User " + user.getUserId() + " get transactions" + transactionList);
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/user/payments/transactionsHistory.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}