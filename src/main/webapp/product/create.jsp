<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <title>Create new product</title>
    <style>
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }

        .container {
            height: 100%;
            align-content: center;
            justify-items: center;
        }

        .container span, label {
            width: 20%;
        }

        .container input, select {
            width: 70%;
        }

        .container form {
            width: 100%;
            text-align: center;
            background-color: #4B5563;
            border: 2px solid #3B82F6;
            padding: 20px;
            border-radius: 8px;
        }

        .row {
            width: 100%;
            justify-content: center;
        }
    </style>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        
        <div class="col-md-6">
            <form action="/products?action=create" method="post">
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Image</span>
                    <input name="image" type="text" class="form-control" placeholder="Image url" aria-label="Image url" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon2">Name</span>
                    <input name="name" type="text" class="form-control" placeholder="Name" aria-label="Name" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon3">Description</span>
                    <input name="description" type="text" class="form-control" placeholder="Description" aria-label="Description" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon4">Price</span>
                    <input name="price" type="number" class="form-control" placeholder="Price" aria-label="Price" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon5">Quantity</span>
                    <input name="quantity" type="number" class="form-control" placeholder="Quantity" aria-label="Quantity" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <label class="input-group-text" for="inputGroupSelect01">Category</label>
                    <select class="form-select" id="inputGroupSelect01" name="categoryID">
                        <option selected value="0">Choose...</option>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.categoryID}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <button class="btn btn-primary" type="submit">Create Product</button>
            </form>
        </div>
        
    </div>
</div>

<% String message = (String) request.getAttribute("message");
    String alertType = (String) request.getAttribute("alertType");
%>

<% if (message != null) { %>
<div style="position: fixed; top: 80px; left: 20px; z-index: 1050; width: auto; position-area: top;" class="alert alert-<%= alertType %> alert-dismissible fade show" role="alert">
    <%= message %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>


</body>
</html>
