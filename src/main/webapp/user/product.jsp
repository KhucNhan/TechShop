<%@ page import="com.example.techshop.service.DAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/menu.jsp"></jsp:include>

<div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="row">
                <c:forEach items="${requestScope['products']}" var="product">
                    <div class="col-md-3">
                        <div class="card">
                            <img src="${product.image}" class="card-img-top" alt="">
                            <div class="card-body">
                                <h5 class="card-title text-truncate">${product.name}</h5>
                                <p style="color: red" class="card-subtitle mb-2 text-body-secondary"><fmt:formatNumber
                                        value="${product.price}" pattern="#,###"/> VND</p>
                                    <%--                                <p style="font-size: 1rem;" class="card-text text-truncate">${product.description}</p>--%>
                                <a href="" class="card-link mt-1">Details</a>
                                <br>
                                <a href="/add-to-cart?productID=${product.productID}" class="btn btn-primary">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

</body>
</html>