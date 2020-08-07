package com.Alex.Flights;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alex.UserPackage.User;
import com.Alex.UserPackage.UserSelection;

@Service
public class FlightsService {

	@Autowired
	private FlightsRepository flightsRep;

	public List<Flights> getAllFlights() {
		return (List<Flights>) flightsRep.findAll();
	}
    
	public String getDuration(Date d1, Date d2) {
		long different = d2.getTime() - d1.getTime();
	    long minutesInMilli = 1000 * 60;
	    long hoursInMilli = minutesInMilli * 60;
	    long elapsedHours = different / hoursInMilli;
	    different = different % hoursInMilli;
	    long elapsedMinutes = different / minutesInMilli;
	    different = different % minutesInMilli;
	    String duration = elapsedHours + "hrs " + elapsedMinutes + "mm";
	    return duration;
	}
	
	public List<Flights> getAllMatchedFlights(UserSelection userSelection) {
		List<Flights> flightsList = new ArrayList<Flights>();
		if (!userSelection.isFlightReturn()) {
			// One way flight
			flightsList = flightsRep.findByDestinationAndOriginAndTakeOffDate(userSelection.getDestination(),
					userSelection.getOrigin(), userSelection.getTakeOffDate());
			System.out.println("ALL FLIGHTS" + flightsList);
		}
		// Return flight
		else {
			List<Flights> secondaryFlights = flightsRep.findByDestinationAndOriginAndTakeOffDate(
					userSelection.getOrigin(), userSelection.getDestination(), userSelection.getReturnDate());
			System.out.println("SECONDARY FLIGHTS" + secondaryFlights);
			List<Flights> primaryFlights = flightsRep.findByDestinationAndOriginAndTakeOffDate(
					userSelection.getDestination(), userSelection.getOrigin(), userSelection.getTakeOffDate());
			System.out.println("Primary FLIGHTS" + primaryFlights);
			for (Flights p : primaryFlights) {
				for (Flights s : secondaryFlights) {
					if (p.getAirline().equals(s.getAirline())) {
						flightsList.add(p);
						flightsList.add(s);
					}
				}
			}

			for (Flights p : primaryFlights) {
				for (Flights s : secondaryFlights) {
					if (!p.getAirline().equals(s.getAirline())) {
						flightsList.add(p);
						flightsList.add(s);
					}
				}
			}

		}
		return flightsList;
	}

	public void addFlightUser(User user, Flights flight) {
          flight.setUser(user);
	}
	
	public void deleteFlightUser(User user, String flightNumber) {
		Flights flight = flightsRep.findByUserAndFlightNumber(user, flightNumber);
		flightsRep.delete(flight);
	}
	
	public List<Flights> retrieveAllFlightsUnderUser(long userId){
		List<Flights>list = new ArrayList<Flights>();
		
		if(flightsRep.findByUserId(userId).isEmpty()) {
			list.add(new Flights());
			return list;
		}
		flightsRep.findByUserId(userId).forEach(list::add);					
		return list;
	}

	public Flights retrieveFlight(String flightNumber) {
		Optional<Flights> flight = flightsRep.findById(flightNumber);
		return flight.get();
	}

	public void updateFlight(Flights flight) {
		flightsRep.save(flight);
	}

	public List<Flights> processReturnFlights(List<Flights> flights) {
		for (int i = 0; i < flights.size() - 1; i++) {
			// $ is special char that requires additional \\
			String temp = flights.get(i).getPrice().replaceAll("\\$", "");
			int price1 = Integer.parseInt(temp);
			flights.get(i).setPrice("");
			String temp1 = flights.get(i + 1).getPrice().replaceAll("\\$", "");
			int price2 = Integer.parseInt(temp1);
			String price = Integer.toString(price1 + price2);
			flights.get(i + 1).setPrice("$" + price);
		}
		return flights;
	}

	public void addFLights(Flights flight) {
		flightsRep.save(flight);
	}

	public void deleteFlight(String flightNumber) {
		flightsRep.deleteById(flightNumber);
	}

	public void updateUser(Flights flight) {
		flightsRep.save(flight);
	}
}
