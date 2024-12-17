<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<form action="/authentication?action=signup" method="post">
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
<a href="/authentication?action=login">Back to login</a>
</body>
</html>
