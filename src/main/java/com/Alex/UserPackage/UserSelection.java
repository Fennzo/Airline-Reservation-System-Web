package com.Alex.UserPackage;

import java.util.Date;

import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;

public class UserSelection {

	String origin;
	String destination;
	@Future
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date takeOffDate;
	@Future
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date returnDate;
	boolean flightReturn;
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
	public Date getTakeOffDate() {
		return takeOffDate;
	}
	public void setTakeOffDate(Date takeOffDate) {
		this.takeOffDate = takeOffDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public boolean isFlightReturn() {
		return flightReturn;
	}
	public void setFlightReturn(boolean flightReturn) {
		this.flightReturn = flightReturn;
	}
	public UserSelection(String origin, String destination, Date takeOffDate, Date returnDate, boolean flightReturn) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.takeOffDate = takeOffDate;
		this.returnDate = returnDate;
		this.flightReturn = flightReturn;
	}
	
	@Override
	public String toString() {
		return "UserSelection [origin=" + origin + ", destination=" + destination + ", takeOffDate=" + takeOffDate
				+ ", returnDate=" + returnDate + ", flightReturn=" + flightReturn + "]";
	}
	protected UserSelection() {
		
	}
}
