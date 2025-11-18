package com.tap;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * =====================================================================
 *  ChatServlet
 *  -------------------------------------------------------------------
 *  PURPOSE:
 *      This servlet receives chatbot messages sent from the frontend
 *      (JavaScript fetch/POST request).
 *
 *  HOW IT WORKS:
 *      - Frontend sends "message" to this servlet using POST.
 *      - Servlet reads the user message.
 *      - (Future scope) You can generate bot reply and send JSON back.
 *
 *  NOTE:
 *      Currently this servlet ONLY receives the message.
 *      No output is sent back yet.
 * =====================================================================
 */

@WebServlet("/chatbot")
public class ChatServlet extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        /* ---------------------------------------------------------------
         * 1️⃣  GET USER MESSAGE FROM REQUEST
         * ---------------------------------------------------------------
         * JavaScript will send a POST request like:
         * fetch("/chatbot", { method:"POST", body: message })
         *
         * We extract that message here.
         */
        String userMessage = req.getParameter("message");

        // Debug log (optional): Helps verify communication
        System.out.println("📩 Chatbot received: " + userMessage);

        // (Currently no response is sent back to JS)
    }
}
