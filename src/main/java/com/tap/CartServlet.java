package com.tap;

import java.io.IOException;

import com.tap.daoimpl.CartDaoImpl;
import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Cart;
import com.tap.model.Menu;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * ======================================================================
 * 🛒 CartServlet — Manages the Shopping Cart for the user
 * ======================================================================
 * This servlet handles ALL cart-related operations:
 *
 *  ✔ Adding a menu item to the cart  
 *  ✔ Updating the quantity of an item  
 *  ✔ Removing an item from the cart  
 *  ✔ Linking cart with a specific restaurant  
 *  ✔ Ensuring only logged-in users can use the cart  
 *
 *  🚀 KEY POINTS:
 *  - The cart is stored inside SESSION (so every user has their own cart)
 *  - If user is not logged in, immediately block and redirect
 *  - After every action, user is redirected to cart.jsp
 *  - Menu items are fetched from DB using MenuDao
 *  - Cart items are stored in cartDao map inside session
 *
 * ======================================================================
 */

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ==================================================================
        // SECTION 1️⃣ : Retrieve Existing Session & Validate Login
        // ==================================================================
        /**
         * We must check if the user is logged in.
         * - If NO login → Do not allow cart operations
         * - Redirect the user to loginwarning.jsp
         */
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("callingloginservlet");

        if (user == null) {
            System.out.println("⚠️ User not logged in — redirecting to login warning page.");
            resp.sendRedirect("loginwarning.jsp");
            return; // Stop further code execution
        }

        // ==================================================================
        // SECTION 2️⃣ : Cart Initialization for First-Time Users
        // ==================================================================
        /**
         * Each user gets their own cart stored in HttpSession.
         * We check if cartDao already exists:
         *
         *  ➤ If YES → use the existing cart  
         *  ➤ If NO  → create a new CartDaoImpl object
         */
        CartDaoImpl cartDao = (CartDaoImpl) session.getAttribute("cartDao");

        if (cartDao == null) {
            cartDao = new CartDaoImpl(); // New empty cart
            session.setAttribute("cartDao", cartDao);
        }

        // ==================================================================
        // SECTION 3️⃣ : Save Restaurant ID into Session
        // ==================================================================
        /**
         * When user selects a restaurant, we store restaurantId in session.
         * This ensures:
         *
         *  ✔ Cart belongs to a single restaurant  
         *  ✔ User cannot mix items from different restaurants  
         */
        String restaurantParam = req.getParameter("restaurantId");

        if (restaurantParam != null && !restaurantParam.isEmpty()) {
            int restaurantId = Integer.parseInt(restaurantParam);
            session.setAttribute("restaurantId", restaurantId);
        }

        // ==================================================================
        // SECTION 4️⃣ : Identify Which Cart Action User Wants
        // ==================================================================
        /**
         * Based on the 'action' parameter, we decide:
         *
         *  - add    → addCartItem()
         *  - update → updateCartItem()
         *  - remove → deleteCartItem()
         *
         *  This keeps code clean and modular.
         */
        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                addCartItem(req, cartDao);

            } else if ("update".equals(action)) {
                updateCartItem(req, cartDao);

            } else if ("remove".equals(action)) {
                deleteCartItem(req, cartDao);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Always update session with latest cart values
        session.setAttribute("cartDao", cartDao);

        // ==================================================================
        // SECTION 5️⃣ : Redirect User Back to the Cart Page
        // ==================================================================
        /**
         * After any action (add/update/remove), we redirect back to cart.jsp
         * instead of opening a blank servlet page.
         */
        resp.sendRedirect("cart.jsp");
    }

    // ======================================================================
    // FUNCTION 1️⃣ : ADD ITEM TO CART
    // ======================================================================
    /**
     * Fetch menu item from database → Create Cart object → Add to cartDao
     */
    private void addCartItem(HttpServletRequest req, CartDaoImpl cartDao) {

        int itemId = Integer.parseInt(req.getParameter("itemid"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        // Fetch menu details from database
        MenuDaoImpl impl = new MenuDaoImpl();
        Menu menuItem = impl.getMenu(itemId);

        if (menuItem != null) {

            // Create a new Cart item
            Cart item = new Cart(
                    menuItem.getMenuid(),
                    menuItem.getItemname(),
                    menuItem.getPrice(),
                    quantity,
                    menuItem.getImagepath()
            );

            // Add to cart
            cartDao.addCartItem(item);
        }
    }

    // ======================================================================
    // FUNCTION 2️⃣ : UPDATE CART ITEM QUANTITY
    // ======================================================================
    /**
     * Receives new quantity from UI  
     * Calls cartDao.updateCartItem() to update in map  
     */
    private void updateCartItem(HttpServletRequest req, CartDaoImpl cartDao) {

        int itemId = Integer.parseInt(req.getParameter("itemId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        cartDao.updateCartItem(itemId, quantity);
    }

    // ======================================================================
    // FUNCTION 3️⃣ : DELETE ITEM FROM CART
    // ======================================================================
    /**
     * Removes the item from cart using itemId
     */
    private void deleteCartItem(HttpServletRequest req, CartDaoImpl cartDao) {

        int itemId = Integer.parseInt(req.getParameter("itemId"));
        cartDao.deleteCartItem(itemId);
    }

}