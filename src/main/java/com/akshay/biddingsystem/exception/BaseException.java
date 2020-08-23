package com.akshay.biddingsystem.exception;

/* 
 *  This is BaseException class Which will send following parameters when ever exception is thrown.  
 *  member function get and set values of fields
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	// Type of exception
	private String type;
	//Response is true or false
	private boolean status;
	//StatusCode of response
	private  String statuscode;
	
	private String message;
	
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BaseException() {}
	
	public BaseException(String message){
		this.message = message;	
    }
	
	public BaseException(String statusCode, String message){
		this.statuscode = statusCode;
		this.message = message;
    }
	
	public BaseException(String type, boolean status, String statuscode, String message) {
		super();
		this.type = type;
		this.status = status;
		this.statuscode = statuscode;
		this.message = message;
	}

}
