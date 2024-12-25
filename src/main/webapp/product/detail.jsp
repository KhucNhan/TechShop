<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product detail</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
</head>
<body>
<jsp:include page="/menu.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row" style="height: 80vh">
        <div class="col-md-6 text-center" style="height: 80vh">
            <img class="mw-100 mh-100" style="height: 55vh;" src="${product.image}">
        </div>
        <div class="col-md-6 d-flex align-items-center" style="height: 80vh">
            <div style="height: 60vh; position: relative">
                <h1>${product.name}</h1>
                <h2 class="mb-5" style="color: red"><fmt:formatNumber value="${product.price}" pattern="#,###"/> VND</h2>
                <h3 class="mb-5">${product.description}</h3>
                <h4>Type: ${product.categoryID == 1 ? 'Phone' : 'Laptop'}</h4>
                <a style="position: absolute; bottom: 18vh;" href="/add-to-cart?productID=${product.productID}" class="btn btn-primary">Add to cart</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
