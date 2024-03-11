package com.example.demo.service;

import com.example.demo.entity.Users;

public interface UserService {
	
	public String addUser(Users user);
	
	public boolean emailExists(String email);
	
	public boolean validUser(String email,String password);
	
	public String getUserRole(String email);

	public Users getUser(String email);

	public void updateUser(Users user);

}
