<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.tap.model.Orders, com.tap.model.Cart, com.tap.daoimpl.CartDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Confirmation Section</title>
<link rel="stylesheet" href="orderconfirm.css?v=<%= System.currentTimeMillis() %>">
<link rel="icon" href="Image/Applicationlogo.png" type="image/png">
</head>
<body>

<!-- 🚴‍♂️ FULLSCREEN LOADING ANIMATION -->
<div id="loading-animation">
    <img src="https://cdn.dribbble.com/users/221637/screenshots/12282529/rider.gif" 
         alt="Loading...">
</div>

<div class="order-confirm-container">

  <!-- 🎬 Delivery Man Animation -->
  <div class="success-animation">
    <div class="checkmark">
       <video autoplay muted loop playsinline width="220">
    		<source src="Image/delivery_man.mp4" type="video/mp4">
  		</video>
    </div>
  </div>

  <h1>Thank You for Your Order!</h1>

  <div class="order-summary">
    <%
      Orders order = (Orders) session.getAttribute("orders");
      if (order != null) {
    %>
      <p><strong>Order ID:</strong> <%= order.getOrderid() %></p>
      <p><strong>Status:</strong> <%= order.getStatus() %></p>
      <p><strong>Payment Mode:</strong> <%= order.getPaymentmode() %></p>
      <p><strong>Total Amount:</strong> ₹<%= order.getTotalamount() %></p>
      <p><strong>Order Date:</strong> <%= order.getOrderdate() %></p>
      
      <p class="eta">⏰ Estimated Delivery: 
      <%= new java.text.SimpleDateFormat("hh:mm a").format(new java.util.Date(System.currentTimeMillis() + (45 * 60 * 1000))) %></p>

    <% } else { %>

      <p style="color:red;">Order details not found.</p>

    <% } %>
  </div>

  <!-- 🍔 Ordered Items -->
  <%
    CartDaoImpl cartDao = (CartDaoImpl) session.getAttribute("cartDao");
    if (cartDao != null && !cartDao.getMap().isEmpty()) {
  %>
    <h2>Your Ordered Items</h2>
    <table class="items-table">
      <tr>
        <th>Item</th>
        <th>Qty</th>
        <th>Price</th>
      </tr>
      <%
        for (Cart item : cartDao.getMap().values()) {
      %>
      <tr>
        <td><%= item.getItemname() %></td>
        <td><%= item.getQuantity() %></td>
        <td>₹<%= item.getPrice() * item.getQuantity() %></td>
      </tr>
      <% } %>
    </table>
  <% } %>

  <!-- 🔘 Buttons -->
  <div class="buttons">
    <button class="home-btn" onclick="goHome()">🏠 Go to Home</button>

    <a href="menu?restaurantid=<%= ((Orders)session.getAttribute("orders")).getRestaurantid() %>" 
       class="order-btn">🍽️ Order More</a>
  </div>

</div>

<!-- 🚦 JavaScript for Delay Loading -->
<script>
function goHome() {
    document.getElementById("loading-animation").style.display = "flex";

    setTimeout(function() {
        window.location.href = "<%= request.getContextPath() %>/restaurant";
    }, 1000);
}
</script>

</body>
</html>
