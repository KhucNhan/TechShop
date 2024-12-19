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
<jsp:include page="/menu.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
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
                            <form id="changeQuantity" class="d-inline" action="/cart?action=changeQuantity&productID=${cartItem.value.product.productID}" method="post">
                                <input oninput="submitQuantity()" name="quantity" type="number" value="${cartItem.value.quantity}">
                            </form>

                            <script>
                                function submitQuantity() {
                                    setTimeout(() => {
                                        document.getElementById("changeQuantity").submit();
                                    }, 1500);
                                }
                            </script>

                            <form class="d-inline" action="/cart?action=increaseQuantity&productID=${cartItem.value.product.productID}" method="post">
                                <button class="btn" type="submit">+</button>
                            </form>
                            <c:if test="${message != null}">
                                <div style="color:red">${message}</div>
                            </c:if>
                        </td>
                        <td><fmt:formatNumber value="${cartItem.value.product.price * cartItem.value.quantity}" pattern="#,###"/> VND</td>
                        <td>
                            <a href="/delete-from-cart?&productID=${cartItem.key}" class="btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>
</body>
</html>
