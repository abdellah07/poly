package com.polyfrontiere.service.declaration;

import com.polyfrontiere.api.source.declarations.DeclarationsSourceAPI;
import com.polyfrontiere.data.declaration.domain.Declaration;
import com.polyfrontiere.data.declaration.domain.PassDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeclarationMapperService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeclarationMapperService.class);

	@Autowired
	private DeclarationsSourceAPI sourceApi;
	
	public Declaration[] getDeclarationsForNextHours(int nbHours) throws Exception {
		Declaration[] declarations = null;
		
		try {
			declarations = sourceApi.getDeclarationsForNextHours(nbHours);
		} catch (Exception e) {
			LOGGER.error("DeclarationInfoService - " + e.getMessage());
		}
		
		return declarations;
	}

	public PassDeclaration[] getPassportIdsForNextHours(int hours){
		return sourceApi.getPassportIdsForNextHours(hours);
	}
}
