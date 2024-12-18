<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <form action="/cart?action=home" method="post">
        <button type="submit" class="btn btn-primary">Back home</button>
    </form>
    <table class="table table-striped table-hover">
        <tr>
            <td>Select</td>
            <td>Image</td>
            <td>Name</td>
            <td>Price</td>
            <td>Quantity</td>
            <td>Total</td>
            <td>Action</td>
        </tr>

        <c:forEach items="${sessionScope['cart']}" var="cartItem">
            <tr>
                <td><input type="checkbox" name="selected"></td>
                <td><img style="width: 194px; height: 194px;" src="${cartItem.value.product.image}"></td>
                <td>${cartItem.value.product.name}</td>
                <td><fmt:formatNumber value="${cartItem.value.product.price}" pattern="#,###"/> VND</td>
                <td>
                    <form class="d-inline" action="/cart?action=decreaseQuantity&productID=${cartItem.value.product.productID}" method="post">
                        <button class="btn" type="submit">-</button>
                    </form>
                    <form class="d-inline" action="/cart?action=changeQuantity&productID=${cartItem.value.product.productID}" method="post">
                        <input type="number" value="${cartItem.value.quantity}">
                    </form>
                    <form class="d-inline" action="/cart?action=increaseQuantity&productID=${cartItem.value.product.productID}" method="post">
                        <button class="btn" type="submit">+</button>
                    </form>
                </td>
                <td><fmt:formatNumber value="${cartItem.value.product.price * cartItem.value.quantity}" pattern="#,###"/> VND</td>
                <td>
                    <a href="/delete-from-cart?&productID=${cartItem.key}" class="btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
