package com.DAO;

import com.entity.User;

public interface UserDAO {
	public boolean userRegistre(User us);
	
   // public boolean userApplied(UserApply usa);
	
	public User login(String email,String password);

}
