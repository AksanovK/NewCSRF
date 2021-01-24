package ru.itis.javalab.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


public class СsrfFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)  {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getMethod().equals("POST")) {
            String requestCsrf = request.getParameter("_csrf_token");
            String sessionCsrf = (String) request.getSession(false).getAttribute("_csrf_token");
            if (sessionCsrf.equals(requestCsrf)) {
                try {
                    filterChain.doFilter(servletRequest, servletResponse);
                } catch (IOException | ServletException e) {
                    throw new IllegalArgumentException(e);
                }
                return;
            } else {
                try {
                    response.sendRedirect("/login");
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
                return;
            }
        }
        if (request.getMethod().equals("GET")) {
            String csrf = UUID.randomUUID().toString();
            request.setAttribute("_csrf_token", csrf);
            request.getSession().setAttribute("_csrf_token", csrf);
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServletException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
