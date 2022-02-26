package com.polyfrontiere.api.source.declarations.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polyfrontiere.api.source.SourceAPI;
import com.polyfrontiere.api.source.declarations.DeclarationsSourceAPI;
import com.polyfrontiere.data.declaration.domain.Declaration;
import com.polyfrontiere.data.declaration.domain.PassDeclaration;

public class BasicDeclarationsSourceAPI extends SourceAPI implements DeclarationsSourceAPI {
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicDeclarationsSourceAPI.class);	
	private static final String URL = "https://run.mocky.io/v3/cdf76147-b4b2-4718-897f-12c0176e88b2";
	private static final String passURL = "https://run.mocky.io/v3/569548da-b06a-4d20-8546-7d723992ccee";
	public BasicDeclarationsSourceAPI() {
		super(URL,passURL);
	}


	@Override
	public Declaration[] getDeclarationsForNextHours(int nbHours) throws IOException {
		Declaration[] declarations = null;

		declarations = restTemplate.getForObject(URL,Declaration[].class);
		
        if (!(declarations.length > 0)) throw new IOException();
        return declarations;
	}

	@Override
	public PassDeclaration[] getPassportIdsForNextHours(int nbHours) {
		return restTemplate.getForObject(passURL, PassDeclaration[].class);
	}
	
}
