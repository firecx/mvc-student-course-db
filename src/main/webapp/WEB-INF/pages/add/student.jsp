<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
</head>
<body>
<form action="/add/student" method="POST">
    Name: <input name="username" />
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