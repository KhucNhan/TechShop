<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <link rel="stylesheet" href="/css/table.css">
    <title>Order detail</title>
</head>
<body>
<jsp:include page="${user.role == 'admin' ? '/menuAdmin.jsp' : '/menu.jsp'}"/>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row justify-content-center">
        <table class="table" style="margin-bottom: 0">
            <thead style="place-items: stretch; max-height: 6vh; display: block; text-transform: uppercase; width: 100%;">
                <tr style="height: 100%">
                    <th style="width: 20%">Product image</th>
                    <th style="width: 20%">Product</th>
                    <th style="width: 20%">Quantity</th>
                    <th style="width: 20%">Price</th>
                    <th style="width: 20%">Total</th>
                </tr>
            </thead>
            <tbody style="width: 100%;">
            <c:forEach items="${orderDetails}" var="orderDetail">
                <tr>
                    <td  style="width: 20%"><img style="width: 50%; height: 50%" src="${orderDetail.product.image}"></td>
                    <td  style="width: 20%">${orderDetail.product.name}</td>
                    <td  style="width: 20%">${orderDetail.quantity}</td>
                    <td  style="width: 20%"><fmt:formatNumber value="${orderDetail.price}" pattern="#,###"/> VND</td>
                    <td  style="width: 20%"><fmt:formatNumber value="${orderDetail.totalPrice}" pattern="#,###"/> VND</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="width: 100%; background-color: #87919d; height: 7vh; font-size:larger; align-items: center; font-weight: bold; color: #00ffe2;" class="d-flex justify-content-around">
            <label>Total quantity: ${totalQuantity}</label>
            <label>Total: <fmt:formatNumber value="${order.total}" pattern="#,###"/> VND</label>
        </div>
    </div>
</div>
</body>
</html>
