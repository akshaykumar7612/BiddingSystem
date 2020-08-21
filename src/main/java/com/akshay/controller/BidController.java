package com.akshay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.dto.OutputResponseDto;
import com.akshay.service.IBiddingService;
import com.akshay.util.Messages;

@CrossOrigin
@RestController
@RequestMapping(value="/v1")
public class BidController {
	
	@Autowired
	Messages messages;
	
	@Autowired
	IBiddingService iBiddingService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value = "/auction", produces = "application/json")
	public ResponseEntity<OutputResponseDto> fetchAutionsAsPerStatus(@RequestParam(required = false,defaultValue = "RUNNING") String sStatus,
			@RequestParam(defaultValue = "0") int nPageNo, 
            @RequestParam(defaultValue = "3") int nPageSize) throws Exception {

		logger.info("Class File : BidController, Method Name : fetchAutionsAsPerStatus");
		logger.info("Param for Method fetchAutionsAsPerStatus - Status Code : " + sStatus);

		return getResponseEntity(iBiddingService.fetchAutionsAsPerStatus(sStatus,nPageNo,nPageSize));
	}
	
	public ResponseEntity<OutputResponseDto> getResponseEntity(OutputResponseDto outputResponseDto) {
		logger.debug("outputResponseDto :{}" + outputResponseDto);
		HttpStatus st = null;
		String sCode = "";
		String sAppCode = "-";
		try {
			sAppCode = outputResponseDto.getStatusCode().toString();
			sCode = sAppCode.split("-")[1].toString();
			st = HttpStatus.valueOf(Integer.parseInt(sCode));

		} catch (Exception e) {
			st = HttpStatus.I_AM_A_TEAPOT;
			logger.error("Incorrect Http Header set | sAppCode:" + sAppCode + " | HttpCode:" + sCode);
		}
		return new ResponseEntity<>(outputResponseDto, st);
	}
}
