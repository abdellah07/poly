package com.polyfrontiere.service.customs;

import java.util.ArrayList;
import java.util.List;

import com.polyfrontiere.data.declaration.domain.Declaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.polyfrontiere.data.customs.IncomingPassenger;

@Service
public class CustomsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomsService.class);
	
	// TODO: change to Queue
	private List<IncomingPassenger> incomingPassengers;
	private List<String> passengerBlacklist;
	
	public CustomsService() {
		incomingPassengers = new ArrayList<>();
		passengerBlacklist = new ArrayList<>();
	}
	
	public List<IncomingPassenger> getIncomingPassengers() {
		return incomingPassengers;
	}
	
	public boolean addIncomingPassenger(IncomingPassenger incomingPassenger) {
		if (passengerBlacklist.contains(incomingPassenger.getDeclaration().getConducteur().getPassport().getPassportId())) incomingPassenger.setBlacklisted(true);
		return incomingPassengers.add(incomingPassenger);
	}
	
	public boolean blacklistPassenger(String passportID) {
		return passengerBlacklist.add(passportID);
	}

	public List<String> getPassengerBlacklist() {
		return passengerBlacklist;
	}	
}
