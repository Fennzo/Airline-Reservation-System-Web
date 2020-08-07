package com.Alex.Validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE) 
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface ValidPassword {

	String message() default "Please enter at least 8 characters, 1 uppercase letter, 1 lowercase letter, and 1 special character";
	
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
