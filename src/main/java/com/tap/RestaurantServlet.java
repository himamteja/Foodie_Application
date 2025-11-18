package com.tap;

import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * =====================================================================
 * RESTAURANT SERVLET — LOADS ALL RESTAURANTS FOR HOMEPAGE
 * =====================================================================
 * PURPOSE:
 *   ⭐ This servlet loads the list of all restaurants from the database.
 *   ⭐ Sends that list to index.jsp for display.
 *
 * REQUEST FLOW:
 *   ➤ Browser hits: /restaurant
 *   ➤ Servlet loads all restaurant details
 *   ➤ Stores data in request scope
 *   ➤ Forwards to index.jsp to show restaurants
 * =====================================================================
 */

@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* ================================================================
         * 1️⃣ CREATE DAO OBJECT (RestaurantDaoImpl)
         * ---------------------------------------------------------------
         * This DAO contains all database operations related to restaurants.
         * We call getAllRestaurant() → returns List<Restaurant>
         * ================================================================ */
        RestaurantDaoImpl impl = new RestaurantDaoImpl();


        /* ================================================================
         * 2️⃣ FETCH ALL RESTAURANTS FROM DATABASE
         * ---------------------------------------------------------------
         * Example return:
         *   [
         *     {id:1, name:"Dominos", rating:4.2, location:"BTM"},
         *     {id:2, name:"Mehfil", rating:4.5, location:"Kukatpally"},
         *     ...
         *   ]
         * ================================================================ */
        List<Restaurant> allRestaurant = impl.getAllRestaurant();


        /* ================================================================
         * 3️⃣ STORE THE RESTAURANT LIST IN REQUEST SCOPE
         * ---------------------------------------------------------------
         * 'allRestaurant' will now be available in index.jsp using:
         *   request.getAttribute("allRestaurant")
         * ================================================================ */
        req.setAttribute("allRestaurant", allRestaurant);


        /* ================================================================
         * 4️⃣ FORWARD THE REQUEST TO index.jsp
         * ---------------------------------------------------------------
         * index.jsp is your home page which displays categories,
         * hero section, and list of restaurants dynamically.
         * ================================================================ */
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
