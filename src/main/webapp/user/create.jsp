<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/17/2024
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <title>Create new user</title>
    <style>
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }

        .container  span, label {
            width: 20%;
        }

        .container  input, select {
            width: 70%;
        }

        .container  form {
            width: 100%;
            text-align: center;
            background-color: #4B5563;
            border: 2px solid #3B82F6;
            padding: 20px;
            border-radius: 8px;
        }

        .container  .row {
            justify-content: center;
        }
    </style>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        
        <div class="col-md-6">
            <form action="/users?action=create" method="post">
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Image</span>
                    <input name="image" type="text" class="form-control" placeholder="Image url"
                           aria-label="Image url" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon2">Name</span>
                    <input name="name" type="text" class="form-control" placeholder="Name"
                           aria-label="Name" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon3">Username</span>
                    <input name="username" type="text" class="form-control"
                           placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon4">Password</span>
                    <input name="password" type="text" class="form-control"
                           placeholder="Password" aria-label="Password" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <label class="input-group-text" for="inputGroupSelect01">Gender</label>
                    <select class="form-select" id="inputGroupSelect01" name="gender">
                        <option selected value="">Choose...</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon5">Date of birth</span>
                    <input name="dateOfBirth" type="date" class="form-control"
                           placeholder="Date of birth" aria-label="Date of birth" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <label class="input-group-text" for="inputGroupSelect01">Role</label>
                    <select class="form-select" id="inputGroupSelect02" name="role">
                        <option value="admin">Admin</option>
                        <option value="user">User</option>
                    </select>
                </div>

                <button class="btn btn-primary" type="submit">Sign up</button>
            </form>
        </div>
        
    </div>
</div>

<% String message = (String) request.getAttribute("message");
    String alertType = (String) request.getAttribute("alertType");
%>

<% if (message != null) { %>
<div style="position: fixed; top: 80px; left: 20px; z-index: 1050; width: auto; position-area: top;background-color: #ffffff75;" class="alert alert-<%= alertType %> alert-dismissible fade show" role="alert">
    <%= message %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>

</body>
</html>
