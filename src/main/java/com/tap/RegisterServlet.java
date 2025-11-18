package com.tap;

import java.io.IOException;
import java.io.PrintWriter;

import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * =====================================================================
 * REGISTER SERVLET — CREATES A NEW USER IN THE APPLICATION
 * =====================================================================
 * FLOW:
 *   1️⃣ Collect form data from JSP (name, email, password, etc.)
 *   2️⃣ Create a User object
 *   3️⃣ Send data to DAO → save in DB
 *   4️⃣ If registration successful → redirect to Login page
 *   5️⃣ Otherwise → show error message
 * =====================================================================
 */

@WebServlet("/callingprogram")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* ================================================================
         * 1️⃣ READ USER INPUT FROM THE REGISTRATION FORM
         * ---------------------------------------------------------------
         * Fields coming from user_register.jsp
         * ================================================================ */
        String name     = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email    = req.getParameter("email");
        String phone    = req.getParameter("phone");
        String address  = req.getParameter("address");
        String role     = req.getParameter("role"); 
        // (Role may be null if not included in form, usually "User")

        
        /* ================================================================
         * 2️⃣ SET RESPONSE TYPE
         * ---------------------------------------------------------------
         * Not returning JSON, only HTML output or forwarding to JSP.
         * ================================================================ */
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        
        /* ================================================================
         * 3️⃣ CREATE USER OBJECT USING MODEL CLASS
         * ---------------------------------------------------------------
         * Constructor: 
         *   User(name, username, password, email, phone, address, role, createdAt, updatedAt)
         *
         * We pass null for timestamps because the DB will auto-generate.
         * ================================================================ */
        User user = new User(
                name,
                username,
                password,
                email,
                phone,
                address,
                role,
                null,   // createdAt
                null    // updatedAt
        );

        
        /* ================================================================
         * 4️⃣ SAVE USER INTO DATABASE USING DAO
         * ---------------------------------------------------------------
         * addUser(user) returns:
         *   → 1  if success
         *   → 0  if failure
         * ================================================================ */
        UserDaoImpl impl = new UserDaoImpl();
        int res = impl.addUser(user);

        
        /* ================================================================
         * 5️⃣ SUCCESS OR FAILURE HANDLING
         * ---------------------------------------------------------------
         * If registration successful → send user to login page
         * If failed → show message
         * ================================================================ */
        if (res == 1) {

            // registration success → forward to login page
            RequestDispatcher rd = req.getRequestDispatcher("userloginpage.jsp");
            rd.forward(req, resp);

        } else {

            // registration failed → show message
            out.println("<h3 style='color:red; text-align:center;'>❌ Something went wrong. Please try again later.</h3>");
        }
    }
}
