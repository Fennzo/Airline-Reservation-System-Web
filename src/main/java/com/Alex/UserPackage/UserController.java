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

import com.Alex.Flights.FlightsService;
import com.Alex.Security.SecurityConfiguration;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityConfiguration securityConfig;
	
	@Autowired
	private FlightsService flightsService;
	
	@RequestMapping( value = "/registration", method = RequestMethod.GET)
	public String showRegisterUserForm(ModelMap model) {
		model.addAttribute("userRegistration", new User());
		return "registration";
	}
	
	@RequestMapping( value = "/registration", method = RequestMethod.POST)
	public String submitRegisterUserForm(@ModelAttribute("userRegistration") @Valid User user, BindingResult result, ModelMap model, HttpSession httpSession) {
		if ( result.hasFieldErrors()) {
			System.out.println("User registration error: " + result.getFieldError() + result.getErrorCount() + result.getFieldErrors());
			return "registration";
		}
		user.setRole("USER");
		httpSession.setAttribute("user", user);
		user.setMatchingPassword(securityConfig.passwordEncoder().encode(user.getMatchingPassword()));
		user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
		userService.addUser(user);
		System.out.println("Saved user: " + user);
		model.clear();
		return "redirect:/login";
	}
	
	@RequestMapping( value = "/user/allflights")
	public String retrieveAllFlightForUser(ModelMap model, HttpSession httpSession) {
		System.out.println("Session names: " + httpSession.getAttributeNames().toString());
		User user = (User) httpSession.getAttribute("user");
		System.out.println(user.toString());
		model.addAttribute("flightlist", flightsService.retrieveAllFlightsUnderUser(user.getId()));
		return "userAllFlights";
	}
	
	@RequestMapping(value = "/user/allflights/deleteflights")
	public String deleteFlight(ModelMap model, @RequestParam(value = "flightnumber", required =true) String flightnumber, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		flightsService.deleteFlightUser(user, flightnumber);
		model.clear();
		return "userAllFlights";
	}
	
	@RequestMapping( value = "/user/welcome")
	public String userWelcome(Principal principal, HttpSession httpSession) {
		String emailUser = principal.getName();
		System.out.println("Logged in user " + emailUser);
		httpSession.setAttribute("user", userService.objByEmail(emailUser));
		return "UserWelcome";
	}
	
	@RequestMapping( value = "/user/profile/update", method = RequestMethod.GET)
	public String userProfileUpdate(ModelMap model, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		model.addAttribute("userInfo", user);
		return "userProfileUpdate";
	}
	
	@RequestMapping( value = "/user/profile/update", method = RequestMethod.POST)
	public String userProfileUpdateSubmit(@ModelAttribute("userInfo") @Valid User user, BindingResult result, ModelMap model, HttpSession httpSession) {
		if ( result.hasErrors()) {
			System.out.println("User profile update error: " + result.getFieldError());
			return "userProfileUpdate";
		}
		httpSession.removeAttribute("user");
		httpSession.setAttribute("user", user);
		model.clear();
		return "userProfile";
	}
	
	@RequestMapping( value = "/user/profile")
	public String showUserProfile(ModelMap model, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		model.addAttribute("userInfo", user);
		return "userProfile";
	}
	

	
	
	
}
