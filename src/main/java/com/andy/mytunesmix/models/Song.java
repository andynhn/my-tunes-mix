package com.andy.mytunesmix.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="songs")
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, message="Please provide a title for the song")
	@Size(max=255, message="Title must be less than 256 characters")
	private String title;
	
	@Size(min=1, message="Please provide an artist for the song")
	@Size(max=255, message="Artist must be less than 256 characters")
	private String artist;
	
	@Size(min=1, message="Please provide a genre for the song")
	@Size(max=255, message="Genre must be less than 256 characters")
	private String genre;
	
	@Min(1)
	@Max(5)
	private Integer rating;
	
	@Size(min=1, message="Please provide the YouTube Video ID")
	@Size(max=255, message="YouTube Video ID must be less than 256 characters")
	private String youtubelink;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	public Song() {
	}

	public Song(Long id,
			@Size(min = 1, message = "Please provide a title for the song") @Size(max = 255, message = "Title must be less than 256 characters") String title,
			@Size(min = 1, message = "Please provide an artist for the song") @Size(max = 255, message = "Artist must be less than 256 characters") String artist,
			@Size(min = 1, message = "Please provide a genre for the song") @Size(max = 255, message = "Genre must be less than 256 characters") String genre,
			@Min(1) @Max(5) Integer rating,
			@Size(min = 1, message = "Please provide the YouTube Video ID") @Size(max = 255, message = "YouTube Video ID must be less than 256 characters") String youtubelink,
			User user, Date createdAt, Date updatedAt) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.rating = rating;
		this.youtubelink = youtubelink;
		this.user = user;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getYoutubelink() {
		return youtubelink;
	}

	public void setYoutubelink(String youtubelink) {
		this.youtubelink = youtubelink;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
}
