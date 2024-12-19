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

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-hover">
                <tr>
                    <td class="text-center align-content-center">Select</td>
                    <td class="text-center align-content-center">Image</td>
                    <td class="text-center align-content-center">Name</td>
                    <td class="text-center align-content-center">Price</td>
                    <td class="text-center align-content-center">Quantity</td>
                    <td class="text-center align-content-center">Total</td>
                    <td class="text-center align-content-center">Action</td>
                </tr>

                <c:forEach items="${sessionScope['cart']}" var="cartItem">
                    <tr>
                        <td class="text-center align-content-center"><input type="checkbox" name="selected"></td>
                        <td class="text-center align-content-center"><img style="width: 194px; height: 194px;" src="${cartItem.value.product.image}"></td>
                        <td class="align-content-center">${cartItem.value.product.name}</td>
                        <td class="text-center align-content-center"><fmt:formatNumber value="${cartItem.value.product.price}" pattern="#,###"/> VND</td>
                        <td class="text-center align-content-center">
                            <form class="d-inline" action="/cart?action=decreaseQuantity&productID=${cartItem.value.product.productID}" method="post">
                                <button style="border: 0; background-color: white;" type="submit">-</button>
                            </form>
                            <form id="changeQuantity" class="d-inline" action="/cart?action=changeQuantity&productID=${cartItem.value.product.productID}" method="post">
                                <input style="width: 30%;" oninput="submitQuantity()" name="quantity" type="number" value="${cartItem.value.quantity}">
                            </form>

                            <script>
                                function submitQuantity() {
                                    setTimeout(() => {
                                        document.getElementById("changeQuantity").submit();
                                    }, 1500);
                                }
                            </script>

                            <form class="d-inline" action="/cart?action=increaseQuantity&productID=${cartItem.value.product.productID}" method="post">
                                <button style="border: 0; background-color: white;" type="submit">+</button>
                            </form>
                            <c:if test="${message != null}">
                                <div style="color:red">${message}</div>
                            </c:if>
                        </td>
                        <td class="align-content-center"><fmt:formatNumber value="${cartItem.value.product.price * cartItem.value.quantity}" pattern="#,###"/> VND</td>
                        <td class="align-content-center">
                            <a href="/delete-from-cart?&productID=${cartItem.key}" class="btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <footer style="background-color: #fff871">
            <input type="checkbox" name="selected">
            <label> Total:
        </footer>
    </div>
</div>
</body>
</html>
