<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<h2>Login Page</h2>
<body>
<font color= "red">${message}</font>
<form action="loginuser" method= "post">
userName: <input type="text" name = "emailid"></br></br>
Password: <input type="password" name = "password"></br></br>
<input type="submit" value="login"></br>
</form>
<a href="goToMenu">Click Here</a> to goto menu page..!!
</body>
</html>