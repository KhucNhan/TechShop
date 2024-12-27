<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/17/2024
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <title>Edit user</title>
    <style>
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }

        .container {
            height: 100%;
            align-content: center;
            justify-items: center;
        }

        .container span, label {
            width: 20%;
        }

        .container input, select {
            width: 70%;
        }

        .container form {
            width: 100%;
            text-align: center;
            background-color: #4B5563;
            border: 2px solid #3B82F6;
            padding: 20px;
            border-radius: 8px;
        }

        .row {
            width: 100%;
            justify-content: center;
        }
    </style>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        
        <div class="col-md-6">
            <form action="/users?action=edit" method="post">
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Image</span>
                    <input value="${user.image}" name="image" type="text" class="form-control" placeholder="Image url"
                           aria-label="Image url" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon2">Name</span>
                    <input value="${user.name}" name="name" type="text" class="form-control" placeholder="Name"
                           aria-label="Name" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon3">Username</span>
                    <input value="${user.username}" name="username" type="text" class="form-control"
                           placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <label class="input-group-text" for="inputGroupSelect01">Gender</label>
                    <select class="form-select" id="inputGroupSelect01" name="gender">
                        <option ${user.gender == 'Male' ? 'selected' : ''} value="Male">Male</option>
                        <option ${user.gender == 'Female' ? 'selected' : ''} value="Female">Female</option>
                        <option ${user.gender == 'Other' ? 'selected' : ''} value="Other">Other</option>
                    </select>
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon4">Date of birth</span>
                    <input value="${user.dateOfBirth}" name="dateOfBirth" type="date" class="form-control"
                           placeholder="Date of birth" aria-label="Date of birth" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <label class="input-group-text" for="inputGroupSelect01">Role</label>
                    <select class="form-select" id="inputGroupSelect02" name="role">
                        <option ${user.role == 'admin' ? 'selected' : ''} value="admin">Admin</option>
                        <option ${user.role == 'user' ? 'selected' : ''} value="user">User</option>
                    </select>
                </div>

                <div class="input-group mb-3">
                    <label class="input-group-text" for="inputGroupSelect01">Status</label>
                    <select class="form-select" id="inputGroupSelect03" name="status">
                        <option ${user.status ? 'selected' : ''} value="true">Active</option>
                        <option ${user.status ? '' : 'selected'} value="false">Inactive</option>
                    </select>
                </div>

                <input type="hidden" name="userID" value="${user.userID}">
                <input type="hidden" name="password" value="${user.password}">

                <button class="btn btn-primary" type="submit">Update</button>
            </form>
        </div>
        
    </div>
</div>

<% String message = (String) request.getAttribute("message");
    String alertType = (String) request.getAttribute("alertType");
%>

<% if (message != null) { %>
<div style="position: fixed; top: 80px; left: 20px; z-index: 1050; width: auto; position-area: top;" class="alert alert-<%= alertType %> alert-dismissible fade show" role="alert">
    <%= message %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>

</body>
</html>
