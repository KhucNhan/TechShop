<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <title>Change Password</title>
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
            width: 65%;
            justify-content: center;
        }
    </style>
</head>
<body>
<jsp:include page="${user.role == 'admin' ? '/menuAdmin.jsp' : '/menu.jsp'}"/>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form style="margin-left: auto; margin-right: auto" action="/users?action=changePassword" method="post">

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Old password</span>
                    <input  name="oldPassword" type="text" class="form-control ${message == 'Password incorrect' ? 'is-invalid' : ''}"
                           placeholder="Old Password" aria-label="Old Password" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon2">New password</span>
                    <input  name="newPassword" type="password" class="form-control ${message == 'New password didn\'t match' ? 'is-invalid' : ''}"
                            placeholder="New Password" aria-label="New Password" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon3">Re-enter password</span>
                    <input  name="rePassword" type="password" class="form-control ${message == 'New password didn\'t match' ? 'is-invalid' : ''}"
                           placeholder="New password" aria-label="Username" aria-describedby="basic-addon1">
                </div>

                <button type="submit" class="btn btn-primary col-4">Change password</button>
            </form>
        </div>
    </div>
</div>

<% String message = (String) request.getAttribute("message");
    String alertType = (String) request.getAttribute("alertType");
%>

<% if (message != null) { %>
<div style="position: fixed; top: 80px; left: 20px; z-index: 1050; width: auto; position-area: top;background-color: #ffffff75;"
     class="alert alert-<%= alertType %> alert-dismissible fade show" role="alert">
    <%= message %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>

</body>
</html>
