<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f4f4f4;
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }
        .login-container {
            background-color: #ffffff;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }
        .login-header {
            text-align: center;
            margin-bottom: 1.5rem;
        }
        .form-control {
            border-radius: 8px;
        }
        .btn-primary {
            color: white;
            width: 100%;
            padding: 0.75rem;
            border-radius: 8px;
        }
        .form-text {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="login-container">
    <div class="login-header">
        <h2>Welcome Back</h2>
        <p>Please login to your account</p>
    </div>
    <form action="/authenticate?action=login" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control ${message == null ? '' : 'is-invalid'}" id="username" name="username" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control ${message == null ? '' : 'is-invalid'}" id="password" name="password" required>
        </div>
        <div class="form-check" style="padding-left: 0; margin-block: 1vh">
            <label class="form-check-label" style="font-size: smaller; color: red">${message}</label>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
        <p class="form-text mt-3">Don't have an account? <a href="/authenticate?action=signup">Sign up</a></p>
    </form>
</div>
</body>
</html>
