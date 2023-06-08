<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update Profile</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f8f8f8;
      margin: 0;
      padding: 0;
    }

    .form-container {
      max-width: 500px;
      margin: 20px auto;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      padding: 20px;
    }

    .form-container h2 {
      margin-top: 0;
      font-size: 24px;
      color: #333;
      text-align: center;
    }

    .form-group {
      margin-bottom: 20px;
    }

    .form-group label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
      color: #333;
    }

    .form-group input[type="text"],
    .form-group input[type="email"],
    .form-group select {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .form-group select {
      height: 35px;
    }

    .form-group input[type="submit"] {
      background-color: #333;
      color: #fff;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
    }
  </style>
</head>
<body>
<%long aadhaar=(long)session.getAttribute("aadhaar"); %>
  <div class="form-container">
    <h2>Update Profile</h2>
    <form action="update_profile" method="post" class="form">
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" placeholder="Enter your name" required>
      </div>

      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Enter your email" required>
      </div>

      <div class="form-group">
        <label for="number">Phone Number:</label>
        <input type="text" id="number" name="number" placeholder="Enter your phone number" required>
      </div>

      <div class="form-group">
        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
          <option value="">Select your gender</option>
          <option value="male">Male</option>
          <option value="female">Female</option>
          <option value="other">Other</option>
        </select>
      </div>

      <div class="form-group">
        <label for="place">Place:</label>
        <input type="text" id="user" name="place" placeholder="Enter your place" required>
      </div>
      <input type="hidden" name="aadhaar" value="<%=aadhaar %>">
      <div class="form-group">
        <input type="submit" value="Update">
        <a href="profile.jsp">back</a>
      </div>
    </form>
    
  </div>
</body>
</html>
