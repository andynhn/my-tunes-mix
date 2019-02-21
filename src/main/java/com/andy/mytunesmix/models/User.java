package com.andy.mytunesmix.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Email(message="Invalid Email")
	@Size(min=1, message="Please provide a valid email")
	private String email;
	
	@Size(min=3, message="Username must be 3 characters or longer")
	@Size(max=255, message="Username must be less than 256 characters")
	private String username;
	
	@Size(min=2, message="First name must be 2 characters or longer")
	@Size(max=255, message="First name must be less than 256 characters")
	private String fname;
	
	@Size(min=2, message="Last name must be 2 characters or longer")
	@Size(max=255, message="Last name must be less than 256 characters")
	private String lname;
	
	@Size(min=8, message="Password must be 8 characters or longer")
	@Size(max=255, message="Password must be less than 256 characters")
	private String password;
	
	@Transient
	// uses a custom validation in the UserValidator
	private String passwordConfirmation;
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	// One User to Many Songs
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Song> songs;
	
	// CONSTRUCTORS
	
	public User() {
	}

	public User(Long id, @Email(message = "Invalid Email") 	@Size(min=1, message="Please provide a valid email") String email,
			@Size(min = 3, message = "Username must be 3 characters or longer") @Size(max = 255, message = "Username must be less than 256 characters") String username,
			@Size(min = 2, message = "First name must be 2 characters or longer") @Size(max = 255, message = "First name must be less than 256 characters") String fname,
			@Size(min = 2, message = "Last name must be 2 characters or longer") @Size(max = 255, message = "Last name must be less than 256 characters") String lname,
			@Size(min = 8, message = "Password must be 8 characters or longer") @Size(max = 255, message = "Password must be less than 256 characters") String password,
			String passwordConfirmation, Date createdAt, Date updatedAt, List<Song> songs) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.songs = songs;
	}
	
	// GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
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
