package ru.itis.javalab.filters;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebFilter("*")
public class AuthFilter implements Filter {

    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // with Cookies
        /*Cookie[] cookies = request.getCookies();
        Cookie auth = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Auth")) {
                    auth = cookie;
                }
            }
        }
        if (auth != null) {
            List<User> list = usersService.getAllByUUID(auth.getValue());
            if (list.isEmpty()) {
                response.sendRedirect("/login");
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                response.sendRedirect("/users");
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        } else {
            //response.sendRedirect("/login");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("DDDDDD");
            return;
        } */
        String uri = request.getRequestURI();
        if (!uri.equals("/registration") && !uri.equals("/login") && !uri.equals("/users")) {
            HttpSession session = request.getSession(false);
            String uuid = "";
            if ((session != null)&&(session.getAttribute("Auth") != null)) {
                uuid = uuid + session.getAttribute("Auth").toString();
            }
            if (!uuid.equals("")) {
                    List<User> list = usersService.getAllByUUID(uuid);
                    if (list.isEmpty()) {
                        response.sendRedirect("/login");
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    } else {
                        response.sendRedirect("/users");
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
            } else {
                //filterChain.doFilter(servletRequest, servletResponse);
                System.out.println("DDDDDD");
              // return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("DDDDDD");
        return;
    }

    @Override
    public void destroy() {

    }
}
