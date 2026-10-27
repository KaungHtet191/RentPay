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

@WebServlet("/admin.jsp")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String SELECT_QUERY = "SELECT ID,Name, Email, PhoneNumber, Password FROM user";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE ID = ?";

    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        pw.println("<html><head>");
        pw.println("<link rel='stylesheet' href='allComponent/style.css'>");
        pw.println("</head><body>");
        pw.println("<h1 style='text-align:center'>User Database View</h1><br>");

        // Delete 
        pw.println("<div style='text-align:center; margin-bottom:20px;'>");
        pw.println("  <form action='admin.jsp' method='post'>");
        pw.println("    <label for='email'>Enter ID to Delete: </label>");
        pw.println("    <input type='number' id='ID' name='id' placeholder='e.g. 1' required style='padding:5px; width:250px;'>");
        pw.println("    <button type='submit' style='background-color:red; color:white; padding:6px 12px; border:none; cursor:pointer;'>Delete User</button>");
        pw.println("  </form>");
        pw.println("</div>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/currency-app", "root", "root");
             PreparedStatement ps = con.prepareStatement(SELECT_QUERY);
             ResultSet rs = ps.executeQuery()) {

            pw.println("<table border='1' style='width:80%; margin:0 auto; border-collapse:collapse;'>");
            pw.println("<tr style='font-family:arial black; background-color:#f2f2f2;'>");
            pw.println("<th>ID</th>");
            pw.println("<th>Name</th>");
            pw.println("<th>Email</th>");
            pw.println("<th>Phone Number</th>");
            pw.println("<th>Password</th>");
            pw.println("</tr>");

            while (rs.next()) {
                pw.println("<tr style='text-align:center'>");
                pw.println("<td>" + rs.getString("ID") + "</td>");
                pw.println("<td>" + rs.getString("Name") + "</td>");
                pw.println("<td>" + rs.getString("Email") + "</td>");
                pw.println("<td>" + rs.getString("PhoneNumber") + "</td>");
                pw.println("<td>" + rs.getString("Password") + "</td>");
                pw.println("</tr>");
            }
            pw.println("</table>");

        } catch (SQLException e) {
            e.printStackTrace();
            pw.println("<h3 style='color:red; text-align:center;'>Database Error: " + e.getMessage() + "</h3>");
        }

        pw.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("id");

        if (email != null && !email.trim().isEmpty()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
             try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/currency-app", "root", "root");
                 PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
                
            
                ps.setString(1, email);
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Delete 
        doGet(request, response);
    }
}