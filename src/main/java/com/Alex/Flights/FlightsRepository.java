package com.Alex.Flights;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Alex.UserPackage.User;

public interface FlightsRepository extends CrudRepository<Flights, String> {
		
		public List<Flights> findByDestinationAndOriginAndTakeOffDate(String destination, String origin, Date takeOffDate);
		public List<Flights> findByUserId(long id);	
		public Flights findByUserAndFlightNumber(User user, String flightNumber);
}
