<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login Section</title>
<link rel="icon" href="Image/Applicationlogo.png" type="image/png">
<link rel="stylesheet" href="login.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>
	<form action="callingloginservlet">
		<h1>Welcome to Njoy_the_food</h1>
		<h3>Please Login here 👇</h3>
		<h1>User Login</h1>
		
		<% String errorMessage = (String)request.getAttribute("errorMessage"); 
			if (errorMessage != null){
		%>
		
		<p class="error-Message"><%= errorMessage %></p>
		
		<% 
			}
		%>
			<label for="username">UserName</label>
			<input type="text" name="username" id="username" required>
			
			<label for="password">Password</label>
			<input type="password" name="password" id="password" required>										
			<input type="submit" value="Login">
			
			<p>
			If you Don't have an account <a href="user_register.jsp">click
				here to Register</a>
			</p>
	</form>
</body>
</html>