package com.Alex.Flights;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.Alex.UserPackage.User;

@Entity
public class Flights {

	@Id
	String flightNumber;
	@Pattern(regexp = "[a-zA-Z]+", message = "Enter letters only!")
	String origin;
	@Pattern(regexp = "[a-zA-Z]+", message = "Enter letters only!")
	String destination;
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	Date takeOffTime;
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	Date landingTime;
	String flightDuration;
	@Pattern(regexp = "[a-zA-Z][a-zA-Z ]++", message = "Enter letters only!")
	String airline;
	String price;

	// Many flights to one user
	@ManyToOne
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date takeOffDate;
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date landingDate;
	boolean flightReturn;

	public boolean isFlightReturn() {
		return flightReturn;
	}

	public void setFlightReturn(boolean flightReturn) {
		this.flightReturn = flightReturn;
	}

	public Date getLandingDate() {
		return landingDate;
	}

	public void setLandingDate(Date landingDate) {
		this.landingDate = landingDate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getTakeOffTime() {
		return takeOffTime;
	}

	public void setTakeOffTime(Date takeOffTime) {
		this.takeOffTime = takeOffTime;
	}

	public Date getLandingTime() {
		return landingTime;
	}

	public void setLandingTime(Date landingTime) {
		this.landingTime = landingTime;
	}

	public String getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(String flightDuration) {
		this.flightDuration = flightDuration;
	}

	public Date getTakeOffDate() {
		return takeOffDate;
	}

	public void setTakeOffDate(Date takeOffDate) {
		this.takeOffDate = takeOffDate;
	}

	public Flights() {

	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Flights(String flightNumber, String origin, String destination, Date takeOffTime, Date landingTime,
			String flightDuration, String airline, String price, Date takeOffDate, Date landingDate,
			boolean flightReturn) {
		super();
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.takeOffTime = takeOffTime;
		this.landingTime = landingTime;
		this.flightDuration = flightDuration;
		this.airline = airline;
		this.price = price;
		this.takeOffDate = takeOffDate;
		this.landingDate = landingDate;
		this.flightReturn = flightReturn;
	}

	@Override
	public String toString() {
		return "Flights [flightNumber=" + flightNumber + ", origin=" + origin + ", destination=" + destination
				+ ", takeOffTime=" + takeOffTime + ", landingTime=" + landingTime + ", flightDuration=" + flightDuration
				+ ", airline=" + airline + ", price=" + price + ", takeOffDate=" + takeOffDate + ", landingDate="
				+ landingDate + ", flightReturn=" + flightReturn + "]";
	}

}
