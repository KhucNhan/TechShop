<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
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
                <td>${user.status ? 'Active' : 'Unactive'}</td>
                <td>
                    <a href="/products?action=edit&productID=${product.productID}">Edit</a>
                    <a href="/products?action=delete&productID=${product.productID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
