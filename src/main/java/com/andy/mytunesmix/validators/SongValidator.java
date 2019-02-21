//package com.andy.mytunesmix.validators;
//
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import com.andy.mytunesmix.models.Song;
//import com.andy.mytunesmix.services.SongService;
//
//@Component
//public class SongValidator implements Validator {
//	private SongService songService;
//	
//	public SongValidator(SongService songService) {
//		this.songService = songService;
//	}
//	
//	@Override
//	public boolean supports(Class<?> clazz) {
//		return Song.class.equals(clazz);
//	}
//	
//	@Override
//	public void validate(Object target, Errors errors) {
//		Song song = (Song) target;
//		
//		// IF A SONG IS RETURNED, THAT YOUTUBE VIDEO EXISTS IN THE DATABASE
//		if(songService.findByYoutubeLink(song.getYoutubelink()) != null) {
//			// ...IF THAT VIDEO WAS UPLOADED BY THE LOGGED IN USER, DISPLAY DUPLICATE VIDEO ERROR...
//			if(songService.findByYoutubeLink(song.getYoutubelink()).getUser().getId() == song.getUser().getId()) {
//				errors.rejectValue("youtubelink",  "DuplicateVideo");
//			}
//		}
//		
//	}
//}