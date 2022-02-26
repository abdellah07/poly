package com.polyfrontiere.service.passport;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polyfrontiere.api.source.passport.PassportSourceAPI;
import com.polyfrontiere.data.declaration.domain.PassDeclaration;
import com.polyfrontiere.data.passport.Passport;

@Service
public class PassportInfoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PassportInfoService.class);
	
	@Autowired
	private PassportSourceAPI passsportSourceApi;
	
	public Passport[] getPassportInfoForIds(PassDeclaration[] ids) {
		// String[] upcomingData = declarationsSourceApi.getPassportIdsForNextHours(nbHours);
		Passport[] passports = null;
		
		try {
			passports = passsportSourceApi.getPassportInfoForIds(ids);
		} 
		catch (IOException | URISyntaxException e) {
			LOGGER.error("getPassportInfoForIds - Unable to retrieve passports for IDs: " + ids + " / Error: " + e.getMessage());
		} 
		
		return passports;
	}
}
