package com.andy.mytunesmix.services;

// By Andy N.H. Nguyen - https://andynhn.me/ - https://github.com/andynhn/

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.andy.mytunesmix.models.User;
import com.andy.mytunesmix.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// <---------- REGISTER USER ---------->
	public User registerUser(User user) {
		// hash their password...
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		// save email, first name, last name, and user name to database as all lower case...
		user.setEmail(user.getEmail().toLowerCase());
		user.setFname(user.getFname().toLowerCase());
		user.setLname(user.getLname().toLowerCase());
		user.setUsername(user.getUsername().toLowerCase());
		return userRepository.save(user);
	}
	
	// <---------- FIND USER BY EMAIL ---------->
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	// <---------- FIND USER BY USERNAME ---------->
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	// <---------- FIND USER BY ID ---------->
	public User findUserById(Long id) {
		// check to see if a user exists with the submitted id
		Optional<User> u = userRepository.findById(id);
		if(u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}
	
	// <---------- AUTHENTICATE USER BEFORE LOGIN ---------->
	public boolean authenticateUser(String email, String password) {
		// first find the user by email
		User user = userRepository.findByEmail(email.toLowerCase());
		// if we can't find it be email, that email does not exist in the database. return false
		if(user == null) {
			return false;
		// if a user is returned with that email
		} else {
			// check if the bcrypt hash of the inputed password matches the bcrypt hashed password saved in the database;
			if(BCrypt.checkpw(password,  user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	// <---------- UPDATE A USER ---------->
	public User update(User user) {
		// hash their password
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		// save email, first name, last name, and user name to database as all lower case...
		user.setEmail(user.getEmail().toLowerCase());
		user.setFname(user.getFname().toLowerCase());
		user.setLname(user.getLname().toLowerCase());
		user.setUsername(user.getUsername().toLowerCase());
		return userRepository.save(user);
	}
	
	// <---------- DELETE A USER ---------->
	public void deleteUser(Long id) {
		// check to see if a user exists with the submitted id
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			// CascadeType.REMOVE in User.java will also delete the songs associated with that user.
			userRepository.deleteById(id);
		} else {
			System.out.println("User does not exist");
		}
	}
}
