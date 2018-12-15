<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account</title>
</head>
<body>
	<h1>Create Account</h1>
	<c:set var="userExists" value='${requestScope["userExists"]}'/>
	<c:out value="${userExists}"/>
	<form action="AccountServlet" method="POST">
		<input type="hidden" name="formNumber" value="1">
		<label for="username">Username: </label>
		<input type="text" id="username" name="username" placeholder="Username"/>
		<br/>
		<label for="password">Password: </label>
		<input type="password" id="password" name="password" placeholder="Password"/>
		<br/>
		<input type="submit" value="Create Account"/>
	</form>
	<c:if test="${requestScope.userExists != null}">
		<form action="login.jsp">
			<input type="submit" value="I want to login">
		</form>
	</c:if>
</body>
