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
	height: 500px;
	margin-left: 40%;
	margin-top: 15%;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	opacity: 50%;
}

.error {
	margin-left: 40%;
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

				<div style="text-align: center; padding: 10px">
					<h2 style="color: red">Tickets not available</h2>
					<p class="par">
						<i class="fa-regular fa-face-smile"></i> Please try again
					</p>
				</div>
				<%
				long aadhaar = (long) session.getAttribute("aadhaar");
				session.setAttribute("aadhaar", aadhaar);
				%>
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
							type="date" id="leaving-on-date" name="date" class="date"
							style="opacity: 2000%;" required>
					</div>
					<button type="submit" class="search_button">Search</button>
				</form>
			</div>
		</div>

	</div>
</body>
</html>