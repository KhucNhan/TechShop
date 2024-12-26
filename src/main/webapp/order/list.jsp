<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <link rel="stylesheet" href="/css/table.css">
    <title>Order</title>
    <style>
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }

        .container  span, label {
            width: 20%;
        }

        .container  input, select {
            width: 70%;
        }

        .container  form {
            width: 100%;
            text-align: center;
            background-color: #4B5563;
            border: 2px solid #3B82F6;
            padding: 20px;
            border-radius: 8px;
        }

        .container  .row {
            justify-content: center;
        }
    </style>
</head>
<body>
<jsp:include page="${user.role == 'admin' ? '/menuAdmin.jsp' : '/menu.jsp'}"/>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12" style="text-align: -webkit-center;">
            <form method="get" action="orders" style="width: 100%; text-align: center; width: 55%; text-align: center; margin-block: 3vh; padding: 1vh; border: 1px solid; background-color: #4B5563; border-radius: 8px;" class="d-flex justify-content-around">
                <div style="margin-inline: 2vw; align-content: center">
                    <label style="font-weight: bold; font-size: large; margin-right: 5px;" for="start">From: </label>
                    <input style="border-radius: 8px; border: 1px; padding-left: 1vw;" id="start" name="start" type="datetime-local">
                </div>
                <div style="margin-inline: 2vw; align-content: center">
                    <label style="font-weight: bold; font-size: large; margin-right: 5px;" for="end">To: </label>
                    <input style="border-radius: 8px; border: 1px; padding-left: 1vw;" id="end" name="end" type="datetime-local">
                </div>
                <input hidden="hidden" name="action" value="filter">
                <button class="btn btn-light" style="color: black" type="submit">Filter</button>
            </form>
            <table class="table" style="width: 100%;">
                <thead style="place-items: stretch; max-height: 6vh; display: block; text-transform: uppercase; width: 100%;">
                <tr style="height: 100%">
                    <th style="width: 10%">#</th>
                    <th style="width: 10%">User</th>
                    <th style="width: 20%">Date</th>
                    <th style="width: 20%">Total</th>
                    <th style="width: 10%">Status</th>
                    <th style="width: 30%">Actions</th>
                </tr>
                </thead>
                <tbody style="width: 100%; height: 60vh">
                <c:forEach items="${orders}" var="orderItem">
                    <tr style="width: 100%;" class="d-flex">
                        <td style="width: 10%">${orderItem.orderID}</td>
                        <td style="width: 10%">
                            <label style="width: 10%;">
                                <c:set var="role" value="${user.role}"/>

                                <c:choose>
                                    <c:when test="${role == 'admin'}">
                                        ${orderItem.userName}
                                    </c:when>
                                    <c:otherwise>
                                        ${user.name}
                                    </c:otherwise>
                                </c:choose>
                            </label>
                        </td>
                        <td style="width: 20%">${orderItem.orderDate}</td>
                        <td style="width: 20%"><fmt:formatNumber value="${orderItem.total}" pattern="#,###"/> VND</td>
                        <td style="width: 10%">${orderItem.status}</td>
                        <td style="width: 30%">
                            <a href="/orders?action=accept&orderID=${orderItem.orderID}"
                               class="btn btn-primary ${orderItem.status == 'Pending' ? '' : 'disabled'}" ${user.role != 'admin' ? 'hidden' : ''}>Accept</a>

                            <a style="margin-inline: 10px;" href="/orders?action=cancel&orderID=${orderItem.orderID}"
                               class="btn btn-warning ${orderItem.status == 'Pending' ? '' : 'disabled'}"   ${user.role != 'admin' ? 'hidden' : ''}>Cancel</a>

                            <a href="/orders?action=detail&orderID=${orderItem.orderID}" class="btn btn-info">Detail</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
