package com.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditCustomerServlet")
public class EditCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String UPDATE_QUERY = "UPDATE customer SET NAME=?, NRC_NUMBER=?, PAYPAL_ID=?, PAYPAL_MAIL=?, POSTAL_CODE=?, CITY=?, ADDRESS=?, COUNTRY=? WHERE ID=?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String nrc = request.getParameter("nrc");
        String paypalId = request.getParameter("paypalId");
        String paypalMail = request.getParameter("paypalMail");
        int postalCode = Integer.parseInt(request.getParameter("postalCode"));
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String country = request.getParameter("country");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/currency-app", "root", "root");
             PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) {

            ps.setString(1, name);
            ps.setString(2, nrc);
            ps.setString(3, paypalId);
            ps.setString(4, paypalMail);
            ps.setInt(5, postalCode);
            ps.setString(6, city);
            ps.setString(7, address);
            ps.setString(8, country);
            ps.setInt(9, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

       
        response.sendRedirect("apply.jsp");
    }
}