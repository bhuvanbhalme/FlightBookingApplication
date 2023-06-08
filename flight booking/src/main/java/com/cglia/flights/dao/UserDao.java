/**
 * The UserDao class is responsible for saving user details into a database.
 */
package com.cglia.flights.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cglia.flights.dto.UserData;

public class UserDao {
	private Connection connection;

	/**
	 * Constructs a UserDao object and initializes the database connection.
	 *
	 * @throws SQLException if a database access error occurs
	 */
	public UserDao() throws SQLException {
		connection = DatabaseConnection.getConnectionObject();
	}

	/**
	 * Saves user details into the database.
	 * 
	 * @param userData The UserData object containing the user details to be saved.
	 * @return The number of rows affected by the database operation.
	 */
	public int saveUserDetails(UserData userData) {
		int i = 0;
		try (PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO bhuvan.user_data (aadhaar, name, email, number, gender, place, password) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
			statement.setLong(1, userData.getAadhaar());
			statement.setString(2, userData.getName());
			statement.setString(3, userData.getEmail());
			statement.setLong(4, userData.getNumber());
			statement.setString(5, userData.getGender());
			statement.setString(6, userData.getPlace());
			statement.setString(7, userData.getPassword());

			// Execute the SQL statement
			i = statement.executeUpdate();
		} catch (SQLException e) {
			// Handle the SQLException in an appropriate way
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * Retrieves user details from the database based on the Aadhaar and password.
	 * 
	 * @param aadhaar  The Aadhaar number of the user.
	 * @param password The password of the user.
	 * @return A ResultSet containing the user details if found, or null if an error
	 *         occurs.
	 */
	public ResultSet getUserDetails(UserData userData) {
		try {
			String sql = "SELECT * FROM bhuvan.user_data WHERE aadhaar=? AND password=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, userData.getAadhaar());
			statement.setString(2, userData.getPassword());

			return statement.executeQuery();
		} catch (SQLException e) {
			// Handle the SQLException in an appropriate way
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Updates user details in the database based on the Aadhaar number.
	 *
	 * @param userData The UserData object containing the user details to be
	 *                 updated.
	 * @return The number of rows affected by the database operation.
	 */
	public int updateUserDetails(UserData userData) {
		int i = 0;

		try (PreparedStatement statement = connection.prepareStatement(
				"UPDATE bhuvan.user_data SET name=?, email=?, number=?, gender=?, place=? WHERE aadhaar=?")) {
			statement.setString(1, userData.getName());
			statement.setString(2, userData.getEmail());
			statement.setLong(3, userData.getNumber());
			statement.setString(4, userData.getGender());
			statement.setString(5, userData.getPlace());
			statement.setLong(6, userData.getAadhaar());
			i = statement.executeUpdate();

		} catch (SQLException e) {
			// Handle the SQLException in an appropriate way
			e.printStackTrace();
		}
		return i;

	}
}
