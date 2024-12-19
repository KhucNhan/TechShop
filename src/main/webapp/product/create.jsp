<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Create new product</title>
</head>
<body>
<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container">
    <div class="row">
        
        <div class="col-md-12">
            <form action="/products?action=create" method="post">
                <label for="image">Image url:</label>
                <input type="text" id="image" name="image"><br>

                <label for="name">Name:</label>
                <input type="text" id="name" name="name"><br>

                <label for="description">Description:</label>
                <textarea id="description" name="description"></textarea><br>

                <label for="price">Price:</label>
                <input type="number" id="price" name="price"><br>

                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity"><br>

                <label for="categoryID">Category ID:</label>
                <input type="number" id="categoryID" name="categoryID"><br>

                <button type="submit">Create Product</button>
            </form>
        </div>
        
    </div>
</div>
</body>
</html>
