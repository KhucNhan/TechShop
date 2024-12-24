<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <form action="/authenticate?action=login" method="post">
        <label for="username">Username: </label>
        <input type="text" id="username" name="username">
        <br>

        <label for="password">Password: </label>
        <input type="password" id="password" name="password">
        <br>
        <c:if test="${message != null}">
            <div>Password incorrect or username doesn't exist.</div>
        </c:if>
        <button type="submit">Login</button>
    </form>
    <a href="/authenticate?action=signup">You don't have account? Sign up here</a>
</body>
</html>
