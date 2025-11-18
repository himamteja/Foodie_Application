package com.tap;

import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * =====================================================================
 * MENU SERVLET — LOAD ALL MENU ITEMS OF A SELECTED RESTAURANT
 * =====================================================================
 * FLOW:
 *   1️⃣ Read restaurantId from URL (menu?restaurantid=101)
 *   2️⃣ Validate restaurantId
 *   3️⃣ Call DAO to fetch menu items for that restaurant
 *   4️⃣ Store the menu list in request scope
 *   5️⃣ Forward to menu.jsp for rendering
 * =====================================================================
 */

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* ================================================================
         * 1️⃣ INITIALIZE DAO TO ACCESS MENU TABLE
         * ---------------------------------------------------------------
         * MenuDaoImpl contains database operations such as:
         *   • getAllMenuByRestaurantId()
         *   • getMenu()
         * ================================================================ */
        MenuDaoImpl impl = new MenuDaoImpl();

        /* ================================================================
         * 2️⃣ FETCH restaurantId FROM REQUEST
         * ---------------------------------------------------------------
         * restaurantId comes from:
         *     restaurant.jsp  →  <a href="menu?restaurantid=101">
         * If absent → user is trying to access menu directly → redirect home.
         * ================================================================ */
        String restaurantParam = req.getParameter("restaurantid");
//        int restaurantId = 0;

        if (restaurantParam == null || restaurantParam.trim().isEmpty() || restaurantParam.equals("null")) {
        	 	resp.sendRedirect("index.jsp");
        	    return;
        } 
        
        int restaurantId;
        
        try {
            restaurantId = Integer.parseInt(restaurantParam);
        } catch (NumberFormatException e) {
            // Value is not a valid integer
            resp.sendRedirect("index.jsp");
            return;
        }

        System.out.println("📌 Restaurant ID Received = " + restaurantId);

        /* ================================================================
         * 3️⃣ FETCH MENU LIST FROM DATABASE USING DAO
         * ---------------------------------------------------------------
         * Example:
         * SELECT * FROM menu WHERE restaurantid = ?
         * ================================================================ */
        List<Menu> list = impl.getAllMenuByRestaurantId(restaurantId);

        // Debug print in console (optional)
        for (Menu menu : list) {
            System.out.println("🍽️ " + menu);
        }

        /* ================================================================
         * 4️⃣ STORE MENU IN REQUEST SCOPE FOR menu.jsp
         * ---------------------------------------------------------------
         * JSP will access it using:
         *     (List<Menu>) request.getAttribute("list")
         * ================================================================ */
        req.setAttribute("list", list);

        /* ================================================================
         * 5️⃣ FORWARD TO JSP FOR DISPLAYING MENU
         * ---------------------------------------------------------------
         * Using RequestDispatcher → NOT redirect
         * Because we want same request & attributes.
         * ================================================================ */
        RequestDispatcher dispatcher = req.getRequestDispatcher("menu.jsp");
        dispatcher.forward(req, resp);
    }
}
