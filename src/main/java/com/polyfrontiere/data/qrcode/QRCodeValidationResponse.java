package com.polyfrontiere.data.qrcode;

public enum QRCodeValidationResponse {
	OK(0, "Your QR code information was validated"), 
	KO(1, "Your QR code information was not validated"),
	CONTROLLER_ERROR(-1, "There was a controller error"), 
	SERVICE_ERROR(-2, "There was a service error");
	
	private String validationCode;
	private String message;

	QRCodeValidationResponse(int validationCode, String message) {
		this.validationCode = String.valueOf(validationCode);
		this.message = message;
	}

	public String getValidationCode() {
		return validationCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public static QRCodeValidationResponse valueOfCode(int code) {
	    for (QRCodeValidationResponse elem : values()) {
	    	if (elem.validationCode.equals(String.valueOf(code))) {
	    		return elem;
	    	}
	    }
	    
	    return QRCodeValidationResponse.SERVICE_ERROR;
	}
}
