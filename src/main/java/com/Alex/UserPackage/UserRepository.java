package com.Alex.UserPackage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

	public List<User> findByEmail(String email);
	
}
