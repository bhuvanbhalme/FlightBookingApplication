package com.cglia.flights.cancel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cglia.flights.dao.DatabaseConnection;
import com.cglia.flights.dao.FlightTicketDetailsDao;
import com.cglia.flights.dto.TicketDetails;

/**
 * This class handles the cancellation of flight tickets. It retrieves the
 * ticket details from the request parameters, deletes the corresponding entry
 * from the database, and redirects the user to the check_ticket.jsp page.
 * 
 * The cancellation process involves deleting the ticket entry based on Aadhaar
 * number, origin, destination, date, time, passenger name, age, and gender.
 * 
 * Servlet implementation class CancelTicketDetails
 * 
 * @author bhuvan.bhalme
 * @version 1.0
 * @since 5/19/2023
 */
@WebServlet("/cancel")
public class CancelTicketDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CancelTicketDetails.class.getName());

	// Configure the logger to use a FileHandler
	static {
		try {
			String customizableParam = "logFilePath";
			String logFilePath = "C:/Flight_Booking_loggers/cancel.log"; // Default log file path

			// Check if the customizable parameter exists as a system property
			String customLogFilePath = System.getProperty(customizableParam);
			if (customLogFilePath != null && !customLogFilePath.isEmpty()) {
				logFilePath = customLogFilePath;
			}

			Path logDirectoryPath = Paths.get("C:/Flight_Booking_loggers");

			// Create the log directory if it doesn't exist
			if (!Files.exists(logDirectoryPath)) {
				Files.createDirectories(logDirectoryPath);
			}

			FileHandler fileHandler = new FileHandler(logFilePath, true);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error configuring logger", e);
		}
	}

	/**
	 * Handles the HTTP POST method for canceling flight tickets.
	 * 
	 * @param request  the HttpServletRequest object that contains the ticket
	 *                 cancellation details
	 * @param response the HttpServletResponse object used to redirect the user
	 *                 after cancellation
	 * @throws ServletException if any servlet-related error occurs
	 * @throws IOException      if any I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			TicketDetails ticketdata = getUpdateUserData(request);
			if (ticketdata != null) {
				FlightTicketDetailsDao ticketdao = new FlightTicketDetailsDao();
				int result = ticketdao.cancelFlightTicket(ticketdata);

				if (result > 0) {
					// Update successful
					session.setAttribute("aadhaar", ticketdata.getAadhaar());
					logger.log(Level.INFO, "Flight ticket cancellation successful for Aadhaar: {0}",
							ticketdata.getAadhaar());

					// Redirect to the check_ticket.jsp page
					response.sendRedirect("check_ticket.jsp");
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error occurred while canceling flight ticket", e);
			// Handle the exception as per your requirement
			// Handle the exception as per your requirement

			// Send an error response or redirect to an error page
			try {
				response.sendRedirect("error.jsp");
			} catch (IOException ex) {
				// Code to handle the IOException
				ex.printStackTrace(); // Or handle the exception in an appropriate way
			}

		} finally {
			try {
				// Close the database connection
				DatabaseConnection.closeConnection();
			} catch (Exception ex) {
				// Error closing database connection
				logger.log(Level.SEVERE, "Error closing database connection", ex);
				response.sendRedirect("error.jsp");
			}
		}
	}

	/**
	 * Retrieves the ticket cancellation details from the request parameters and
	 * creates a TicketDetails object.
	 * 
	 * @param request the HttpServletRequest object containing the request
	 *                parameters
	 * @return the TicketDetails object created from the request parameters, or null
	 *         if any required parameter is missing or invalid
	 */
	private TicketDetails getUpdateUserData(HttpServletRequest request) {

		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String origin = request.getParameter("origin");
		String gender = request.getParameter("gender");
		long aadhaar = 0;
		try {
			aadhaar = Long.parseLong(request.getParameter("aadhaar"));
			// Code to handle the parsed number
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// Additional error handling or error message display can be performed here
		}
		String destination = request.getParameter("destination");
		String date = request.getParameter("date");
		String time = request.getParameter("time");

		if (name != null && !name.isEmpty() && age != null && !age.isEmpty() && origin != null && !origin.isEmpty()
				&& gender != null && !gender.isEmpty() && destination != null && !destination.isEmpty() && date != null
				&& !date.isEmpty() && time != null && !time.isEmpty()) {
			try {
				TicketDetails ticketdata = new TicketDetails();
				ticketdata.setName(name);
				ticketdata.setAge(age);
				ticketdata.setOrigin(origin);
				ticketdata.setGender(gender);
				ticketdata.setAadhaar(aadhaar);
				ticketdata.setDestination(destination);
				ticketdata.setDate(date);
				ticketdata.setTime(time);
				return ticketdata;
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, "Invalid number format", e);
			}
		}
		return null;
	}

}
