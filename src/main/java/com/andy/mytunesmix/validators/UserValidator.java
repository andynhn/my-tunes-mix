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
}
