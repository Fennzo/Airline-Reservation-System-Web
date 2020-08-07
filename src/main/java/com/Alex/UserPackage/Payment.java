package com.Alex.UserPackage;

import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import com.Alex.Validations.ValidExpiry;

@Entity
@ValidExpiry
public class Payment {

	@Id
	@Pattern(regexp = "[a-zA-Z]+", message = "Enter letters only!")
	private String firstName;
	@Pattern(regexp = "[a-zA-Z]+", message = "Enter letters only!")
	private String lastName;
	@Pattern(regexp = "^[0-9]{16}+$", message = "Enter 16 numbers only!")
	private int ccNumber;
	@Pattern(regexp = "0[1-9]|1[0-2]", message = "Enter 01-12!")
	private String mm;
	@Pattern(regexp = "2[0-9]|", message = "Enter 20-29!")
	private String yy;
	@Pattern(regexp = "^[0-9]{3}+$", message = "Enter 3 digits!")
	private String cvv;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(int ccNumber) {
		this.ccNumber = ccNumber;
	}


	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getYy() {
		return yy;
	}

	public void setYy(String yy) {
		this.yy = yy;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "Payment [firstName=" + firstName + ", lastName=" + lastName + ", ccNumber=" + ccNumber + ", mm=" + mm
				+ ", yy=" + yy + ", cvv=" + cvv + "]";
	}
	
	public boolean checkNull() throws IllegalAccessException {
	    for (Field f : getClass().getDeclaredFields())
	        if (f.get(this) == null)
	            return true;
	    return false;            
	}


}
