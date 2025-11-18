<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.tap.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Section</title>
<link rel="stylesheet" href="profile.css?v=<%= System.currentTimeMillis() %>">
<link rel="icon" href="Image/Applicationlogo.png" type="image/png">
</head>
<body>
	<%
	 User user = (User)session.getAttribute("callingloginservlet");
	if(user == null){
		response.sendRedirect("userloginpage.jsp");
		return;
	}
	%>
	
	<div class="profile-container">
		<div class="profile-header">
			<img src="https://static.vecteezy.com/system/resources/previews/009/209/212/original/neon-glowing-profile-icon-3d-illustration-vector.jpg" alt="Profile Avatar" class="avatar">
			<h2>Welcome, <%= user.getName() %> 👋</h2>
		</div>
		
		<div class="profile-details">
			<p><strong>User Id:-</strong><%= user.getUserId() %></p>
			<p><strong>Email:-</strong><%= user.getEmail() %></p>
			<p><strong>Phone:-</strong><%= user.getPhone() %></p>
			<p><strong>Address:-</strong><%= user.getAddress() != null ? user.getAddress() : "Not Provided" %></p>
		</div>
		
		<div class="profile-action">
			<a href="myorders.jsp" class="btn">🧾 My Orders</a>
			<a href="#" class="btn">✏️ Edit Profile</a>
			<a href="" class="btn logout">🚪 Logout</a>
		</div>
	</div>
</body>
</html>