package com.akshay.biddingsystem.exception;

import org.springframework.http.HttpStatus;

/*  
 * This is ValidationException class Which will extend BaseException
 * member function get and set values of fields
 */
public class ValidationException extends BaseException {
	
	private static final long serialVersionUID = 1L;
	
	//http status code
	private HttpStatus httpstatus;
	private Object o;
	
    public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	public HttpStatus getHttpstatus() {
		return httpstatus;
	}

	public void setHttpstatus(HttpStatus httpstatus) {
		this.httpstatus = httpstatus;
	}
	
	public ValidationException() {}
	
	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(HttpStatus httpstatus) {
		super();
		this.httpstatus = httpstatus;
	}

	public ValidationException(String type, boolean status, String statuscode, String message,Object o1) {
		super(type, status, statuscode, message);
		this.o = o1;
		this.httpstatus = HttpStatus.BAD_REQUEST;
	}

}