package com.polyfrontiere.config;

import com.polyfrontiere.api.source.declarations.impl.BasicDeclarationsSourceAPI;
import com.polyfrontiere.api.source.passport.impl.CNILPassportSourceAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import com.polyfrontiere.api.source.declarations.DeclarationsSourceAPI;
import com.polyfrontiere.api.source.passport.PassportSourceAPI;
import com.polyfrontiere.service.cache.CacheService;
import com.polyfrontiere.service.customs.CustomsService;
import com.polyfrontiere.service.declaration.DeclarationMapperService;

@Configuration
@ComponentScan(basePackages = {"service", "api"})
public class ServiceConfig {
	@Bean
	@Primary
	public DeclarationMapperService getDeclarationMapperService() {
		return new DeclarationMapperService();
	}
	
	@Bean
	@Primary
	public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	@Primary
	public PassportSourceAPI getPassportSourceApi() {
		return new CNILPassportSourceAPI();
	}
	
	@Bean
	@Primary
	public DeclarationsSourceAPI getDeclarationsSourceAPI() {
		return new BasicDeclarationsSourceAPI();
	}
	
	@Bean
	@Primary
	public CacheService getCacheService() {
		return new CacheService();
	}
	
	@Bean
	@Primary
	public CustomsService getCustomsService() {
		return new CustomsService();
	}
}