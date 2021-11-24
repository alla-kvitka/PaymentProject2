package com.example.paymentproject.filters;

import com.example.paymentproject.entity.Enums.Role;
import com.example.paymentproject.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession();
        String servletPath = request.getServletPath();

        if (request.getServletPath().equals("/login")
                || request.getServletPath().equals("/registration")
                || request.getServletPath().equals("/index.jsp")) {
            filterChain.doFilter(request, response);
        }
        User user = (User) session.getAttribute("user");
        Role role = Optional.ofNullable(user).map(User::getRole).orElse(null);
        if ((servletPath.contains("/payment") && Role.USER.equals(role))
                || (servletPath.contains("/paymentSubmit") && Role.USER.equals(role))
                || (servletPath.contains("/transactions") && Role.USER.equals(role))
                || (servletPath.contains("/user-cards") && Role.USER.equals(role))
                || (servletPath.contains("/homepage") && Role.USER.equals(role))
                || (servletPath.contains("/allCards") && Role.ADMIN.equals(role))
                || (servletPath.contains("/allUsers") && Role.ADMIN.equals(role))
                || (servletPath.contains("/adminHomepage") && Role.ADMIN.equals(role))) {
            filterChain.doFilter(request, response);
        }
    }
}