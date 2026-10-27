package com.DB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/apply.jsp")
public class AppliedDBServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String SELECT_ALL_QUERY = "SELECT ID, NAME, NRC_NUMBER, PAYPAL_ID, PAYPAL_MAIL, POSTAL_CODE, CITY, ADDRESS, COUNTRY FROM customer";
    private static final String SEARCH_BY_ID_QUERY = "SELECT ID, NAME, NRC_NUMBER, PAYPAL_ID, PAYPAL_MAIL, POSTAL_CODE, CITY, ADDRESS, COUNTRY FROM customer WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM customer WHERE ID = ?";

    public AppliedDBServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String searchId = request.getParameter("searchId");

        pw.println("<html><head>");
        pw.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'>");
        pw.println("<link rel='stylesheet' href='allComponent/style.css'>");
        pw.println("<style>");
        pw.println("table { width:95%; margin:20px auto; border-collapse:collapse; text-align:center; }");
        pw.println("th { font-family:arial black; background-color:#f2f2f2; padding:10px; }");
        pw.println("td { padding:10px; border-bottom:1px solid #ddd; }");
        pw.println(".btn-edit { background-color:#198754; color:white; padding:6px 12px; border:none; text-decoration:none; border-radius:4px; font-weight:bold; display:inline-block; margin-right:5px; }");
        pw.println(".btn-delete { background-color:#dc3545; color:white; padding:6px 12px; border:none; border-radius:4px; font-weight:bold; cursor:pointer; }");
        pw.println(".search-box { text-align:center; margin-top:20px; }");
        pw.println(".search-box input { padding:8px; width:200px; border:1px solid #ccc; border-radius:4px; }");
        pw.println(".search-box button { padding:8px 15px; background-color:#0d6efd; color:white; border:none; border-radius:4px; cursor:pointer; }");
        pw.println(".btn-clear { padding:8px 15px; background-color:#6c757d; color:white; border:none; border-radius:4px; text-decoration:none; margin-left:5px; }");
        pw.println("</style>");
        pw.println("</head><body>");

        pw.println("<h1 style='text-align:center'>Customer Applied List</h1>");

        // ID Filter / Search Box Section
        pw.println("<div class='search-box'>");
        pw.println("  <form action='apply.jsp' method='get'>");
        pw.println("    <input type='number' name='searchId' placeholder='Search by Customer ID...' value='" + (searchId != null ? searchId : "") + "'>");
        pw.println("    <button type='submit'>Search</button>");
        pw.println("<button type='button' onclick=\"window.open('https://mail.google.com', '_blank')\"><i class='fa-solid fa-envelope'></i> Gmail</button>");
      
        if (searchId != null && !searchId.trim().isEmpty()) {
            pw.println("    <a href='apply.jsp' class='btn-clear'>Clear Filter</a>");
        }
        pw.println("  </form>");
        pw.println("</div>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        boolean isSearch = (searchId != null && !searchId.trim().isEmpty());
        String currentQuery = isSearch ? SEARCH_BY_ID_QUERY : SELECT_ALL_QUERY;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/currency-app", "root", "root");
             PreparedStatement ps = con.prepareStatement(currentQuery)) {

            if (isSearch) {
                ps.setInt(1, Integer.parseInt(searchId.trim()));
            }
            try (ResultSet rs = ps.executeQuery()) {
                pw.println("<table>");
                pw.println("<tr>");
                pw.println("<th>ID</th>");
                pw.println("<th>NAME</th>");
                pw.println("<th>NRC NUMBER</th>");
                pw.println("<th>PAYPAL ID</th>");
                pw.println("<th>PAYPAL MAIL</th>");
                pw.println("<th>POSTAL CODE</th>");
                pw.println("<th>CITY</th>");
                pw.println("<th>ADDRESS</th>");
                pw.println("<th>COUNTRY</th>");
                pw.println("<th>ACTIONS</th>");
                pw.println("</tr>");

                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    int id = rs.getInt("ID");
                    pw.println("<tr>");
                    pw.println("<td>" + id + "</td>");
                    pw.println("<td>" + rs.getString("NAME") + "</td>");
                    pw.println("<td>" + rs.getString("NRC_NUMBER") + "</td>");
                    pw.println("<td>" + rs.getString("PAYPAL_ID") + "</td>");
                    pw.println("<td>" + rs.getString("PAYPAL_MAIL") + "</td>");
                    pw.println("<td>" + rs.getInt("POSTAL_CODE") + "</td>");
                    pw.println("<td>" + rs.getString("CITY") + "</td>");
                    pw.println("<td>" + rs.getString("ADDRESS") + "</td>");
                    pw.println("<td>" + rs.getString("COUNTRY") + "</td>");
                    
                    // Edit and Delete Buttons Action Column
                    pw.println("<td style='white-space:nowrap;'>");
                    // Edit Link (Goes to editCustomer.jsp with ID parameter)
                    pw.println("  <a href='editCustomer.jsp?id=" + id + "' class='btn-edit'>Edit</a>");
                    
                    // Delete Form
                    pw.println("  <form action='apply.jsp' method='post' style='display:inline;' onsubmit='return confirm(\"Are you sure you want to delete this row?\");'>");
                    pw.println("    <input type='hidden' name='customerId' value='" + id + "'>");
                    pw.println("    <button type='submit' class='btn-delete'>Delete</button>");
                    pw.println("  </form>");
                    pw.println("</td>");
                    
                    pw.println("</tr>");
                }

                if (!hasData) {
                    pw.println("<tr><td colspan='10' style='color:red;'>No record found.</td></tr>");
                }

                pw.println("</table>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h3 style='color:red; text-align:center;'>Database Error: " + e.getMessage() + "</h3>");
        }

        pw.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerIdStr = request.getParameter("customerId");

        if (customerIdStr != null && !customerIdStr.trim().isEmpty()) {
            try {
                int customerId = Integer.parseInt(customerIdStr);

                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/currency-app", "root", "root");
                     PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
                    
                    ps.setInt(1, customerId);
                    ps.executeUpdate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("apply.jsp");
    }
}