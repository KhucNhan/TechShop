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
                        <td class="text-center align-content-center">
                            <form action="/cart?action=selected&productID=${cartItem.value.product.productID}" method="post">
                                <input type="checkbox" class="product"
                                       data-price="${cartItem.value.product.price * cartItem.value.quantity}"
                                       onclick="calculateTotal(); updateSelectAll()" oninput="this.form.submit()"
                                    ${cartItem.value.product.selected ? 'checked' : ''}>
                            </form>

                        </td>
                        <td class="text-center align-content-center"><img style="width: 194px; height: 194px;"
                                                                          src="${cartItem.value.product.image}"></td>
                        <td class="align-content-center">${cartItem.value.product.name}</td>
                        <td class="text-center align-content-center"><fmt:formatNumber
                                value="${cartItem.value.product.price}" pattern="#,###"/> VND
                        </td>
                        <td class="text-center align-content-center">
                            <form class="d-inline"
                                  action="/cart?action=decreaseQuantity&productID=${cartItem.value.product.productID}"
                                  method="post">
                                <button class="quantityButton" style="border: 0; background-color: white;" type="submit">-</button>
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
                                <button class="quantityButton" style="border: 0; background-color: white;" type="submit">+</button>
                            </form>
                            <c:if test="${message != null}">
                                <div style="color:red">${message}</div>
                            </c:if>
                        </td>
                        <td class="align-content-center"><fmt:formatNumber
                                value="${cartItem.value.product.price * cartItem.value.quantity}" pattern="#,###"/> VND
                        </td>
                        <td class="align-content-center">
                            <a href="/delete-from-cart?&productID=${cartItem.key}" class="btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="d-flex justify-content-between" style="background-color: #fffc73; position: fixed; bottom: 0; left: 0; height: 10vh; font-size: x-large">
            <div class="d-inline" style="width: 30%; align-content: center; margin-inline: 20px">
                <label for="selectAll" class="mr-2">Select all product</label>
                <input style="scale: 1.7; margin-left: 20px;" id="selectAll" type="checkbox" onclick="selectAllProduct()">
            </div>
            <div class="d-inline" style="width: 70%; align-content: center; text-align: end">
                <form class="" action="/cart?action=buy" method="post" style="margin-inline: 20px; margin-block: 0">
                    <label style="width: 200px;text-justify: distribute-center-last; vertical-align: middle" name="total" id="total" class="mr-2">
                        <fmt:formatNumber
                                value="${total}" pattern="#,###"/> VND
                    </label>
                    <button class="btn btn-success" type="submit" style="margin-inline: 20px; font-size: x-large">Buy</button>

<%--                    Modal--%>
<%--                    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">--%>
<%--                        <div class="modal-dialog">--%>
<%--                            <div class="modal-content">--%>
<%--                                <div class="modal-header">--%>
<%--                                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Purchase request has been sent</h1>--%>
<%--                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                                </div>--%>
<%--                                <div class="modal-body">--%>
<%--                                    Please wait for the administrator to approve your order--%>
<%--                                </div>--%>
<%--                                <div class="modal-footer">--%>
<%--                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                </form>
            </div>
        </div>
    </div>
</div>

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

    let isAllSelected = false;

    function selectAllProduct() {
        let selectAllCheckbox = document.getElementById('selectAll');
        let checkboxes = document.querySelectorAll('.product');

        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAllCheckbox.checked;
        });

        calculateTotal();
    }

    function updateSelectAll() {
        let checkboxes = document.querySelectorAll('.product');
        let checkedBoxes = document.querySelectorAll('.product:checked');
        let selectAllCheckbox = document.getElementById('selectAll');

        selectAllCheckbox.checked = (checkboxes.length === checkedBoxes.length);
    }
</script>
</body>
</html>
