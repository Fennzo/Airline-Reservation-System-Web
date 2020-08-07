package com.Alex.Flights;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

import com.Alex.UserPackage.User;
import com.Alex.UserPackage.UserSelection;

@Controller
public class FlightsController {

	@Autowired
	private FlightsService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndexForm(ModelMap model) {
		model.addAttribute("userForm", new UserSelection("", "", new Date(), null, true));
		return "index";
	}
	
	@RequestMapping(value = "/test")
	public String testing(ModelMap model) {
		System.out.println("ALL FLIGHTS" + service.getAllFlights());
		model.addAttribute("flightlist", service.getAllFlights());
		return "flights-listOneWay";
	}
	
	@RequestMapping(value = "/addUserFlight", method = RequestMethod.GET)
	public String addUserFlight(ModelMap model, @RequestParam(value = "flightnum", required = true) String flightnum, HttpSession httpSession) {
		Flights flight = service.retrieveFlight(flightnum);
		if ( httpSession.getAttribute("user") != null) {
			User user = (User) httpSession.getAttribute("user");
			service.addFlightUser(user, flight);
			model.addAttribute("bookFlightUserDetails", user);
		}
		User tempUser = new User();
		httpSession.setAttribute("tempUser", tempUser);
		model.addAttribute("flightBook", tempUser);
		return "userDetailsForFlight";
		
	}
	
	@RequestMapping(value = "/addUserFlight", method = RequestMethod.POST)
	public String submitFlightDetails(ModelMap model, @Valid @ModelAttribute("flightBook") User userFlightDetails, BindingResult result, HttpSession httpSession) {
		if ( result.hasErrors()) {
			System.out.println("ERRORS" + result.getFieldErrors());
			return "userDetailsForFlight";
		}
		
		httpSession.setAttribute("flightUserDetails", userFlightDetails);
		model.addAttribute("confirmUserDetails", userFlightDetails);
		List<String>mmList = null;
		mmList.addAll(Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"));
		model.addAttribute("mmList", mmList);
		List<String>yyList = null;
		yyList.addAll(Arrays.asList("20", "21", "22", "23", "24", "25", "26"));
		model.addAttribute("yyList", yyList);
		return "paymentPage";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitSearchForm(ModelMap model, @Valid @ModelAttribute("userForm") UserSelection userSelection, BindingResult result, HttpSession httpSession) {
		if ( result.hasErrors()) {
			System.out.println("ERRORS" + result.getFieldErrors());
			return "redirect:/";
		}
		System.out.println("Search form submited:" + userSelection.toString());
		httpSession.setAttribute("userSelection", userSelection);
		if (userSelection.isFlightReturn()) {
			model.clear();
			return "redirect:/showReturnFlights";
		}
		else {
			model.clear();
			return "redirect:/showOneWayFlights";
		}
	}
	
	@RequestMapping(value = "/showReturnFlights")
	public String showReturnFlights(ModelMap model, HttpSession httpSession) {
		UserSelection userSelection =  (UserSelection) httpSession.getAttribute("userSelection");
		List<Flights>flights = service.getAllMatchedFlights(userSelection);
		flights = service.processReturnFlights(flights);
		model.addAttribute("flightlist", flights);
		return "flights-listReturn";
	}
	
	@RequestMapping(value = "/showOneWayFlights")
	public String showOneWayFlights(ModelMap model, HttpSession httpSession) {
		UserSelection userSelection =  (UserSelection) httpSession.getAttribute("userSelection");
		model.addAttribute("flightlist", service.getAllMatchedFlights(userSelection));
		return "flights-listOneWay";
	}
	
}
