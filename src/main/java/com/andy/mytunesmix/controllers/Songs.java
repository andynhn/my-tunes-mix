package com.andy.mytunesmix.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andy.mytunesmix.models.Song;
import com.andy.mytunesmix.models.User;
import com.andy.mytunesmix.services.SongService;
import com.andy.mytunesmix.services.UserService;

@Controller
public class Songs {
	
	private final SongService songService;
	private final UserService userService;
	
	public Songs(SongService songService, UserService userService) {
		this.songService = songService;
		this.userService = userService;
	}
	
    // <---------- RENDER PAGE TO ADD A NEW SONG ---------->
	@RequestMapping("/songs/new")
	public String newSong(Model model, @ModelAttribute("song") Song song, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		}
		User u = userService.findUserById(userId);
    	model.addAttribute("user", u);
		return "new.jsp";
	}
	
    // <---------- PROCESS POST REQUEST TO CREATE A NEW SONG ---------->
	@RequestMapping(value="/songs", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("song") Song song, BindingResult result, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		}
		
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			songService.createSong(song);
			return "redirect:/home";
		}
	}
	
    // <---------- GET A SONG BY ID ---------->
	@RequestMapping("/songs/{id}")
	public String show(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes flash) {
		Song s = songService.findSong(id);
		Long userId = (Long) session.getAttribute("userId");
		Long uploaderId = (Long) s.getUser().getId();
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		}
		
//		if(userId != uploaderId) {
//			flash.addFlashAttribute("error", "You cannot view this song!");
//			return "redirect:/home";
//		}

		model.addAttribute("song", s);
		User u = userService.findUserById(userId);
    	model.addAttribute("user", u);
		return "showSong.jsp";
	}
	
	// <--------- RENDER PAGE TO EDIT A SONG ---------->
	@RequestMapping("/songs/{id}/edit")
	public String editSong(Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Song s = songService.findSong(id);
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		} else if(userId != s.getUser().getId()) {
			flash.addFlashAttribute("error", "You cannot edit this song!");
			return "redirect:/home";
		}
		User u = userService.findUserById(userId);
    	model.addAttribute("user", u);
		model.addAttribute("song", s);
		return "editSong.jsp";
	}
	
	
	// <--------- POST REQUEST TO UPDATE A SONG ---------->
	@RequestMapping("/songs/{id}/update")
	public String updateSong(@Valid @ModelAttribute("song") Song song, BindingResult result, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Song s = songService.findSong(id);
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		} else if(userId != s.getUser().getId()) {
			flash.addFlashAttribute("error", "You cannot edit this song!");
			return "redirect:/home";
		}
		
		if(result.hasErrors()) {
			return "editTruck.jsp";
		} else {
			songService.update(song);
			return "redirect:/home";
		}
	}
	
    // <---------- DELETE A SONG BY ID ---------->
	@RequestMapping(value="/songs/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Song s = songService.findSong(id);
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		} else if(userId != s.getUser().getId()) {
			flash.addFlashAttribute("error", "You cannot delete this song!");
			return "redirect:/home";
		}
		songService.deleteSong(id);
		return "redirect:/home";
	}
	
    // <---------- GENERATE USER'S TOP TEN SONG LIST ---------->
	@RequestMapping("/search/topTen")
	public String showTopTen(Model model, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		}
		// finds all of the user's songs, ordered by rating descending
		List<Song> toptensearch = songService.findTopTen(userId);
		ArrayList<Song> songs = new ArrayList<>();
		// If the user has more than 10 songs in their library, save only the first 10 in the list
		if(toptensearch.size() > 10) {
			for(int i=0; i<10; i++) {
				songs.add(toptensearch.get(i));
			}
			model.addAttribute("songs", songs);
		// ...otherwise, add the entire list to the model
		} else {
			model.addAttribute("songs", toptensearch);
			model.addAttribute("addmoresongs", "Add more songs to your library to complete your top ten.");
		}

		User u = userService.findUserById(userId);
    	model.addAttribute("user", u);
		return "topTen.jsp";
	}
	
	// <---------- RENDER DISCOVER MUSIC PAGE ---------->
	@RequestMapping("/discover")
	public String discover(Model model, HttpSession session, RedirectAttributes flash, @RequestParam(value="search", required=false) String search, @RequestParam(value="searchgenre", required=false) String searchgenre) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		}
    	User u = userService.findUserById(userId);
    	model.addAttribute("user", u);
    	// ...if the search by artist form is filled and the search by genre form is not...
    	if(search != null && searchgenre == null) {
    		List<Song> songsearch = songService.searchForSong(search);
    		ArrayList<Song> songs = new ArrayList<>();
    		for(int i=0; i<songsearch.size(); i++) {
    			if(songsearch.get(i).getUser().getId() != userId) {
    				songs.add(songsearch.get(i));
    			}
    		}
    		model.addAttribute("songs", songs);
        // ... if the search by genre form is filled and the search by artist form is not..
    	} else if(search == null && searchgenre != null) {
    		List<Song> genresearch = songService.searchSongsByGenre(searchgenre);
    		ArrayList<Song> songs = new ArrayList<>();
    		for(int i=0; i<genresearch.size(); i++) {
    			if(genresearch.get(i).getUser().getId() != userId) {
    				songs.add(genresearch.get(i));
    			}
    		}
    		model.addAttribute("songs", songs);
    	// ...otherwise, save all of the other songs in the list...
    	} else {
    		List<Song> songs = songService.allOtherSongs(userId);
        	model.addAttribute("songs", songs);
    	}
    	return "discover.jsp";
	}
	
}
