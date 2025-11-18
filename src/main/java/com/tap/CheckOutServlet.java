package com.tap;

import java.io.IOException;
import java.sql.Timestamp;

import com.tap.dao.OrdersDao;
import com.tap.daoimpl.CartDaoImpl;
import com.tap.daoimpl.OrderItemDaoImpl;
import com.tap.daoimpl.OrdersDaoImpl;
import com.tap.model.Cart;
import com.tap.model.OrderItem;
import com.tap.model.Orders;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {
	
	private OrdersDao ordersDao;
	private OrderItemDaoImpl orderItemDaoImpl;

	/**
	 * =====================================================================
	 *  INIT METHOD — Runs automatically when servlet loads
	 * ---------------------------------------------------------------------
	 *  PURPOSE:
	 *     • Create DAO objects only once instead of every request.
	 *     • Improves performance.
	 *
	 *  WHAT IS HAPPENING:
	 *     - ordersDao → communicates with Orders table
	 *     - orderItemDaoImpl → communicates with OrderItems table
	 * =====================================================================
	 */
	@Override
	public void init() {
		try {
			ordersDao = new OrdersDaoImpl();
			orderItemDaoImpl = new OrderItemDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize DAOs", e);
		}
	}

	/**
	 * =====================================================================
	 *  MAIN LOGIC → Handles checkout process for GET & POST
	 * ---------------------------------------------------------------------
	 *  STEPS:
	 *  1️⃣ Validate cart + login
	 *  2️⃣ If GET request → show checkout page
	 *  3️⃣ Extract form details (payment, address)
	 *  4️⃣ Create ORDER entry in database
	 *  5️⃣ Create ORDER_ITEMS entries for each cart item
	 *  6️⃣ Clear cart & redirect to confirmation page
	 * =====================================================================
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
	        throws ServletException, IOException {

		HttpSession session = req.getSession();

		// Read cart + user session
		CartDaoImpl cartDao = (CartDaoImpl) session.getAttribute("cartDao");
		User user = (User) session.getAttribute("callingloginservlet");

		/* ================================================================
		 * 1️⃣ CART VALIDATION — User must have items to checkout
		 * ================================================================ */
		if (cartDao == null || cartDao.getMap().isEmpty()) {
			System.out.println("⚠️ Cart empty — redirecting to cart.jsp");
			resp.sendRedirect(req.getContextPath() + "/cart.jsp");
			return;
		}

		/* ================================================================
		 * 2️⃣ GET REQUEST — Just show checkout.jsp page
		 * ================================================================ */
		if ("GET".equalsIgnoreCase(req.getMethod()) &&
		    req.getParameter("paymentMethod") == null) {

			req.getRequestDispatcher("checkout.jsp").forward(req, resp);
			return;
		}


		/* ================================================================
		 * 3️⃣ CHECK LOGIN + CART AGAIN — Safety check
		 * ================================================================ */
		if (cartDao == null || user == null || cartDao.getMap().isEmpty()) {
			System.out.println("⚠️ Missing user / cart — redirecting.");
			resp.sendRedirect(req.getContextPath() + "/cart.jsp");
			return;
		}

		/* ================================================================
		 * 4️⃣ READ PAYMENT & ADDRESS FROM FORM
		 * ================================================================ */
		String paymentMethod = req.getParameter("paymentMethod");
		String address = req.getParameter("address");

		/* ================================================================
		 * 5️⃣ PREPARE NEW ORDER OBJECT
		 * ================================================================ */
		Orders orders = new Orders();
		orders.setUserid(user.getUserId());

		// restaurantId comes from session
		Integer restaurantId = (Integer) session.getAttribute("restaurantId");
		if (restaurantId != null) {
			orders.setRestaurantid(restaurantId);
		} else {
			System.out.println("⚠️ RestaurantId missing in session!");
		}

		orders.setOrderdate(new Timestamp(System.currentTimeMillis()));
		orders.setPaymentmode(paymentMethod);
		orders.setStatus("Order Confirmed...");

		/* ================================================================
		 * 6️⃣ CALCULATE TOTAL ORDER AMOUNT
		 * ================================================================ */
		double totalAmount = 0;
		for (Cart item : cartDao.getMap().values()) {
			totalAmount += item.getPrice() * item.getQuantity();
		}
		orders.setTotalamount(totalAmount);

		/* ================================================================
		 * 7️⃣ INSERT ORDER INTO DATABASE
		 * ================================================================ */
		int orderid = ordersDao.addOrders(orders);

		if (orderid > 0) {

			orders.setOrderid(orderid); // store orderID

			/* ============================================================
			 * 8️⃣ INSERT EACH CART ITEM INTO ORDER_ITEMS TABLE
			 * ============================================================ */
			for (Cart item : cartDao.getMap().values()) {

				int menuid = item.getItemid();
				int quantity = item.getQuantity();
				double totalprice = item.getPrice() * quantity;

				OrderItem orderItem = new OrderItem(orderid, menuid, quantity, totalprice);

				orderItemDaoImpl.addOrderItem(orderItem);
			}

			/* ============================================================
			 * 9️⃣ CHECKOUT SUCCESS — CLEAR CART
			 * ============================================================ */
			session.removeAttribute("cartDao");
			session.setAttribute("orders", orders);

			// Redirect to confirmation page
			resp.sendRedirect(req.getContextPath() + "/orderconfirm.jsp");

		} else {

			// If order insert failed
			resp.sendRedirect(req.getContextPath() + "/cart.jsp");
		}
	}
}
