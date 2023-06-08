<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.cglia.flights.dao.FlightTicketDetailsDao"%>
<%@ page import="com.cglia.flights.dao.DatabaseConnection"%>
<%@ page import="com.cglia.flights.dto.TicketDetails"%>
<%
// Retrieve form data
String[] names = request.getParameterValues("name");
String[] ages = request.getParameterValues("age");
String[] genders = request.getParameterValues("gender");

String[] origins = request.getParameterValues("origin");
String[] destinations = request.getParameterValues("destination");
String[] times = request.getParameterValues("time");
String[] dates = request.getParameterValues("date");
String[] aadhaarArray = request.getParameterValues("aadhaar");
long[] aadhaar = new long[aadhaarArray.length];

for (int i = 0; i < aadhaarArray.length; i++) {
	aadhaar[i] = Long.parseLong(aadhaarArray[i]);
}
TicketDetails ticketData = new TicketDetails();
FlightTicketDetailsDao flightTicketdetails = new FlightTicketDetailsDao();

for (int i = 0; i < names.length; i++) {
	ticketData.setAadhaar(aadhaar[i]);
	ticketData.setOrigin(origins[i]);
	ticketData.setDestination(destinations[i]);
	ticketData.setDate(dates[i]);
	ticketData.setTime(times[i]);
	ticketData.setName(names[i]);
	ticketData.setAge(ages[i]);
	ticketData.setGender(genders[i]);
	flightTicketdetails.insertFlightTicket(ticketData);
}

response.sendRedirect("success.jsp");
DatabaseConnection.closeConnection();
%>
