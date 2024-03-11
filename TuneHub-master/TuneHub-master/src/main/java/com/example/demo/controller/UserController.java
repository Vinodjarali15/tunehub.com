package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Song;
import com.example.demo.entity.Users;
import com.example.demo.service.SongService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	SongService songService;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {  
		
		boolean userStatus=service.emailExists(user.getEmail());
		
		if(userStatus==false)
		{
			service.addUser(user);
			
			return "home";
		}
		else
		{
			System.out.println("User alredy exist");
			
			return "registration";
		}
		
	}	

	@PostMapping("/validate")
	public String validUser(@RequestParam("email")String email, @RequestParam("password")String password,
			HttpSession session, Model model) {
		if(service.validUser(email,password)==true)
		{
			String role=service.getUserRole(email);
			session.setAttribute("email", email);
			if(role.equals("Admin"))
			{
				return "adminHome";
			}
			else
			{
				Users user=service.getUser(email);
				boolean userStatus=user.isPremium();
				List<Song> songlist = songService.fetchAllSong();
				model.addAttribute("songs", songlist);
				
				model.addAttribute("isPremium", userStatus);
				
				return "customerHome";
			}
		}
		else
		{
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
}
