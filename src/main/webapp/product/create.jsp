<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create new product</title>
</head>
<body>
<h1>Create new product</h1>
<h2>
    <a href="products?action=users">Back to products</a>
</h2>
<form method="post">
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
</body>
</html>
