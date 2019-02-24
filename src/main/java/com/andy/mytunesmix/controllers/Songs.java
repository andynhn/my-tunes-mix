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
import com.andy.mytunesmix.validators.SongValidator;

@Controller
public class Songs {
	
	private final SongService songService;
	private final UserService userService;
	private final SongValidator songValidator;
	
	public Songs(SongService songService, UserService userService, SongValidator songValidator) {
		this.songService = songService;
		this.userService = userService;
		this.songValidator = songValidator;
	}
	
    // <---------- RENDER PAGE TO ADD A NEW SONG ---------->
	@RequestMapping("/songs/new")
	public String newSong(Model model, @ModelAttribute("song") Song song, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page");
			return "redirect:/login";
		} else {
			// User u = userService.findUserById(userId);
	    	model.addAttribute("user", userService.findUserById(userId));
			return "new.jsp";
		}
	}
	
    // <---------- PROCESS POST REQUEST TO CREATE A NEW SONG ---------->
	@RequestMapping(value="/songs", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("song") Song song, BindingResult result, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page");
			return "redirect:/login";
		} else {
			// Check to see if the user submitted a song without their user id attached or if the user id submitted does not match the id of the logged in user (which should be included as a hidden input)
			songValidator.validateSong(song, result, userService.findUserById(userId));
			if(result.hasErrors()) {
				return "new.jsp";
			} else {
				songService.createSong(song);
				return "redirect:/home";
			}
		}
	}
	
    // <---------- GET A SONG BY ID ---------->
	@RequestMapping("/songs/{id}")
	public String show(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes flash) {
		// access user id from session
		Long userId = (Long) session.getAttribute("userId");
		// try to find a song based on the id in the path variable
		Song s = songService.findSong(id);
		// if the user is not logged in...
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page");
			return "redirect:/login";
		// ...if that song id requested does not exist...
		} else if(s == null) {
			flash.addFlashAttribute("error", "Error processing your request");
			return "redirect:/home";
		} else {
			model.addAttribute("song", s);
			// User u = userService.findUserById(userId);
	    	model.addAttribute("user", userService.findUserById(userId));
			return "showSong.jsp";
		}
	}
	
	// <--------- RENDER PAGE TO EDIT A SONG ---------->
	@RequestMapping("/songs/{id}/edit")
	public String editSong(Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		// access user id from session
		Long userId = (Long) session.getAttribute("userId");
		// try to find a song based on the id in the path variable
		Song s = songService.findSong(id);
		// if the user is not logged in...
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page");
			return "redirect:/login";
		// ...if the song id requested does not exist...
		} else if(s == null) {
			flash.addFlashAttribute("error", "Error processing your request");
			return "redirect:/home";
		// ...if the song exists, but does not belong to the user...
		} else if(userId != s.getUser().getId()) {
			flash.addFlashAttribute("error", "You cannot edit this song");
			return "redirect:/home";
		// ...if the above checks pass, proceed with rendering the edit page...
		} else {
			model.addAttribute("song", s);
			// User u = userService.findUserById(userId);
	    	model.addAttribute("user", userService.findUserById(userId));
			return "editSong.jsp";
		}
	}
	
	
	// <--------- POST REQUEST TO UPDATE A SONG ---------->
	@RequestMapping("/songs/{id}/update")
	public String updateSong(@Valid @ModelAttribute("song") Song song, BindingResult result, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		// access user id from session
		Long userId = (Long) session.getAttribute("userId");
		// try to find a song based on the id in the path variable
		Song s = songService.findSong(id);
		// if the user is not logged in...
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page");
			return "redirect:/login";
		// if the song id requested does not exist...
		} else if(s == null) {
			flash.addFlashAttribute("error", "Error processing your request");
			return "redirect:/home";
		// ...if the song exists, but does not belong to the user...
		} else if(userId != s.getUser().getId()) {
			flash.addFlashAttribute("error", "You cannot edit this song");
			return "redirect:/home";
		// ...if the above checks pass, proceed with editing the song...
		} else {
			// Check to see if the user submitted a song without their user id attached or if the user id submitted does not match the id of the logged in user (which should be included as a hidden input)
			songValidator.validateSong(song, result, userService.findUserById(userId));
			// ...if there are errors in the form data...
			if(result.hasErrors()) {
				return "editSong.jsp";
			} else {
				// if all the above checks pass, update the song
				songService.update(song);
			}
			return "redirect:/home";
		}
	}
	
    // <---------- DELETE A SONG BY ID ---------->
	@RequestMapping(value="/songs/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		// access user id from session
		Song s = songService.findSong(id);
		// try to find a song based on the id in the path variable
		Long userId = (Long) session.getAttribute("userId");
		// if the user is not logged in...
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page");
			return "redirect:/login";
		// ...if the song id requested does not exist...
		} else if(s == null) {
			flash.addFlashAttribute("error", "Error processing your request");
			return "redirect:/home";
		// ...if the user tries to delete a song that they did not create...
		} else if(userId != s.getUser().getId()) {
			flash.addFlashAttribute("error", "You cannot delete this song");
			return "redirect:/home";
		// ...if the above checks pass, delete the song
		} else {
			songService.deleteSong(id);
		}
		return "redirect:/home";
	}
	
    // <---------- GENERATE USER'S TOP TEN SONG LIST ---------->
	@RequestMapping("/search/topTen")
	public String showTopTen(Model model, HttpSession session, RedirectAttributes flash) {
		// access user id from session
		Long userId = (Long) session.getAttribute("userId");
		// if the user is not logged in...
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page");
			return "redirect:/login";
		} else {
			// finds the user's top ten songs, ordered by rating descending
			List<Song> songs = songService.findTopTen(userId);
			model.addAttribute("songs", songs);
			// if the user has less than 10 songs in their library...
			if(songs.size() < 10) {
				model.addAttribute("addmoresongs", "Add more tunes to your library to complete your top ten.");
			} 
			// User u = userService.findUserById(userId);
	    	model.addAttribute("user", userService.findUserById(userId));
			return "topTen.jsp";
		}
	}
	
	// <---------- RENDER DISCOVER MUSIC PAGE ---------->
	@RequestMapping("/discover")
	public String discover(Model model, HttpSession session, RedirectAttributes flash, @RequestParam(value="search", required=false) String search, @RequestParam(value="searchgenre", required=false) String searchgenre) {
		// access user id from session
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page");
			return "redirect:/login";
		} else {
	    	// User u = userService.findUserById(userId);
	    	model.addAttribute("user", userService.findUserById(userId));
	    	// ...if the search by artist form is filled and the search by genre form is not...
	    	if(search != null && searchgenre == null) {
	    		System.out.println("Made it here");
	    		List<Song> songsearch = songService.searchForSong(search);
	    		ArrayList<Song> songs = new ArrayList<>();
	    		for(int i=0; i<songsearch.size(); i++) {
	    			if(songsearch.get(i).getUser().getId() != userId) {
	    				songs.add(songsearch.get(i));
	    			}
	    		}
	    		model.addAttribute("songs", songs);
	    		return "discover.jsp";
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
	    		return "discover.jsp";
	    	// ...otherwise, save all of the other songs in the list...
	    	} else {
	    		List<Song> songs = songService.discoverSongs(userId);
	        	model.addAttribute("songs", songs);
	        	return "discover.jsp";
	    	}
		}
	}
	
}
