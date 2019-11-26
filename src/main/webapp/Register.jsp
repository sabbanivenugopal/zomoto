<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register page</title>
</head>
<h2>Registration Page</h2>
<body>
<font color="red">${message}</font>
<form action="userregister" method= "post">
Name: <input type ="text" name="username"></br></br>
Email Id: <input type ="text" name="emailid"></br></br>
Mobile No:<input type="text" name="mobile"></br></br>
Password: <input type="password" name="password"></br></br>
City: <input type ="text" name="city"></br></br>
<input type ="submit" value="Register"></br></br>
</form>
<a href ="login.jsp"><span color="red">click here</span></a>for login..!!</br></br>
<a href="goToMenu">Click Here</a> to goto menu page..!!
</body>
</html>