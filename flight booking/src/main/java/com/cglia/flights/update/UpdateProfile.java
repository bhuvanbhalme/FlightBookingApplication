package com.cglia.flights.update;

/**
 * The UpdateProfile servlet handles the updating of user profiles.
 */
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
import com.cglia.flights.dao.UserDao;
import com.cglia.flights.dto.UserData;

@WebServlet("/update_profile")
public class UpdateProfile extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UpdateProfile.class.getName());

	// Configure the logger to use a FileHandler
	static {
		try {
			String customizableParam = "logFilePath";
			String logFilePath = "C:/Flight_Booking_loggers/update.log"; // Default log file path

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
	 * Handles the HTTP POST request for updating the user profile.
	 *
	 * @param request  The HttpServletRequest object containing the request
	 *                 parameters.
	 * @param response The HttpServletResponse object used to send the response.
	 * @throws ServletException if the request could not be handled
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			UserData userdata = getUpdateUserData(request);
			if (userdata != null) {
				UserDao userdao = new UserDao();
				int result = userdao.updateUserDetails(userdata);

				if (result > 0) {
					// Update successful
					session.setAttribute("aadhaar", userdata.getAadhaar());
					logger.log(Level.INFO, "User profile updated successfully for Aadhaar: " + userdata.getAadhaar());
					response.sendRedirect("profile.jsp");
				} else {
					// Update failed
					logger.log(Level.WARNING, "User profile update failed for Aadhaar: " + userdata.getAadhaar());
					response.getWriter().print("<h1 style='color:red;'>Sorry, there was a server problem</h1>");
				}
			} else {
				// Update failed
				logger.log(Level.WARNING, "User data is null");
				response.getWriter().print("<h1 style='color:red;'>Invalid user data</h1>");
			}
		} catch (SQLException ex) {
			// Handle exceptions by redirecting to an error page
			logger.log(Level.SEVERE, "An error occurred while updating the user profile", ex);
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
	 * Retrieves the user data from the HttpServletRequest object.
	 *
	 * @param request The HttpServletRequest object containing the request
	 *                parameters.
	 * @return The UserData object containing the user details, or null if the data
	 *         is invalid.
	 */
	private UserData getUpdateUserData(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String numberStr = request.getParameter("number");
		String gender = request.getParameter("gender");
		String place = request.getParameter("place");

		if (name != null && !name.isEmpty() && email != null && !email.isEmpty() && numberStr != null
				&& !numberStr.isEmpty() && gender != null && !gender.isEmpty() && place != null && !place.isEmpty()) {
			try {
				long aadhaar = (Long) session.getAttribute("aadhaar");
				long number = Long.parseLong(numberStr);

				UserData data = new UserData();
				data.setAadhaar(aadhaar);
				data.setName(name);
				data.setEmail(email);
				data.setNumber(number);
				data.setGender(gender);
				data.setPlace(place);

				return data;
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, "Invalid number format", e);
			}
		}
		return null;
	}
}
