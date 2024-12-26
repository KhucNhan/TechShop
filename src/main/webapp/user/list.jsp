<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/table.css">
    <link rel="stylesheet" href="/css/menu_footer.css">
    <title>User Management</title>
    <style>
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }
    </style>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12">
            <h2>
                <a class="btn btn-primary" href="/users?action=create">Add new user</a>
            </h2>
            <div align="center">
                <table border="1" cellpadding="5" class="table">
                    <thead style="place-items: stretch; max-height: 6vh; display: block; text-transform: uppercase; width: 100%;">
                    <tr style="height: 100%">
                        <th style="width: 5%;" class="text-center">#</th>
                        <th style="width: 10%" class="text-center">Image</th>
                        <th style="width: 10%" class="text-center">Name</th>
                        <th style="width: 10%" class="text-center">Username</th>
                        <th style="width: 10%" class="text-center">Password</th>
                        <th style="width: 10%" class="text-center">Gender</th>
                        <th style="width: 15%" class="text-center">Date of birth</th>
                        <th style="width: 10%" class="text-center">Role</th>
                        <th style="width: 5%" class="text-center">Status</th>
                        <th style="width: 15%" class="text-center">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr class="d-flex">
                             <td style="width: 5%;" class="text-center">${user.userID}</td>
                             <td style="width: 10%;" class="text-center"><img style="" src="${user.image}"></td>
                             <td style="width: 10%;" class="text-center">${user.name}</td>
                             <td style="width: 10%;" class="text-center">${user.username}</td>
                             <td style="width: 10%;" class="text-center">${user.getHashPassword()}</td>
                             <td style="width: 10%;" class="text-center">${user.gender}</td>
                             <td style="width: 15%;" class="text-center">${user.dateOfBirth}</td>
                             <td style="width: 10%;" class="text-center">${user.role}</td>
                             <td style="width: 5%;" class="text-center">${user.status ? 'Active' : 'Inactive'}</td>
                             <td style="width: 15%;" class="text-center">
                                <a class="btn btn-warning" href="/users?action=edit&userID=${user.userID}">Edit</a>

                                <c:if test="${user.status == true}">
                                    <a class="btn btn-danger" href="/users?action=delete&userID=${user.userID}">Inactive</a>
                                </c:if>
                                <c:if test="${!user.status == true}">
                                    <a class="btn btn-success" href="/users?action=active&userID=${user.userID}">Active</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
