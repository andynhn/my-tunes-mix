package com.andy.mytunesmix.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andy.mytunesmix.models.Song;
import com.andy.mytunesmix.models.User;
import com.andy.mytunesmix.services.SongService;
import com.andy.mytunesmix.services.UserService;
import com.andy.mytunesmix.validators.UserValidator;

@Controller
public class Users {

    private final UserService userService;
    private final SongService songService;
    private final UserValidator userValidator;
    
    public Users(UserService userService, UserValidator userValidator, SongService songService) {
    	this.userService = userService;
    	this.userValidator = userValidator;
    	this.songService = songService;
    }
    
    // <---------- DISPLAY INDEX PAGE ---------->
    @RequestMapping("/")
    public String index(HttpSession session) {
    	// redirect user to home if they are already logged in.
    	Long userId = (Long) session.getAttribute("userId");
    	if(userId != null) {
    		return "redirect:/home";
    	}
        return "index.jsp";
    }
    
    // <---------- DISPLAY REGISTRATION PAGE ---------->
    @RequestMapping("/register")
    public String registerForm(@ModelAttribute("user") User user, HttpSession session) {
    	// redirect user to home if they are already logged in.
    	Long userId = (Long) session.getAttribute("userId");
    	if(userId != null) {
    		return "redirect:/home";
    	}
        return "register.jsp";
    }
    
    // <---------- DISPLAY LOGIN PAGE ---------->
    @RequestMapping("/login")
    public String login(HttpSession session) {
    	// redirect user to home if they are already logged in.
    	Long userId = (Long) session.getAttribute("userId");
    	if(userId != null) {
    		return "redirect:/home";
    	}
        return "login.jsp";
    }
    
    // <---------- POST REQUEST TO CREATE A USER ---------->
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	// validations for email and password are implemented with "@Valid" and "BindingResult result" (based on validations specified in User.java)
    	// the code below checks our custom validation for password and password confirmation (based on code written in UserValidator and messages.properties)
    	userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "register.jsp";
		} else {
			User u = userService.registerUser(user);
			session.setAttribute("userId",  u.getId());
			return "redirect:/home";
		}
    }
    
    // <---------- POST REQUEST TO LOGIN A USER ---------->
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
    	// checks if the email and password credentials are valid
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if(isAuthenticated) {
    		// if valid, save the user's id into session
    		User u = userService.findByEmail(email);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/home";
    	} else {
    		model.addAttribute("error", "Invalid Credentials. Please try again.");
    		return "login.jsp";
    	}
    }
    
    // <---------- DISPLAY HOME PAGE TO LOGGED IN USERS ---------->
    @RequestMapping("/home")
    public String home(HttpSession session, Model model, RedirectAttributes flash, @RequestParam(value="search", required=false) String search, @RequestParam(value="searchgenre", required=false) String searchgenre) {
        // access userId from session
    	Long userId = (Long) session.getAttribute("userId");
    	
    	// ...if userId is not in session, create a flash message then REDIRECT to login...
    	if(userId == null) {
    		flash.addFlashAttribute("error", "You must be logged in to view that page!");
    		return "redirect:/login";
    	} 
    	
    	// access the user based on the user id that was saved in session and then add that user to the model
    	User u = userService.findUserById(userId);
    	model.addAttribute("user", u);
    	
    	// ...if the search by artist form is filled and the search by genre form is not...
    	if(search != null && searchgenre == null) {
    		List<Song> songsearch = songService.searchForSong(search);
    		ArrayList<Song> songs = new ArrayList<>();
    		for(int i=0; i<songsearch.size(); i++) {
    			if(songsearch.get(i).getUser().getId() == userId) {
    				songs.add(songsearch.get(i));
    			}
    		}
    		model.addAttribute("songs", songs);
    	// ... if the search by genre form is filled and the search by artist form is not..
    	} else if(search == null && searchgenre != null) {
    		List<Song> genresearch = songService.searchSongsByGenre(searchgenre);
    		ArrayList<Song> songs = new ArrayList<>();
    		for(int i=0; i<genresearch.size(); i++) {
    			if(genresearch.get(i).getUser().getId() == userId) {
    				songs.add(genresearch.get(i));
    			}
    		}
    		model.addAttribute("songs", songs);
    	// ...if no search forms are filed, get all of the user's songs...    	
    	} else {
    		List<Song> songs = songService.allUserSongs(userId);
        	model.addAttribute("songs", songs);
    	}

    	return "home.jsp";
    }
    
    // <---------- LOG OUT THE USER BY CLEARING SESSION ---------->
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
        // redirect to login page
    	session.invalidate();
    	return "redirect:/";
    }
    
}
