package com.akshay.biddingsystem.dto;

public class ValidationMessageDto {

	private String field;
	private String errorMessage;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ValidationMessageDto() {
	}

	public ValidationMessageDto(String field, String errorMessage) {
		super();
		this.field = field;
		this.errorMessage = errorMessage;
	}

}
