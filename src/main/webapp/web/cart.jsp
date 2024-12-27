<%@ page import="com.example.techshop.model.OrderDetails" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <link rel="stylesheet" href="/css/cart.css">
    <style>
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }
    </style>
</head>
<body>
<jsp:include page="/menu.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12">
            <div class="overflow-y-auto" style="max-height: 76vh; width: 100%">
                <table class="table" style="width: 100%; background-color: #4B5563;">
                    <thead class="table-light"
                           style="display: block; width: 100%; background-color: #f37979; text-transform: uppercase">
                    <tr style="width: 100%; height: 6vh; font-weight: bold">
                        <td style="width: 5%" class="text-center align-content-center">Select</td>
                        <td style="width: 15%;" class="text-center align-content-center">Image</td>
                        <td style="width: 20%" class="text-center align-content-center">Name</td>
                        <td style="width: 15%" class="text-center align-content-center">Price</td>
                        <td style="width: 15%; padding-right: 32px" class="text-center align-content-center">Quantity</td>
                        <td style="width: 20%" class="text-center align-content-center">Total</td>
                        <td style="width: 10%; padding-left:20px" class="align-content-center">Action</td>
                    </tr>
                    </thead>

                    <tbody class="tbody" style="overflow-y: auto;display: block;height: 74vh; width: 100%">
                    <%
                        Map<Integer, OrderDetails> cart = (Map<Integer, OrderDetails>) session.getAttribute("cart");
                        int size = (cart != null) ? cart.size() : 0;
                        request.setAttribute("size", size);
                    %>
                    <c:if test="${size == 0}">
                        <h1 style="position: fixed; top:250px; left: 0; text-align: center; width: 100%">You don't have any product in your cart</h1>
                    </c:if>
                    <c:forEach items="${sessionScope['cart']}" var="cartItem">
                        <tr style="width: 100%;">
                            <td style="width: 5%" class="text-center align-content-center">
                                <form action="/cart?action=selected&productID=${cartItem.value.product.productID}"
                                      method="post">
                                    <input type="checkbox" class="product"
                                           data-price="${cartItem.value.product.price * cartItem.value.quantity}"
                                           onclick="calculateTotal()" oninput="this.form.submit()"
                                        ${cartItem.value.product.selected ? 'checked' : ''}>
                                </form>

                            </td>
                            <td style="width: 15%" class="text-center align-content-center"><img
                                    style="height: 16vh; width: 100%"
                                    src="${cartItem.value.product.image}">
                            </td>
                            <td style="width: 20%" class="align-content-center">${cartItem.value.product.name}</td>
                            <td style="width: 15%" class="text-center align-content-center"><fmt:formatNumber
                                    value="${cartItem.value.product.price}" pattern="#,###"/> VND
                            </td>
                            <td style="width: 15%" class="text-center align-content-center">
                                <form class="d-inline"
                                      action="/cart?action=decreaseQuantity&productID=${cartItem.value.product.productID}"
                                      method="post">
                                    <button class="quantityButton" style="border: 0; background-color: white;"
                                            type="submit">-
                                    </button>
                                </form>

                                <form id="changeQuantity" class="d-inline"
                                      action="/cart?action=changeQuantity&productID=${cartItem.value.product.productID}"
                                      method="post">
                                    <input style="width: 30%;" oninput="submitQuantity()" name="quantity" type="number"
                                           value="${cartItem.value.quantity}">
                                </form>

                                <form class="d-inline"
                                      action="/cart?action=increaseQuantity&productID=${cartItem.value.product.productID}"
                                      method="post">
                                    <button class="quantityButton" style="border: 0; background-color: white;"
                                            type="submit">+
                                    </button>
                                </form>
                                <c:if test="${oos != null && oos == cartItem.value.product.productID}">
                                    <div style="color:red">Product out of stock</div>
                                </c:if>
                                <c:if test="${in != null && in == cartItem.value.product.productID}">
                                    <div style="color:red">Invalidate number</div>
                                </c:if>
                            </td>
                            <td style="width: 20%" class="text-center align-content-center"><fmt:formatNumber
                                    value="${cartItem.value.product.price * cartItem.value.quantity}" pattern="#,###"/>
                                VND
                            </td>
                            <td style="width: 10%" class="text-center align-content-center">
                                <a href="/delete-from-cart?&productID=${cartItem.key}" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>
        <div class="col-md-12" style="z-index: 1000;">
            <div class="d-flex justify-content-between"
                 style="background-color: #fffc73; height: 10vh; font-size: x-large;">
                <div class="d-inline" style="width: 30%; align-content: center; margin-left: 20px">
                    <form action="/cart?action=selectedAll" method="post">
                        <label style="color: black" for="selectAll" class="mr-2">Select all product</label>
                        <input style="scale: 1.7; margin-left: 20px;" id="selectAll" type="checkbox" name="selectAll"
                               oninput="this.form.submit()" ${allSelected != null ? 'checked' : ''}>
                    </form>
                </div>
                <div class="d-inline" style="width: 70%; align-content: center; text-align: end">
                    <form class="" action="/cart?action=buy" method="post" style="margin-inline: 20px; margin-block: 0">
                        <label style="width: 200px;text-justify: distribute-center-last; vertical-align: middle; color:red"
                               name="total" id="total" class="mr-2">
                            <fmt:formatNumber
                                    value="${total}" pattern="#,###"/> VND
                        </label>
                        <button class="btn btn-success buy" type="submit"
                                style="margin-inline: 20px; font-size: x-large">Buy
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<% String message = (String) request.getAttribute("message");
    String alertType = (String) request.getAttribute("alertType");
%>

<% if (message != null) { %>
<div style="position: fixed; top: 80px; left: 20px; z-index: 1050; width: auto; position-area: top;"
     class="alert alert-<%= alertType %> alert-dismissible fade show" role="alert">
    <%= message %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>

<script>
    function submitQuantity() {
        setTimeout(() => {
            document.getElementById("changeQuantity").submit();
        }, 1500);
    }

    function calculateTotal() {
        let total = 0;
        let checkboxes = document.querySelectorAll('.product:checked');

        checkboxes.forEach((checkbox) => {
            let price = parseFloat(checkbox.dataset.price) || 0;
            total += price;
        });

        document.getElementById('total').innerText = total.toLocaleString('vi-VN') + " VND";
    }
</script>
</body>
</html>
