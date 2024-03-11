package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.Playlist;
import com.example.demo.entity.Song;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.SongService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PlaylistController {
	@Autowired
	SongService songService;
	
	@Autowired
	PlaylistService playlistservice;
	
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		
		List<Song> songlist=songService.fetchAllSong();
		model.addAttribute("songs", songlist);
		return "createPlaylist";
	}
	
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		//Updating playlist table
		playlistservice.addPlaylist(playlist);
		
		//Updating song table
		List<Song> songList=playlist.getSong();
		for(Song s:songList)
		{
			s.getPlaylist().add(playlist);
			songService.updateSong(s);
		}
		
		
		return "adminHome";
	}
	
	@GetMapping("/viewPlaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> playlist = playlistservice.fetchAllPlaylist();
		model.addAttribute("playlist", playlist);
		
		return "displayPlaylist";
	}
	
	
}
