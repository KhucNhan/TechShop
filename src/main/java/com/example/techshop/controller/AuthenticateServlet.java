package com.example.techshop.controller;

import com.example.techshop.model.OrderDetails;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AuthenticationServlet", value = "/authenticate")
public class AuthenticateServlet extends HttpServlet {
    private DAO dao;

    @Override
    public void init() throws ServletException {
        dao = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "signup":
                showSignUp(req, resp);
                break;
            case "logout":
                logout(req, resp);
                break;
            default:
                showLogin(req, resp);
                break;
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();

        resp.sendRedirect("authenticate/login.jsp");
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
        String rePassword = req.getParameter("rePassword");
        String gender = req.getParameter("gender");
        String dateOfBirth = req.getParameter("dateOfBirth");

        RequestDispatcher dispatcher;

        String message = "Not null requirement";
        if (name.isEmpty()) {
            req.setAttribute("message", message);
            dispatcher = req.getRequestDispatcher("authenticate/signup.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (username.length() < 8) {
            message = "Username must have at least 8 character";
            req.setAttribute("message", message);
            dispatcher = req.getRequestDispatcher("authenticate/signup.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (password.length() < 8 || rePassword.length() < 8) {
            message = "Password must have at least 8 character";
            req.setAttribute("message", message);
            dispatcher = req.getRequestDispatcher("authenticate/signup.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        } else if (!password.equals(rePassword)) {
            message = "Incorrect password";
            req.setAttribute("message", message);
            dispatcher = req.getRequestDispatcher("authenticate/signup.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (gender == null) {
            req.setAttribute("message", message);
            dispatcher = req.getRequestDispatcher("authenticate/signup.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (dateOfBirth.isEmpty()) {
            req.setAttribute("message", message);
            dispatcher = req.getRequestDispatcher("authenticate/signup.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (dao.checkLogin(username, password) == null) {
            User signupUser = new User(name, username, password, gender, Date.valueOf(dateOfBirth));
            dao.insertUser(signupUser);
            message = "Signup success";
        } else {
            message = "This username has exist";
        }

        req.setAttribute("message", message);
        dispatcher = req.getRequestDispatcher("authenticate/signup.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.length() < 8 || password.length() < 8) {
            req.setAttribute("message", "Username and Password must have at least 8 character");
            req.getRequestDispatcher("authenticate/login.jsp").forward(req, resp);
            return;
        }

        User currentUser = dao.checkLogin(username, password);


        if (currentUser != null && currentUser.isStatus()) {
            HttpSession session = req.getSession();
            session.setAttribute("currentUserID", currentUser.getUserID());
            Map<Integer, OrderDetails> cart = new HashMap<>();
            session.setAttribute("cart", cart);
            session.setAttribute("cartItemCount", cart.size());
            resp.sendRedirect("products");
        } else {
            req.setAttribute("message", "Password incorrect or username doesn't exist.");
            req.getRequestDispatcher("authenticate/login.jsp").forward(req, resp);
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
