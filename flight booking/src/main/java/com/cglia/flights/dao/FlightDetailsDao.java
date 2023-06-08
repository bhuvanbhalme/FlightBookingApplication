package com.cglia.flights.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class that handles the database connection and retrieval of flight details.
 */
public class FlightDetailsDao {
	private Connection connection;
	private PreparedStatement statement;
	ResultSet rs = null;

	/**
	 * Retrieves flight details from the database based on origin, destination, and
	 * date.
	 *
	 * @param origin      the origin of the flight
	 * @param destination the destination of the flight
	 * @param date        the date of the flight
	 * @return a ResultSet containing the flight details
	 */
	public ResultSet getFlightDetails(String origin, String destination, String date) {
		try {

			connection = DatabaseConnection.getConnectionObject();
			String sql = "SELECT * FROM bhuvan.flight_details WHERE origin=? AND destination=? AND date=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, origin);
			statement.setString(2, destination);
			statement.setString(3, date);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			// Handle the SQLException in an appropriate way
			e.printStackTrace();
		}
		return rs;
	}
	
}
