package com.polyfrontiere.api.source.passport.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polyfrontiere.api.source.SourceAPI;
import com.polyfrontiere.api.source.passport.PassportSourceAPI;
import com.polyfrontiere.data.declaration.domain.PassDeclaration;
import com.polyfrontiere.data.passport.Passport;

@Component
public class CNILPassportSourceAPI extends SourceAPI implements PassportSourceAPI {
	private static final Logger LOGGER = LoggerFactory.getLogger(CNILPassportSourceAPI.class);
	private static final String URL[] = {"https://run.mocky.io/v3/2c3544bf-6f74-43d7-b576-0d4f3d138141","https://run.mocky.io/v3/1b88516b-c83a-4dc7-8054-1681f69fab19"};

	private static final Map<String,Integer> pays = new HashMap<>();
	
	public CNILPassportSourceAPI() {
		super(URL);
		pays.put("MAR",0);
		pays.put("FRA",1);
	}
	
	private Passport[] getPassports(List<PassDeclaration> passDelList) throws JsonProcessingException {
		LOGGER.info("PASSLIST: " + passDelList);
		String PATH = URL[pays.get(passDelList.get(0).getOrigine())];
		List<String> ids  = new ArrayList<>();
		for (PassDeclaration pasDec : passDelList) {
			ids.add(pasDec.getPassportId());
		}
		ObjectMapper mapper = new ObjectMapper();
		String idsJSON = mapper.writeValueAsString(ids);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(idsJSON,headers);
		LOGGER.info("ids json : " + request.getBody());
		Passport[] passports = restTemplate.postForObject(PATH,request,Passport[].class);
		return passports;
	}
	
	@Override
	public Passport[] getPassportInfoForIds(PassDeclaration[] passportsDec) {
		List<Passport> result = new ArrayList<Passport>();
		Map<String,List<PassDeclaration>> passGrp = new HashMap<>();
		for (PassDeclaration passport : passportsDec){
			if(passGrp.get(passport.getOrigine()) == null){
				passGrp.put(passport.getOrigine(),new ArrayList<>());
			}
			passGrp.get(passport.getOrigine()).add(passport);
		}
		
		for (List<PassDeclaration> value : passGrp.values()) {
			Passport[] fetchPass = new Passport[0];
			try {
				fetchPass = getPassports(value);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			for (Passport pass : fetchPass){
					result.add(pass);
			}
		}
		
		return  result.toArray(new Passport[result.size()]);
	}
}
