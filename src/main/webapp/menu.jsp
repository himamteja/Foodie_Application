<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.List, com.tap.model.Menu "%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Njoy_the_food Menu Section</title>
<link rel="stylesheet" href="menu.css?v=<%= System.currentTimeMillis() %>">
<link rel="icon" href="Image/Applicationlogo.png" type="image/png">
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
            placeholder="Search for dishes..."
          />
        </div>
        
        <nav>
          <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="myorders.jsp">My Orders</a></li>
            <li><a href="#offers">Offers</a></li>            
            <li><a href="userloginpage.jsp">Login</a></li>
          </ul>
        </nav>

        <div class="user-actions">
          <a href="cart.jsp" class="cart-icon">
            <img src="Image/shopping-cart.png" alt="shopping-cart">
            <span class="cart-count">
             <%= (session.getAttribute("cartDao") != null) 
            ? ((com.tap.daoimpl.CartDaoImpl)session.getAttribute("cartDao")).getMap().size() 
            : 0 %>
            </span>
          </a>
        </div>
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

    <!-- === Menu Section === -->
    <section id="menu" class="menu-section">
      <h1 style="text-align: center; margin: 30px 0; font-size: 2rem">
        🍽️ Our Delicious Item's
      </h1>
      <div class="menu-grid">
        <%
        List<Menu> list = (List<Menu>)request.getAttribute("list");
        for (Menu menu : list) {
		%>
		
		
        <div class="menu-item">
        <div class="menu-image">
          <img
            src="<%= menu.getImagepath() %>"
            alt="image not found"
          /></div>
          <div class="menu-info">
            <div class="menu-name"><%= menu.getItemname() %></div>
            <div class="menu-description">
             <%= menu.getDescription() %>
            </div>
            <div class="menu-footer">
            <div class="menu-name">⭐<%= menu.getRatings() %></div>
              <div class="menu-price">Rs.<%= menu.getPrice() %></div>
              
              
              <form action="cart" method="post">
              	<input type="hidden" name="restaurantId" value="<%= menu.getRestaurantid() %>">
              	<input type="hidden" name="itemid" value="<%= menu.getMenuid() %>">
              	<input type="hidden" value="1" min="1" name="quantity">
              	<input type="hidden" name="action" value="add">
              	<input class="add-button" type="submit" value="Add to cart"> 
              </form>
              
              
            </div>
        </div>
      </div>
        <% }
        %>   
      </div>
    </section>
    
    <div id="chatbot-icon">💬</div>

<div id="chatbot-container" class="hidden">
  <div class="chat-header">
    <h3>Foodie Assistant 🤖</h3>
    <button id="close-chat">×</button>
  </div>
  <div id="chat-body" class="chat-body"></div>
  <div class="chat-input">
    <input type="text" id="user-input" placeholder="Ask me something..." />
    <button id="send-btn">Send</button>
  </div>
</div>
    

    <!-- Special Offers -->
    <section class="offers" id="offers">
      <div class="container">
        <h2 class="section-title">Special Offers</h2>
        <div class="offers-grid">
          <div class="offer-card">
            <div class="offer-image">
              <img
                src="https://www.holidify.com/images/cmsuploads/compressed/shutterstock_649541308_20191010160155.png"
                alt="image not found"
              />
            </div>
            <div class="offer-title">50% Offer</div>
            <div class="offer-description">On your first order</div>
            <div class="offer-code">Use: Welcome50</div>
          </div>

          <div class="offer-card">
            <div class="offer-image">
              <img
                src="https://wallpapers.com/images/hd/traditional-thali-platter-indian-food-7ppdmw8bs4n1f36j.jpg"
                alt="food"
              />
            </div>
            <div class="offer-title">Free Delivery</div>
            <div class="offer-description">On order above 150</div>
            <div class="offer-code">Use: FreeDeal</div>
          </div>

          <div class="offer-card">
            <div class="offer-image">
              <img
                src="https://cdn.pixabay.com/photo/2020/05/17/04/22/pizza-5179939_1280.jpg"
                alt="pizza"
              />
            </div>
            <div class="offer-title">Buy 1 Get 1</div>
            <div class="offer-description">On slected pizzas</div>
            <div class="offer-code">Use: Bogo</div>
          </div>

          <div class="offer-card">
            <div class="offer-image">
              <img
                src="https://butteroverbae.com/wp-content/uploads/2020/10/karachi-chicken-biryani-11.jpg"
                alt="biriyani"
              />
            </div>
            <div class="offer-title">15% Offer</div>
            <div class="offer-description">On Weekend orders</div>
            <div class="offer-code">Use: WeekEnd20</div>
          </div>

          <div class="offer-card">
            <div class="offer-image">
              <img
                src="https://img.freepik.com/premium-vector/best-weekend-offer-speech-bubble_251840-1634.jpg"
                alt="image not found"
              />
            </div>
            <div class="offer-title">20% + coupen</div>
            <div class="offer-description">
              Orders above 300 on only weekends
            </div>
            <div class="offer-code">Use: cpRights</div>
          </div>
        </div>
      </div>
    </section>

    <!-- Footer Section -->
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
        <div class="footer-button">
          <p>&copy; 2025 Njoy_the_food. All rights reserved.</p>
        </div>
      </div>
    </footer> 
    
    <script>
document.addEventListener("DOMContentLoaded", () => {
  const chatbotIcon = document.getElementById("chatbot-icon");
  const chatbotContainer = document.getElementById("chatbot-container");
  const closeChat = document.getElementById("close-chat");
  const sendBtn = document.getElementById("send-btn");
  const chatBody = document.getElementById("chat-body");
  const userInput = document.getElementById("user-input");

  // Open chatbot window
  chatbotIcon.addEventListener("click", () => {
    chatbotContainer.classList.toggle("hidden");
  });

  // Close chatbot window
  closeChat.addEventListener("click", () => {
    chatbotContainer.classList.add("hidden");
  });

  // Handle sending messages
  function sendMessage() {
  const message = userInput.value.trim(); // get message safely

  if (message === "") return; // stop empty input
  
  // ✅ First show user message (BEFORE clearing)
  const userMsg = document.createElement("div");
  userMsg.classList.add("user-msg");
  userMsg.innerHTML = `<strong>You:</strong> ${message}`;
  chatBody.appendChild(userMsg);
  chatBody.scrollTop = chatBody.scrollHeight;

  userInput.value = ""; // clear after displaying

  // Bot typing indicator
  const typingMsg = document.createElement("div");
  typingMsg.classList.add("bot-msg");
  typingMsg.innerHTML = "<em>🤖 Bot is typing...</em>";
  chatBody.appendChild(typingMsg);
  chatBody.scrollTop = chatBody.scrollHeight;

  // Simulate bot response
  setTimeout(() => {
    typingMsg.remove();

    const botReply = document.createElement("div");
    botReply.classList.add("bot-msg");
    botReply.innerHTML = `<strong>Bot:</strong>Welcome to Njoy_the_food I'm your Foodie Assistant 🍔! How can I Help you...?`;

    chatBody.appendChild(botReply);
    chatBody.scrollTop = chatBody.scrollHeight;
  }, 1200);
}


  // Send button click
  sendBtn.addEventListener("click", sendMessage);

  // Send message with Enter key
  userInput.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
      e.preventDefault();
      sendMessage();
    }
  });
});
</script>
    
         
</body>
</html>