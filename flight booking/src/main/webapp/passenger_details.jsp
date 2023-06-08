<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<title>Passenger Details</title>
<link rel="stylesheet" href="passenger_css.css">
<style>
.passengerDetails {
	display: flex;
	background: linear-gradient(to top, rgba(0, 0, 0, 0.5) 50%,
		rgba(0, 0, 0, 0.5) 50%), url(search_img.jpg);
	flex-direction: column;
	align-items: center;
}

.passengerBox {
	width: 400px;
	padding: 20px;
	background-color: #f2f2f2;
	border-radius: 10px;
	height: auto;
}

.passenger {
	margin-bottom: 10px;
}

.passenger label {
	display: inline-block;
	width: 70px;
	font-weight: bold;
}

.passenger input[type="text"], .passenger input[type="number"],
	.passenger select {
	width: 200px;
	padding: 5px;
	border-radius: 5px;
	border: 1px solid #ccc;
}

.submit {
	margin-top: 20px;
}

.sb {
	padding: 10px 20px;
	background-color: #008080;
	color: #fff;
	font-size: 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	width: 100px;
}

.head {
	display: flex;
	justify-content: space-between;
	margin-top: 20px;
}

.head button {
	width: 120%;
	height: 40px;
	border-radius: 50px;
	border: none;
	border-color: #008080;
	background-color: #fff;
	color: #008080;
	font-size: 14px;
	cursor: pointer;
}

.head button:hover {
	background-color: #008080;
	color: #fff;
}

.ticket {
	margin-left: 20px;
}

.sb:hover {
	background-color: #008080;
	color: #fff;
}
</style>
</head>
<body>
	<div class="passengerDetails">
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
						<p>coupons</p></a></li>
				<li><a href="check_ticket.jsp"><i
						class="fa-solid fa-ticket"></i>
						<p>TICKETS</p></a></li>
				<li><a href="https://www.youtube.com/"><i
						class="fa-brands fa-youtube"></i>
						<p>Youtube</p></a></li>
				<div class="active"></div>
			</ul>
		</div>
		<div class="head">
			<a href="check_ticket.jsp"><button>TICKETS</button></a> <a
				href="FlightSearch.jsp"><button class="ticket">BACK</button></a>
		</div>
		<div class="passengerBox">

			<div id="passengersContainer">

				<%
				int numberValue = 1;
				if ("" != request.getParameter("numberValue")) {
					numberValue = Integer.parseInt(request.getParameter("numberValue"));
				}
				String origin = (String) session.getAttribute("origin");
				String destination = (String) session.getAttribute("destination");
				String time = (String) session.getAttribute("time");
				String date = (String) session.getAttribute("date");
				long money = (long) session.getAttribute("money");
				long aadhaar = (long) session.getAttribute("aadhaar");
				%>
				<form action="SendPassengerDetails.jsp" method="POST">

					<%
					for (int i = 0; i < numberValue; i++) {
					%>
					<div class="passenger">
						<label for="name<%=i%>">Name:</label> <input type="text"
							name="name" id="name<%=i%>" required> <label
							for="age<%=i%>">Age:</label> <input type="number" name="age"
							id="age<%=i%>" required> <label for="gender<%=i%>">Gender:</label>
						<select name="gender" id="gender<%=i%>" required>
							<option value="male">Male</option>
							<option value="female">Female</option>
							<option value="other">Other</option>
						</select>
					</div>

					<input type="hidden" name="aadhaar" value="<%=aadhaar%>"> <input
						type="hidden" name="origin" value="<%=origin%>"> <input
						type="hidden" name="destination" value="<%=destination%>">
					<input type="hidden" name="time" value="<%=time%>"> <input
						type="hidden" name="date" value="<%=date%>">


					<%
					}
					money = money * numberValue;
					%>
					<div class="submit">
						<button type="submit" class="sb">
							$
							<%=money%></button>
					</div>
			</div>
		</div>

		</form>

	</div>
</body>
</html>
<%@ page import="java.util.UUID"%>