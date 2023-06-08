package com.cglia.flights.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
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
import com.cglia.flights.dao.UserDao;
import com.cglia.flights.dto.UserData;

/**
 * implementation for user login.
 * 
 * This servlet handles user login functionality. It receives user login data
 * through an HTTP POST request and validates the provided credentials. If the
 * credentials are valid, it saves the user's details to the database and
 * redirects the user to the FlightSearch.jsp page. If the credentials are
 * invalid, it redirects the user to the login.html page.
 * 
 * This class extends the HttpServlet class and overrides the doPost() method to
 * handle the login request.
 * 
 * @author bhuvan.bhalme
 * @version 1.0
 * @see 5/19/2023
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Login.class.getName());
	long aadhaar = 0;
	// Configure the logger to use a FileHandler
	static {
		// Configuration for the logger
		// Sets the log file path and formats the log messages

		try {
			String customizableParam = "logFilePath";
			String logFilePath = "C:/Flight_Booking_loggers/login.log"; // Default log file path

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
	 * Handles the HTTP POST method for user login.
	 *
	 * @param request  the HttpServletRequest object that contains the user login
	 *                 data
	 * @param response the HttpServletResponse object used to redirect the user
	 *                 after login
	 * @throws ServletException if any servlet-related error occurs
	 * @throws IOException      if any I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			aadhaar = Long.parseLong(request.getParameter("aadhaar"));
			// Code to handle the parsed number
		} catch (NumberFormatException e) {
			// Code to handle the NumberFormatException
			try {
				response.getWriter().println("Invalid number format: " + e.getMessage());
			} catch (IOException ex) {
				// Code to handle the IOException
				ex.printStackTrace(); // Or handle the exception in an appropriate way
			}
			// Additional error handling or error message display can be performed here
		}
		try {
			UserData userdata = getUserDataFromRequest(request);
			if (userdata != null) {
				// Save user details to the database
				UserDao userdao = new UserDao();
				ResultSet rs = userdao.getUserDetails(userdata);

				if (rs.next()) {
					session.setAttribute("aadhaar", aadhaar);
					// Redirect to the check_FlightSearch.jsp page
					logger.log(Level.INFO, "User profile Login successfully for Aadhaar: {0}", aadhaar);

					response.sendRedirect("FlightSearch.jsp");
				} else {
					// Redirect to the check_login.html page
					logger.log(Level.INFO, "Invalid Input");
					response.sendRedirect("login.html");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
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
	 * Extracts user login data from the HttpServletRequest object.
	 * 
	 * @param request the HttpServletRequest object
	 * @return UserData object containing user login data
	 */
	private UserData getUserDataFromRequest(HttpServletRequest request) {
		String aadhaarStr = request.getParameter("aadhaar");
		String password = request.getParameter("password");
		if (aadhaarStr != null && !aadhaarStr.isEmpty() && password != null && !password.isEmpty()) {
			try {
				long aadhaar = Long.parseLong(aadhaarStr);
				UserData data = new UserData();
				data.setAadhaar(aadhaar);
				data.setPassword(password);
				return data;
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, "Invalid number format", e);
			}

		}
		return null;
	}
}
