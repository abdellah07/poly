package com.polyfrontiere.data.declaration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polyfrontiere.data.declaration.domain.Declaration;
import com.polyfrontiere.data.passport.Passport;

public class Parser {
    private static URL url = null;
    
    public Parser(String strUrl) {
    	try {
        	url = new URL(strUrl);
    	} catch (MalformedURLException mue) {
    		//
    	}
    }

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static List<Declaration> parse() throws IOException {
        List<Declaration> declarations = objectMapper.readValue(url,new TypeReference<List<Declaration>>(){});
        return declarations;
    }
    
    public static Declaration parseQR(String json) throws IOException {
    	Declaration declaration = null;
    	
		json = json.replaceAll("^\"|\"$|\\\\", "");
    	
        try {
			declaration = objectMapper.readValue(json, new TypeReference<Declaration>(){});
		} catch (IOException e) {
			throw e;
		}
    	
    	return declaration;
    }
    
    public static Declaration[] mockParseDec(String json) throws IOException {
    	List<Declaration> declarations = null;
    	
        try {
			declarations = objectMapper.readValue(json.getBytes(), new TypeReference<List<Declaration>>(){});
		} catch (IOException e) {
			throw e;
		}
    	
    	return declarations.toArray(new Declaration[declarations.size()]);
    }
    
    public static Passport[] mockParsePass(String json) throws IOException {
    	List<Passport> passports = null;
    	
   		try {
			passports = objectMapper.readValue(json.getBytes(), new TypeReference<List<Passport>>(){});
		} catch (IOException e) {
			throw e;
		}
    	
    	return passports.toArray(new Passport[passports.size()]);
    }
}
