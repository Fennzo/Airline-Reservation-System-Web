package com.Alex.Validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//Define where is the annotation used, type = class, field = field, method etc
@Target(ElementType.FIELD) 
//When will the annotation be processed compilation, runtime etc
@Retention(RetentionPolicy.RUNTIME)
//Where is the logic
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
	
	//Error message
	String message() default "Invalid email";
	//Required for annotation
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
}
