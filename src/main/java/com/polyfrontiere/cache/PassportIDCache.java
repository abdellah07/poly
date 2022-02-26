package com.polyfrontiere.cache;

import java.util.ArrayList;
import java.util.List;

public class PassportIDCache {
	private List<String> passportIDCache;
	
	public PassportIDCache() {
		passportIDCache = new ArrayList<>();
	}
	
	public PassportIDCache(List<String> newCache) {
		passportIDCache = newCache;
	}
	
	public void clear() {
		passportIDCache.clear();
	}
	
	public void refresh(List<String> listPassports) {
		clear();
		this.passportIDCache.addAll(listPassports);
	}	
	
	public boolean isEmpty() {
		return passportIDCache.isEmpty();
	}
	
	public boolean contains(String passportID) {
		return passportIDCache.contains(passportID);
	}
	
	public List<String> getCache(){
		return passportIDCache;
	}
}
