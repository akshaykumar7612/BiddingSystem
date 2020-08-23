package com.akshay.biddingsystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.biddingsystem.dto.InputPlaceBidDto;
import com.akshay.biddingsystem.dto.OutputResponseDto;
import com.akshay.biddingsystem.dto.ValidationMessageDto;
import com.akshay.biddingsystem.exception.ValidationException;
import com.akshay.biddingsystem.service.IBiddingService;
import com.akshay.biddingsystem.util.Messages;

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
	
	@PostMapping(value = "/auction/{nItemCode}/bid", produces = "application/json")
	public ResponseEntity<OutputResponseDto> placeAuctionsBid(@RequestHeader("ETag") Long nVersion,
			@PathVariable("nItemCode") Long nItemCode,
			@Valid @RequestBody InputPlaceBidDto inputPlaceBidDto,
			final BindingResult bindingResult) throws Exception {
		
		if (bindingResult.hasErrors()) {
			List<ValidationMessageDto> v = new ArrayList<>();

			for (FieldError err : bindingResult.getFieldErrors()) {
				ValidationMessageDto e = new ValidationMessageDto();
				e.setErrorMessage(err.getDefaultMessage());
				e.setField(err.getField());
				v.add(e);

			}
			throw new ValidationException("Input parameters", false,
					messages.getStastusCode("bs.validation.exception.message.id"),
					messages.get("bs.validation.exception.message"), v.get(0).getErrorMessage());
		}
		
		logger.info("Class File : BidController, Method Name : placeAuctionsBid");
		logger.info("Header for Method placeAuctionsBid : "+nVersion);
		logger.info("Param for Method placeAuctionsBid Item Code : "+ nItemCode +", Bid Amount : " + inputPlaceBidDto.getdBidAmount());

		return getResponseEntity(iBiddingService.placeAuctionsBid(nItemCode,nVersion,inputPlaceBidDto.getdBidAmount()));
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
