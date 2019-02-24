package com.andy.mytunesmix.validators;

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
		if(userService.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email",  "DuplicateEmail");
		}
		// IF A USER IS RETURNED, THAT USERNAME IS ALREADY IN USE. DISPLAY ERROR
		if(userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username",  "DuplicateUsername");
		}
	}
	
	public void validateUpdate(Object target, Errors errors, User user) {
		User submittedUser = (User) target;
		
		// CHECK TO SEE IF THE PASSWORD CONFIRMATION MATCHES THE PASSWORD
		if(!submittedUser.getPasswordConfirmation().equals(submittedUser.getPassword())) {
			errors.rejectValue("passwordConfirmation",  "Match");
		}
		// IF A USER IS RETURNED FROM THE FIRST SEARCH AND THAT USER IS NOT THE LOGGED IN USER, THEN THAT EMAIL IS BEING USED BY SOMEONE ELSE. DISPLAY ERROR
		if(userService.findByEmail(submittedUser.getEmail()) != null && !submittedUser.getEmail().equals(user.getEmail())) {
			errors.rejectValue("email",  "DuplicateEmail");
		}
		// IF A USER IS RETURNED FROM THE FIRST SEARCH AND THAT USER IS NOT THE LOGGED IN USER, THEN THAT USERNAME IS BEING USED BY SOMEONE ELSE. DISPLAY ERROR
		if(userService.findByUsername(submittedUser.getUsername()) != null && !submittedUser.getUsername().equals(user.getUsername())) {
			errors.rejectValue("username",  "DuplicateUsername");
		}
	}
}
