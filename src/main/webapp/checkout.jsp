<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, com.tap.model.Cart, com.tap.daoimpl.CartDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Njoy_the_food CheckOut-Section</title>
<link rel="stylesheet" href="checkout.css?v=<%= System.currentTimeMillis() %>">
<link rel="icon" href="Image/Applicationlogo.png" type="image/png">
</head>
<body>
	<div class="checkout-container">
  <h1>🧾 Checkout Summary</h1>
  
  <%
  	CartDaoImpl cartDao = (CartDaoImpl)session.getAttribute("cartDao");
  	Integer restaurantId = (Integer)session.getAttribute("restaurantId");
  	
  	if (cartDao != null && !cartDao.getMap().isEmpty()){
  %>
  
  <!-- ✅ Order Summary Table -->
  <table>
  	<tr>
  		<th>Item Name</th>
        <th>Quantity</th>
        <th>Price (₹)</th>
        <th>Total (₹)</th>
  	</tr>
  
  
  <%
  	double grandTotal = 0;
  	for (Cart item : cartDao.getMap().values()) {
      double itemTotal = item.getPrice() * item.getQuantity();
      grandTotal += itemTotal;
  %>
  
  <tr>
  	<td><%= item.getItemname() %></td>
  	<td><%= item.getQuantity() %></td>
  	<td><%= item.getPrice() %></td>
  	<td><%= itemTotal %></td>
  </tr>
  
  <%
  	}
  %>
  </table>
  <div class="grand-total">
  	Grand Total:- ₹<%= grandTotal %>
  </div>
  
  <form action="checkout" method="post">
      <label for="address">Delivery Address:-</label>
      <textarea style="resize: none" name="address" id="address" required></textarea><br></br>

      <label>Payment Method:-</label>
      <select name="paymentMethod" required>
        <option value="">--Select Payment Method--</option>
        <option value="credit_card">Credit Card</option>
        <option value="debit_card">Debit Card</option>
        <option value="net_banking">Net Banking</option>
        <option value="upi">UPI</option>
        <option value="cash_on_delivery">Cash on Delivery</option>
      </select> <br>
      <input type="submit" value="Place the Order" />
    </form>
    
    <% } else { %>
      <p style="text-align:center; color:#999;">Your cart is empty.</p>
      <div style="text-align:center; margin-top:20px;">
        <a href="menu.jsp" style="text-decoration:none; color:#ff6600; font-weight:600;">Go back to Menu</a>
      </div>
    <% } %>
</div>
</body>
</html>