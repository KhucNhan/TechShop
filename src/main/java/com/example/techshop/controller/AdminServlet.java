package com.example.techshop.controller;

import com.example.techshop.model.Product;
import com.example.techshop.model.User;
import com.example.techshop.service.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admins")
public class AdminServlet extends HttpServlet {
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
            case "logout":
//                logout(req, resp);
                break;
            case "products":
                goToProducts(req, resp);
                break;
            case "create":
                showCreateUser(req, resp);
                break;
            case "delete":
                showDeleteUser(req, resp);
                break;
            case "edit":
                showUpdateUser(req, resp);
                break;
            default:
                showAllUsers(req, resp);
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
            case "create":
                createUser(req, resp);
                break;
            case "edit":
                updateUser(req, resp);
                break;
            default:
                break;
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
        String image = req.getParameter("image");
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String role = req.getParameter("role");
        String status = req.getParameter("status");

        String message = "";
        if (image.isEmpty()) {
            message = "x";
            req.setAttribute("imageMessage", message);
        }

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

        if (role == null) {
            message = "x";
            req.setAttribute("roleMessage", message);
        }

        if (status == null) {
            message = "x";
            req.setAttribute("statusMessage", message);
        }

        if (message.isEmpty()) {
            User signupUser = new User(image, name, username, password, gender, Date.valueOf(dateOfBirth), role, Boolean.parseBoolean(status));
            dao.insertUser(signupUser);
        }
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) {
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

        RequestDispatcher dispatcher = req.getRequestDispatcher("admin/create.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showUpdateUser(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("userID"));
        User user = dao.selectUser(id);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin/edit.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showDeleteUser(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("userID"));
        dao.deleteUser(id);

        List<User> users = dao.selectAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showCreateUser(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void goToProducts(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("products");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAllUsers(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users = dao.selectAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
