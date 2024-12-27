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
    <style>
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
            position: relative;
        }

        .carousel-item img {
            height: 100%;
        }
    </style>
</head>
<body>
<jsp:include page="/menu.jsp"></jsp:include>
<c:if test="${userIsSearching == false || userIsSearching == null}">
    <div id="carouselExampleCaptions" class="carousel slide">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
        </div>
        <div class="carousel-inner" style="height: 100vh">
            <div class="carousel-item active">
                <img src="https://img4.thuthuatphanmem.vn/uploads/2020/06/05/anh-nen-cong-nghe-cho-may-tinh_103227133.jpeg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block" style="z-index: 1000;font-family: math;bottom: 19.25rem;">
                    <h4 style="color: white; font-size: xxx-large">Welcome to Khuc Store</h4>
                    <p style="color: white; font-size: xx-large">Glad to see you here with us.</p>
                    <br><br><br>
                    <h5 style="color: #ff8200; font-size: xx-large">Customer experience is our top priority.</h5>
                    <p style="color: #ff8200; font-size: x-large">Please enjoy your shopping time.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="https://images.pexels.com/photos/1303085/pexels-photo-1303085.jpeg?cs=srgb&dl=pexels-george-dolgikh-551816-1303085.jpg&fm=jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block" style="z-index: 1000;font-family: math;bottom: 23.25rem;">
                    <h5 style="color: white; font-size: xxx-large">During this Christmas season</h5>
                    <p style="color: white; font-size: xx-large">We have the best product with the best price</p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</c:if>

<div class="container" style="margin-bottom: 3vh; padding-block:11vh">
    <div class="row">
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
    </div>
</div>

<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
