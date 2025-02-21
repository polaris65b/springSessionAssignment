package com.example.springsessionassignment.common.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;


import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/signup", "/login", "/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("LOGIN_USER") == null) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "need login");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
