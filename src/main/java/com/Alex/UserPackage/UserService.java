package com.Alex.UserPackage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRep;
	
	public void addUser(User user) {
		userRep.save(user);
	}
	
	
	public void deleteUser(long userId) {
		userRep.deleteById(userId);
	}
	
	public List<User> retrieveAllUsers(){
		List<User>temp =(List<User>) userRep.findAll();
		return temp;
	}
	
	public User objByEmail(String email) {
		List<User> temp= userRep.findByEmail(email);
		return temp.get(0);
	}
	
	public boolean searchByEmail(String email) {
		List<User> temp= userRep.findByEmail(email);
		if (!temp.isEmpty())
			return false;
		return true;
	}
	
	public void updateUser(User user) {
		userRep.save(user);
	}
}
