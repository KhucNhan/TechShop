<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
</head>
<body>
<h2>
    <a href="/admins?action=create">Add new user</a>
</h2>
<h2>
    <a href="/admins?action=products">Go to products</a>
</h2>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of User</h2></caption>
        <tr>
            <th>#</th>
            <th>Image</th>
            <th>Name</th>
            <th>Username</th>
            <th>Password</th>
            <th>Gender</th>
            <th>Date of birth</th>
            <th>Role</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.userID}</td>
                <td><img style="width: 100px; height: 100px" src="${user.image}"></td>
                <td>${user.name}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.gender}</td>
                <td>${user.dateOfBirth}</td>
                <td>${user.role}</td>
                <td>${user.status ? 'Active' : 'Inactive'}</td>
                <td>
                    <a href="/admins?action=edit&userID=${user.userID}">Edit</a>
                    <a href="/admins?action=delete&userID=${user.userID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
