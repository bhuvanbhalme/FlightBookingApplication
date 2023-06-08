package com.cglia.flights.dto;

/**
 * Represents the flight details for a flight booking.
 */
public class FlightDetails {
	private String origin;
	private String destination;
	private String date;
	private String time;
	private long money;

	/**
	 * Gets the origin of the flight.
	 *
	 * @return the origin of the flight
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Sets the origin of the flight.
	 *
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Gets the destination of the flight.
	 *
	 * @return the destination of the flight
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Sets the destination of the flight.
	 *
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Gets the date of the flight.
	 *
	 * @return the date of the flight
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date of the flight.
	 *
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the time of the flight.
	 *
	 * @return the time of the flight
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time of the flight.
	 *
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Gets the cost of the flight ticket.
	 *
	 * @return the cost of the flight ticket
	 */
	public long getMoney() {
		return money;
	}

	/**
	 * Sets the cost of the flight ticket.
	 *
	 * @param money the cost to set ticket
	 */
	public void setMoney(long money) {
		this.money = money;
	}

	/**
	 * Constructor for FlightDetails with parameters.
	 *
	 * @param origin      the origin of the flight
	 * @param destination the destination of the flight
	 * @param date        the date of the flight
	 * @param time        the time of the flight
	 * @param money       the cost of the flight ticket
	 */
	public FlightDetails(String origin, String destination, String date, String time, long money) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.money = money;
	}

	/**
	 * Default constructor for FlightDetails.
	 */
	public FlightDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
}
