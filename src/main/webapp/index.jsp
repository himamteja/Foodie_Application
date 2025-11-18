<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.List, com.tap.model.Restaurant, com.tap.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant Home Page</title>
<link rel="icon" href="Image/Applicationlogo.png" type="image/png">
<link rel="stylesheet" href="style.css?v=<%= System.currentTimeMillis() %>">

</head>
<body>
	<header>
      <div class="container">
        <div class="location">
        <img style="border-radius: 50%; width: 70px; height: 70px" src="Image/Applicationlogo.png" alt="Application Logo">
          <h3>
            <span>📍 Deliver to: <strong>Bengaluru, 560076</strong></span>
          </h3>
        </div>

        <div class="search-container">
          <input
            type="text"
            class="search-input"
            placeholder="Search for restaurant..."
          />
        </div>
        
        <nav class="navbar">
  			<ul>
    		<%
      			User user = (User) session.getAttribute("user");
      			if (user != null) {
    		%>
        		<!-- 👤 After Login Navbar -->
        		<li><a href="index.jsp">Home</a></li>
        		<li><a href="myorders.jsp">My Orders</a></li>
        		<li><a href="#contact">Contact</a></li>
        		<li><a href="logout">Logout</a></li>
            	<li><a href="profile.jsp">
            		<img style="width:40px; height:40px; border-radius:50%; alt="image not found" src="Image/profile-logo.png">
            	</a></li>
    		<%
      			} else {
    		%>
        		<!-- 🚪 Before Login Navbar -->
        		<li><a href="index.jsp">Home</a></li>
        		<li><a href="#about">About</a></li>
        		<li><a href="#contact">Contact</a></li>
        		<li><a href="userloginpage.jsp">Login</a></li>
    		<%
      			}
    		%>
  			</ul>
		</nav>
      </div>
    </header>

    <!-- === Hero Section === -->
    <section class="hero">
      <div class="hero-container">
        <h1>Delicious Food Delivered Fast</h1>
        <p>
          Order from your favorite restaurants and get your food delivered to
          your doorstep.
        </p>
        <a href="#menu" class="cta-button"> Order Now</a>
      </div>
    </section>

    <!-- === Categories Section === -->
    <section class="categories" id="categories">
      <div class="container">
        <h2 class="section-title">Popular Categories</h2>
        <div class="categories-grid">
          <!-- Section 1 -->
          <div class="categories-card">
            <img
              src="https://cdn.pixabay.com/photo/2020/05/17/04/22/pizza-5179939_1280.jpg"
              width="100px"
              height="50px"
              alt="image not found"
            />
            <h3>Pizza</h3>
            <p>Delicious pizzas from top restaurants</p>
          </div>

          <!-- Categories Section 2 -->
          <div class="categories-card">
            <img
              src="https://www.mashed.com/img/gallery/only-14-percent-of-people-consider-this-the-best-fast-food-burger/l-intro-1624562493.jpg"
              width="100px"
              height="50px"
              alt="image not found"
            />
            <h3>Burger</h3>
            <p>Delicious Burger from top restaurants</p>
          </div>

          <!-- Categories Section 3 -->
          <div class="categories-card">
            <img
              src="https://www.mashed.com/img/gallery/popular-kfc-menu-items-ranked-worst-to-best/l-intro-1622140749.jpg"
              width="100px"
              height="50px"
              alt="image not found"
            />
            <h3>KFC</h3>
            <p>Delicious KFC from top restaurants</p>
          </div>

          <!-- Categories Section 4 -->
          <div class="categories-card">
            <img
              src="https://assets.cntraveller.in/photos/6218cfdbd84ae9ad8ecff426/master/w_1600%2Cc_limit/sp%2520biryani.jpg"
              width="100px"
              height="50px"
              alt="image not found"
            />
            <h3>Biriyani</h3>
            <p>Delicious Biriyani from top restaurants</p>
          </div>

          <!-- Categories Section 5 -->
          <div class="categories-card">
            <img
              src="https://media.cntraveller.in/wp-content/uploads/2020/05/homemade-paneer-recipes-1366x768.jpg"
              width="100px"
              height="50px"
              alt="image not found"
            />
            <h3>panner</h3>
            <p>Delicious Panner dishes from top restaurants</p>
          </div>

          <!-- Categories Section 6 -->
          <div class="categories-card">
            <img
              src="https://i.ytimg.com/vi/G_B1Py4dNR8/maxresdefault.jpg"
              width="100px"
              height="50px"
              alt="image not found"
            />
            <h3>Mutton Curry</h3>
            <p>Delicious Mutton curry from top restaurants</p>
          </div>

          <!-- Categories Section 7 -->
          <div class="categories-card">
            <img
              src="https://wallpapers.com/images/hd/traditional-thali-platter-indian-food-7ppdmw8bs4n1f36j.jpg"
              width="100px"
              height="50px"
              alt="image not found"
            />
            <h3>South Indiam Meals</h3>
            <p>Delicious South Indian Meals from top restaurants</p>
          </div>

          <!-- Categories Section 8 -->
          <div class="categories-card">
            <img
              src="https://c.ndtvimg.com/2023-07/rmjkg64_indian-food_625x300_25_July_23.jpg"
              width="100px"
              height="50px"
              alt="image not found"
            />
            <h3>Pure Veg</h3>
            <p>Delicious Pure Veg dishes from top restaurants</p>
          </div>
        </div>
      </div>
    </section>

    <!-- === Restaurant Section === -->
    <section class="restaurants" id="restaurants">
      <div class="container">
    	  <h2 class="section-title">Top Restaurant Near You</h2>
      
          <div class="restaurant-grid">
      <%
    	List<Restaurant> allRestaurant = (List<Restaurant>) request.getAttribute("allRestaurant");

    		if (allRestaurant != null && !allRestaurant.isEmpty()) {
        	for (Restaurant restaurant : allRestaurant) {
		%>

			<a style="text-decoration: none;" href="menu?restaurantid=<%= restaurant.getRestaurantid() %>">
  				<div class="restaurant-card">
    			<img src="<%= restaurant.getImagepath() %>" width="100px" height="50px" alt="image not found" />
    				<div class="restaurant-info">
      				<div class="restaurant-name"><%= restaurant.getName() %></div>
      				<div class="restaurant-cuisine"><%= restaurant.getCusinetype() %></div>
      				<div class="restaurant-cuisine">LOCATION = <%= restaurant.getAddress() %></div>
    			</div>
    			<div class="restaurant-details">
      				<div class="ratings">⭐ <%= restaurant.getRating() %></div>
      				<div class="delivery-time"><%= restaurant.getEta() %></div>
    			</div>
  			</div>
			</a>

		<%
        	}
    			} else {
		%>
			<p style="text-align:center; color:#777; font-weight:bold;">🍽️ No restaurants available right now. Please try again later.</p>
		<%
    		}
		%>

        </div>
      </div>
    </section>

    <!-- === Footer Section === -->
    <footer class="footer" id="contact">
      <div class="container">
        <div class="footer-content">
          <div class="footer-section">
            <h3>FoodieExpress</h3>
            <p>
              Your favourite food delivery app bringing delicious meals right to
              your doorsteps.
            </p>
          </div>

          <div class="footer-section">
            <h3>Quick Links</h3>
            <ul>
              <li><a href="#restaurants">Restaurant</a></li>
              <li><a href="#menu">Menu</a></li>
              <li>Offers</li>
              <li>Contact</li>
            </ul>
          </div>

          <div class="footer-section">
            <h3>Support</h3>
            <ul>
              <li>Help Center</li>
              <li>Track Orders</li>
              <li>Refund Policy</li>
              <li>Terms & Conditions</li>
            </ul>
          </div>

          <div class="footer-section">
            <h3>Contact-Info</h3>
            <ul>
              <li>📞 01-843-143-FOODIE</li>
              <li>📧 support@foodieexpress.com</li>
              <li>📍 BTM Layout-2nd Stage, BTM 560076</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="footer-button">
            <p>&copy; 2025 Njoy_the_food. All rights reserved.</p>
          </div>
    </footer>
</body>
</html>