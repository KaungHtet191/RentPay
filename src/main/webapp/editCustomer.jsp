<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Customer</title>
<link rel="stylesheet" href="allComponent/style.css">
<style>
    .form-container { width: 400px; margin: 30px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; font-family: Arial, sans-serif; }
    .form-group { margin-bottom: 12px; text-align: left; }
    .form-group label { display: block; font-weight: bold; margin-bottom: 5px; }
    .form-group input { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
    .btn-submit { width: 100%; background-color: #198754; color: white; border: none; padding: 10px; border-radius: 4px; cursor: pointer; font-size: 16px; font-weight: bold; }
    .btn-submit:hover { background-color: #157347; }
    .btn-cancel { display: block; text-align: center; margin-top: 10px; color: #6c757d; text-decoration: none; }
</style>
</head>
<body>

<%
    String idStr = request.getParameter("id");
    if (idStr == null || idStr.trim().isEmpty()) {
        response.sendRedirect("apply.jsp");
        return;
    }

    int id = Integer.parseInt(idStr);
    String name = "", nrc = "", paypalId = "", paypalMail = "", city = "", address = "", country = "";
    int postalCode = 0;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/currency-app", "root", "root");
             PreparedStatement ps = con.prepareStatement("SELECT * FROM customer WHERE ID = ?")) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("NAME");
                    nrc = rs.getString("NRC_NUMBER");
                    paypalId = rs.getString("PAYPAL_ID");
                    paypalMail = rs.getString("PAYPAL_MAIL");
                    postalCode = rs.getInt("POSTAL_CODE");
                    city = rs.getString("CITY");
                    address = rs.getString("ADDRESS");
                    country = rs.getString("COUNTRY");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<div class="form-container">
    <h2 style="text-align: center;">Edit Customer Details</h2>
    <form action="EditCustomerServlet" method="post">
        
        <!-- ID  cannot delete-->
        <div class="form-group">
            <label>Customer ID:</label>
            <input type="text" name="id" value="<%= id %>" readonly style="background-color: #e9ecef;">
        </div>

        <div class="form-group">
            <label>Name:</label>
            <input type="text" name="name" value="<%= name %>" required>
        </div>

        <div class="form-group">
            <label>NRC Number:</label>
            <input type="text" name="nrc" value="<%= nrc %>" required>
        </div>

        <div class="form-group">
            <label>PayPal ID:</label>
            <input type="text" name="paypalId" value="<%= paypalId %>" required>
        </div>

        <div class="form-group">
            <label>PayPal Email:</label>
            <input type="email" name="paypalMail" value="<%= paypalMail %>" required>
        </div>

        <div class="form-group">
            <label>Postal Code:</label>
            <input type="number" name="postalCode" value="<%= postalCode %>" required>
        </div>

        <div class="form-group">
            <label>City:</label>
            <input type="text" name="city" value="<%= city %>" required>
        </div>

        <div class="form-group">
            <label>Address:</label>
            <input type="text" name="address" value="<%= address %>" required>
        </div>

        <div class="form-group">
            <label>Country:</label>
            <input type="text" name="country" value="<%= country %>" required>
        </div>
	<button type="submit" class="btn-submit">Update Customer</button>
        <a href="apply.jsp" class="btn-cancel">Cancel</a>
    </form>
</div>

</body>
</html>