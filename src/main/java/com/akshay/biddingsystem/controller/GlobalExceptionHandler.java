package com.akshay.biddingsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.biddingsystem.dto.OutputResponseDto;
import com.akshay.biddingsystem.util.Context;
import com.akshay.biddingsystem.util.Messages;


@RestController
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Messages messages;
	
	@Autowired
	Context context;
	
	@ExceptionHandler(value = Exception.class)
    public ResponseEntity<OutputResponseDto> handleException(Exception e){
    	OutputResponseDto outputResponseDto = new OutputResponseDto();
    	outputResponseDto.setSuccess(false);
    	outputResponseDto.setData(null);
    	outputResponseDto.setMessage(messages.get("global.exception.message"));
    	outputResponseDto.setStatusCode(messages.getStastusCode("global.exception.message.id"));
    	outputResponseDto.setTraceId(context.getTraceId());
    	logger.error("Exception in global handler : {}"+e.getStackTrace());
		return new ResponseEntity<>(outputResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}