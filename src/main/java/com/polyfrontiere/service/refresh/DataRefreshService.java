package com.polyfrontiere.service.refresh;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.polyfrontiere.data.declaration.domain.Declaration;
import com.polyfrontiere.data.declaration.domain.PassDeclaration;
import com.polyfrontiere.data.passport.Passport;
import com.polyfrontiere.data.mail.EmailSender;
import com.polyfrontiere.data.qrcode.QrCode;
import com.polyfrontiere.service.cache.CacheService;
import com.polyfrontiere.service.declaration.DeclarationMapperService;
import com.polyfrontiere.service.forward.PostForwardService;
import com.polyfrontiere.service.passport.PassportInfoService;
import com.polyfrontiere.data.utils.ParserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataRefreshService {	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataRefreshService.class);
	
	@Autowired
	private DeclarationMapperService declarationsService;
	
	@Autowired
	private PassportInfoService passportService;
	
	@Autowired
	private CacheService cacheService;

	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private PostForwardService forwardService;


	public void refreshData(int delay) {
		Declaration[] declarations = null;
		
		try {
			declarations = declarationsService.getDeclarationsForNextHours(0);
		} catch (Exception e) {
			LOGGER.info("refreshData - Unable to retrieve ");
		}
		
		// IDs de passeports
		// String[] passportIDs = new String[] {"1"};
		PassDeclaration[] passDeclarations = ParserUtils.getPassportIDsFromDeclarations(declarations);
		
		// Détails de passeports
		Passport[] passports = passportService.getPassportInfoForIds(passDeclarations);
		
		// generateAndSendQRCode(Arrays.asList(declarations));
		
		// Envoi des données au poste de contrôle pour caching
		cacheService.updateInfoCache(Arrays.asList(passports));
		
		// Caching détails de passeports
		List<String> passID = new ArrayList<>();
		for (PassDeclaration passDec: passDeclarations) {
			passID.add(passDec.getPassportId());
		}
		try {
			forwardService.forwardIDCache(passID);
		} catch (RestClientException | URISyntaxException e) {
			LOGGER.error("Error forwarding data to control post : " + e.getMessage());
		}
	}
	
	private void generateAndSendQRCode(List<Declaration> declarations) {
		for (Declaration declaration : declarations) {
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				String json = objectMapper.writeValueAsString(declaration);
				QrCode qrCode = new QrCode(json);
				File file = qrCode.saveImage(declaration.getConducteur().getPassport().getPassportId());
				LOGGER.info(declaration.getConducteur().getMail());
				emailSender.sendEmail(declaration,file);
			} 
			catch (JsonProcessingException e) {
				LOGGER.error("refreshData - Can't parse declaration to json string : " + e.getMessage());
			} 
			catch (Exception e) {
				LOGGER.error("refreshData - Can't store declaration : " + e.getMessage());
			}
		}
	}
}