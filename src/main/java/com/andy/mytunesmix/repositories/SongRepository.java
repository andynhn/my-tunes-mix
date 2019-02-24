package com.andy.mytunesmix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andy.mytunesmix.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long>{
	
	List<Song> findAll();
	
	void deleteById(Long id);
	
	List<Song> findByArtistContaining(String search);
	
	@Query(value="SELECT * FROM songs WHERE genre=?1 ORDER BY created_at DESC LIMIT 1000", nativeQuery=true)
	List<Song> findByGenreContaining(String searchgenre);
	
	// Return a list of 500 songs not uploaded by the logged-in user
	@Query(value="SELECT * FROM songs WHERE user_id!=?1 ORDER BY created_at DESC LIMIT 500", nativeQuery=true)
	List<Song> discoverSongs(Long userId);
	
	//@Query("SELECT s FROM Song s WHERE user_id=?1 ORDER BY rating DESC")
	@Query(value="SELECT * FROM songs WHERE user_id=?1 ORDER BY rating DESC LIMIT 10", nativeQuery=true)
	List<Song> findTopTen(Long userId);
	
	// Return a list of songs that were uploaded by the logged-in user
	@Query("SELECT s FROM Song s WHERE user_id=?1 ORDER BY title ASC")
	List<Song> findUserSongs(Long userId);
	
	// Return all other songs not uploaded by the logged in user
	@Query("SELECT s FROM Song s WHERE user_id!=?1 ORDER BY title ASC")
	List<Song> findOtherSongs(Long userId);
	
}
