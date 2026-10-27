package com.DB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/UserPage/apply")
public class ApplyDB extends HttpServlet {
	private final static String query="insert into customer(ID,NAME,NRC_NUMBER,PAYPAL_ID,PAYPAL_MAIL,POSTAL_CODE,CITY,ADDRESS,COUNTRY) values(?,?,?,?,?,?,?,?,?)";
	private static final long serialVersionUID = 1L;
	private static final String Printwriter = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(req.getContextPath());
		PrintWriter pw=response.getWriter();
		String id=req.getParameter("ID");
		String name=req.getParameter("NAME");
		String nrc=req.getParameter("NRC_NUMBER");
		String paypal_id=req.getParameter("PAYPAL_ID");
		String paypal_mail=req.getParameter("PAYPAL_MAIL");
		String postal=req.getParameter("POSTAL_CODE");
		String city=req.getParameter("CITY");
		String address=req.getParameter("ADDRESS");
		String country=req.getParameter("COUNTRY");;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		try (Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/currency-app","root","root")){
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, nrc);
			ps.setString(4, paypal_id);
			ps.setString(5, paypal_mail);
			ps.setString(6, postal);
			ps.setString(7, city);
			ps.setString(8, address);
			ps.setString(9, country);
			int count=ps.executeUpdate();
			if(count==1) {
				pw.println("<h2>Record Registery Successful</h2>");
			}else {
				pw.println("<h2>Record Registery Fail</h2>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			pw.println("<h2>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
