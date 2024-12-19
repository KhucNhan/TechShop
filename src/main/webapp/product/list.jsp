<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Product Management Application</title>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12">
            <h2>
                <a class="btn btn-primary" href="/products?action=create">Add New Product</a>
            </h2>
            <div align="center">
                <table border="1" cellpadding="5" class="col-md-10">
                    <tr>
                        <th class="text-center">#</th>
                        <th class="text-center" class="col-md-1">Image</th>
                        <th class="text-center">Name</th>
                        <th class="text-center">Description</th>
                        <th class="text-center">Price</th>
                        <th class="text-center">Quantity</th>
                        <th class="text-center">Category</th>
                        <th class="text-center">Status</th>
                        <th class="text-center">Edit</th>
                        <th class="text-center">Delete</th>
                    </tr>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td class="text-center">${product.productID}</td>
                            <td class="text-center"><img class="col-md-6" src="${product.image}"></td>
                            <td class="text-center">${product.name}</td>
                            <td class="text-center">${product.description}</td>
                            <td class="text-center"><fmt:formatNumber value="${product.price}" pattern="#,###"/> VND</td>
                            <td class="text-center">${product.quantity}</td>
                            <td class="text-center">${product.categoryID}</td>
                            <td class="text-center">${product.status ? 'In stock' : 'Out of stock'}</td>
                            <td class="text-center">
                                <a class="btn btn-warning" href="/products?action=edit&productID=${product.productID}">Edit</a>
                            </td>
                            <td class="text-center">
                                <c:if test="${product.status == true}">
                                    <a class="btn btn-danger" href="/products?action=delete&productID=${product.productID}">Inactive</a>
                                </c:if>
                                <c:if test="${!product.status == true}">
                                    <a class="btn btn-success" href="/products?action=active&productID=${product.productID}">Active</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        
    </div>
</div>
</body>
</html>
