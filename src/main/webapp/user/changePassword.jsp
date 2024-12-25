<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <title>Change Password</title>
</head>
<body>
<jsp:include page="/menu.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12" style="align-items: center">
            <form style="margin-left: auto; margin-right: auto" action="/users?action=changePassword" method="post">
                <div class="row d-flex justify-content-center">
                    <c:if test="${requestScope['success'] != null}">
                        <div style="position: fixed; top:10vh; left:42%; text-align: center" class="alert alert-success col-2 align-self-center">Success</div>
                    </c:if>

                    <div class="w-100"></div>

                    <div class="form-floating mb-3 col-4 " style="padding-left: 0; padding-right: 0">
                        <input style="color: black" name="oldPassword" type="password" class="form-control" id="floatingPassword"
                               placeholder="Enter your password">
                        <label style="color: black" for="floatingPassword">Password</label>
                        <c:if test="${requestScope['oldPassword'] != null}">
                            <span style="color: red; position: fixed;  left: 65%; top: 19vh" class="alert alert-danger>Password incorrect">
                                Password incorrect
                            </span>
                        </c:if>
                    </div>

                    <div class="w-100"></div>

                    <div class="form-floating mb-3 col-4 " style="padding-left: 0; padding-right: 0">
                        <input style="color: black" name="password" type="password" class="form-control" id="floatingNewPassword"
                               placeholder="Enter your password">
                        <label style="color: black" for="floatingNewPassword">New password</label>
                    </div>

                    <div class="w-100"></div>

                    <div class="form-floating mb-3 col-4 " style="padding-left: 0; padding-right: 0">
                        <input style="color: black" name="rePassword" type="password" class="form-control" id="floatingReNewPassword"
                               placeholder="Enter password again">
                        <label style="color: black" for="floatingReNewPassword">Enter password again</label>
                        <c:if test="${requestScope['rePassword'] != null}">
                            <span style="color: red; position: fixed;  left: 65%; top: 36vh" class="alert alert-danger>Password incorrect">
                                Password incorrect
                            </span>
                        </c:if>
                    </div>

                    <div class="w-100"></div>

                    <button type="submit" class="btn btn-primary col-4">Change password</button>
                </div>
            </form>
        </div>
    </div>
</div>

<% String message = (String) request.getAttribute("message");
    String alertType = (String) request.getAttribute("alertType");
%>

<% if (message != null) { %>
<div style="position: fixed; top: 80px; left: 20px; z-index: 1050; width: auto;" class="alert alert-<%= alertType %> alert-dismissible fade show" role="alert">
    <%= message %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>

</body>
</html>
