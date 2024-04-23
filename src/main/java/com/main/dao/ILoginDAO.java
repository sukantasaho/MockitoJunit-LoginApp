package com.main.dao;

public interface ILoginDAO {

	public boolean authenticateUser(String username, String password);
	public String addUser(String user, String role);
}
