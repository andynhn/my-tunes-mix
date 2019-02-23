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
	
	List<Song> findByGenreContaining(String searchgenre);
	
//	@Query(value="SELECT * FROM songs ORDER BY rating DESC LIMIT 10", nativeQuery=true)
//	List<Song> findTopTen();
	
	// Return a list of songs that where uploaded by the logged-in user
	@Query("SELECT s FROM Song s WHERE user_id=?1 ORDER BY rating DESC")
	List<Song> findTopTen(Long userId);
	
	// Return a list of songs that where uploaded by the logged-in user
	@Query("SELECT s FROM Song s WHERE user_id=?1 ORDER BY title ASC")
	List<Song> findUserSongs(Long userId);
	
	// Return a list of songs that where uploaded by the logged-in user
	@Query("SELECT s FROM Song s WHERE user_id!=?1 ORDER BY title ASC")
	List<Song> findOtherSongs(Long userId);
}
