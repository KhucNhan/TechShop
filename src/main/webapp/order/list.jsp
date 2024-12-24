<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Order</title>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12">
            <table>
                <tr>
                    <td>#</td>
                    <td>User</td>
                    <td>Date</td>
                    <td>Total</td>
                    <td>Status</td>
                    <td>Actions</td>
                </tr>

                <c:forEach items="${orders}" var="orderItem">
                    <tr>
                        <td>${orderItem.orderID}</td>
                        <td>${orderItem.userID}</td>
                        <td>${orderItem.orderDate}</td>
                        <td><fmt:formatNumber value="${orderItem.total}" pattern="#,###"/> VND</td>
                        <td>${orderItem.status}</td>
                        <td class="d-flex justify-content-around">
                            <a href="/orders?action=accept&orderID=${orderItem.orderID}" class="btn btn-primary">Accept</a>
                            <a href="/orders?action=cancel&orderID=${orderItem.orderID}" class="btn btn-warning">Cancel</a>
                            <a href="#" class="btn btn-info">Detail</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
