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
import jakarta.servlet.http.HttpSession;

@WebServlet("/callingloginservlet")
public class LoginServlet extends HttpServlet {

	// =====================================================================
	// LOGIN ATTEMPTS COUNTER
	// ---------------------------------------------------------------------
	// Purpose:
	//   • Allow maximum 3 incorrect attempts.
	//   • After 3 failures → block user from logging in.
	// =====================================================================
	int count = 3;
	
	/**
	 * =====================================================================
	 * MAIN LOGIN PROCESSING METHOD
	 * ---------------------------------------------------------------------
	 * STEPS:
	 *  1️⃣ Read username & password from login form
	 *  2️⃣ Fetch user from DB using DAO
	 *  3️⃣ Validate username
	 *  4️⃣ Validate password
	 *  5️⃣ On success → create session & redirect
	 *  6️⃣ On failure → show error message on login page
	 * =====================================================================
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/* ================================================================
		 * 1️⃣ EXTRACT LOGIN FORM INPUTS
		 * ================================================================ */
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		/* ================================================================
		 * 2️⃣ CHECK IF USER EXISTS IN DATABASE
		 * ================================================================ */
		UserDaoImpl impl = new UserDaoImpl();
		User user = impl.getUser(username);

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		/* ================================================================
		 * 3️⃣ USERNAME VALIDATION
		 * ---------------------------------------------------------------
		 * If username does NOT exist in DB → return error message
		 * ================================================================ */
		if (user == null) {

			req.setAttribute("errorMessage",
					"❌ Username not found. Please check again.");

			RequestDispatcher rd = req.getRequestDispatcher("userloginpage.jsp");
			rd.include(req, resp);
			return;
		}

		/* ================================================================
		 * 4️⃣ PASSWORD VALIDATION
		 * ================================================================ */
		String storedPassword = user.getPassword();

		if (password.equals(storedPassword)) {

			/* ============================================================
			 * 5️⃣ LOGIN SUCCESS → CREATE SESSION
			 * -----------------------------------------------------------
			 * Store user details in session for future use:
			 *  - user (full object)
			 *  - username (string)
			 *  - callingloginservlet (backup)
			 * ============================================================ */
			HttpSession session = req.getSession();

			session.setAttribute("user", user);
			session.setAttribute("callingloginservlet", user);
			session.setAttribute("username", username);

			// Redirect user to restaurant home page
			resp.sendRedirect("restaurant");
			return;
		}

		/* ================================================================
		 * 6️⃣ PASSWORD INCORRECT — SHOW ERROR + REDUCE ATTEMPTS
		 * ================================================================ */
		else if (count > 0) {

			req.setAttribute("errorMessage",
					"⚠️ Incorrect password. You have " + count + " attempts left.");

			count--;

			RequestDispatcher rd = req.getRequestDispatcher("userloginpage.jsp");
			rd.include(req, resp);
			return;
		}

		/* ================================================================
		 * 7️⃣ MAX LOGIN ATTEMPTS EXCEEDED — BLOCK USER
		 * ================================================================ */
		else {

			req.setAttribute("errorMessage",
					"🚫 Too many failed attempts. Please contact admin.");

			out.println("Your attempts are exceeded, please contact your Admin.");

			RequestDispatcher rd = req.getRequestDispatcher("userloginpage.jsp");
			rd.forward(req, resp);
		}
	}
}
