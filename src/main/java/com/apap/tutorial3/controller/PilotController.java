package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;

	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value= "licenseNumber", required = true) String licenseNumber,
			@RequestParam(value= "name", required = true) String name,
			@RequestParam(value= "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}

	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);

		model.addAttribute("pilot", archive);
		return "view-pilot";
	}

	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();

		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}

	@RequestMapping(value = {"/pilot/view/license-number", "/pilot/view/license-number/{licenseNumber}"})
	public String viewPath(@PathVariable Optional<String> licenseNumber, Model model) {
		if (licenseNumber.isPresent() && pilotService.getPilotDetailByLicenseNumber(licenseNumber.get()) != null) {
			PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber.get());
			
			model.addAttribute("pilot", pilot);
			return "view-pilot";
		}
		else {
			return "viewpath-pilot-error";
		}
	}
	
	@RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}​")
	public String updatePath(@PathVariable Optional<String> licenseNumber, @PathVariable Integer flyHour, Model model) {
		if (licenseNumber.isPresent() && pilotService.getPilotDetailByLicenseNumber(licenseNumber.get()) != null) {
			PilotModel pilot = pilotService.updatePilotFlyHourByLicenseNumber(licenseNumber.get(), flyHour);
			model.addAttribute("flyHour", pilot.getFlyHour());
			return "update-FlyHour";
		}
		else {
			return "viewpath-pilot-error";
		}
	}
	
	@RequestMapping("/pilot/delete/id/{id}")
	public String deletePath(@PathVariable String id, Model model) {
		if (pilotService.getPilotDetailById(id) != null) {
			PilotModel pilot = pilotService.getPilotDetailById(id);
			
			model.addAttribute("pilot", pilot);
			return "viewID-pilot";
		}
		else {
			return "viewpath-pilot-error";
		}
	}
	
}
