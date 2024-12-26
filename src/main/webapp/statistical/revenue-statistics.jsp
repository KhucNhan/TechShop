<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Revenue Statistics</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/menu_footer.css">
    <style>
    </style>
</head>
<body>
<%
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
%>

<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-top: 20vh; height: 70vh; width: 100%">
    <form method="get" action="/revenue-statistics" class="d-flex" style="background-color: white; width: 100%; justify-content: center; margin-bottom: 0; border-bottom: 2px solid black; padding: 16px;">
        <select name="year" class="form-select" style="width: 15%;">
            <c:forEach var="i" begin="<%= currentYear - 5 %>" end="<%= currentYear %>" step="1">
                <option value="${i}" ${i == year ? 'selected' : ''}>${i}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-primary">Show</button>
    </form>

    <canvas id="revenueChart" width="500" height="200" style="background-color: white"></canvas>
</div>

<script>
    var ctx = document.getElementById('revenueChart').getContext('2d');
    var revenueData = {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        datasets: [{
            label: 'Revenue (VND)',
            data: [
                <c:forEach var="stat" items="${statistics}">
                ${stat.totalRevenue},
                </c:forEach>
            ],
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        }]
    };

    var revenueChart = new Chart(ctx, {
        type: 'bar',
        data: revenueData,
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function(value) {
                            return value.toLocaleString(); // Hiển thị số theo dạng phân cách dấu phẩy
                        }
                    }
                }
            },
            plugins: {
                tooltip: {
                    callbacks: {
                        label: function(tooltipItem) {
                            return tooltipItem.raw.toLocaleString() + ' VND'; // Hiển thị doanh thu với đơn vị VND
                        }
                    }
                }
            }
        }
    });
</script>
</body>
</html>
