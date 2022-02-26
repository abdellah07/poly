package com.polyfrontiere.api.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.polyfrontiere.data.customs.IncomingPassenger;
import com.polyfrontiere.data.declaration.domain.Declaration;
import com.polyfrontiere.data.declaration.domain.PassDeclaration;
import com.polyfrontiere.data.passport.Passport;
import com.polyfrontiere.service.cache.CacheService;
import com.polyfrontiere.service.customs.CustomsService;
import com.polyfrontiere.service.declaration.DeclarationMapperService;
import com.polyfrontiere.service.passport.PassportInfoService;
@RestController
public class PolyfrontiereController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PolyfrontiereController.class);
	
	@Autowired
	private DeclarationMapperService declarationService;

	@Autowired
	private PassportInfoService passportService;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private CustomsService customsService;
	
	@GetMapping("/")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("This is a Polyfrontiere test");
	}
	
	@RequestMapping(value = "/declarations", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Declaration[]> getDeclarations() {
		Declaration[] declarations = null;
		try {
			declarations = declarationService.getDeclarationsForNextHours(0);
		} catch (Exception e) {
			LOGGER.error("Unable to retrieve declarations");
		}
		return ResponseEntity.ok(declarations);
	}
	
	@RequestMapping(value = "/info_cache", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Passport[]> getInfoCache() {
		Passport[] passports = null;
		try {
			List<Passport> lPassports = cacheService.getPassportInfoCache();
			passports = lPassports.toArray(new Passport[lPassports.size()]);
		} catch (Exception e) {
			LOGGER.error("Unable to retrieve PassportInfo");
		}
		
		return ResponseEntity.ok(passports);
	}


	@RequestMapping(
			value = "/passports/MAR",
			method = RequestMethod.POST)
	public ResponseEntity<Passport[]> passports(@RequestParam(value = "passports") String[] passportDecData) {
		PassDeclaration passportDec[] = new PassDeclaration[1];
		passportDec[0] = new PassDeclaration(passportDecData[0],"MAR");

		LOGGER.info("declaration recu"+passportDec);
		Passport[] passports = null;
		passports = passportService.getPassportInfoForIds(passportDec);
		
		return ResponseEntity.ok(passports);
	}
	
	@RequestMapping(
			value = "/incoming",
			method = RequestMethod.GET)
	public ResponseEntity<List<IncomingPassenger>> getIncomingPassengers() {
		List<IncomingPassenger> incomingPassengers = Collections.emptyList();
		
		try {
			incomingPassengers = customsService.getIncomingPassengers();
		} catch (Exception e) {
			LOGGER.error("Unable to get incoming passengers");
		}
		
		return ResponseEntity.ok(incomingPassengers);
	}	
	
	@RequestMapping(
			value = "/add_incoming", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addToIncomingPassengers(@RequestBody IncomingPassenger incomingPassenger) {
		String response = "Error";
		
		try {
			response = String.valueOf(customsService.addIncomingPassenger(incomingPassenger));
		} catch (Exception e) {
			LOGGER.error("Unable to add incoming passenger: " + incomingPassenger);
		}
		
		return ResponseEntity.ok(response);
	}

	@RequestMapping(
			value = "/blacklist",
			method = RequestMethod.GET)
	public ResponseEntity<List<String>> getBlacklist() {
		List<String> blacklist = Collections.emptyList();
		
		try {
			blacklist = customsService.getPassengerBlacklist();
		} catch (Exception e) {
			LOGGER.error("Unable to get passenger blacklist");
		}
		
		return ResponseEntity.ok(blacklist);
	}
	
	@RequestMapping(
			value = "/add_blacklist/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<String> addToBlacklist(@PathVariable String id) {
		String response = "Error";
		try {
			response = "Passenger of ID: " + id + " was " + (customsService.blacklistPassenger(id) ? "" : "not ") + " added to customs blacklist";
		} catch (Exception e) {
			LOGGER.error("Unable to add passenger of ID: " + id + " to customs blacklist");
		}
		
		return ResponseEntity.ok(response);
	}
}
