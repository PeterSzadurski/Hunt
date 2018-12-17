<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="ISO-8859-1">
<title>Hunt - You Win</title>
<style>
	body {
		background-color: black;
		color: white;
		font-family: "Courier New", Courier, monospace;
	}
	#main {
		margin: auto;
		width: 600px;
		background-color: black;
	}
	#win {
		font-size: 24pt;
		background-color: black;
		color: yellow;
		font-family: "Courier New", Courier, monospace;
		padding: 20px;
		margin: auto;
	}
</style>

</head>
<body>
<%session = request.getSession();%>
<div id="main">
	<div id="win">You Win!</div>
	<span style="colror:white"><%=session.getAttribute("name")%> has completed the hunt!</span>
	<a href="entry.html" style="color:white">Return to Login</a>
</div>

</body>