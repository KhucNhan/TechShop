<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <link rel="stylesheet" href="/css/table.css">
    <title>Product Management Application</title>
    <style>
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }
    </style>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12">
            <h2>
                <a class="btn btn-primary" href="/products?action=create">Add New Product</a>
            </h2>
<%--            <div align="center">--%>
                <table border="1" cellpadding="5" class="col-md-12 table">
                    <thead style="place-items: stretch; max-height: 6vh; display: block; text-transform: uppercase; width: 100%;">
                        <tr style="height: 100%">
                            <th style="width: 5%" class="text-center">#</th>
                            <th style="width: 10%" class="text-center">Image</th>
                            <th style="width: 15%" class="align-content-center">Name</th>
                            <th style="width: 20%" class="align-content-center">Description</th>
                            <th style="width: 15%" class="text-center">Price</th>
                            <th style="width: 5%" class="text-center">Quantity</th>
                            <th style="width: 5%" class="text-center">Category</th>
                            <th style="width: 10%" class="text-center">Status</th>
                            <th style="width: 15%" class="text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr class="d-flex">
                            <td style="width: 6%" class="text-center">${product.productID}</td>
                            <td style="width: 11%" class="text-center"><img src="${product.image}"></td>
                            <td style="width: 16%; text-align: start" class="align-content-center">${product.name}</td>
                            <td style="width: 22%; text-align: start" class="align-content-center">${product.description}</td>
                            <td style="width: 16%" class="text-center"><fmt:formatNumber value="${product.price}" pattern="#,###"/> VND</td>
                            <td style="width: 8%" class="text-center">${product.quantity}</td>
                            <td style="width: 9%" class="text-center">${product.categoryID == 1 ? 'Phone' : 'Laptop'}</td>
                            <td style="width: 10%" class="text-center">${product.status ? 'In stock' : 'Out of stock'}</td>
                            <td style="width: 16%" class="text-center">
                                <a class="btn btn-warning" href="/products?action=edit&productID=${product.productID}">Edit</a>
                                <c:if test="${product.status == true}">
                                    <a class="btn btn-danger" href="/products?action=delete&productID=${product.productID}">Inactive</a>
                                </c:if>
                                <c:if test="${!product.status == true}">
                                    <a class="btn btn-success" href="/products?action=active&productID=${product.productID}">Active</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>
</body>
</html>
