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
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12">
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
                <tbody style="width: 100%;">
                <c:forEach items="${orders}" var="orderItem">
                    <tr style="width: 100%;" class="d-flex">
                        <td style="width: 10%">${orderItem.orderID}</td>
                        <td style="width: 10%">
                            <label style="width: 10%;">
                                    ${orderItem.userName}
                        </label>
                        </td>
                        <td style="width: 20%">${orderItem.orderDate}</td>
                        <td style="width: 20%"><fmt:formatNumber value="${orderItem.total}" pattern="#,###"/> VND</td>
                        <td style="width: 10%">${orderItem.status}</td>
                        <td style="width: 30%">
                            <a href="/orders?action=accept&orderID=${orderItem.orderID}" class="btn btn-primary ${orderItem.status == 'Pending' ? '' : 'disabled'}">Accept</a>

                            <a style="margin-inline: 10px;" href="/orders?action=cancel&orderID=${orderItem.orderID}" class="btn btn-warning ${orderItem.status == 'Pending' ? '' : 'disabled'}">Cancel</a>

                            <a href="/orders?action=detail&orderID=${orderItem.orderID}" class="btn btn-info">Detail</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
