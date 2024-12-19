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
    <title>Edit user</title>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container">
    <div class="row">
        
        <div class="col-md-12">
            <form action="/users?action=edit" method="post">
                <label for="image" style="<c:if test="${requestScope['imageMessage'] != null}"> color: red;</c:if>">Image: </label>
                <input type="text" id="image" name="image" value="${user.image}">
                <br>

                <label for="name" style="<c:if test="${requestScope['nameMessage'] != null}"> color: red;</c:if>">Name: </label>
                <input type="text" id="name" name="name" value="${user.name}">
                <br>

                <label for="username" style="<c:if test="${requestScope['usernameMessage'] != null}"> color: red;</c:if>">Username: </label>
                <input type="text" id="username" name="username" value="${user.username}">
                <br>

                <label style="<c:if test="${requestScope['genderMessage'] != null}"> color: red;</c:if>">
                    Gender:
                </label>
                <input type="radio" id="male" name="gender" value="Male" ${user.gender.equalsIgnoreCase("Male") ? 'checked' : ''}>
                <label for="male">Male</label>

                <input type="radio" id="female" name="gender" value="Female" ${user.gender.equalsIgnoreCase("Female") ? 'checked' : ''}>
                <label for="female">Female</label>

                <input type="radio" id="other" name="gender" value="Other" ${user.gender.equalsIgnoreCase("Other") ? 'checked' : ''}>
                <label for="other">Other</label>
                <br>

                <label for="dateOfBirth" style="<c:if test="${requestScope['dateMessage'] != null}"> color: red;</c:if>">Date of birth: </label>
                <input type="date" name="dateOfBirth" id="dateOfBirth" value="${user.dateOfBirth}">
                <br>

                <label style="<c:if test="${requestScope['roleMessage'] != null}"> color: red;</c:if>">
                    Role:
                </label>
                <input type="radio" id="admin" name="role" value="Admin" ${user.role.equalsIgnoreCase("Admin") ? 'checked' : ''}>
                <label for="admin">Admin</label>

                <input type="radio" id="user" name="role" value="User" ${user.role.equalsIgnoreCase("User") ? 'checked' : ''}>
                <label for="user">User</label>
                <br>

                <label style="<c:if test="${requestScope['statusMessage'] != null}"> color: red;</c:if>">
                    Status:
                </label>
                <input type="radio" id="active" name="status" value="Active" ${user.status ? 'checked' : ''}>
                <label for="active">Active</label>

                <input type="radio" id="inactive" name="status" value="Inactive" ${user.status ? '' : 'checked'}>
                <label for="inactive">Inactive</label>
                <br>

                <button type="submit">Sign up</button>
            </form>
        </div>
        
    </div>
</div>
</body>
</html>
