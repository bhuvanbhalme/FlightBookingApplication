package com.cglia.flights.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class representing a database connection.
 */
public class DatabaseConnection {
	private static Connection connection;

	/**
	 * Retrieves the singleton instance of the database connection.
	 *
	 * @return the database connection instance
	 * @throws SQLException if a database access error occurs
	 */
	public static Connection getConnectionObject() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String dbUrl = "jdbc:mysql://192.168.60.30:3306/bhuvan";
				String dbUsername = "bhuvan";
				String dbPassword = "bhuvan@12345";
				connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			} catch (ClassNotFoundException e) {
				// Handle the ClassNotFoundException in an appropriate way
				e.printStackTrace();
			}
		}
		return connection;
	}

	/**
	 * Closes the database connection.
	 */
	public static void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("connection is closed");
			}
		} catch (SQLException e) {
			// Handle the SQLException in an appropriate way
			e.printStackTrace();
		}
	}
}
