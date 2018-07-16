package com.dna.cparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dna.cparking.exception.ExceptionParking;
import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.service.GatekeeperService;


@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/cparking")
public class GatekeeperController {
	
	@Autowired
	private GatekeeperService gatekeeperService;

	@RequestMapping(value = "/gatekeeper", method = RequestMethod.POST)	
	public ResponseEntity<Object> enterVehicleInParkig(@RequestBody Vehicle vehicle) {
		try {
			gatekeeperService.registerVehicleEntry(vehicle);
		} catch (ExceptionParking e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<>(vehicle, HttpStatus.OK);
	}
}