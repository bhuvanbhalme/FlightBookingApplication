package com.cglia.flights.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cglia.flights.dao.DatabaseConnection;
import com.cglia.flights.dao.UserDao;
import com.cglia.flights.dto.UserData;

/**
 * Servlet implementation class Registration Handles the user registration
 * process by saving the user data to the database.
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Registration.class.getName());

	/**
	 * Static initializer block to configure the logger. It creates a file handler
	 * for logging registration activities.
	 */
	static {
		try {
			String customizableParam = "logFilePath";
			String logFilePath = "C:/Flight_Booking_loggers/registration.log";

			// Check if a custom log file path is provided as a system property
			String customLogFilePath = System.getProperty(customizableParam);
			if (customLogFilePath != null && !customLogFilePath.isEmpty()) {
				logFilePath = customLogFilePath;
			}

			// Create the log directory if it doesn't exist
			Path logDirectoryPath = Paths.get("C:/Flight_Booking_loggers");
			if (!Files.exists(logDirectoryPath)) {
				Files.createDirectories(logDirectoryPath);
			}

			// Create a file handler and set its formatter
			FileHandler fileHandler = new FileHandler(logFilePath, true);
			fileHandler.setFormatter(new SimpleFormatter());

			// Add the file handler to the logger
			logger.addHandler(fileHandler);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error configuring logger", e);
		}
	}

	/**
	 * Handles the HTTP POST request for user registration. Retrieves user data from
	 * the request, saves it to the database, and redirects the user to the
	 * appropriate page.
	 *
	 * @param request  the HttpServletRequest object containing the request
	 *                 parameters
	 * @param response the HttpServletResponse object for sending the response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Retrieve user data from the request
			UserData userdata = getUserDataFromRequest(request);

			if (userdata != null) {
				// Save user details to the database
				UserDao userdao = new UserDao();
				int i = userdao.saveUserDetails(userdata);

				if (i > 0) {
					// User registration successful
					logger.log(Level.INFO, "User registered successfully");
					response.sendRedirect("login.html");
				} else {
					// Failed to save user details
					logger.log(Level.WARNING, "Failed to save user details");
					response.sendRedirect("error.jsp");
				}
			} else {
				// Invalid user data
				logger.log(Level.WARNING, "Invalid user data");
				response.sendRedirect("error.jsp");
			}
		} catch (Exception ex) {
			// Error during user registration
			logger.log(Level.SEVERE, "Error during user registration", ex);
			response.sendRedirect("error.jsp");
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
	 * Extracts user data from the HttpServletRequest object.
	 *
	 * @param request the HttpServletRequest object containing the request
	 *                parameters
	 * @return the UserData object containing the extracted user data, or null if
	 *         the user data is invalid or incomplete
	 */
	private UserData getUserDataFromRequest(HttpServletRequest request) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String numberStr = request.getParameter("number");
		String aadhaarStr = request.getParameter("aadhaar");
		String gender = request.getParameter("gender");
		String place = request.getParameter("place");
		String password = request.getParameter("password");

		if (name != null && !name.isEmpty() && email != null && !email.isEmpty() && numberStr != null
				&& !numberStr.isEmpty() && aadhaarStr != null && !aadhaarStr.isEmpty() && gender != null
				&& !gender.isEmpty() && place != null && !place.isEmpty() && password != null && !password.isEmpty()) {
			try {
				long number = Long.parseLong(numberStr);
				long aadhaar = Long.parseLong(aadhaarStr);
				return new UserData(aadhaar, name, email, number, gender, place, password);
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, "Invalid number format", e);
			}
		}

		return null;
	}
}
