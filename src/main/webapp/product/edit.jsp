<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<h1>Edit Product</h1>
<h2>
    <a href="products?action=users">List All Products</a>
</h2>
<form method="post">
    <input type="hidden" name="productID" value="${product.productID}">
    <label for="image">Image:</label>
    <input type="text" id="image" name="image" value="${product.image}"><br>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${product.name}"><br>
    <label for="description">Description:</label>
    <textarea id="description" name="description">${product.description}</textarea><br>
    <label for="price">Price:</label>
    <input type="number" id="price" name="price" value="${product.price}"><br>
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
</body>
</html>
