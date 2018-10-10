package com.apap.tutorial5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDB;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDB;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDB.save(flight);
	}

	@Override
	public void deleteFlightByID(long id) {
		flightDB.deleteById(id);
	}

	@Override
	public FlightModel getFlightByID(long id) {
		return flightDB.getOne(id);
	}
	

	


}
