package com.polyfrontiere.cache;

import java.util.ArrayList;
import java.util.List;

import com.polyfrontiere.data.passport.Passport;

public class PassportInfoCache {
	private List<Passport> passportCache;
	
	public PassportInfoCache() {
		passportCache = new ArrayList<>();
	}
	
	public PassportInfoCache(List<Passport> newCache) {
		passportCache = newCache;
	}
	
	public void clear() {
		passportCache.clear();
	}
	
	public void refresh(List<Passport> listPassports) {
		clear();
		this.passportCache.addAll(listPassports);
	}
	
	public boolean isEmpty() {
		return passportCache.isEmpty();
	}
	
	public boolean contains(Passport passport) {
		return passportCache.contains(passport);
	}
	
	public List<Passport> getCache(){
		return passportCache;
	}
}
