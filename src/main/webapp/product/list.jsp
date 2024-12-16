<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Management Application</title>
</head>
<body>
<h1>Product Management</h1>
<h2>
    <a href="/products?action=create">Add New Product</a>
</h2>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Product</h2></caption>
        <tr>
            <th>#</th>
            <th>Image</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Category</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.productID}</td>
                <td><img style="width: 100px; height: 100px" src="${product.image}"></td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td><fmt:formatNumber value="${product.price}" pattern="#,###"/> VND</td>
                <td>${product.quantity}</td>
                <td>${product.categoryID}</td>
                <td>${product.status ? 'In stock' : 'Out of stock'}</td>
                <td>
                    <a href="/products?action=edit&productID=${product.productID}">Edit</a>
                    <a href="/products?action=delete&productID=${product.productID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
