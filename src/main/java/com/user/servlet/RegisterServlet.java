package com.user.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;
 @WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name=req.getParameter("userName");
			String email=req.getParameter("email");
			String phno=req.getParameter("mobile");
			String password=req.getParameter("password");
			String check=req.getParameter("check");
			//System.out.println(name+" "+email+" "+phno+" "+password+" "+check);
			
			User us=new User();
			us.setName(name);
			us.setEmail(email);
			us.setPhone(phno);
			us.setPassword(password);
			
			HttpSession session=req.getSession();
			if(check!=null) {
				UserDAOImpl dao=new UserDAOImpl(DBConnect.getConn());
				boolean f=dao.userRegistre(us);
				if(f) {
					//System.out.println("User Register Successful...");
					session.setAttribute("succMsg","Registration Successful...");
					resp.sendRedirect("register.jsp");
				}else {
					//System.out.println("User Register UnSuccessful...");
					session.setAttribute("failedMsg","Registration Wrong on Server...");
					resp.sendRedirect("register.jsp");
				}
			}else {
				//System.out.println("Please Check Agree & Terms Condition");
				session.setAttribute("failedMsg","Please Check Agree & Terms Condition...");
				resp.sendRedirect("register.jsp");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
