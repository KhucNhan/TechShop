<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }
    </style>
</head>
<body style="background-color: #f8f9fa;">
<div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card p-4 shadow-lg" style="width: 450px; border-radius: 10px;">
        <h3 class="text-center mb-4">Sign Up</h3>
        <form action="/authenticate?action=signup" method="post">

            <!-- Full Name -->
            <div class="mb-3">
                <label for="fullName" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="fullName" name="name" required>
            </div>

            <!-- Username -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <!-- Password -->
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
            </div>

            <!-- Gender -->
            <div class="mb-3 d-flex">
                <label class="form-label">Gender</label>
                <div class="input-group" style=" justify-content: space-around; padding-inline: 20px;">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" id="male" name="gender" value="Male" required="">
                        <label class="form-check-label" for="male">Male</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" id="female" name="gender" value="Female">
                        <label class="form-check-label" for="female">Female</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" id="other" name="gender" value="Other">
                        <label class="form-check-label" for="other">Other</label>
                    </div>
                </div>
            </div>

            <!-- Date of Birth -->
            <div class="mb-3">
                <label for="dob" class="form-label">Date of Birth</label>
                <input type="date" class="form-control" id="dob" name="dateOfBirth" required>
            </div>

            <div class="form-check" style="padding-left: 0; margin-block: 1vh">
                <label class="form-check-label" style="font-size: smaller; ${message == 'Signup success' ? 'color: darkgreen' : 'color: red'}">${message}</label>
            </div>

            <!-- Submit Button -->
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">Sign Up</button>
            </div>

            <!-- Login Redirect -->
            <div class="text-center mt-3">
                <p>Already have an account? <a href="/authenticate?action=login" class="text-decoration-none">Login here</a></p>
            </div>
        </form>
    </div>
</div>

</body>
</html>
