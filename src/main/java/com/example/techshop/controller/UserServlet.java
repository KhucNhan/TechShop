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
            case "active":
                showActiveUser(req, resp);
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

    private void showActiveUser(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("userID"));
        User user = dao.selectUser(id);
        user.setStatus(true);
        dao.updateUser(id, user);

        List<User> users = dao.selectAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
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

        String message = "Not null requirement";
        if (image.isEmpty()) {
            req.setAttribute("message", message);
            req.setAttribute("alertType", "alert");
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/updateInformation.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (name.isEmpty()) {
            req.setAttribute("message", message);
            req.setAttribute("alertType", "alert");
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/updateInformation.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (username.length() < 8) {
            req.setAttribute("message", "Username must have at least 8 character");
            req.setAttribute("alertType", "alert");
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/updateInformation.jsp");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (dateOfBirth.isEmpty()) {
            req.setAttribute("message", message);
            req.setAttribute("alertType", "alert");
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

        message = "Update information success";
        req.setAttribute("message", message);
        req.setAttribute("alertType", "success");

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
        
        int userID = Integer.parseInt(req.getParameter("userID"));
        User user = dao.selectUser(userID);

        String message = "Not null requirement";
        if (checkNullInput(req, resp, image, message, user)) return;

        if (checkNullInput(req, resp, name, message, user)) return;

        if (username.length() < 8) {
            req.setAttribute("message", "Username must have at least 8 character");
            req.setAttribute("alertType", "alert");
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (checkNullInput(req, resp, status, message, user)) return;

        if (checkNullInput(req, resp, role, message, user)) return;

        if (checkNullInput(req, resp, gender, message, user)) return;

        if (checkNullInput(req, resp, dateOfBirth, message, user)) return;

        User signupUser = new User(image, name, username, password, gender, Date.valueOf(dateOfBirth), role, Boolean.parseBoolean(status));
        dao.updateUser(userID, signupUser);
        req.setAttribute("message", "Edit user success");
        req.setAttribute("alertType", "success");
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkNullInput(HttpServletRequest req, HttpServletResponse resp, String input, String message, User user) {
        if (input.isEmpty()) {
            req.setAttribute("message", message);
            req.setAttribute("alertType", "alert");
            req.setAttribute("user", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    private boolean checkNullInput(HttpServletRequest req, HttpServletResponse resp, String input, String message) {
        if (input.isEmpty()) {
            req.setAttribute("message", message);
            req.setAttribute("alertType", "alert");
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) {
        String image = req.getParameter("image");
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String role = req.getParameter("role");

        String message = "Not null and invalidate requirement";

        if (checkNullInput(req, resp, name, message)) return;

        if (checkNullInput(req, resp, gender, message)) return;

        if (checkNullInput(req, resp, dateOfBirth, message)) return;

        if (username.length() < 8) {
            req.setAttribute("message", "Username must have at least 8 character");
            req.setAttribute("alertType", "alert");
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (password.length() < 8) {
            req.setAttribute("message", "Password must have at least 8 character");
            req.setAttribute("alertType", "alert");
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (image.isEmpty()) {
            User signupUser = new User(name, username, password, gender, Date.valueOf(dateOfBirth), role);
            dao.insertUser(signupUser);
        } else {
            User signupUser = new User(image, name, username, password, gender, Date.valueOf(dateOfBirth), role);
            dao.insertUserWithImage(signupUser);
        }

        req.setAttribute("message", "Create user success");
        req.setAttribute("alertType", "success");
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
