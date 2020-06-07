<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meals</title>
    <style>
        th {
            font-weight: normal;
            color: #039;
            padding: 13px 15px;
        }

        tr:nth-child(2n) {
            background: #e8edff;
        }

        .withoutExcess {
            color: green;
        }

        .withExcess {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h21><a href="index.html">Home</a></h21>
    <h3>Meals</h3>
    <hr>
    <table border="10">
        <thead>
        <tr>
            <th>Дата/Время</th>
            <th>Описание</th>
            <th>Калории</th>
            <th>Action</th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"></jsp:useBean>
            <tr class="${meal.excess? 'withExcess' : 'withoutExcess'}">
                <td>
                    <fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"></fmt:parseDate>
                    <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?operation=update&id=${meal.id}">Edit</a> <a
                        href="meals?operation=delete&id=${meal.id}">Delete</a></td>
                    <%--                <td><a href="meals?operation=delete&id=${meal.id}">Delete</a></td>--%>
            </tr>
        </c:forEach>
    </table>
    <h3><a href="meals?operation=create">Add a meal</a></h3>
</section>
</body>
</html>