package com.andy.mytunesmix.controllers;

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
			return "redirect:/dashboard";
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
		
		if(userId != uploaderId) {
			flash.addFlashAttribute("error", "You cannot view this song!");
			return "redirect:/dashboard";
		}

		model.addAttribute("song", s);
		return "showSong.jsp";
	}
	
	// <--------- RENDER PAGE TO EDIT A SONG ---------->
	@RequestMapping("/song/{id}/edit")
	public String editSong(Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Song s = songService.findSong(id);
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		} else if(userId != s.getUser().getId()) {
			flash.addFlashAttribute("error", "You cannot edit this song!");
			return "redirect:/dashboard";
		}
		User u = userService.findUserById(userId);
    	model.addAttribute("user", u);
		model.addAttribute("song", s);
		return "editSong.jsp";
	}
	
	
	// <--------- POST REQUEST TO UPDATE A SONG ---------->
	@RequestMapping("/song/{id}/update")
	public String updateSong(@Valid @ModelAttribute("song") Song song, BindingResult result, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Song s = songService.findSong(id);
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		} else if(userId != s.getUser().getId()) {
			flash.addFlashAttribute("error", "You cannot edit this song!");
			return "redirect:/dashboard";
		}
		
		if(result.hasErrors()) {
			return "editTruck.jsp";
		} else {
			songService.update(song);
			return "redirect:/dashboard";
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
			return "redirect:/dashboard";
		}
		songService.deleteSong(id);
		return "redirect:/dashboard";
	}
	
    // <---------- GENERATE TOP TEN SONG LIST ---------->
	@RequestMapping("/search/topTen")
	public String showTopTen(Model model, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/login";
		}
		List<Song> songs = songService.findTopTen();
		model.addAttribute("songs", songs);
		User u = userService.findUserById(userId);
    	model.addAttribute("user", u);
		return "topTen.jsp";
	}
	
}
