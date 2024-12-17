<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/authentication?action=login" method="post">
        <label for="username">Username: </label>
        <input type="text" id="username" name="username">
        <br>

        <label for="password">Password: </label>
        <input type="password" id="password" name="password">
        <br>

        <button type="submit">Login</button>
    </form>
    <a href="/authentication?action=signup">You don't have account? Sign up here</a>
</body>
</html>
