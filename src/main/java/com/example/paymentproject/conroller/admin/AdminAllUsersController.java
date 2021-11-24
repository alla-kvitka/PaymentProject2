package com.example.paymentproject.conroller.admin;

import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "allUsers", value = "/allUsers")

public class AdminAllUsersController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdminAllCardsController.class);
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int noOfRecords = userService.countOfAllUsersCards();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / size);
        List<User> users = userService.findUsers(page, size);
        req.setAttribute("allUsers", users);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        getServletContext().getRequestDispatcher("/WEB-INF/views/admin/adminAllUsers.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("hidden"));
        if (req.getParameter("button3") != null) {
            userService.blockUser(userId);
            LOGGER.info("ADMIN BLOCK user " + userId);
        } else if (req.getParameter("button4") != null) {
            userService.unBlockUser(userId);
            LOGGER.info("ADMIN unblock user " + userId);
        }
    }

    public void destroy() {
        super.destroy();
    }
}

