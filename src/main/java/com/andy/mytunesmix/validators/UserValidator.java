package com.andy.mytunesmix.validators;

// By Andy N.H. Nguyen - https://andynhn.me/ - https://github.com/andynhn/

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.andy.mytunesmix.models.User;
import com.andy.mytunesmix.services.UserService;

@Component
public class UserValidator implements Validator {
	private UserService userService;
	
	public UserValidator(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		// CHECK TO SEE IF THE PASSWORD CONFIRMATION MATCHES THE PASSWORD
		if(!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirmation",  "Match");
		}
		// IF A USER IS RETURNED, THAT EMAIL IS ALREADY IN USE. DISPLAY ERROR
		if(userService.findByEmail(user.getEmail().toLowerCase()) != null) {
			errors.rejectValue("email",  "DuplicateEmail");
		}
		// IF A USER IS RETURNED, THAT USERNAME IS ALREADY IN USE. DISPLAY ERROR
		if(userService.findByUsername(user.getUsername().toLowerCase()) != null) {
			errors.rejectValue("username",  "DuplicateUsername");
		}
		// Don't allow certain words as username
		if(user.getUsername().toLowerCase().contains("admin")) {
			errors.rejectValue("username",  "InvalidUsername");
		}
	}
	
	public void validateUpdate(Object target, Errors errors, User user) {
		User submittedUser = (User) target;
		
		// CHECK TO SEE IF THE PASSWORD CONFIRMATION MATCHES THE PASSWORD
		if(!submittedUser.getPasswordConfirmation().equals(submittedUser.getPassword())) {
			errors.rejectValue("passwordConfirmation",  "Match");
		}
		// IF A USER IS RETURNED FROM THE FIRST SEARCH AND THAT USER IS NOT THE LOGGED IN USER, THEN THAT EMAIL IS BEING USED BY SOMEONE ELSE. DISPLAY ERROR
		if(userService.findByEmail(submittedUser.getEmail().toLowerCase()) != null && !submittedUser.getEmail().toLowerCase().equals(user.getEmail())) {
			errors.rejectValue("email",  "DuplicateEmail");
		}
		// IF A USER IS RETURNED FROM THE FIRST SEARCH AND THAT USER IS NOT THE LOGGED IN USER, THEN THAT USERNAME IS BEING USED BY SOMEONE ELSE. DISPLAY ERROR
		if(userService.findByUsername(submittedUser.getUsername().toLowerCase()) != null && !submittedUser.getUsername().toLowerCase().equals(user.getUsername())) {
			errors.rejectValue("username",  "DuplicateUsername");
		}
		// Don't allow certain words as username
		if(submittedUser.getUsername().toLowerCase().contains("admin")) {
			errors.rejectValue("username",  "InvalidUsername");
		}
		
	}
}
