<%@ page import="com.cglia.flights.dao.FlightDetailsDao"%>
<%@ page import="com.cglia.flights.dao.DatabaseConnection"%>

<!doctype html>
<html lang="en" xml:lang="en">
<head>
<title>BB Flights</title>
<link rel="stylesheet" href="flight_search.css">
<script src="https://kit.fontawesome.com/80115097ef.js"
	crossorigin="anonymous"></script>
<style>
.h2 {
	color: blue;
	text-align: center;
}

* {
	box-sizing: border-box;
}

.column {
	float: left;
	width: 60%;
	padding: 10px;
	height: 450px;
	margin-left: 45%;
	margin-top: 10%;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	opacity: 50%;
}

.data {
	margin-left: 50%;
}

.pdata {
	width: 100%;
	margin-top: 25px;
	border-collapse: collapse;
}

.pdata th, .pdata td {
	padding: 10px;
	text-align: left;
}

.pdata th {
	background-color: #155d7a;
	color: aqua;
}

.pdata tr:hover {
	background-color: black;
}

.row:after {
	content: "";
	display: table;
	clear: both;
}

select {
	width: 200px;
	height: 50px;
	margin: 45px;
	text-align: center;
	background-color: #155d7a;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	border-color: black;
	color: aqua;
}

select:focus {
	min-width: 200px;
	width: auto;
}

.date {
	width: 600px;
	height: 35px;
	margin: 10px;
	text-align: center;
	background-color: #155d7a;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	border-color: #008080;
	color: aqua;
}

.search_button {
	width: 600px;
	height: 40px;
	margin: 70px;
	text-align: center;
	background-color: #4682B4;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	color: aqua;
}

.header {
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	background-color: #4682B4;
	width: 100px;
	height: 40px
}
</style>
</head>
<body>

	<div class="main">
		<input type="checkbox" id="check"> <label for="check">
			<i class="fas fa-bars" id="btn"></i> <i class="fas fa-times"
			id="cancel"></i>
		</label>
		<div class="side-nav">
			<a href="#" class="logo"> <img src="sidelogo.jpg"
				class="logo-img" alt="black_img.jpg">
			</a>
			<ul class="nav-links">
				<li><a href="logout.jsp"><i
						class="fa-solid fa-right-from-bracket"></i>
						<p>LOGOUT</p></a></li>
				<li><a href="profile.jsp"><i
						class="fa-solid fa-address-book"></i>
						<p>Profile</p></a></li>
				<li><a href="https://www.grabon.in/"><i
						class="fa-sharp fa-regular fa-scale-balanced"></i>
						<p>Coupons</p></a></li>
				<li><a href="check_ticket.jsp"><i
						class="fa-solid fa-ticket"></i>
						<p>Tickets</p></a></li>
				<li><a href="https://www.youtube.com/"><i
						class="fa-brands fa-youtube"></i>
						<p>Youtube</p></a></li>
				<div class="active"></div>
			</ul>
		</div>
		<div class="bhuvan">
			<div class="icon">
				<h2 class="logo">Flights</h2>
			</div>
			<div class="menu">
				<ul>

					<li><a href="check_ticket.jsp"><button class="header">CHECK
								TICKET</button></a></li>
					<li><a href="logout.jsp"><button class="header">LOGOUT</button></a></li>
					<li><a href="FlightSearch.jsp"><button class="header">BACK</button></a></li>


				</ul>
			</div>
		</div>

		<div class="contact">

			<div class="column" style="background-color: #155d7a;">
				<div class="contact"></div>
				<form action="Flight_Search2.jsp">
					<div class="form-group" style="opacity: 2000%;">
						<select name="origin" class="drop" id="select1" required>
							<option value="">LEAVING FROM</option>
							<option value="Mumbai">MUMBAI</option>
							<option value="Delhi">DELHI</option>
							<option value="Banglore">BANGLORE</option>
							<option value="Kolkata">KOLKATA</option>
						</select> <select name="destination" class="drop" id="select2" required>
							<option value="">ARRIVING AT</option>
							<option value="Mumbai">MUMBAI</option>
							<option value="Delhi">DELHI</option>
							<option value="Banglore">BANGLORE</option>
							<option value="Kolkata">KOLKATA</option>
						</select>
					</div>
					<div class="form">
						<label for="leaving-on-date">Leaving On</label> <br> <input
							type="date" name="date" class="date" style="opacity: 2000%;"
							required>
					</div>
					<button type="submit" class="search_button">Search</button>
				</form>
			</div>

			<div class="data">
				<table class="pdata" border="1">
					<caption></caption>
					<tr>
						<th></th>
						<th>Origin</th>
						<th>Destination</th>
						<th>Date</th>
						<th>Time</th>
						<th>Money</th>
						<th></th>
					</tr>
					<%
					String origin = request.getParameter("origin");
					String destination = request.getParameter("destination");
					String date = request.getParameter("date");
					String time = "";
					long money = 0;
					FlightDetailsDao flightObject = new FlightDetailsDao();
					ResultSet rs = flightObject.getFlightDetails(origin, destination, date);
					long aadhaar = (long) session.getAttribute("aadhaar");
					session.setAttribute("aadhaar", aadhaar);
					int n = 0;
					while (rs.next()) {
						n++;
						origin = rs.getString(1);
						destination = rs.getString(2);
						date = rs.getString(3);
						time = rs.getString(4);
						money = rs.getLong(5);
					%>
					<tr>
						<td><%=n%></td>
						<td><%=origin%></td>
						<td><%=destination%></td>
						<td><%=date%></td>
						<td><%=time%></td>
						<td><%=money%></td>
						<td>
							<form action="enterPassenger.jsp" method="post">
								<input type="hidden" name="origin" value="<%=origin%>">
								<input type="hidden" name="destination" value="<%=destination%>">
								<input type="hidden" name="date" value="<%=date%>"> <input
									type="hidden" name="time" value="<%=time%>"> <input
									type="hidden" name="money" value="<%=money%>">
								<button type="submit">SELECT</button>
							</form>
						</td>
					</tr>
					<%
					}
					if (n == 0) {
					response.sendRedirect("tickect_not.jsp");
					}
					session.setAttribute("origin", origin);
					session.setAttribute("destination", destination);
					session.setAttribute("date", date);
					session.setAttribute("time", time);
					session.setAttribute("money", money);
					// Database Connection Closing
					DatabaseConnection.closeConnection();
					%>

				</table>
			</div>

		</div>
</body>
</html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page errorPage="error.jsp"%>
