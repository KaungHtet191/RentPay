package com.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoanManagementServlet")
public class LoanManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String INSERT_LOAN_QUERY = "INSERT INTO loans (customer_id, job_type, max_loan_limit, loan_amount, interest_rate, deadline_date, total_amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_LOAN_QUERY = "DELETE FROM loans WHERE loan_id = ?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/currency-app", "root", "root")) {

                if ("delete".equals(action)) {
                    int loanId = Integer.parseInt(request.getParameter("loanId"));
                    try (PreparedStatement ps = con.prepareStatement(DELETE_LOAN_QUERY)) {
                        ps.setInt(1, loanId);
                        ps.executeUpdate();
                    }
                } else {
                    int customerId = Integer.parseInt(request.getParameter("customerId"));
                    String jobType = request.getParameter("jobType");
                    double maxLimit = Double.parseDouble(request.getParameter("maxLimit"));
                    double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
                    double interestRate = Double.parseDouble(request.getParameter("interestRate"));
                    String deadlineDate = request.getParameter("deadlineDate");

                    // Total Amount အတိုးနှုန်းပါ ပေါက်တွက်ခြင်း
                    double totalAmount = loanAmount + (loanAmount * (interestRate / 100.0));

                    // Validation Check
                    if (loanAmount <= maxLimit) {
                        try (PreparedStatement ps = con.prepareStatement(INSERT_LOAN_QUERY)) {
                            ps.setInt(1, customerId);
                            ps.setString(2, jobType);
                            ps.setDouble(3, maxLimit);
                            ps.setDouble(4, loanAmount);
                            ps.setDouble(5, interestRate);
                            ps.setString(6, deadlineDate);
                            ps.setDouble(7, totalAmount);
                            ps.executeUpdate();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("manageLoan.jsp");
    }
}