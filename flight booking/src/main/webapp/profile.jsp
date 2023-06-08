<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Profile</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f8f8;
	margin: 0;
	padding: 0;
}

.profile {
	max-width: 500px;
	margin: 20px auto;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.profile-header {
	padding: 20px;
	text-align: center;
	background-color: #333;
	color: #fff;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.profile-header h2 {
	margin: 0;
	font-size: 24px;
}

.profile-body {
	padding: 20px;
}

.field {
	margin-bottom: 20px;
}

.field label {
	font-weight: bold;
	display: block;
	margin-bottom: 5px;
}

.field p {
	margin: 0;
	color: #666;
}

.field .value {
	color: #333;
	font-size: 18px;
	margin-top: 5px;
}

.field .value span {
	color: #666;
	font-size: 14px;
}

.field .icon {
	display: inline-block;
	vertical-align: middle;
	margin-right: 5px;
}

.field .icon img {
	width: 20px;
	height: 20px;
}
</style>
</head>
<body>
	<%
long aadhaar=(long)session.getAttribute("aadhaar");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://192.168.60.30:3306/bhuvan", "bhuvan",
		"bhuvan@12345");
PreparedStatement ps = con
		.prepareStatement("SELECT * FROM bhuvan.user_data WHERE aadhaar=? ");
ps.setLong(1, aadhaar);
ResultSet rs = ps.executeQuery();
String name="";
String email="";
long number=0;
String gender="";
String user="";
if (rs.next()) {
	
           
		name = rs.getString(2);
		 email = rs.getString(3);
		 number= rs.getLong(4);
		 gender= rs.getString(5);
		 user=rs.getString(6);
}
session.setAttribute("aadhaar", aadhaar);
%>
	<div class="profile">
		<div class="profile-header">
			<h2>User Profile</h2>
		</div>
		<div class="profile-body">
			<div class="field">
				<label for="name">Name:</label>
				<p class="value" id="name"><%= name %></p>
			</div>

			<div class="field">
				<label for="email">Email:</label>
				<p class="value" id="email"><%=email %></p>
			</div>

			<div class="field">
				<label for="number">Phone Number:</label>
				<p class="value" id="number"><%=number %></p>
			</div>

			<div class="field">
				<label for="gender">Gender:</label>
				<p class="value" id="gender"><%=gender %></p>
			</div>

			<div class="field">
				<label for="place">Place:</label>
				<p class="value" id="place"><%=user %></p>
			</div>
			<div class="field">
				<button><a href="UpdateProfile.jsp">Update</a></button>
				<button><a href="FlightSearch.jsp">Back</a></button>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page errorPage="error.jsp"%>