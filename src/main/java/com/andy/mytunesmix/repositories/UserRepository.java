package com.andy.mytunesmix.repositories;

// By Andy N.H. Nguyen - https://andynhn.me/ - https://github.com/andynhn/

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andy.mytunesmix.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
	User findByUsername(String username);
	void deleteById(Long id);
	
}
