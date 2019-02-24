package com.andy.mytunesmix.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andy.mytunesmix.models.Song;
import com.andy.mytunesmix.repositories.SongRepository;

@Service
public class SongService {
	
	private final SongRepository songRepository;
	
	public SongService(SongRepository songRepository) {
		this.songRepository = songRepository;
	}
	
	// <---------- GET ALL SONGS ---------->
	public List<Song> allSongs() {
		return songRepository.findAll();
	}
	
	// <---------- GET ALL SONGS FOR THE LOGGED IN USER ---------->
	public List<Song> allUserSongs(Long userId) {
		return songRepository.findUserSongs(userId);
	}
	
	// <---------- GET ALL OTHER SONGS FOR DISCOVER PAGE ---------->
	public List<Song> allOtherSongs(Long userId) {
		return songRepository.findOtherSongs(userId);
	}
	
	// <---------- ADD A SONG ---------->
	public Song createSong(Song s) {
		// before saving into database, convert title, artist, and genre to lower case
		s.setTitle(s.getTitle().toLowerCase());
		s.setArtist(s.getArtist().toLowerCase());
		s.setGenre(s.getGenre().toLowerCase());
		return songRepository.save(s);
	}
	
	// <---------- FIND A SONG BY ID ---------->
	public Song findSong(Long id) {
		Optional<Song> optionalSong = songRepository.findById(id);
		if(optionalSong.isPresent()) {
			return optionalSong.get();
		} else {
			return null;
		}
	}
	
	// <---------- UPDATE A SONG ---------->
	public Song update(Song song) {
		// before saving into database, convert title, artist, and genre to lower case
		song.setTitle(song.getTitle().toLowerCase());
		song.setArtist(song.getArtist().toLowerCase());
		song.setGenre(song.getGenre().toLowerCase());
		return songRepository.save(song);
	}
	
	// <---------- DELETE A SONG ---------->
	public void deleteSong(Long id) {
		Optional<Song> optionalSong = songRepository.findById(id);
		if(optionalSong.isPresent()) {
			songRepository.deleteById(id);
		} else {
			System.out.println("Song does not exist.");
		}
	}
	
	// <---------- SEARCH FOR SONGS BY ARTIST ---------->
	public List<Song> searchForSong(String search) {
		return songRepository.findByArtistContaining(search.toLowerCase());
	}
	
	// <---------- SEARCH FOR SONGS BY GENRE ---------->
	public List<Song> searchSongsByGenre(String search) {
		return songRepository.findByGenreContaining(search.toLowerCase());
	}
	
	// <---------- FIND THE TOP TEN SONGS ---------->
	public List<Song> findTopTen(Long userId) {
		return songRepository.findTopTen(userId);
	}
	// <---------- DISCOVER SONGS ---------->
	public List<Song> discoverSongs(Long userId) {
		return songRepository.discoverSongs(userId);
	}
}
