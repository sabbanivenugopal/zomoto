<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>This is update user page</h3>
<form action="updateUserPage" method= "post">

Name: <input type ="text" name="username" readonly="readonly" value="${userInfo.username}"/></br></br>
Email Id: <input type ="text" name="emailid" value="${userInfo.emailid}"/></br></br>
Mobile No:<input type="text" name="mobile" value="${userInfo.mobile}"/></br></br>
Password: <input type="password" name="password" value="${userInfo.password}"/></br></br>
City: <input type ="text" name="city" value="${userInfo.city}"/></br></br>
<input type ="submit" value="update"></br></br>
</form>
<a href="goToMenu">Click Here</a> to goto menu page..!!
</body>
</html>