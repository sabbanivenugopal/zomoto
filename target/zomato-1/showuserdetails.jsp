<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function edituser(name) {
		alert('edit button triggered..!!')
		document.forms[0].action = "${pageContext.request.contextPath}/edituser?username="
				+ name;
		document.forms[0].method = "post";
		document.forms[0].submit();

	}
	function deleteuser(name) {

		alert(name + ' delete button triggred!!')
		document.forms[0].action = "${pageContext.request.contextPath}/deleteuser?username="
				+ name;
		document.forms[0].method = "post";
		document.forms[0].submit();

	}
</script>
</head>
<h2>Show User Register List</h2>
<body>
	<font color="red">${user}</font>
	</br>
	<form action="">
		<table border="1">
			<tr>
				<th>UserName</th>
				<th>EmailId</th>
				<th>Mobile</th>
				<th>Password</th>
				<th>City</th>
			</tr>

			<c:forEach var="user" items="${userdetails}">
				<tr>
					<td>${user.username}</td>
					<td>${user.emailid}</td>
					<td>${user.mobile}</td>
					<td>${user.password}</td>
					<td>${user.city}</td>
					<td><input type="submit" onclick="edituser('${user.username}')" value="Edit" /></td>
					<td><input type="submit"
						onclick="deleteuser('${user.username}')" value="Delete" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	</br>
	<a href="goToMenu">Click Here</a> to goto menu page..!!
</body>
</html>