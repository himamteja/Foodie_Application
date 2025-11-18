<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Register Section</title>
<link rel="icon" href="Image/Applicationlogo.png" type="image/png">
<link rel="stylesheet" href="register.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>
	<form action="callingprogram" method="post">
		<h1>User Registration Form</h1>

			<label for="name">Name</label>
			<input type="text" name="name" id="name" required>
				
			<label for="username">UserName</label>
			<input type="text" name="username" id="username" required>
				
			<label for="password">Password</label>
			<input type="password" name="password" id="password" required>

			<label for="email">Email</label>
			<input type="email" name="email" id="email" required>

			<label for="phone">Phone</label>
			<input type="tel" name="phone" id="phone" required>

			<label for="address">Address</label>
			<input type="text" name="address" id="address" required>
	
			<input type="submit" value="Submit">
	</form>
</body>
</html>