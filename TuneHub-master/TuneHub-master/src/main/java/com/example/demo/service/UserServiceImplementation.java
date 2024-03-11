package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
@Service
public class UserServiceImplementation implements UserService{
	@Autowired
    UsersRepository repo;
	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "User added succefuly";
	}
	@Override
	public boolean emailExists(String email) {
		if(repo.findByEmail(email)==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	@Override
	public boolean validUser(String email, String password) {
	  if(repo.findByEmail(email)!=null)
	  {
		Users user=repo.findByEmail(email);
		
		String db_pass=user.getPassword();
		
		if(password.equals(db_pass))
		 {
			return true;
		 }
		 else 
		 {
			 return false;
		 }
	  }
	  else
	  {
		  return false;
	  }
	}
	
	@Override
	public String getUserRole(String email) {
		Users user=repo.findByEmail(email);
		
		return user.getRole();
	}
	@Override
	public Users getUser(String email) {
		return repo.findByEmail(email);
	}
	@Override
	public void updateUser(Users user) {
		repo.save(user);
	}
	
}
