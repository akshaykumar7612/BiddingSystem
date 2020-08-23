package com.akshay.biddingsystem.exception;

import org.springframework.http.HttpStatus;

public class TransactionRollbackException extends BaseException {

	private static final long serialVersionUID = 1L;
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
	
	
	public TransactionRollbackException() {}
	
	public TransactionRollbackException(String message) {
		super(message);
	}
	
	public TransactionRollbackException(HttpStatus httpstatus) {
		super();
		this.httpstatus = httpstatus;
	}

	public TransactionRollbackException(String type, boolean status, String statuscode, String message, Object o1) {
		super(type, status, statuscode, message);
		this.o = o1;
		this.httpstatus = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	
	@Override
	public String toString() {
		return "TransactionRollbackException [httpstatus=" + httpstatus + ", o=" + o + "]";
	}
}
