<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.tap.model.Cart, com.tap.daoimpl.CartDaoImpl" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Njoy_the_food - Checkout</title>
<link rel="stylesheet" href="cart.css?v=<%= System.currentTimeMillis() %>">
<link rel="icon" href="Image/Applicationlogo.png" type="image/png">
</head>

<body>

<%
    CartDaoImpl cartDao = (CartDaoImpl) session.getAttribute("cartDao");
    Integer restaurantId = (Integer) session.getAttribute("restaurantId");

    boolean hasItems = (cartDao != null && !cartDao.getMap().isEmpty());

    String safeMenuUrl = (restaurantId == null)
                            ? "restaurant"
                            : "menu?restaurantid=" + restaurantId;
%>

<!-- ===================== EMPTY CART CHECK FIRST ===================== -->

<% if (!hasItems) { %>

<div class="empty-full-wrapper">

    <div class="empty-cart-card">

        <img src="https://cdni.iconscout.com/illustration/premium/thumb/empty-cart-3428210-2902552.png" 
             class="empty-cart-img">

        <h2>Your cart is empty</h2>
        <p>Go back and explore restaurants</p>

        <a href="<%= safeMenuUrl %>" class="btn btn-empty-card">Go Back To Menu</a>

    </div>

</div>

<% return; } %> <!-- STOP rendering page -->

<!-- ======================================================
     IF CART HAS ITEMS → SHOW FULL CHECKOUT LAYOUT
====================================================== -->

<div class="checkout-wrapper">

    <!-- ========================= LEFT SIDE ========================= -->
    <div class="checkout-left">

        <div class="left-container">

            <!-- LEFT PROGRESS BAR -->
            <div class="progress-bar">

                <div class="progress-node active-step">
                    <img src="https://www.neuroartsresourcecenter.com/_next/static/media/profile.a227a6b3.png" 
                    class="progress-icon">
                </div>

                <div class="progress-line"></div>

                <div class="progress-node">
                    <img src="https://static.vecteezy.com/system/resources/previews/014/022/352/original/gps-icon-logo-design-map-pointer-icon-pin-location-symbol-vector.jpg" 
                    class="progress-icon">
                </div>

                <div class="progress-line"></div>

                <div class="progress-node">
                    <img src="https://static.vecteezy.com/system/resources/previews/017/314/782/original/an-amazing-icon-of-payment-easy-to-use-and-download-vector.jpg" 
                    class="progress-icon">
                </div>

            </div>

            <!-- LEFT CONTENT -->
            <div class="left-content">

                <div class="section-card">
                    <h3>Info/Suggestion...</h3>
                    <p>To place your order, click the <q>Help</q> 
                    button if you have any doubts. 
					If you're facing any issues, use the <q>Suggestion</q> 
					button to share your ideas—we'll work on them.
					</p>

                    <div class="left-buttons">
                        <a href="#" class="btn login">HELP</a>
                        <a href="#" class="btn signup">Suggestion</a>
                    </div>
                </div>

                <div class="section-card">
                    <h3>Delivery Address</h3>
                    <p>Select your delivery address or add a new one.</p>
                </div>

                <div class="section-card">
                    <h3>Payment</h3>
                    <p>Select a payment method to continue.</p>
                </div>

            </div>

        </div>
    </div>



    <!-- ========================= RIGHT SIDE ========================= -->
    <div class="checkout-right">

        <!-- Restaurant Title -->
        <div class="restaurant-header">
            <h3>Your Items</h3>
        </div>

        <!-- CART ITEMS -->
        <div class="cart-item-list">

            <% for (Cart item : cartDao.getMap().values()) { %>

            <div class="cart-item-card">

                <img class="item-img" src="<%= item.getImagepath() %>" alt="image not found">

                <div class="item-info">
                    <h4><%= item.getItemname() %></h4>
                    <p>₹<%= item.getPrice() %></p>

                    <div class="qty-controls">

                        <!-- Decrease -->
                        <form action="cart" method="post">
                            <input type="hidden" name="itemId" value="<%= item.getItemid() %>">
                            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                            <button class="qty-btn" <% if(item.getQuantity()==1) { %> disabled <% } %>>-</button>
                        </form>

                        <span><%= item.getQuantity() %></span>

                        <!-- Increase -->
                        <form action="cart" method="post">
                            <input type="hidden" name="itemId" value="<%= item.getItemid() %>">
                            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                            <button class="qty-btn">+</button>
                        </form>

                    </div>
                </div>

                <!-- Remove Item -->
                <form action="cart" method="post">
                    <input type="hidden" name="itemId" value="<%= item.getItemid() %>">
                    <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                    <input type="hidden" name="action" value="remove">
                    <button class="remove-btn">🗑️</button>
                </form>

            </div>

            <% } %>

        </div>
        
        <!-- ADD MORE ITEMS BUTTON -->
<div class="add-more-box">
    <a href="<%= safeMenuUrl %>" class="btn add-more-btn">+ Add More Items</a>
</div>

        <!-- BILL BOX -->
        <div class="bill-box">
            <h4>Bill Details</h4>
            <p>Item Total: ₹<%= cartDao.getTotalPrice() %></p>
            <p>Delivery Fee: ₹30</p>
            <p>GST: ₹18</p>
            <hr>
            <p class="total">TOTAL PAY: ₹<%= cartDao.getTotalPrice() + 48 %></p>
        </div>

        <!-- CHECKOUT BUTTON -->
        <form action="<%= request.getContextPath() %>/checkout" method="get">
            <input type="submit" class="btn checkout-btn" value="Proceed to Checkout">
        </form>

    </div>

</div>

</body>
</html>
