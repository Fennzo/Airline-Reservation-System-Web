 package com.Alex.Validations;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.Alex.UserPackage.User;

public class PasswordValidator implements ConstraintValidator<ValidPassword, User> {

  
    private String message;
	
	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
		String password = user.getPassword();
		String temp = message;
		String matchingPassword = user.getMatchingPassword();
		if (password== null || matchingPassword == null) {
		return false;
		}
		
		System.out.println("PASSWORDS: " + password + matchingPassword);
		
		boolean flag = Pattern.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", password);
		boolean flag1 = password.equals(matchingPassword);
		
		if ( !flag1 ) {
			message = "Passwords do not match!";
		}
		else {
			message = temp;
		}
		
		 context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate(message)
	                .addPropertyNode("password").addConstraintViolation();
	        
		return flag && flag1;
		
	}
	
	//Show default message if no special message is set
	@Override
	public void initialize(ValidPassword validPassword) {
		
		 message = validPassword.message();
	}

}
