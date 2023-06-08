package com.cglia.flights.dto;

import java.util.logging.Logger;

/**
 * Represents user data for flight bookings.
 */
public class UserData {
	private static final Logger logger = Logger.getLogger(UserData.class.getName());

	private long aadhaar;
	private String name;
	private String email;
	private long number;
	private String gender;
	private String place;
	private String password;

	/**
	 * Default constructor for UserData.
	 */
	public UserData() {
		super();
		logger.info("UserData instance created");
	}

	/**
	 * Constructor for UserData with parameters.
	 *
	 * @param aadhaar  the Aadhaar number of the user
	 * @param name     the name of the user
	 * @param email    the email of the user
	 * @param number   the phone number of the user
	 * @param gender   the gender of the user
	 * @param place    the place of the user
	 * @param password the password of the user
	 */
	public UserData(long aadhaar, String name, String email, long number, String gender, String place,
			String password) {
		super();
		this.aadhaar = aadhaar;
		this.name = name;
		this.email = email;
		this.number = number;
		this.gender = gender;
		this.place = place;
		this.password = password;
		logger.info("UserData instance created with parameters");
	}

	/**
	 * Gets the Aadhaar number of the user.
	 *
	 * @return the Aadhaar number
	 */
	public long getAadhaar() {
		return aadhaar;
	}

	/**
	 * Sets the Aadhaar number of the user.
	 *
	 * @param aadhaar the Aadhaar number to set
	 */
	public void setAadhaar(long aadhaar) {
		this.aadhaar = aadhaar;
	}

	/**
	 * Gets the name of the user.
	 *
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the user.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email of the user.
	 *
	 * @return the email of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone number of the user.
	 *
	 * @return the phone number of the user
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * Sets the phone number of the user.
	 *
	 * @param number the phone number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/**
	 * Gets the gender of the user.
	 *
	 * @return the gender of the user
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the user.
	 *
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the place of the user.
	 *
	 * @return the place of the user
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Sets the place of the user.
	 *
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * Gets the password of the user.
	 *
	 * @return the password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
