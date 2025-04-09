<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<html>
<head>
<meta charset="UTF-8">
<title>Course Form</title>
</head>
<body>
<form action="../addCourse" method="POST">
    ID: <input name="courseid" />
    <br><br>
    Course name: <input name="coursename" />
    <br><br>
    <input type="submit" value="Submit" />
    <br><br>
</form>
    <a href="/index.jsp" class="home-button">На главную</a>
    <div class="message">
        <p>${message}</p>
    </div>
</body>
</html>