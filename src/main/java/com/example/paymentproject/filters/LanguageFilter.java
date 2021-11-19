package com.example.paymentproject.filters;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


    @WebFilter("/*")
    public class LanguageFilter implements Filter {

        public static final String EN = "en";
        public static final String UK = "uk";
        public static final String LANG = "lang";
        public static final String ENCODING = "UTF-8";

        @Override
        public void init(FilterConfig filterConfig) {

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws ServletException, IOException {
            request.setCharacterEncoding(ENCODING);
            response.setCharacterEncoding(ENCODING);
            HttpServletRequest req = (HttpServletRequest) request;
            String lang = getCookieValue(req);
            if (lang != null && (lang.equals(EN) || lang.equals(UK)))
                request.setAttribute(LANG, lang);
            else
                request.setAttribute(LANG, UK);
            chain.doFilter(request, response);
        }

        private String getCookieValue(HttpServletRequest request) {
            if (request != null) {
                Cookie[] cook = request.getCookies();
                if (cook != null)
                    for (Cookie s : cook) {
                        if (LANG.equalsIgnoreCase(s.getName())) {
                            return s.getValue();
                        }
                    }
            }
            return null;
        }

        @Override
        public void destroy() {
        }

    }

