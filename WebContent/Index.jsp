<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>

	body {
		background-color: black;
		color: white;
		font-family: "Courier New", Courier, monospace;
	}

</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HUNT</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>

$(document).keydown(function(e){
	console.log("test");
	//switch (e.keyCode) {
		//case 38:
			//document.getElementById("key").value = "yeeto"
		//	console.log(document.getElementById("key").value)
		//	$.get('myservlet', {
		//		key : e.keycode
		//		},
		//		function(responseText) {
		//			$('#gameOutput').text(responseText);
		//		});
	
		//document.getElementById("key").value = e.keyCode;
		var keys = e.keyCode;
		console.log (keys);
		$.get('GameServlet', {
			key : keys
		}, function(responseText) {
			$('#gameOutput').html(responseText);
		});
	});		
	
$(document).ready (function(){
	console.log("test2");
	
	var keys = 93;
	//switch (e.keyCode) {
		//case 38:
			//document.getElementById("key").value = "yeeto"
		//	console.log(document.getElementById("key").value)
		//	$.get('myservlet', {
		//		key : e.keycode
		//		},
		//		function(responseText) {
		//			$('#gameOutput').text(responseText);
		//		});
	
		//document.getElementById("key").value = e.keyCode;

		$.get('GameServlet', {
			key : keys
		
		}, function(responseText) {
			$('#gameOutput').html(responseText);
		});
	});
//	}
</script>

<style>
.grid {
	display: grid;
	grid-template-columns: 15px 15px 15px 15px 15px 15px 15px 15px 15px 15px;
	align-content: end;
}
.grid > div {
	text-align: center;
	font-size: 12px;
}

</style>

</head>
<body>
test
<form id="keyInput" action = "GameServlet">
<input type = "hidden" id = "key" name = "key">
</form>
<table id ="gameOutput">
	
 </table>
</body>
</html>