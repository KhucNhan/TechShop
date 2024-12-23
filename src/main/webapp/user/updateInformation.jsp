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

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12">
            <form action="/users?action=updateInformation" method="post">
                <div class="row d-flex justify-content-center">
                    <c:if test="${requestScope['success'] != null}">
                        <div style="position: fixed; top:10vh; left:42%; text-align: center"
                             class="alert alert-success col-2 align-self-center">Success
                        </div>
                    </c:if>

                    <div class="w-100"></div>

                    <div class=" mb-3 col-4 " style="padding-left: 0; padding-right: 0">
                        <label style="<c:if test="${requestScope['imageMessage'] != null}"> color: red;</c:if>" for="image">Image url</label>
                        <input name="image" type="text" class="" id="image"
                                value="${user.image}">
                    </div>

                    <div class="w-100"></div>

                    <div class=" mb-3 col-4 " style="padding-left: 0; padding-right: 0">
                        <label style="<c:if test="${requestScope['nameMessage'] != null}"> color: red;</c:if>" for="name">Name</label>
                        <input name="name" type="text" class="" id="name"
                                value="${user.name}">
                    </div>

                    <div class="w-100"></div>

                    <div class=" mb-3 col-4 " style="padding-left: 0; padding-right: 0">
                        <label style="<c:if test="${requestScope['usernameMessage'] != null}"> color: red;</c:if>" for="username">Username</label>
                        <input name="username" type="text" class="" id="username"
                                value="${user.username}">
                    </div>

                    <div class="w-100"></div>

                    <div class=" mb-3 col-4 " style="padding-left: 0; padding-right: 0">
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
                    </div>

                    <div class="w-100"></div>

                    <div class=" mb-3 col-4 " style="padding-left: 0; padding-right: 0">
                        <label style="<c:if test="${requestScope['dateMessage'] != null}"> color: red;</c:if>" for="dateOfBirth">Date of birth</label>
                        <input name="dateOfBirth" type="date" class="" id="dateOfBirth"
                               value="${user.dateOfBirth}">
                    </div>

                    <div class="w-100"></div>

                    <button type="submit" class="btn btn-primary col-4">Update</button>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>
