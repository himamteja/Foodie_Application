<p align="center">
  <img src="screenshots/Applicationlogo.png" width="350px" alt="Njoy_the_food Logo">
</p>

<h1 align="center">🍽️ Njoy_the_food – Online Food Delivery Web Application</h1>

<p align="center">
  A full-stack Java web application for seamless food ordering, real-time cart updates, order tracking, and chatbot assistance — built using JSP, Servlets & MySQL.
</p>

<p align="center">
  <a href="#-project-overview">Overview</a> •
  <a href="#-tech-stack--badges">Tech Stack</a> •
  <a href="#-features">Features</a> •
  <a href="#-screenshots">Screenshots</a> •
  <a href="#-getting-started">Getting Started</a> •
  <a href="#-contributing">Contributing</a> •
  <a href="#-license">License</a>
</p>

---

## 🎥 Demo Preview (GIF)

<p align="center">
  <img src="screenshots/project-demo-gif.gif" width="450px" alt="Njoy_the_food Demo">
</p>

---

## 🎥 Project Demo Video

➡️ **Watch Full Video:👇**  
[Click here to watch the demo](screenshots/project-demo.mp4)

---

## 🛠 Tech Stack & Badges

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/JSP-323330?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Servlets-4B8BBE?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/JDBC-003B57?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"/>
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white"/>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"/>
  <img src="https://img.shields.io/badge/Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black"/>
  <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white"/>
</p>

---

## 📌 Project Overview

**Njoy_the_food** is a full-stack Java web application that simulates a modern online food delivery platform.

Users can:

- Browse restaurants and food items  
- Add items to cart with quantity updates  
- View order summary and checkout  
- Track order confirmations  
- Access their order history  
- Get help using a simple chatbot-style assistant 🤖  

Built with **JSP, Servlets, JDBC, and MySQL**, it focuses on **clean UI, responsiveness, and real-world flow** like popular apps (Swiggy / Zomato).

---

## ✨ Features

- 🏠 **Home page** with highlighted categories & offers  
- 🍲 **Dynamic menu listing** from backend  
- 🛒 **Add to cart**, update quantity, and remove items  
- 📄 **Order summary & checkout flow**  
- ✅ **Order confirmation** page with success message  
- 📦 **My Orders** page to view past orders  
- 🔐 **User authentication** – login & registration  
- 🤖 **Chatbot-like helper** for guidance  
- 🗄 **MySQL database** integration with JDBC  
- 📱 **Responsive design** for desktop & mobile  

---

## 🖼 Screenshots

> All screenshots are resized to keep the README clean and neat.

## 🖼 Screenshots

### Row 1
<table align="center">
  <tr>
    <td align="center">
      <img src="screenshots/home.png" width="250px"><br>
      <b>🏠 Home Page</b>
    </td>
    <td align="center">
      <img src="screenshots/menu.png" width="250px"><br>
      <b>🍽️ Menu</b>
    </td>
    <td align="center">
      <img src="screenshots/cart.png" width="250px"><br>
      <b>🛒 Cart</b>
    </td>
  </tr>
</table>

---

### Row 2
<table align="center">
  <tr>
    <td align="center">
      <img src="screenshots/chatbot.png" width="250px"><br>
      <b>🤖 Chatbot</b>
    </td>
    <td align="center">
      <img src="screenshots/login.png" width="250px"><br>
      <b>🔐 Login</b>
    </td>
    <td align="center">
      <img src="screenshots/register.png" width="250px"><br>
      <b>📝 Registration</b>
    </td>
  </tr>
</table>

---

### Row 3
<table align="center">
  <tr>
    <td align="center">
      <img src="screenshots/myorders.png" width="250px"><br>
      <b>📦 My Orders</b>
    </td>
    <td align="center">
      <img src="screenshots/checkout.png" width="250px"><br>
      <b>💳 Checkout</b>
    </td>
    <td align="center">
      <img src="screenshots/order-success.png" width="250px"><br>
      <b>🎉 Order Success</b>
    </td>
  </tr>
</table>

---

### Row 4
<table align="center">
  <tr>
    <td align="center">
      <img src="screenshots/delivery-animation.png" width="250px"><br>
      <b>🛵 Delivery Animation</b>
    </td>
  </tr>
</table>

---

## 🚀 Getting Started (High Level)
1️⃣ Clone the Repository
git clone 👉 https://github.com/himamteja/Foodie_Application.git

2️⃣ Import into Eclipse

Open Eclipse IDE

Go to: File → Import → Existing Projects into Workspace

Select the cloned folder and finish

3️⃣ Configure Apache Tomcat

Add a new Apache Tomcat 9/10 server in Eclipse

Right-click project → Properties → Targeted Runtimes → select Tomcat

4️⃣ Setup MySQL Database

Create a database (e.g. njoy_the_food or your DB name)

Import your SQL schema/data if you have one

Update DB credentials in your DB connection class, for example:

## // Example
String url = "jdbc:mysql://localhost:3306/your_db_name";
String username = "root";
String password = "your_password";

5️⃣ Run the Application

Right-click on the project → Run As → Run on Server

Choose your Tomcat server

---

## Open browser and navigate to:👇

http://localhost:8080/Food_Application/

---

##📜 License (MIT)
---
This project is licensed under the MIT License.

©2025 Himamteja Peyyala

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.

---

## 👨‍💻 Author

Himamteja Peyyala
🔗 GitHub: https://github.com/himamteja

---

## 🌐 GitHub: @himamteja

---

## ⭐ Support

If you found this project useful, please consider giving it a ⭐ on GitHub.
It really motivates me to build and share more projects! 😊

---

## 🎉 Thank you for visiting my Project-repo!  
<p align="center">
  <img src="https://media.giphy.com/media/l2SqckbZRno5HfBhS/giphy.gif" width="200"/>
</p>
