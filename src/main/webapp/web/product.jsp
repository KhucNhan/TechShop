<%@ page import="com.example.techshop.service.DAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <link rel="stylesheet" href="/css/card.css">
</head>
<body>
<jsp:include page="/menu.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
<%--        <div class="col-md"></div>--%>
        <div class="col-md-12">
            <div class="row g-4 text-center row-cols-5">
                <c:forEach items="${requestScope['products']}" var="product">
                    <div class="col">
                        <div class="card shadow-lg border border-secondary">
                            <img style="height: 23vh" src="${product.image}" class="card-img-top" alt="">
                            <div class="card-body">
                                <h5 class="card-title text-truncate border-top border-secondary">${product.name}</h5>
                                <p class="card-subtitle mb-2 text-body-secondary price"><fmt:formatNumber
                                        value="${product.price}" pattern="#,###"/> VND</p>
                                    <%--                                <p style="font-size: 1rem;" class="card-text text-truncate">${product.description}</p>--%>
                                <a href="/products?action=detail&productID=${product.productID}" style="color: whitesmoke" class="card-link mt-1">Details</a>
                                <br>
                                <c:if test="${!fn:contains(productID, product.productID)}">
                                <a id="addToCartBtn" href="/add-to-cart?productID=${product.productID}" class="btn btn-primary mt-1">Add
                                    to cart</a>
                                </c:if>
                                <c:if test="${fn:contains(productID, product.productID)}">
                                    <button id="addToCartBtn" class="btn btn-primary mt-1" disabled>Out of stock</button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
<%--        <div class="col-md-1"></div>--%>
    </div>
</div>

<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
