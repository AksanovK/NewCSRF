package ru.itis.javalab.servlets;


import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

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
        //req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        req.getRequestDispatcher("/ftlh/registration.ftlh").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String encodedPassword = passwordEncoder.encode(password);
        String uuid = UUID.randomUUID().toString();
        User user = User.builder()
                .UUID(uuid)
                .password(encodedPassword)
                .email(email)
                .build();
        usersService.addUser(user);
        req.getSession().setAttribute("Auth", uuid);
        try {
            resp.sendRedirect("/users");
            return;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
}
