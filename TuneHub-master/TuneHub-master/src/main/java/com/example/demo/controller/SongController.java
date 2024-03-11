package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Song;
import com.example.demo.service.SongService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SongController {
	@Autowired
	SongService service;
	
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus=service.nameExists(song.getName());
		if(songStatus==false)
		{
			service.addSong(song);
			System.out.println("song added successfuly");
			return "adminHome";
		}
		else
		{
			System.out.println("song alredy exist");
			return "adminHome";
		}
	}
	
	@GetMapping("/viewSong")
	public String viewSong(Model model) {
		
		List<Song> songlist = service.fetchAllSong();
		model.addAttribute("songs", songlist);
		
		return "displaysongs";
	}
	
	@GetMapping("/playSong")
	public String playSong(Model model) {
		
		boolean premiumUser=false;
		
		if(premiumUser==true)
		{
			List<Song> songlist = service.fetchAllSong();
			model.addAttribute("songs", songlist);
			
			return "displaysongs";
		}
		else
		{
			return "makePayment";
		}
		
	}
	
	
}
