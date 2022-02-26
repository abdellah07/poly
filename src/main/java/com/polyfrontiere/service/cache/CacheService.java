package com.polyfrontiere.service.cache;

import java.util.List;

import org.springframework.stereotype.Service;

import com.polyfrontiere.cache.PassportInfoCache;
import com.polyfrontiere.data.passport.Passport;

@Service
public class CacheService {
	private PassportInfoCache passportInfoCache;
	
	public CacheService() {
		passportInfoCache = new PassportInfoCache();
	}
	
	public void updateInfoCache(List<Passport> newCache) {
		passportInfoCache.refresh(newCache);
	}
	
	public List<Passport> getPassportInfoCache(){
		return passportInfoCache.getCache();
	}
}
