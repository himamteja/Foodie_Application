<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Warning Section</title>
<link rel="stylesheet" href="loginwarning.css?v=<%= System.currentTimeMillis() %>">
<link rel="icon" href="Image/Applicationlogo.png" type="image/png">
</head>
<body>
<div class="warning-container">
  <h1>⚠️ Login Required</h1>
  <p>You don’t have an account in this application.</p>
  <p>Please register or log in to place your order.</p>

  <div class="buttons">
    <a href="userloginpage.jsp" class="login-btn">🔐 Login</a>
    <a href="user_register.jsp" class="register-btn">📝 Register</a>
  </div>

  <p class="note">If you already have an account, please log in.<br>
  Otherwise, create one to continue your order.</p>
</div>
</body>
</html>
