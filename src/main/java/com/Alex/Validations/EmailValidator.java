package com.Alex.Validations;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.Alex.UserPackage.UserService;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	
	@Autowired
	private UserService service;
	
	//Actual place to place the logic to check if the data is valid or not
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email == null) {
		return false;
		}
		
		boolean temp = Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", email);
		if ( service.retrieveAllUsers() != null) {
		return temp && service.searchByEmail(email);
		}
		
		else {
			return temp;
		}
		}
		
	
	
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
	}

}
