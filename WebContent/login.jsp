<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hunt Login</title>
<style>
	body {
		background-color: black;
		color: white;
		font-family: "Courier New", Courier, monospace;
	}
	input {
		background-color: black;
		border-color: white;
		color: white;
		font-family: "Courier New", Courier, monospace;
	}
	td {
		padding: 5px;
	}
</style>
</head>
<body>
	<h1>Login Page</h1>
	<c:set var="userExists" value='${requestScope["userExists"]}'/>
	<c:out value="${userExists}"/>
	<c:set var="passwordWrong" value='${requestScope["passwordWrong"]}'/>
	<c:out value="${passwordWrong}"/>
	<form action="AccountServlet" method="POST">
		<input type="hidden" name="formNumber" value="2">
		<label for="username">Username: </label>
		<input type="text" id="username" name="username" placeholder="Username"/>
		<br/>
		<label for="password">Password: </label>
		<input type="password" id="password" name="password" placeholder="Password"/>
		<br/>
		<input type="submit" value="Login"/>
	</form>
	<c:if test="${requestScope.userExists != null}">
		<form action="create-account.jsp" method="post">
			<input type="submit" value="I want to create an account">
		</form>
	</c:if>
</body>
</html>