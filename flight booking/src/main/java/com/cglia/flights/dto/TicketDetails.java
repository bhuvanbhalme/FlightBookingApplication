package com.cglia.flights.dto;

/**
 * Represents the ticket details for a flight booking.
 */
public class TicketDetails {
	private long aadhaar;
	private String origin;
	private String destination;
	private String date;
	private String time;
	private String name;
	private String age;
	private String gender;

	/**
	 * Default constructor for TicketDetails.
	 */
	public TicketDetails() {
		super();
	}

	/**
	 * Constructor for TicketDetails with parameters.
	 *
	 * @param aadhaar     the Aadhaar number of the ticket holder
	 * @param origin      the origin of the flight
	 * @param destination the destination of the flight
	 * @param date        the date of the flight
	 * @param time        the time of the flight
	 * @param name        the name of the ticket holder
	 * @param age         the age of the ticket holder
	 * @param gender      the gender of the ticket holder
	 */
	public TicketDetails(long aadhaar, String origin, String destination, String date, String time, String name,
			String age, String gender) {
		super();
		this.aadhaar = aadhaar;
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	/**
	 * Gets the Aadhaar number of the ticket holder.
	 *
	 * @return the Aadhaar number of the ticket holder
	 */
	public long getAadhaar() {
		return aadhaar;
	}

	/**
	 * Sets the Aadhaar number of the ticket holder.
	 *
	 * @param aadhaar the Aadhaar number to set
	 */
	public void setAadhaar(long aadhaar) {
		this.aadhaar = aadhaar;
	}

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
	 * Gets the name of the ticket holder.
	 *
	 * @return the name of the ticket holder
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the ticket holder.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the age of the ticket holder.
	 *
	 * @return the age of the ticket holder
	 */
	public String getAge() {
		return age;
	}

	/**
	 * Sets the age of the ticket holder.
	 *
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * Gets the gender of the ticket holder.
	 *
	 * @return the gender of the ticket holder
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the ticket holder.
	 *
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

}
