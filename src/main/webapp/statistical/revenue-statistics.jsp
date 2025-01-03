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
        body {
            background-image: url("https://files.oaiusercontent.com/file-CCSV4pxxgouJe9ymj4qgdj?se=2024-12-26T09%3A09%3A32Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D604800%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D08c74b3f-e0f2-4eab-b3e6-c946c68c5adc.webp&sig=QAdTrnl2M4hV2vBft3S5x71zqiQtPbU3zR%2BLYG70bOM%3D");
        }
    </style>
</head>
<body>
<%
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
%>

<jsp:include page="/menuAdmin.jsp"></jsp:include>

<div class="container" style="margin-top: 20vh; height: 70vh; width: 100%; justify-items: center">
    <form method="get" action="/revenue-statistics" class="d-flex" style="justify-content: space-around; background-color: white; width: 50%; margin-bottom: 0; border-bottom: 2px solid black; padding: 16px;">
        <select name="year" class="form-select" style="width: 25%;">
            <c:forEach var="i" begin="<%= currentYear - 5 %>" end="<%= currentYear %>" step="1">
                <option value="${i}" ${i == year ? 'selected' : ''}>${i}</option>
            </c:forEach>
        </select>

        <select name="month" class="form-select" style="width: 25%;">
            <option value="0">All</option>
            <c:forEach var="i" begin="1" end="12" step="1">
                <option value="${i}" ${i == month ? 'selected' : ''}>${i}</option>
            </c:forEach>
        </select>

        <button style="width: 20%" type="submit" class="btn btn-primary">Show</button>
    </form>

    <canvas id="revenueChart" width="500" height="200" style="background-color: white"></canvas>
</div>

<script>
    var ctx = document.getElementById('revenueChart').getContext('2d');
    var revenueData = {
        labels: [],  // Mảng trống sẽ được điền sau
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

    // Kiểm tra nếu month = 0, thì labels là các tháng trong năm, nếu không thì là các ngày trong tháng
    var selectedMonth = <%= request.getParameter("month") %>;  // Lấy giá trị từ tham số month trong request
    if (selectedMonth == 0) {
        revenueData.labels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    } else {
        // Lấy số ngày trong tháng đã chọn, ví dụ cho tháng 2 hoặc tháng 4, 6, 9, 11 có thể có 30 ngày
        var daysInMonth = new Date(<%= request.getParameter("year") %>, selectedMonth, 0).getDate();
        revenueData.labels = [];
        for (var i = 1; i <= daysInMonth; i++) {
            revenueData.labels.push(i.toString());  // Thêm các ngày vào labels
        }
    }

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
