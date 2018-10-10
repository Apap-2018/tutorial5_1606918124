package com.apap.tutorial5.service;

import java.util.List;

import com.apap.tutorial5.model.PilotModel;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	void deletePilot(PilotModel pilot);
	void addPilot(PilotModel pilot);
	List<PilotModel> findAll();
}
