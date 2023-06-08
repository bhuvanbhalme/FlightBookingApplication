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
	width: 32%;
	height: auto;
	margin: 10% auto;
	padding: 10px;
	border-radius: 50px;
	background-color: #155d7a;
	opacity: 50%;
	text-align: center;
	color: white;
}

.column .contact {
	display: inline-block;
	padding-right: 50px;
}

.column .contact h1 {
	margin: 0;
	padding: 0;
}

.column .contact button {
	background: black;
	border: none;
	cursor: pointer;
}

.column .contact button h2 {
	margin: 0;
	padding: 0;
	color: white;
}

.column .contact label {
	margin: 0 10px;
	font-size: 18px;
	font-weight: bold;
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
	width: 200px;
	height: 40px;
	margin: 50px;
	text-align: center;
	background-color: white;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	color: aqua;
	margin-left: 10%;
}

.header {
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	background-color: #4682B4;
	width: 100px;
	height: 40px
}

.origin {
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

.dest {
	margin-left: 300p14x;
	margin-top: 10px;
}

.header {
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	background-color: #4682B4;
	width: 100px;
	height: 40px
}

.contact {
	margin-right: 10px;
}

.lines {
	margin-right: 550px;
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
				<li><a href="https://www.grabon.in/"> <i
						class="fa-sharp fa-regular fa-scale-balanced"> </i>
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

		<div class="column" style="background-color: #155d7a;">

			<div class="contact">
				<div class="nameObj"></div>
				<%
				String origin = request.getParameter("origin");
				String destination = request.getParameter("destination");
				String time = request.getParameter("time");
				String date = request.getParameter("date");
				long money = Long.parseLong(request.getParameter("money"));

				long aadhaar = (long) session.getAttribute("aadhaar");

				session.setAttribute("aadhaar", aadhaar);
				session.setAttribute("origin", origin);
				session.setAttribute("destination", destination);
				session.setAttribute("time", time);
				session.setAttribute("date", date);
				session.setAttribute("money", money);
				%>



				<div class="lines">

					<div
						style="display: inline-block; padding-right: 110px; color: white;">
						<h1><%=origin%>
						</h1>
					</div>
					<div style="display: inline-block; color: white;">
						<h1></h1>
						<h1><%=destination%></h1>
					</div>


					<br>
					<div
						style="display: inline-block; padding-right: 80px; color: white;">
						<h1><%=time%><br>
						</h1>
					</div>
					<div style="display: inline-block; color: white;">
						<h1></h1>
						<h1><%=date%><br>
						</h1>
					</div>
					<br>
					<div style="margin-right: 80px; margin-top: 50px;">
						<button onclick="increaseNumber()">
							<h2>
								<i class="fa-solid fa-user-plus"></i>
							</h2>
						</button>

						<label id="number">1</label>



						<button onclick="decreaseNumber()">
							<h2>
								<i class="fa-solid fa-user-minus"></i>
							</h2>
						</button>
					</div>
					<script>
						var number = 1;

						function increaseNumber() {
							number++;
							document.getElementById("number").textContent = number;
							document.getElementById("numberValue").value = number;
						}

						function decreaseNumber() {
							number--;
							document.getElementById("number").textContent = number;
							document.getElementById("numberValue").value = number;
						}
					</script>

					<form action="passenger_details.jsp" method="post">
						<input type="hidden" name="numberValue" id="numberValue">
						<button type="submit" class="search_button" style="color: black;">
							<h2>BOOK</h2>
						</button>
					</form>
				</div>
			</div>
		</div>

	</div>
</body>
</html>