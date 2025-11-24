<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.tap.daoimpl.OrdersDaoImpl, com.tap.model.Orders, com.tap.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Njoy_the_food Orders Section</title>
<link rel="stylesheet" href="myorders.css?v=<%= System.currentTimeMillis() %>">
<link rel="icon" href="Image/Applicationlogo.png" type="image/png">
</head>
<body>

<div class="orders-container">
  <h1>📦 My Orders</h1>

  <%
    User user = (User) session.getAttribute("callingloginservlet");
    if (user == null) {
  %>
      <div class="not-logged">
        <p>You need to <strong>log in</strong> to view your orders.</p>
        <a href="userloginpage.jsp" class="login-btn">Login Now</a>
      </div>
  <%
    } else {
      OrdersDaoImpl ordersDao = new OrdersDaoImpl();
      List<Orders> orderList = ordersDao.getAllOrders();
      boolean hasOrders = false;
  %>

  <div class="order-list">
    <%
      for (Orders order : orderList) {
        if (order.getUserid() == user.getUserId()) {
          hasOrders = true;
    %>
      <div class="order-card" id="order-card-<%= order.getOrderid() %>">
        <h3>Order ID: <%= order.getOrderid() %></h3>
        <p><strong>Restaurant ID:</strong> <%= order.getRestaurantid() %></p>
        <p><strong>Amount:</strong> ₹<%= order.getTotalamount() %></p>
        <p><strong>Status:</strong> <%= order.getStatus() %></p>
        <p><strong>Payment Mode:</strong> <%= order.getPaymentmode() %></p>
        <p><strong>Order Date:</strong> <%= order.getOrderdate() %></p>

        <form action="checkout.jsp" method="post">
          <input type="hidden" name="orderid" value="<%= order.getOrderid() %>">
          <button type="submit" class="reorder-btn">🔁 Order Again</button>
        </form>
        
        <button type="button"
        		class="delete-btn"
        		onclick="deleteOrder(<%= order.getOrderid() %>)">
    			🗑️ Delete
		</button>
        
      </div>
    <%
        }
      }

      if (!hasOrders) {
    %>
      <p class="no-orders">😔 No orders found. Place your first order now!</p>
      <a href="menu.jsp" class="order-now-btn">🍴 Browse Restaurants</a>
    <%
      }
    }
    %>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
function deleteOrder(orderId) {

    // Step 1: Show Confirmation Popup
    Swal.fire({
        title: "Are you sure?",
        text: "This order will be permanently deleted.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes, delete it!",
        cancelButtonText: "Cancel",
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6"
    }).then((result) => {

        // If user clicked YES
        if (result.isConfirmed) {

            // Step 2: Make AJAX Call to DeleteOrderServlet
            $.ajax({
                url: "DeleteOrdersServlet",
                type: "POST",
                data: { orderid: orderId },

                success: function() {
                    // Step 3: Success Toast Message
                    Swal.fire({
                        toast: true,
                        position: "bottom-end",
                        icon: "success",
                        title: "Order Deleted Successfully!",
                        timer: 2000,
                        showConfirmButton: false
                    });

                    // Step 4: Fade Out Deleted Card
                    $("#order-card-" + orderId).fadeOut(500, function(){
                        $(this).remove();
                    });
                },

                error: function() {
                    Swal.fire({
                        toast: true,
                        position: "bottom-end",
                        icon: "error",
                        title: "Failed to delete order!",
                        timer: 2000,
                        showConfirmButton: false
                    });
                }
            });
        }
    });
}
</script>

</body>
</html>
