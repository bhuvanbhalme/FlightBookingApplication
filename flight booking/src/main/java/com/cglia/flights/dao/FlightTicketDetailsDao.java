package com.cglia.flights.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cglia.flights.dto.TicketDetails;

/**
 * DAO class for flight ticket details.
 */
public class FlightTicketDetailsDao {
	private Connection connection;

	/**
	 * Constructs a FlightTicketDetailsDao object and initializes the database
	 * connection.
	 *
	 * @throws SQLException if a database access error occurs
	 */
	public FlightTicketDetailsDao() throws SQLException {
		connection = DatabaseConnection.getConnectionObject();
	}

	/**
	 * Cancels a flight ticket based on the provided ticket details.
	 *
	 * @param ticketData the ticket details to be canceled
	 * @return the number of rows affected by the cancellation operation
	 */
	public int cancelFlightTicket(TicketDetails ticketData) {
		int i = 0;

		try (PreparedStatement statement = connection.prepareStatement(
				"delete from bhuvan.flight_tickect_details where aadhaar=? and origin=? and destination=? and date=? and time=? and name=? and age=? and gender=?")) {
			statement.setLong(1, ticketData.getAadhaar());
			statement.setString(2, ticketData.getOrigin());
			statement.setString(3, ticketData.getDestination());
			statement.setString(4, ticketData.getDate());
			statement.setString(5, ticketData.getTime());
			statement.setString(6, ticketData.getName());
			statement.setString(7, ticketData.getAge());
			statement.setString(8, ticketData.getGender());
			i = statement.executeUpdate();
		} catch (SQLException e) {
			// Handle the SQLException in an appropriate way
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * Inserts a flight ticket into the database.
	 *
	 * @param ticketData the ticket details to be inserted
	 * @return the number of rows affected by the insertion operation
	 */
	public int insertFlightTicket(TicketDetails ticketData) {
		int i = 0;
		try (PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO  bhuvan.flight_tickect_details(aadhaar, origin, destination, date, time, name, age, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
			statement.setLong(1, ticketData.getAadhaar());
			statement.setString(2, ticketData.getOrigin());
			statement.setString(3, ticketData.getDestination());
			statement.setString(4, ticketData.getDate());
			statement.setString(5, ticketData.getTime());
			statement.setString(6, ticketData.getName());
			statement.setString(7, ticketData.getAge());
			statement.setString(8, ticketData.getGender());
			i = statement.executeUpdate();
		} catch (SQLException e) {
			// Handle the SQLException in an appropriate way
			e.printStackTrace();
		}
		return i;

	}

	/**
	 * Retrieves a flight ticket from the database based on the provided ticket
	 * details.
	 *
	 * @param ticketData the ticket details to be used for retrieving the ticket
	 * @return a ResultSet containing the flight ticket data, or null if no ticket
	 *         is found
	 */
	public ResultSet getFlightTicket(TicketDetails ticketData) {
		try {
			String sql = "select * from bhuvan.flight_tickect_details where aadhaar=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, ticketData.getAadhaar());
			ResultSet result = statement.executeQuery();
			return result;
		} catch (SQLException e) {
			// Handle the SQLException in an appropriate way
			e.printStackTrace();
		}
		return null;
	}
}
