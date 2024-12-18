<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/17/2024
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create new user</title>
</head>
<body>
<h2>
    <a href="admins?action=users">Back to users</a>
</h2>
<form action="/admins?action=create" method="post">
    <label for="name" style="<c:if test="${requestScope['nameMessage'] != null}"> color: red;</c:if>">Name: </label>
    <input type="text" id="name" name="name">
    <br>

    <label for="username" style="<c:if test="${requestScope['usernameMessage'] != null}"> color: red;</c:if>">Username: </label>
    <input type="text" id="username" name="username">
    <br>

    <label for="password" style="<c:if test="${requestScope['passwordMessage'] != null}"> color: red;</c:if>">Password: </label>
    <input type="password" id="password" name="password">
    <br>

    <label id="genderLabel" style="<c:if test="${requestScope['genderMessage'] != null}"> color: red;</c:if>">
        Gender:
    </label>
    <input type="radio" id="male" name="gender" value="Male">
    <label for="male">Male</label>

    <input type="radio" id="female" name="gender" value="Female">
    <label for="female">Female</label>

    <input type="radio" id="other" name="gender" value="Other">
    <label for="other">Other</label>
    <br>

    <label for="dateOfBirth" style="<c:if test="${requestScope['dateMessage'] != null}"> color: red;</c:if>">Date of birth: </label>
    <input type="date" name="dateOfBirth" id="dateOfBirth">
    <br>

    <button type="submit">Sign up</button>
</form>
</body>
</html>
