package com.tap;

import java.io.IOException;

import com.tap.daoimpl.OrdersDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * DeleteOrdersServlet
 *
 * This servlet handles the deletion of a user's order from the system.
 * It is triggered when the user confirms the delete action on the
 * "My Orders" page. The request is sent via AJAX or a POST form submission.
 *
 * Workflow:
 * 1. Receive the order ID sent from the JSP page.
 * 2. Call OrdersDaoImpl.deleteOrders() to remove the order from the database.
 * 3. Redirect back to myorders.jsp with an appropriate message parameter:
 *      - msg=deleted (if deletion is successful)
 *      - msg=failed  (if deletion failed)
 *
 * URL Mapping: /DeleteOrdersServlet
 */
@WebServlet("/DeleteOrdersServlet")
public class DeleteOrdersServlet extends HttpServlet {
	
    /**
     * Handles POST requests for deleting an order.
     * This method is executed when a user clicks the delete button
     * and confirms the action.
     *
     * @param req  The HttpServletRequest object containing client data
     * @param resp The HttpServletResponse object used to send response data
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		
        // Retrieve order ID from request parameters (sent from JSP)
		int orderId = Integer.parseInt(req.getParameter("orderid"));

        // Order Data Access Object
		OrdersDaoImpl impl = new OrdersDaoImpl();
		
        // Attempt to delete the order from the database
		boolean deleted = impl.deleteOrders(orderId);
		
        // Redirect user back to myorders.jsp with a status message
		if (deleted) {
			resp.sendRedirect("myorders.jsp?msg=deleted");
		} else {
			resp.sendRedirect("myorders.jsp?msg=failed");
		}
	}
}
