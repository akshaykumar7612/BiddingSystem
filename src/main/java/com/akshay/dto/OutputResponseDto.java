package com.akshay.dto;

public class OutputResponseDto {

	private boolean success;
	private Object data;
	private String message;
	private String statusCode;
	private String traceId;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean status) {
		this.success = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public OutputResponseDto() {
	}

	public OutputResponseDto(boolean success, Object data, String statusCode, String message, String traceId) {
		super();
		this.success = success;
		this.data = data;
		this.message = message;
		this.statusCode = statusCode;
		this.traceId = traceId;
	}

	@Override
	public String toString() {
		return "OutputResponseDto [success=" + success + ", data=" + data + ", message=" + message + ", statusCode="
				+ statusCode + ", traceId=" + traceId + "]";
	}

}

