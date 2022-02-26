package com.polyfrontiere.api.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * MAROC 0
 * FRANCE 1
 */
public abstract class SourceAPI {
	private String[] sourcePath;

	@Autowired
	protected RestTemplate restTemplate;
	
	public SourceAPI() {
		// 
	}
	
	protected SourceAPI(String ... sourcePath) {
		this.sourcePath = sourcePath;
	}
	
	public String[] getSourcePath() {
		return sourcePath;
	}
	
	public RestTemplate getRestTemplate() {
	    return restTemplate;
	}
	
	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
	   this.restTemplate = restTemplate;
	}
}
