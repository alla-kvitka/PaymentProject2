package com.example.paymentproject.filters;

import com.example.paymentproject.entity.Enums.Role;
import com.example.paymentproject.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter
public class AuthorizationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AuthorizationFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession();
        String servletPath = request.getServletPath();
        User user = (User) session.getAttribute("user");
        if (servletPath.contains("/payment") && !user.getRole().equals(Role.USER)
                || servletPath.contains("/paymentSubmit") && !user.getRole().equals(Role.USER)
                || servletPath.contains("/transactions") && !user.getRole().equals(Role.USER)
                || servletPath.contains("/user-cards") && !user.getRole().equals(Role.USER)
                || servletPath.contains("/homepage") && !user.getRole().equals(Role.USER)
                || servletPath.contains("/allCards") && !user.getRole().equals(Role.ADMIN)
                || servletPath.contains("/block") && !user.getRole().equals(Role.ADMIN)
                || servletPath.contains("/unblock") && !user.getRole().equals(Role.ADMIN)
                || servletPath.contains("/adminHomepage") && !user.getRole().equals(Role.ADMIN)) {
            response.sendRedirect("/WEB-INF/views/error/error.jsp");
            logger.error("Access Denied");
        }

        filterChain.doFilter(request, response);
    }


    public void destroy() {
    }

}
