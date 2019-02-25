package com.andy.mytunesmix.validators;

// By Andy N.H. Nguyen - https://andynhn.me/ - https://github.com/andynhn/

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.andy.mytunesmix.models.Song;
import com.andy.mytunesmix.models.User;

@Component
public class SongValidator implements Validator {
	
	public SongValidator() {
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Song.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {

	}
	
	public void validateSong(Object target, Errors errors, User user) {
		Song song = (Song) target;
		// if the submitted form does not include a value for user...
		// OR if the user id submitted does not match the id of the logged in user (through tampering, etc.), display an error
		if(song.getUser() == null || song.getUser().getId() != user.getId()) {
			errors.rejectValue("user", "NoUserData");
		}
	}
}