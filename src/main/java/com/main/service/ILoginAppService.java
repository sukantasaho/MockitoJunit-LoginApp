package com.main.service;

public interface ILoginAppService {

	public boolean login(String username, String password);
	public String registerUser(String user, String role);
}
