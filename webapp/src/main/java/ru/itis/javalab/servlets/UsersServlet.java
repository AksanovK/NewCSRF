package ru.itis.javalab.servlets;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        try {
            configuration.setTemplateLoader(new FileTemplateLoader(new File("D:\\javaNEW\\src\\projects\\webapp\\src\\main\\webapp\\ftlh")));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        try {
            Cookie [] cookies = request.getCookies();
            Cookie cookie = null;
            for (Cookie c : cookies) {
                if (c.getName().equals("color")) {
                    cookie = c;
                }
            }


            Template template = configuration.getTemplate("users.ftlh");
            List<User> users = new ArrayList<>();
            users.add(User.builder()
                    .id(1L)
                    .UUID(UUID.randomUUID().toString())
                    .password("123456")
                    .email("java@mail.ru")
                    .build());
            users.add(User.builder()
                    .id(2L)
                    .UUID(UUID.randomUUID().toString())
                    .password("111111")
                    .email("java2@mail.ru")
                    .build());

            request.setAttribute("userForJsp", users);
            request.setAttribute("color", cookie);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("users", users);
            attributes.put("color", cookie);
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("output.html");
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            try {
                template.process(attributes, fileWriter);
            } catch (TemplateException e) {
                throw new IllegalArgumentException(e);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }




        try {
            //request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
            request.getRequestDispatcher("/ftlh/users.ftlh").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String color = request.getParameter("color");
        Cookie cookie = new Cookie("color", color);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        try {
            response.sendRedirect("/users");
            return;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
