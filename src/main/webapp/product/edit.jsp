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
            <form method="post">
                <input type="hidden" name="productID" value="${product.productID}">
                <label for="image">Image:</label>
                <input type="text" id="image" name="image" value="${product.image}"><br>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${product.name}"><br>
                <label for="description">Description:</label>
                <textarea id="description" name="description">${product.description}</textarea><br>
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" value="<fmt:formatNumber value="${product.price}" pattern="#.###"/>">
                <label> VND</label><br>
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" value="${product.quantity}"><br>
                <label for="categoryID">Category ID:</label>
                <input type="number" id="categoryID" name="categoryID" value="${product.categoryID}"><br>
                <input type="radio" id="statusAvailable" name="status" value="true" ${product.status ? 'checked' : ''}>
                <label for="statusAvailable">In stock</label>

                <input type="radio" id="statusUnavailable" name="status" value="false" ${product.status ? 'checked' : ''}>
                <label for="statusUnavailable">Out of stock</label>
                <button type="submit">Update Product</button>
            </form>
        </div>
        
    </div>
</div>
</body>
</html>
