<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Loan Management</title>
<style>
body { font-family: Arial, sans-serif; margin: 20px; }
.form-box { width: 500px; margin: 0 auto 30px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; }
.form-group { margin-bottom: 12px; }
.form-group label { display: block; font-weight: bold; margin-bottom: 5px; }
.form-group input, .form-group select { width: 100%; padding: 8px; box-sizing: border-box; }
.btn-submit { width: 100%; background-color: #0d6efd; color: white; border: none; padding: 10px; cursor: pointer; border-radius: 4px; font-weight: bold; }
table { width: 95%; margin: 0 auto; border-collapse: collapse; text-align: center; }
th { background-color: #f2f2f2; padding: 10px; border-bottom: 2px solid #ddd; }
td { padding: 10px; border-bottom: 1px solid #ddd; }
.btn-delete { background-color: #dc3545; color: white; border: none; padding: 5px 10px; border-radius: 4px; cursor: pointer; }
.search-container { width: 95%; margin: 15px auto; }
.search-input { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; }
tr.highlight { background-color: #fff3cd !important; transition: background-color 0.3s ease; }
</style>
<script>
// အလုပ်အကိုင် အမျိုးအစားအလိုက် Max Limit အလိုအလျောက် သတ်မှတ်ပေးသည့် JavaScript
function updateLoanLimit() {
    var jobType = document.getElementById("jobType").value;
    var limitInput = document.getElementById("maxLimit");
    if (jobType === "Government") {
        limitInput.value = 5000000;
    } else if (jobType === "Company") {
        limitInput.value = 3000000;
    } else {
        limitInput.value = 1000000;
    }
}

// Table ထဲတွင် စာသားဖြင့် ရှာဖွေသည့် Filter
function filterLoanList() {
    var input = document.getElementById("searchBar").value.toUpperCase();
    var table = document.querySelector("table");
    var tr = table.getElementsByTagName("tr");

    for (var i = 1; i < tr.length; i++) {
        var rowText = tr[i].textContent || tr[i].innerText;
        if (rowText.toUpperCase().indexOf(input) > -1) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }
    }
}

// Input Box ထဲ Customer ID ရိုက်လိုက်လျှင် Table Row ကို အလိုအလျောက် Highlight လုပ်ပေးသည့် Function
function highlightCustomerRow() {
    var inputId = document.getElementById("searchCustId").value.trim();
    var table = document.querySelector("table");
    var tr = table.getElementsByTagName("tr");

    for (var i = 1; i < tr.length; i++) {
        var custIdCell = tr[i].getElementsByTagName("td")[1]; // CUST ID Column
        if (custIdCell) {
            var custIdValue = custIdCell.textContent.trim() || custIdCell.innerText.trim();
            if (inputId !== "" && custIdValue === inputId) {
                tr[i].classList.add("highlight");
                tr[i].scrollIntoView({ behavior: 'smooth', block: 'center' });
            } else {
                tr[i].classList.remove("highlight");
            }
        }
    }
}
</script>
</head>
<body>

<%
String customerId = request.getParameter("customerId");
%>

<div class="form-box">
<h2 style="text-align: center;">Set Customer Loan</h2>
<form action="LoanManagementServlet" method="post">
<div class="form-group">
<label>Customer ID:</label>
<input type="text" name="customerId" id="searchCustId" value="<%= (customerId != null ? customerId : "") %>" onkeyup="highlightCustomerRow()" required placeholder="Enter Customer ID">
</div>

<div class="form-group">
<label>Job Type:</label>
<select name="jobType" id="jobType" onchange="updateLoanLimit()" required>
<option value="">-- Select Job Type --</option>
<option value="Government">Government Employee</option>
<option value="Company">Company Employee</option>
<option value="Other">Other / Self-Employed</option>
</select>
</div>
<div class="form-group">
<label>Max Loan Limit (MMK):</label>
<input type="number" name="maxLimit" id="maxLimit" readonly style="background-color: #e9ecef;">
</div>

<div class="form-group">
<label>Requested Loan Amount (MMK):</label>
<input type="number" name="loanAmount" required>
</div>

<div class="form-group">
<label>Interest Rate (%):</label>
<input type="number" step="0.1" name="interestRate" placeholder="e.g. 5.0" required>
</div>

<div class="form-group">
<label>Deadline Date:</label>
<input type="date" name="deadlineDate" required>
</div>

<button type="submit" class="btn-submit">Save Loan Details</button>
</form>
</div>

<h2 style="text-align: center;">Loan Management List</h2>

<!-- Search Bar Filter -->
<div class="search-container">
    <input type="text" id="searchBar" class="search-input" onkeyup="filterLoanList()" placeholder="🔍 Search loans by ID, Customer Name, Job Type...">
</div>

<table>
<tr>
<th>LOAN ID</th>
<th>CUST ID</th>
<th>NAME</th>
<th>JOB TYPE</th>
<th>LOAN AMOUNT</th>
<th>INTEREST (%)</th>
<th>DEADLINE</th>
<th>TOTAL AMOUNT</th>
<th>ACTIONS</th>
</tr>
<%
try {
Class.forName("com.mysql.cj.jdbc.Driver");
try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/currency-app", "root", "root");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT l.loan_id, l.customer_id, c.NAME, l.job_type, l.loan_amount, l.interest_rate, l.deadline_date, l.total_amount FROM loans l JOIN customer c ON l.customer_id = c.ID")) {
while (rs.next()) {
%>
<tr>
<td><%= rs.getInt("loan_id") %></td>
<td><%= rs.getInt("customer_id") %></td>
<td><%= rs.getString("NAME") %></td>
<td><%= rs.getString("job_type") %></td>
<td><%= rs.getDouble("loan_amount") %></td>
<td><%= rs.getDouble("interest_rate") %>%</td>
<td><%= rs.getDate("deadline_date") %></td>
<td><%= rs.getDouble("total_amount") %></td>
<td>
<form action="LoanManagementServlet" method="post" style="display:inline;">
<input type="hidden" name="action" value="delete">
<input type="hidden" name="loanId" value="<%= rs.getInt("loan_id") %>">
<button type="submit" class="btn-delete" onclick="return confirm('Delete this record?')">Delete</button>
</form>
</td>
</tr>
<%
}
}
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>

</body>
</html>