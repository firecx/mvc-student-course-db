<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<html>
<head>
<meta charset="UTF-8">
<title>Course Form</title>
</head>
<body>
<form action="/add/course" method="POST">
    Course name: <input name="coursename" />
    <br><br>
    Duration: <input name="duration" />
    <br><br>
    Description: <input name="description" />
    <br><br>
    Price: <input name="price" />
    <br><br>
    <input type="submit" value="Submit" />
    <br><br>
</form>
    <a href="/" class="home-button">На главную</a>
    <div class="message">
        <p>${message}</p>
    </div>
</body>
</html>