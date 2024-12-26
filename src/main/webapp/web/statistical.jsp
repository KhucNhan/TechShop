<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistical</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <link rel="stylesheet" href="/css/table.css">
    <style>
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }

        .col-md-12 > h2 {
            background-color: bisque;
            padding: 12px;
            margin-bottom: 0;
            color: black;
            font-weight: bold;
        }

        body img {
            height: 12vh;
        }

        table tr {
            height: 14vh;
        }
    </style>
</head>
<body>
<jsp:include page="${user.role == 'admin' ? '/menuAdmin.jsp' : '/menu.jsp'}"/>

<div class="container" style="margin-bottom: 3vh; padding-top:11vh">
    <div class="row">
        <div class="col-md-12" style="margin-bottom: 5vh">
            <h2 class="text-center">
                Top Selling Products by Revenue
            </h2>
            <table border="1" cellpadding="5" class="col-md-12 table">
                <thead style="place-items: stretch; max-height: 6vh; display: block; text-transform: uppercase; width: 100%;">
                <tr style="height: 100%">
                    <c:if test="${user.role == 'user'}">
                        <th style="width: 5%" class="text-center">Top</th>
                        <th style="width: 20%" class="text-center">Image</th>
                        <th style="width: 20%" class="align-content-center">Name</th>
                        <th style="width: 35%" class="align-content-center">Description</th>
                        <th style="width: 20%" class="text-center">Price</th>
                    </c:if>
                    <c:if test="${user.role == 'admin'}">
                        <th style="width: 5%" class="text-center">Top</th>
                        <th style="width: 10%" class="text-center">Image</th>
                        <th style="width: 20%" class="align-content-center">Name</th>
                        <th style="width: 25%" class="align-content-center">Description</th>
                        <th style="width: 20%" class="text-center">Price</th>
                        <th style="width: 20%" class="text-center">Total Revenue</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${productsBestSell}" varStatus="loop">
                    <tr class="d-flex">
                        <c:if test="${user.role == 'user'}">
                            <td style="width: 6%" class="text-center">${loop.index + 1}</td>
                            <td style="width: 22%" class="text-center"><img src="${product.image}"></td>
                            <td style="width: 22%; text-align: start" class="align-content-center">${product.name}</td>
                            <td style="width: 37%; text-align: start" class="align-content-center">${product.description}</td>
                            <td style="width: 20%" class="text-center"><fmt:formatNumber value="${product.price}" pattern="#,###"/> VND</td>
                        </c:if>
                        <c:if test="${user.role == 'admin'}">
                            <td style="width: 6%" class="text-center">${loop.index + 1}</td>
                            <td style="width: 11%" class="text-center"><img src="${product.image}"></td>
                            <td style="width: 22%; text-align: start" class="align-content-center">${product.name}</td>
                            <td style="width: 27%; text-align: start" class="align-content-center">${product.description}</td>
                            <td style="width: 22%" class="text-center"><fmt:formatNumber value="${product.price}" pattern="#,###"/> VND</td>
                            <td style="width: 20%" class="text-center"><fmt:formatNumber value="${totalRevenue.get(loop.index)}" pattern="#,###"/> VND</td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-md-12">
            <h2 class="text-center">
                Top Selling Products by Quantity
            </h2>
            <table border="1" cellpadding="5" class="col-md-12 table">
                <thead style="place-items: stretch; max-height: 6vh; display: block; text-transform: uppercase; width: 100%;">
                <tr style="height: 100%">
                    <c:if test="${user.role == 'user'}">
                        <th style="width: 5%" class="text-center">Top</th>
                        <th style="width: 20%" class="text-center">Image</th>
                        <th style="width: 20%" class="align-content-center">Name</th>
                        <th style="width: 35%" class="align-content-center">Description</th>
                        <th style="width: 20%" class="text-center">Price</th>
                    </c:if>
                    
                    <c:if test="${user.role == 'admin'}">
                        <th style="width: 5%" class="text-center">Top</th>
                        <th style="width: 10%" class="text-center">Image</th>
                        <th style="width: 20%" class="align-content-center">Name</th>
                        <th style="width: 25%" class="align-content-center">Description</th>
                        <th style="width: 20%" class="text-center">Price</th>
                        <th style="width: 20%" class="text-center">Total Sold</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${productsBestSellByQuantity}" varStatus="loop">
                    <tr class="d-flex">
                        <c:if test="${user.role == 'user'}">
                            <td style="width: 6%" class="text-center">${loop.index + 1}</td>
                            <td style="width: 22%" class="text-center"><img src="${product.image}"></td>
                            <td style="width: 22%; text-align: start" class="align-content-center">${product.name}</td>
                            <td style="width: 37%; text-align: start" class="align-content-center">${product.description}</td>
                            <td style="width: 20%" class="text-center"><fmt:formatNumber value="${product.price}" pattern="#,###"/> VND</td>
                        </c:if>
                        
                        <c:if test="${user.role == 'admin'}">
                            <td style="width: 6%" class="text-center">${loop.index + 1}</td>
                            <td style="width: 11%" class="text-center"><img src="${product.image}"></td>
                            <td style="width: 22%; text-align: start" class="align-content-center">${product.name}</td>
                            <td style="width: 27%; text-align: start" class="align-content-center">${product.description}</td>
                            <td style="width: 22%" class="text-center"><fmt:formatNumber value="${product.price}" pattern="#,###"/> VND</td>
                            <td style="width: 20%" class="text-center">${totalQuantity.get(loop.index)}</td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <a href="revenue-statistics">Detail</a>
    </div>
</div>

</body>
</html>
