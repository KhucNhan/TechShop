<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>User Management</title>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12">
            <h2>
                <a class="btn btn-primary" href="/users?action=create">Add new user</a>
            </h2>
            <div align="center">
                <table border="1" cellpadding="5">
                    <tr>
                        <th class="text-center">#</th>
                        <th class="text-center" class="col-md-2">Image</th>
                        <th class="text-center">Name</th>
                        <th class="text-center">Username</th>
                        <th class="text-center">Password</th>
                        <th class="text-center">Gender</th>
                        <th class="text-center">Date of birth</th>
                        <th class="text-center">Role</th>
                        <th class="text-center">Status</th>
                        <th class="text-center">Edit</th>
                        <th class="text-center">Delete</th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td class="text-center">${user.userID}</td>
                            <td class="text-center"><img style="width: 100px; height: 100px" src="${user.image}"></td>
                            <td class="text-center">${user.name}</td>
                            <td class="text-center">${user.username}</td>
                            <td class="text-center">${user.password}</td>
                            <td class="text-center">${user.gender}</td>
                            <td class="text-center">${user.dateOfBirth}</td>
                            <td class="text-center">${user.role}</td>
                            <td class="text-center">${user.status ? 'Active' : 'Inactive'}</td>
                            <td class="text-center">
                                <a class="btn btn-warning" href="/users?action=edit&userID=${user.userID}">Edit</a>
                            </td>
                            <td class="text-center">
                                <a class="btn btn-danger" href="/users?action=delete&userID=${user.userID}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
