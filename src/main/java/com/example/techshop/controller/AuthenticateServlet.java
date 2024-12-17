package com.example.techshop.controller;

import com.example.techshop.model.User;
import com.example.techshop.service.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AuthenticationServlet", value = "/authenticate")
public class AuthenticateServlet extends HttpServlet {
    private DAO dao;

    @Override
    public void init() throws ServletException {
        dao = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "signup":
                showSignUp(req, resp);
                break;
            default:
                showLogin(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "login":
                login(req, resp);
                break;
            case "signup":
                signup(req, resp);
                break;
        }
    }

    private void signup(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String dateOfBirth = req.getParameter("dateOfBirth");

        String message = "";
        if (name.isEmpty()) {
            message = "x";
            req.setAttribute("nameMessage", message);
        }

        if (username.length() < 8) {
            message = "x";
            req.setAttribute("usernameMessage", message);
        }

        if (password.length() < 8) {
            message = "x";
            req.setAttribute("passwordMessage", message);
        }

        if (gender == null) {
            message = "x";
            req.setAttribute("genderMessage", message);
        }

        if (dateOfBirth.isEmpty()) {
            message = "x";
            req.setAttribute("dateMessage", message);
        }

        if (message.isEmpty()) {
            User signupUser = new User(name, username, password, gender, Date.valueOf(dateOfBirth));
            dao.insertUser(signupUser);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("authenticate/signup.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User currentUser = dao.checkLogin(username, password);

        try {
            if (currentUser != null) {
                HttpSession session = req.getSession();

                resp.sendRedirect("products");
            } else {
                resp.sendRedirect("error-404.jsp");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showSignUp(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("authenticate/signup.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showLogin(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("authenticate/login.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
