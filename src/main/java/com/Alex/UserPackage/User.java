package com.Alex.UserPackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;


import com.Alex.Validations.ValidEmail;
import com.Alex.Validations.ValidPassword;

// Mark as table
@Entity
@ValidPassword
public class User {
	@Pattern(regexp="[a-zA-Z]+", message = "Enter letters only!")
	private String firstName;
	@Pattern(regexp="[a-zA-Z]+", message = "Enter letters only!")
	private String lastName;
	private String password;
	private String matchingPassword;
	private String passportNumber;
	private String address;
	@Pattern(regexp="^[\\d+]+$", message = "Enter numbers and country code only!")
	private String phoneNumber;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ValidEmail
	private String email;

	// Mark as primary key
	@Id
	// Will be auto generated
	@GeneratedValue
	private long id;
	private String role;

	public User(String firstName, String lastName, String password, String passportNumber, String address,
			String phoneNumber, String email, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.passportNumber = passportNumber;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", matchingPassword=" + matchingPassword + ", passportNumber=" + passportNumber + ", address="
				+ address + ", phoneNumber=" + phoneNumber + ", email=" + email + ", id=" + id + ", role=" + role + "]";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

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

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User() {

	}

	public User(String firstName, String lastName, String password, String matchingPassword, String email,
			String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.matchingPassword = matchingPassword;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public long getId() {
		return id;
	}

}
