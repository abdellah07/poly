package com.polyfrontiere.data.qrcode;

public class QRCodeValidationSpringResponse {
	private String validationCode;
	private String message;
		
	public QRCodeValidationSpringResponse(String validationCode, String message) {
		this.validationCode = validationCode;
		this.message = message;
	}

	public QRCodeValidationSpringResponse(QRCodeValidationResponse response) {
		this.validationCode = response.getValidationCode();
		this.message = response.getMessage();
	}

	public String getValidationCode() {
		return validationCode;
	}

	public String getMessage() {
		return message;
	}
	
	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "QRCodeValidationSpringResponse [validationCode=" + validationCode + ", message=" + message + "]";
	}	
}
