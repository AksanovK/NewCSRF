package ru.itis.javalab.servlets;

import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
        this.passwordEncoder = (PasswordEncoder) servletContext.getAttribute("passwordEncoder");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        req.getRequestDispatcher("/ftlh/login.ftlh").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        List<User> list = usersService.getAllByEmail(email);
        User user = list.get(0);
        String password1 = "";
        String uuid = "";
        if (user != null) {
            password1 = user.getPassword();
            uuid = user.getUUID();
        }
        if (passwordEncoder.matches(password, password1)) {
            /*Cookie cookie = new Cookie("Auth", uuid);
            cookie.setMaxAge(60*60*24);
            resp.addCookie(cookie);*/
            req.getSession().setAttribute("Auth", uuid);
            try {
                resp.sendRedirect("/users");
                return;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            try {
                resp.sendRedirect("/registration");
                return;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
