package com.andy.mytunesmix.models;

// By Andy N.H. Nguyen - https://andynhn.me/ - https://github.com/andynhn/

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Email(message="Invalid Email")
	@Size(min=1, message="Please provide a valid email")
	@Size(max=255, message="Email has a 255 character limit")
	private String email;
	
	@Size(min=3, message="Username must be between 3 and 20 characters")
	@Size(max=20, message="Username must be between 3 and 20 characters")
	@Pattern(regexp="^[A-Za-z0-9_]+$", message="Username can only contain letters, numbers, and underscores")
	private String username;
	
	@Size(min=1, message="Please provide your first name")
	@Size(max=255, message="First name has a 255 character limit")
	private String fname;
	
	@Size(min=1, message="Please provide your last name")
	@Size(max=255, message="Last name has a 255 character limit")
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
	// CascadeType.REMOVE will delete all songs associated with a User when that user removes their account
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="user", fetch=FetchType.LAZY)
	private List<Song> songs;
	
	// CONSTRUCTORS
	
	public User() {
	}

	public User(Long id, @Email(message = "Invalid Email") @Size(min = 1, message = "Please provide a valid email") @Size(max = 255, message="Email has a 255 character limit") String email,
			@Size(min = 3, message = "Username must be between 3 and 20 characters") @Size(max = 20, message = "Username must be between 3 and 20 characters") @Pattern(regexp="^[A-Za-z0-9_]+$", message="Username can only contain letters, numbers, and underscores") String username,
			@Size(min = 1, message = "Please provide your first name") @Size(max = 255, message = "First name has a 255 character limit") String fname,
			@Size(min = 1, message = "Please provide your last name") @Size(max = 255, message = "Last name has a 255 character limit") String lname,
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
