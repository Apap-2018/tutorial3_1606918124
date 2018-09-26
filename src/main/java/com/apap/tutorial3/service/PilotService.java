package com.apap.tutorial3.service;

import java.util.List;
import com.apap.tutorial3.model.PilotModel;

public interface PilotService {
	void addPilot (PilotModel pilot);
	List<PilotModel> getPilotList();
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	PilotModel updatePilotFlyHourByLicenseNumber(String licenseNumber, Integer flyHour);
	PilotModel getPilotDetailById(String id);
	PilotModel getRemove(PilotModel pilot);
}
