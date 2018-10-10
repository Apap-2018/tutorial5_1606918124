package com.apap.tutorial5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete/{flightID}", method = RequestMethod.GET)
	private String delete(@PathVariable(value = "flightID") long flightID, Model model) {
		flightService.deleteFlightByID(flightID);
		return "deletePilot";
	}
	
	@RequestMapping(value = "/flight/update/{licenseNumber}/{flightID}", method = RequestMethod.GET)
	private String update(@PathVariable(value = "licenseNumber") String licenseNumber, @PathVariable(value = "flightID") long flightID, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		FlightModel flight = flightService.getFlightByID(flightID);
		
		model.addAttribute("pilot", pilot);
		model.addAttribute("flight", flight);
		return "updateFlight";
	}
	
	@RequestMapping(value = "/flight/update", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel flight) {
		flight.setFlightNumber(flight.getFlightNumber());
		flight.setOrigin(flight.getOrigin());
		flight.setDestination(flight.getDestination());
		flight.setTime(flight.getTime());
		flightService.addFlight(flight);
		return "update";
	}
	
	@RequestMapping(value = "/flight/view")
	public String view(@RequestParam("flightNumber") String flightNumber, Model model) {
		ArrayList<FlightModel> allFlightSesuai = new ArrayList<>();
		List<PilotModel> allPilot = pilotService.findAll();
		for (PilotModel pilot:allPilot) {
			for (FlightModel flight:pilot.getPilotFlight()) {
				if (flight.getFlightNumber().equals(flightNumber)) {
					allFlightSesuai.add(flight);
				}
			}
		}
		model.addAttribute("allFlightSesuai", allFlightSesuai);
		return "view-flight";
	}
	
}
