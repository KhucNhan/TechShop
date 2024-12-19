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
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
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
            case "updateInformation":
                showUpdateUserInformation(req, resp);
                break;
            case "changePassword":
                showChangePassword(req, resp);
                break;
            default:
                showAllUsers(req, resp);
                break;
        }
    }

    private void showChangePassword(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/changePassword.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showUpdateUserInformation(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int id = (Integer) session.getAttribute("currentUserID");
        User user = dao.selectUser(id);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/updateInformation.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
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
            case "updateInformation":
                updateUserInformation(req, resp);
                break;
            case "changePassword":
                changePassword(req, resp);
                break;
            default:
                break;
        }
    }

    private void updateUserInformation(HttpServletRequest req, HttpServletResponse resp) {
        String image = req.getParameter("image");
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String gender = req.getParameter("gender");
        String dateOfBirth = req.getParameter("dateOfBirth");

        HttpSession session = req.getSession();
        int id = (Integer) session.getAttribute("currentUserID");
        User user = dao.selectUser(id);
        req.setAttribute("user", user);

        String message = "";
        if (image.isEmpty()) {

            message = "x";
            req.setAttribute("imageMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/updateInformation.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (name.isEmpty()) {
            message = "x";
            req.setAttribute("nameMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/updateInformation.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (username.length() < 8) {
            message = "x";
            req.setAttribute("usernameMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/updateInformation.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (gender == null) {
            message = "x";
            req.setAttribute("genderMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/updateInformation.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (dateOfBirth.isEmpty()) {
            message = "x";
            req.setAttribute("dateMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/updateInformation.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        user.setImage(image);
        user.setName(name);
        user.setUsername(username);
        user.setGender(gender);
        user.setDateOfBirth(Date.valueOf(dateOfBirth));

        message = "v";
        req.setAttribute("success", message);

        dao.updateUser(id, user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/updateInformation.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void changePassword(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int userID = (Integer) session.getAttribute("currentUserID");
        User user = dao.selectUser(userID);

        String oldPassword = req.getParameter("oldPassword");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");

        String message = "";
        if (!user.getPassword().equals(oldPassword)) {
            message = "x";
            req.setAttribute("oldPassword", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/changePassword.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (!password.equals(rePassword)) {
            message = "x";
            req.setAttribute("rePassword", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/changePassword.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        user.setPassword(password);
        dao.updateUser(userID, user);
        message = "v";
        req.setAttribute("success", message);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/changePassword.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
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
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (name.isEmpty()) {
            message = "x";
            req.setAttribute("nameMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (username.length() < 8) {
            message = "x";
            req.setAttribute("usernameMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (password.length() < 8) {
            message = "x";
            req.setAttribute("passwordMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (gender == null) {
            message = "x";
            req.setAttribute("genderMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (dateOfBirth.isEmpty()) {
            message = "x";
            req.setAttribute("dateMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (role == null) {
            message = "x";
            req.setAttribute("roleMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (status == null) {
            message = "x";
            req.setAttribute("statusMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        User signupUser = new User(image, name, username, password, gender, Date.valueOf(dateOfBirth), role, Boolean.parseBoolean(status));
        dao.insertUser(signupUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
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
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (username.length() < 8) {
            message = "x";
            req.setAttribute("usernameMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (password.length() < 8) {
            message = "x";
            req.setAttribute("passwordMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (gender == null) {
            message = "x";
            req.setAttribute("genderMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (dateOfBirth.isEmpty()) {
            message = "x";
            req.setAttribute("dateMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        User signupUser = new User(name, username, password, gender, Date.valueOf(dateOfBirth));
        dao.insertUser(signupUser);

        RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showCreateUser(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
