<%@ page import="com.cglia.flights.dao.FlightTicketDetailsDao"%>
<%@ page import="com.cglia.flights.dao.DatabaseConnection"%>
<%@ page import="com.cglia.flights.dto.TicketDetails"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Passenger Details</title>
<style>
body {
	font-family: Arial, sans-serif;
}

table {
	font-size: 20px;
	margin-left: 25px;
	border-collapse: collapse;
	width: 100%;
}

th, td {
	padding: 8px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
}

form {
	display: inline;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 8px 16px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
}

button:hover {
	background-color: #45a049;
}

.cancel-btn {
	background-color: #f44336;
}

.cancel-btn:hover {
	background-color: #d32f2f;
}
</style>
</head>
<body>
	<table class="pdata">
		<caption></caption>
		<tr>
			<th>#</th>
			<th>Aadhaar</th>
			<th>Origin</th>
			<th>Destination</th>
			<th>YY-MM-DD</th>
			<th>Time</th>
			<th>Name</th>
			<th>Age</th>
			<th>Gender</th>
			<th><a href="FlightSearch.jsp"><button class="cancel-form">Back</button></a></th>

		</tr>
		<%
		long aadhaar = (long) session.getAttribute("aadhaar");
		FlightTicketDetailsDao ticket = new FlightTicketDetailsDao();
		TicketDetails ticketData = new TicketDetails();
		ticketData.setAadhaar(aadhaar);
		ResultSet rs = ticket.getFlightTicket(ticketData);
		session.setAttribute("aadhaar", aadhaar);
		int n = 0;
		while (rs.next()) {
			n++;
			aadhaar = rs.getLong(1);
			String origin = rs.getString(2);
			String destination = rs.getString(3);
			String date = rs.getString(4);
			String time = rs.getString(5);
			String name = rs.getString(6);
			String age = rs.getString(7);
			String gender = rs.getString(8);
		%>
		<tr>
			<td><%=n%></td>
			<td><%=aadhaar%></td>
			<td><%=origin%></td>
			<td><%=destination%></td>
			<td><%=date%></td>
			<td><%=time%></td>
			<td><%=name%></td>
			<td><%=age%></td>
			<td><%=gender%></td>
			<td>
				<form class="cancel-form" action="cancel" method="post">
					<input type="hidden" name="aadhaar" value="<%=aadhaar%>"> <input
						type="hidden" name="origin" value="<%=origin%>"> <input
						type="hidden" name="destination" value="<%=destination%>">
					<input type="hidden" name="date" value="<%=date%>"> <input
						type="hidden" name="time" value="<%=time%>"> <input
						type="hidden" name="name" value="<%=name%>"> <input
						type="hidden" name="age" value="<%=age%>"> <input
						type="hidden" name="gender" value="<%=gender%>">
					<button type="submit" class="cancel-btn">CANCEL</button>
				</form>
			</td>

		</tr>
		<%
		}
		DatabaseConnection.closeConnection();
		%>
	</table>
</body>
</html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page errorPage="error.jsp"%>