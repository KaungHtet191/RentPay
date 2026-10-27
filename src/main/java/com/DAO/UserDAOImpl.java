package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDAOImpl implements UserDAO{
	private Connection conn;

	public UserDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean userRegistre(User us) {
		// TODO Auto-generated method stub
		boolean f=false;
		try {
			String sql="insert into user(Name,Email,PhoneNumber,Password) values(?,?,?,?)";
			 
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, us.getName());
			ps.setString(2, us.getEmail());
			ps.setString(3, us.getPhone());
			ps.setString(4, us.getPassword());
			int i=ps.executeUpdate();
			if(i==1) {
				f=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public User login(String email, String password) {
		User us=null;
		try {
			String sql="select * from user where email=? and password=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				us=new User();
				us.setName(rs.getString(1));
				us.setEmail(rs.getString(2));
				us.setPhone(rs.getString(3));
				us.setPassword(rs.getString(4));
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return us; 
	}

	
}
