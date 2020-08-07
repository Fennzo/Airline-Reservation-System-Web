package com.Alex.UserPackage;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Alex.Flights.Flights;
import com.Alex.Flights.FlightsService;

@Controller
public class AdminController {

	@Autowired
	private FlightsService flightsService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/admin/allflights/addflight", method = RequestMethod.GET)
	public String adminAddFlight(ModelMap model) {
		model.addAttribute("userRegistration", new Flights());
		return "addFlight";
	}

	@RequestMapping(value = "/admin/allflights/addFlight", method = RequestMethod.POST)
	public String submitFlightAddition(@ModelAttribute("newFlight") @Valid Flights flight, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			System.out.println("Admin add flight error: " + result.getFieldError());
			return "addFlight";
		}
		flight.setPrice("$" + flight.getPrice());
		flightsService.addFLights(flight);
		model.clear();
		return "redirect:admin/allflights";
	}

	@RequestMapping(value = "/admin/allflights/updateFlight", method = RequestMethod.GET)
	public String adminUpdateFlight(ModelMap model,
			@RequestParam(value = "flightnumber", required = true) String flightnumber) {
		model.addAttribute("updateFlightObj", flightsService.retrieveFlight(flightnumber));
		return "adminUpdateFlight";
	}

	@RequestMapping(value = "/admin/allflights/updateflight", method = RequestMethod.POST)
	public String adminSubmitUpdateFlight(@ModelAttribute("updateFlightObj") @Valid Flights flight,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			System.out.println("Admin update flight error: " + result.getFieldError());
			return "redirect:/admin/allflights?flightnumber=" + flight.getFlightNumber();
		}
		if (!flight.getPrice().contains("$")) {
			flight.setPrice("$" + flight.getPrice());
		}
		String duration = flightsService.getDuration(flight.getTakeOffTime(), flight.getLandingTime());
		flight.setFlightDuration(duration);
		flightsService.updateFlight(flight);
		model.clear();
		return "redirect:/admin/allflights";
	}

	@RequestMapping(value = "/admin/allusers")
	public String adminDisplayAllUsers(ModelMap model) {
		model.addAttribute("userList", userService.retrieveAllUsers());
		return "adminAllUsers";
	}
	
	@RequestMapping(value = "/admin/allusers/flight")
	public String adminUserMoreInfo(ModelMap model, @RequestParam(value = "userId", required = true) long userId) {

		model.addAttribute("flightListForUser", flightsService.retrieveAllFlightsUnderUser(userId));
		return "adminUserFlight";
	}

	// Show all flights for admin
	@RequestMapping(value = "/admin/allflights")
	public String adminShowAllFlights(ModelMap model) {
		System.out.println("Adding flights from admin panel");
		model.addAttribute("flightlist", flightsService.getAllFlights());
		return "adminAllFlights";
	}

	// Delete flights
	@RequestMapping(value = "/admin/allflights/deleteFlight")
	public String deleteFlight(ModelMap model,
			@RequestParam(value = "flightnumber", required = true) String flightnumber) {
		flightsService.deleteFlight(flightnumber);
		model.clear();
		return "redirect:/admin/allflights";
	}

	@RequestMapping(value = "/admin/welcome")
	public String adminProfile(Principal principal, HttpSession httpSession) {
		System.out.println("Logged in admin " + principal.getName());
		httpSession.setAttribute("adminLogged", principal.getName());
		return "adminWelcome";
	}

}
