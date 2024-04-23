package com.main.service;

import com.main.dao.ILoginDAO;
//import com.main.dao.LoginDAOImp;

public class LoginAppServiceImp implements ILoginAppService {

	ILoginDAO loginDAO;
	 
	public LoginAppServiceImp(ILoginDAO dao)
	{
		this.loginDAO = dao;
	}
	@Override
	public boolean login(String username, String password) {
		 
		if(username.equals("") || password.equals(""))
		 throw new IllegalArgumentException("Empty credential");
		boolean b = loginDAO.authenticateUser(username, password);
		System.out.println("service:login-"+b);
		if(b)
		{
			return true;
		}
		else
		return false;
		 
	}
	@Override
	public String registerUser(String user, String role) 
	{
		String status = null;
		 if(!role.equalsIgnoreCase("") && !role.equalsIgnoreCase("visitor"));
		 {
			 loginDAO.addUser(user, role);
			 status = "User Added";
		 } 
		 status = "User Not Added";
		 
		 return status;
	}

}
