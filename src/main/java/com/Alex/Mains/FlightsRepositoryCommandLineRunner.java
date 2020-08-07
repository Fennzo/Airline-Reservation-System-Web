package com.Alex.Mains;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Alex.Flights.Flights;
import com.Alex.Flights.FlightsRepository;
import com.Alex.Flights.FlightsService;

@Component
public class FlightsRepositoryCommandLineRunner implements CommandLineRunner {

	@Autowired
	private FlightsRepository flightsRep;
	
	@Autowired
	private FlightsService service;
	
	@Override
	public void run(String... args) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = df.parse("2020-12-10");
		Date d2 = df.parse("2020-12-11");
		DateFormat dft = new SimpleDateFormat("hh:mm aa");
		Date dd3 = dft.parse("08:45 am");
		Date dd4 = dft.parse("09:45 pm");
		String duration = service.getDuration(dd3, dd4);
		Flights flights = new Flights("SQ404", "SIN", "LAX", dd3, dd4, duration, "Singapore Airlines", "$1050", d1, d2, false);
		flightsRep.save(flights);
		Date dd12 = dft.parse("04:45 am");
		Date dd5 = dft.parse("09:45 pm");
		Date d3 = df.parse("2021-01-01");
		Date d4 = df.parse("2021-01-02");
		String duration1 = service.getDuration(dd3, dd4);
		Flights flights1 = new Flights("UA356", "LAX", "SIN", dd12, dd5, duration1, "United Airlines", "$941", d3, d4, false);
		flightsRep.save(flights1);
		Date dd6 = dft.parse("10:15 am");
		Date dd7 = dft.parse("11:40 pm");
		Date d5 = df.parse("2020-09-15");
		Date d6 = df.parse("2020-09-16");
		String duration2 = service.getDuration(dd3, dd4);
		Flights flights2 = new Flights("EM544", "SIN", "LAX", dd6, dd7, duration2, "Emirates", "$591", d5, d6, false);
		flightsRep.save(flights2);
		Date dd8 = dft.parse("11:55 am");
		Date dd9 = dft.parse("03:25 pm");
		Date d7 = df.parse("2020-12-25");
		Date d8 = df.parse("2020-12-26");
		String duration3 = service.getDuration(dd3, dd4);
		Flights flights3 = new Flights("SQ501", "SIN", "LAX", dd8, dd9, duration3, "Singapore Airlines", "$1203", d7, d8, false);
		flightsRep.save(flights3);
		Date dd10 = dft.parse("11:45 pm");
		Date dd11 = dft.parse("09:25 am");
		Date d9 = df.parse("2020-10-10");
		Date d10 = df.parse("2020-10-11");
		String duration4 = service.getDuration(dd3, dd4);
		Flights flights4 = new Flights("MA403", "LAX", "SIN", dd10, dd11, duration4, "Malaysia Airlines", "$2102", d9, d10, false);
		flightsRep.save(flights4);
		System.out.println("Saved flights:" + flights4);
	}

}
