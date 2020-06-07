<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal Update</title>
</head>
<body>
<section>
    <h21><a href="index.html">Home</a></h21>
    <h3>Meal</h3>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}"/>
        <label>Date/Time</label>
        <input type="datetime-local" name="dateTime" value="${meal.dateTime}"/>
        <br>
        <label>Description</label>
        <input type="text" name="description" value="${meal.description}"/>
        <br>
        <label>Calories</label>
        <input type="number" name="calories" value="${meal.calories}"/>
        <br>
        <button type="submit">Submit</button>
    </form>

</section>
</body>
</html>