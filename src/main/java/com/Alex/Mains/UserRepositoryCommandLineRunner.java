package com.Alex.Mains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Alex.Security.SecurityConfiguration;
import com.Alex.UserPackage.User;
import com.Alex.UserPackage.UserRepository;

@Component
// Runs after the app has started
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private SecurityConfiguration securityConfig;
	
	@Override
	public void run(String... args) throws Exception {
		// Creates new user upon running the app
		String password = securityConfig.passwordEncoder().encode("jpoo123");
		User user = new User("Alex", "Jin", password, password, "yijian36@gmail.com", "ADMIN");
		User user1 = new User("Alex", "Jin", password, password, "jyj@gmail.com", "USER");
		userRep.save(user1);
		userRep.save(user);
		System.out.println("Saved user:" + user);
	}	

}
