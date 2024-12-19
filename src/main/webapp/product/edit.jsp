<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Edit Product</title>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container">
    <div class="row">
        
        <div class="col-md-12">
            <form action="/products?action=edit&productID=${product.productID}" method="post">
                <input style="<c:if test="${requestScope['imageMessage'] != null}"> color: red;</c:if>" type="hidden" name="productID" value="${product.productID}">
                <label for="image">Image:</label>
                <input type="text" id="image" name="image" value="${product.image}"><br>

                <label style="<c:if test="${requestScope['nameMessage'] != null}"> color: red;</c:if>" for="name">Name:</label>
                <input type="text" id="name" name="name" value="${product.name}"><br>

                <label style="<c:if test="${requestScope['descriptionMessage'] != null}"> color: red;</c:if>" for="description">Description:</label>
                <textarea id="description" name="description">${product.description}</textarea><br>

                <label style="<c:if test="${requestScope['priceMessage'] != null}"> color: red;</c:if>" for="price">Price:</label>
                <input type="number" id="price" name="price" value="<fmt:formatNumber value="${product.price}" pattern="#.###"/>">
                <label> VND</label><br>

                <label style="<c:if test="${requestScope['quantityMessage'] != null}"> color: red;</c:if>" for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" value="${product.quantity}"><br>

                <label style="<c:if test="${requestScope['categoryIDMessage'] != null}"> color: red;</c:if>" for="categoryID">Category ID:</label>
                <input type="number" id="categoryID" name="categoryID" value="${product.categoryID}"><br>

                <label style="<c:if test="${requestScope['statusMessage'] != null}"> color: red;</c:if>">Status: </label>
                <input type="radio" id="statusAvailable" name="status" value="true" ${product.status ? 'checked' : ''}>
                <label for="statusAvailable">In stock</label>

                <input type="radio" id="statusUnavailable" name="status" value="false" ${product.status ? 'checked' : ''}>
                <label for="statusUnavailable">Out of stock</label>
                <br>

                <button type="submit">Update Product</button>
            </form>
        </div>
        
    </div>
</div>
</body>
</html>
