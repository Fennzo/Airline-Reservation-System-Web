package com.Alex.UserPackage;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/* @Repository to manage all persistence based related methods
 * @Service business logic
 * @Componenet basic bean creation
 * */
@Repository
// Each method works in transaction to interact with the database
// Auto manage each transaction in each method
@Transactional
// Persistance is under hibernate
public class UserDaoService {
	
	//Entities are managed EntityManager using @PersistaenceContext	 
	@PersistenceContext
	private EntityManager em;
	
	public long insert(User user) {
		// Check if record already exists
		// Opne transaction
		em.persist(user);
		// close transaction
		return user.getId();
	}
	
	
}
