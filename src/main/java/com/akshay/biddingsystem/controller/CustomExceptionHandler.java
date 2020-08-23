package com.akshay.biddingsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.biddingsystem.dto.OutputResponseDto;
import com.akshay.biddingsystem.exception.TransactionRollbackException;
import com.akshay.biddingsystem.exception.ValidationException;
import com.akshay.biddingsystem.util.Context;
import com.akshay.biddingsystem.util.Messages;

@RestController
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Context context;

	@Autowired
	Messages messages;

	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<OutputResponseDto> handleValidationException(ValidationException e) {
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(false);
		outputResponseDto.setData(null);
		outputResponseDto.setTraceId(context.getTraceId());
		outputResponseDto.setStatusCode(e.getStatuscode());
		outputResponseDto.setMessage(e.getO().toString());
		return new ResponseEntity<>(outputResponseDto, e.getHttpstatus());
	}
	
	@ExceptionHandler(value = TransactionRollbackException.class)
	public ResponseEntity<OutputResponseDto> handleTransactionRollbackException(TransactionRollbackException e) {
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(false);
		outputResponseDto.setData(null);
		outputResponseDto.setTraceId(context.getTraceId());
		outputResponseDto.setStatusCode(e.getStatuscode());
		outputResponseDto.setMessage(e.getO().toString());
		return new ResponseEntity<>(outputResponseDto, e.getHttpstatus());
	}
	
	@ExceptionHandler(value = MissingRequestHeaderException.class)
	public ResponseEntity<OutputResponseDto> handleMissingRequestHeaderException(
			MissingRequestHeaderException e) {
		OutputResponseDto errorResponseDto = new OutputResponseDto();
		errorResponseDto.setSuccess(false);
		errorResponseDto.setTraceId(context.getTraceId());
		errorResponseDto.setData(null);
		errorResponseDto.setStatusCode(messages.getStastusCode("common.filter.exception.invalidHeaderParameters.id"));
		errorResponseDto.setMessage(messages.get("common.filter.exception.invalidHeaderParameters"));
		logger.error(e.getMessage());

		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}

}