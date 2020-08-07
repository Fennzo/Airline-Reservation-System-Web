package com.Alex.Validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.Alex.UserPackage.Payment;


public class ExpiryValidator implements ConstraintValidator<ValidExpiry, Payment> {


	//Actual place to place the logic to check if the data is valid or not
	@Override
	public boolean isValid(Payment payment, ConstraintValidatorContext context) {
		try {
			int mm = Integer.parseInt(payment.getMm());
			if ( payment.checkNull() || (payment.getYy().equals("20") && mm < 9)) {
			return false;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	
		return true;
		}
		
	
	
	@Override
	public void initialize(ValidExpiry constraintAnnotation) {
	}

}
