package com.andy.mytunesmix.controllers;

// By Andy N.H. Nguyen - https://andynhn.me/ - https://github.com/andynhn/
	
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
    	Long userId = (Long) session.getAttribute("userId");
    	// redirect user to home if they are already logged in.
    	if(userId != null) {
    		return "redirect:/home";
    	} else {
            return "index.jsp";
    	}
    }
    
    // <---------- DISPLAY REGISTRATION PAGE ---------->
    @RequestMapping("/register")
    public String registerForm(@ModelAttribute("user") User user, HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId");
    	// redirect user to home if they are already logged in.
    	if(userId != null) {
    		return "redirect:/home";
    	} else {
            return "register.jsp";
    	}
    }
    
    // <---------- DISPLAY LOGIN PAGE ---------->
    @RequestMapping("/login")
    public String login(HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId");
    	// redirect user to home if they are already logged in.
    	if(userId != null) {
    		return "redirect:/home";
    	} else {
            return "login.jsp";
    	}
    }
    
    // <---------- POST REQUEST TO CREATE A USER ---------->
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	// validations for email and password are implemented with "@Valid" and "BindingResult result" (based on validations specified in User.java)
    	// the code below checks our custom validation (based on code written in UserValidator and messages.properties)
    	userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "register.jsp";
		} else {
			// User u = userService.registerUser(user);
			// Create a new user, then save that user's auto generated ID into session as userId
			session.setAttribute("userId", userService.registerUser(user).getId());
			return "redirect:/home";
		}
    }
    
    // <---------- POST REQUEST TO LOGIN A USER ---------->
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
    	// check if the email and password credentials are valid
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if(isAuthenticated) {
    		// if valid, save the user's id into session
    		// User u = userService.findByEmail(email);
    		session.setAttribute("userId", userService.findByEmail(email).getId());
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
    	// ...if userId is not in session, REDIRECT to login...
    	if(userId == null) {
    		flash.addFlashAttribute("error", "You must be logged in to view that page!");
    		return "redirect:/login";
    	// if the user is indeed logged in, perform the following checks...
    	} else {
        	// access the user based on the user id that was saved in session and then add that user to the model
        	// User u = userService.findUserById(userId);
        	model.addAttribute("user", userService.findUserById(userId));
        	// ...if the search by artist form is filled and the search by genre form is not...
        	if(search != null && searchgenre == null) {
        		// ...if the search artist input is longer than 255 characters
        		if(search.length() > 255) {
        			model.addAttribute("error", "Search must be less than 256 characters");
        			List<Song> songs = songService.allUserSongs(userId);
                	model.addAttribute("songs", songs);
    	        	// if the user has no songs in their library, create attribute emptylibrary and set to true. In JSP, remove search forms
            		if(songs.size() == 0) {
            			model.addAttribute("emptylibrary", true);
            		}
                	return "home.jsp";
        		}
        		// ...otherwise, perform the search
        		List<Song> songsearch = songService.searchForSong(search);
        		ArrayList<Song> songs = new ArrayList<>();
        		for(int i=0; i<songsearch.size(); i++) {
        			if(songsearch.get(i).getUser().getId() == userId) {
        				songs.add(songsearch.get(i));
        			}
        		}
        		model.addAttribute("songs", songs);
        		if(songs.size() == 0) {
        			model.addAttribute("noresults", "Sorry. We did not find any results from your search. Try again.");
        		}
        		return "home.jsp";
        	// ... if the search by genre form is filled and the search by artist form is not..
        	} else if(search == null && searchgenre != null) {
        		// ...if the search genre input is longer than 255 characters
        		if(searchgenre.length() > 255) {
        			model.addAttribute("error", "Search must be less than 256 characters");
        			List<Song> songs = songService.allUserSongs(userId);
                	model.addAttribute("songs", songs);
    	        	// if the user has no songs in their library, create attribute emptylibrary and set to true. In JSP, remove search forms
            		if(songs.size() == 0) {
            			model.addAttribute("emptylibrary", true);
            		}
                	return "home.jsp";
        		}
        		// ...otherwise, perform the search
        		List<Song> genresearch = songService.searchSongsByGenre(searchgenre);
        		ArrayList<Song> songs = new ArrayList<>();
        		for(int i=0; i<genresearch.size(); i++) {
        			if(genresearch.get(i).getUser().getId() == userId) {
        				songs.add(genresearch.get(i));
        			}
        		}
        		model.addAttribute("songs", songs);
        		if(songs.size() == 0) {
        			model.addAttribute("noresults", "Sorry. We did not find any results from your search. Try again.");
        		}
        		return "home.jsp";
        	// ...if there is no search form submitted, get all of the user's songs...    	
        	} else {
        		List<Song> songs = songService.allUserSongs(userId);
            	model.addAttribute("songs", songs);
	        	// if the user has no songs in their library, create attribute emptylibrary and set to true. In JSP, remove search forms
        		if(songs.size() == 0) {
        			model.addAttribute("emptylibrary", true);
        		}
            	return "home.jsp";
        	}
    	}
    }
    // <---------- DISPLAY EDIT PROFILE PAGE ---------->
    @RequestMapping("/users/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
        // access userId from session
    	Long userId = (Long) session.getAttribute("userId");    	
    	// ...if userId is not in session, REDIRECT to login...
    	if(userId == null) {
    		flash.addFlashAttribute("error", "You must be logged in to view that page");
    		return "redirect:/login";
    	// ...if the user tries to edit a different user...
    	} else if(userId != id) {
    		flash.addFlashAttribute("error", "You are not allowed access to that page");
    		return "redirect:/home";
    	} else {
        	// access the user based on the user id that was saved in session and then add that user data to the model
        	// User u = userService.findUserById(userId);
    		model.addAttribute("user", userService.findUserById(userId));
        	return "editProfile.jsp";
    	}
    }
    // <---------- PROCESS REQUEST TO EDIT USER INFORMATION ---------->
    @RequestMapping("/users/{id}/update")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
        // access userId from session
    	Long userId = (Long) session.getAttribute("userId");
    	// ...if userId is not in session, REDIRECT to login...
    	if(userId == null) {
    		flash.addFlashAttribute("error", "You must be logged in to view that page");
    		return "redirect:/login";
    	// ...if the user tries to edit a song that they did not add themselves...
    	} else if(userId != id) {
    		flash.addFlashAttribute("error", "You are not allowed access to that page");
    		return "redirect:/home";
    	} else {
    		//...otherwise, check to see if the form data is valid...
        	userValidator.validateUpdate(user, result, userService.findUserById(userId));
        	if(result.hasErrors()) {
        		return "editProfile.jsp";
        	//...if the form data is valid, update the user, then redirect to home page
        	} else {
        		userService.update(user);
        		flash.addFlashAttribute("success", "Your changes have been saved");
        	}
        	return "redirect:/home";
    	}
    }
    
    // <---------- DELETE A USER BY ID ---------->
    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
        // access userId from session
    	Long userId = (Long) session.getAttribute("userId");
    	// access user based on path variable id
    	User u = userService.findUserById(id);
    	// if the user is not logged in...
    	if(userId == null) {
    		return "redirect:/login";
    	// ...if that user id does not belong to anyone (does not exist)...
    	} else if(u == null) {
    		return "redirect:/home";
    	// ...if the user tries to delete a different user...
    	} else if(userId != u.getId()) {
    		return "redirect:/home";
    	} else {
        	userService.deleteUser(id);
        	session.invalidate();
        	flash.addFlashAttribute("deletion", "Your account has been deleted.");
        	return "redirect:/";
    	}
    }
    
    // <---------- LOG OUT THE USER BY CLEARING SESSION ---------->
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
}
