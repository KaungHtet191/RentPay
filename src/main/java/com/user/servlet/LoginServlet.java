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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			UserDAOImpl dao=new UserDAOImpl(DBConnect.getConn());
			
			HttpSession session=req.getSession();	
			
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			if("adminforregister@gmail.com".equals(email) && "admin".equals(password)) {
				User us=new User();
				session.setAttribute("userobj", us);
				resp.sendRedirect("admin.jsp");
			}else if("adminforcustomer@gmail.com".equals(email) && "admin".equals(password)) {
				User us=new User();
				session.setAttribute("userobj", us);
				resp.sendRedirect("apply.jsp");
			}
			else if("adminforloan@gmail.com".equals(email) && "admin".equals(password)) {
				User us=new User();
				session.setAttribute("userobj", us);
				resp.sendRedirect("manageLoan.jsp");
			}
				else {
				User us=dao.login(email, password);
				if(us!=null) {
					session.setAttribute("userobj", us);
					resp.sendRedirect("UserPage/index2.jsp");
				}
				else {
					session.setAttribute("failedMsg", "Email & Password Invalid");
					resp.sendRedirect("login.jsp");
				}
				resp.sendRedirect("UserPage/index2.jsp");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
