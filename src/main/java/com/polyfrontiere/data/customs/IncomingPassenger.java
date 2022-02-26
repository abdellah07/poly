package com.polyfrontiere.data.customs;

import com.polyfrontiere.data.declaration.domain.Declaration;

public class IncomingPassenger {
	private Declaration declaration;
	private String validationCode;
	private boolean blacklisted;
	
	public IncomingPassenger(Declaration declaration, String validationCode, boolean blacklisted) {
		this.declaration = declaration;
		this.validationCode = validationCode;
		this.blacklisted = blacklisted;
	}
	
	public Declaration getDeclaration() {
		return declaration;
	}
	
	public String getValidationCode() {
		return validationCode;
	}
	
	public boolean getBlacklisted() {
		return blacklisted;
	}
	
	public void setBlacklisted(boolean blacklisted) {
		this.blacklisted = blacklisted;
	}

	@Override
	public String toString() {
		return "IncomingPassenger [declaration=" + declaration + ", validationCode=" + validationCode + ", blacklisted="
				+ blacklisted + "]";
	}
}
