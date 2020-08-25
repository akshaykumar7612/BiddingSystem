package com.akshay.biddingsystem.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.akshay.biddingsystem.dto.InputPlaceBidDto;
import com.akshay.biddingsystem.dto.OutputFetchAuctionDto;
import com.akshay.biddingsystem.dto.OutputPageableFetchAuctionDto;
import com.akshay.biddingsystem.dto.OutputResponseDto;
import com.akshay.biddingsystem.service.IBiddingService;
import com.akshay.biddingsystem.util.Messages;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BidControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;

	@Autowired
	Messages messages;
	
	@MockBean
    private IBiddingService iBiddingService;
	
	@Test
	@Tag("FetchAuction")
	public void getRunningAuctionSuccessTestWithPaginationValues() throws Exception {
		Long nInputItemCode = 10L;
		String sInputStatus = "RUNNING";
		Long nInputVersion = 1L;
		Double dInputHighestBidAmount = 4500.0;
		Double dInputStepRate = 1000.0;
		
		int nInputTotalPages =  2;
        Long nInputTotalElements = 5L;
        Integer nInputPageSize = 4;
        Integer nInputPageNumber = 1;
        boolean bInputSorted = false;
        boolean bInputFirst = false;
        boolean bInputlast = true;
        
        String sContentType = "application/json";
        int nInputArraySize = 1;
        
		List<OutputFetchAuctionDto> lOutputFetchAuctionDto = new ArrayList<OutputFetchAuctionDto>();
		OutputFetchAuctionDto outputFetchAuctionDto = new OutputFetchAuctionDto(nInputItemCode,sInputStatus,dInputHighestBidAmount,dInputStepRate,nInputVersion);
		lOutputFetchAuctionDto.add(outputFetchAuctionDto);
		OutputPageableFetchAuctionDto outputPageableFetchAuctionDto = new OutputPageableFetchAuctionDto(lOutputFetchAuctionDto,nInputTotalPages,nInputTotalElements,nInputPageSize,nInputPageNumber,bInputFirst,bInputlast,bInputSorted);
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(true);
		outputResponseDto.setData(outputPageableFetchAuctionDto);
		outputResponseDto.setMessage("Fetch Auction Details Success");
		outputResponseDto.setStatusCode("bs-200-1002");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.fetchAutionsAsPerStatus(sInputStatus,nInputPageNumber,nInputPageSize)).willReturn(outputResponseDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/auction")
															  .queryParam("sStatus", sInputStatus)
															  .queryParam("nPageNo", nInputPageNumber.toString())
															  .queryParam("nPageSize", nInputPageSize.toString());

		mockMvc.perform(requestBuilder)
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(sContentType))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto",hasSize(nInputArraySize)))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].nItemCode").value(nInputItemCode))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].sStatus").value(sInputStatus))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].dHighestBidPrice").value(dInputHighestBidAmount))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].dStepRate").value(dInputStepRate))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].nVersion").value(nInputVersion))
			   .andExpect(jsonPath("$.data.nTotalPages").value(nInputTotalPages))
			   .andExpect(jsonPath("$.data.nTotalElements").value(nInputTotalElements))
			   .andExpect(jsonPath("$.data.nPageSize").value(nInputPageSize))
			   .andExpect(jsonPath("$.data.nPageNumber").value(nInputPageNumber))
			   .andExpect(jsonPath("$.data.first").value(bInputFirst))
			   .andExpect(jsonPath("$.data.sorted").value(bInputSorted))
			   .andExpect(jsonPath("$.data.last").value(bInputlast));
		
		  verify(iBiddingService, VerificationModeFactory.times(1)).fetchAutionsAsPerStatus(sInputStatus,nInputPageNumber,nInputPageSize);
	      reset(iBiddingService);
	}
	
	@Test
	@Tag("FetchAuction")
	public void getOverAuctionSuccessTestWithPaginationValues() throws Exception {
		Long nInputItemCode = 9L;
		String sInputStatus = "OVER";
		Long nInputVersion = 1L;
		Double dInputHighestBidAmount = 2500.0;
		Double dInputStepRate = 250.0;
		
		int nInputTotalPages =  2;
        Long nInputTotalElements = 5L;
        Integer nInputPageSize = 4;
        Integer nInputPageNumber = 1;
        boolean bInputSorted = false;
        boolean bInputFirst = false;
        boolean bInputlast = true;
        
        String sContentType = "application/json";
        int nInputArraySize = 1;
        
		List<OutputFetchAuctionDto> lOutputFetchAuctionDto = new ArrayList<OutputFetchAuctionDto>();
		OutputFetchAuctionDto outputFetchAuctionDto = new OutputFetchAuctionDto(nInputItemCode,sInputStatus,dInputHighestBidAmount,dInputStepRate,nInputVersion);
		lOutputFetchAuctionDto.add(outputFetchAuctionDto);
		OutputPageableFetchAuctionDto outputPageableFetchAuctionDto = new OutputPageableFetchAuctionDto(lOutputFetchAuctionDto,nInputTotalPages,nInputTotalElements,nInputPageSize,nInputPageNumber,bInputFirst,bInputlast,bInputSorted);
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(true);
		outputResponseDto.setData(outputPageableFetchAuctionDto);
		outputResponseDto.setMessage("Fetch Auction Details Success");
		outputResponseDto.setStatusCode("bs-200-1002");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.fetchAutionsAsPerStatus(sInputStatus,nInputPageNumber,nInputPageSize)).willReturn(outputResponseDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/auction")
															  .queryParam("sStatus", sInputStatus)
															  .queryParam("nPageNo", nInputPageNumber.toString())
															  .queryParam("nPageSize", nInputPageSize.toString());

		mockMvc.perform(requestBuilder)
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(sContentType))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto",hasSize(nInputArraySize)))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].nItemCode").value(nInputItemCode))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].sStatus").value(sInputStatus))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].dHighestBidPrice").value(dInputHighestBidAmount))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].dStepRate").value(dInputStepRate))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].nVersion").value(nInputVersion))
			   .andExpect(jsonPath("$.data.nTotalPages").value(nInputTotalPages))
			   .andExpect(jsonPath("$.data.nTotalElements").value(nInputTotalElements))
			   .andExpect(jsonPath("$.data.nPageSize").value(nInputPageSize))
			   .andExpect(jsonPath("$.data.nPageNumber").value(nInputPageNumber))
			   .andExpect(jsonPath("$.data.first").value(bInputFirst))
			   .andExpect(jsonPath("$.data.sorted").value(bInputSorted))
			   .andExpect(jsonPath("$.data.last").value(bInputlast));
		
		  verify(iBiddingService, VerificationModeFactory.times(1)).fetchAutionsAsPerStatus(sInputStatus,nInputPageNumber,nInputPageSize);
	      reset(iBiddingService);
	}
	
	@Test
	@Tag("FetchAuction")
	public void geAuctionSuccessTestWithDefaultValues() throws Exception {
		String sInputStatus = "RUNNING";
		
		Long nInputItemCode1 = 1L;
		Long nInputVersion1 = 1L;
		Double dInputHighestBidAmount1 = 300.0;
		Double dInputStepRate1 = 50.0;
		
		Long nInputItemCode2 = 3L;
		Long nInputVersion2 = 1L;
		Double dInputHighestBidAmount2 = 1050.0;
		Double dInputStepRate2 = 150.0;
		
		Long nInputItemCode3 = 4L;
		Long nInputVersion3 = 2L;
		Double dInputHighestBidAmount3 = 1600.0;
		Double dInputStepRate3 = 200.0;
		
		int nInputTotalPages =  2;
        Long nInputTotalElements = 5L;
        Integer nInputPageSize = 3;
        Integer nInputPageNumber = 0;
        boolean bInputSorted = false;
        boolean bInputFirst = true;
        boolean bInputlast = false;
        
        String sContentType = "application/json";
        int nInputArraySize = 3;
        
		List<OutputFetchAuctionDto> lOutputFetchAuctionDto = new ArrayList<OutputFetchAuctionDto>();
		OutputFetchAuctionDto outputFetchAuctionDto1 = new OutputFetchAuctionDto(nInputItemCode1,sInputStatus,dInputHighestBidAmount1,dInputStepRate1,nInputVersion1);
		OutputFetchAuctionDto outputFetchAuctionDto2 = new OutputFetchAuctionDto(nInputItemCode2,sInputStatus,dInputHighestBidAmount2,dInputStepRate2,nInputVersion2);
		OutputFetchAuctionDto outputFetchAuctionDto3 = new OutputFetchAuctionDto(nInputItemCode3,sInputStatus,dInputHighestBidAmount3,dInputStepRate3,nInputVersion3);
		lOutputFetchAuctionDto.add(outputFetchAuctionDto1);
		lOutputFetchAuctionDto.add(outputFetchAuctionDto2);
		lOutputFetchAuctionDto.add(outputFetchAuctionDto3);
		OutputPageableFetchAuctionDto outputPageableFetchAuctionDto = new OutputPageableFetchAuctionDto(lOutputFetchAuctionDto,nInputTotalPages,nInputTotalElements,nInputPageSize,nInputPageNumber,bInputFirst,bInputlast,bInputSorted);
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(true);
		outputResponseDto.setData(outputPageableFetchAuctionDto);
		outputResponseDto.setMessage("Fetch Auction Details Success");
		outputResponseDto.setStatusCode("bs-200-1002");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.fetchAutionsAsPerStatus(sInputStatus,nInputPageNumber,nInputPageSize)).willReturn(outputResponseDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/auction");

		mockMvc.perform(requestBuilder)
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(sContentType))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto",hasSize(nInputArraySize)))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].nItemCode").value(nInputItemCode1))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].sStatus").value(sInputStatus))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].dHighestBidPrice").value(dInputHighestBidAmount1))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].dStepRate").value(dInputStepRate1))
			   .andExpect(jsonPath("$.data.lOutputFetchAuctionDto[0].nVersion").value(nInputVersion1))
			   .andExpect(jsonPath("$.data.nTotalPages").value(nInputTotalPages))
			   .andExpect(jsonPath("$.data.nTotalElements").value(nInputTotalElements))
			   .andExpect(jsonPath("$.data.nPageSize").value(nInputPageSize))
			   .andExpect(jsonPath("$.data.nPageNumber").value(nInputPageNumber))
			   .andExpect(jsonPath("$.data.first").value(bInputFirst))
			   .andExpect(jsonPath("$.data.sorted").value(bInputSorted))
			   .andExpect(jsonPath("$.data.last").value(bInputlast));
		
		  verify(iBiddingService, VerificationModeFactory.times(1)).fetchAutionsAsPerStatus(sInputStatus,nInputPageNumber,nInputPageSize);
	      reset(iBiddingService);
	}
	
	@Test
	@Tag("FetchAuction")
	public void getAuctionFailureTestWithPaginationValues() throws Exception {
		String sInputStatus = "RUNNINGY";        
		Integer nInputPageSize = 4;
        Integer nInputPageNumber = 1;
        
        String sContentType = "application/json";
        
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(false);
		outputResponseDto.setData(null);
		outputResponseDto.setMessage("Fetch Auction Details Failure");
		outputResponseDto.setStatusCode("bs-404-1001");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.fetchAutionsAsPerStatus(sInputStatus,nInputPageNumber,nInputPageSize)).willReturn(outputResponseDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/auction")
															  .queryParam("sStatus", sInputStatus)
															  .queryParam("nPageNo", nInputPageNumber.toString())
															  .queryParam("nPageSize", nInputPageSize.toString());

		mockMvc.perform(requestBuilder)
			   .andExpect(status().isNotFound())
			   .andExpect(content().contentType(sContentType));
		
		  verify(iBiddingService, VerificationModeFactory.times(1)).fetchAutionsAsPerStatus(sInputStatus,nInputPageNumber,nInputPageSize);
	      reset(iBiddingService);
	}
	
	
	@Test
	@Tag("PlaceBid")
	public void placeBidSuccessTest() throws Exception {
		Long nInputItemCode = 10L;
		String sInputStatus = "RUNNING";
		Long nInputVersion = 1L;
		Double dInputBidAmount = 5600.0;
		Double dInputStepRate = 1000.0;
		String sInputContext = "ewogICJ1c2VySWQiOjY5MSwKICAidHJhY2VJZCI6Ijk5OTkiLAogICJ1c2VyQWdlbnQiOiJDaHJvbWUiLAogICJsYW5ndWFnZUNvZGUiOiJlbiIKfQ==";
		String sContentType = "application/json";
		
		Long nOutputVersion = 2L;
		OutputFetchAuctionDto outputFetchAuctionDto = new OutputFetchAuctionDto(nInputItemCode,sInputStatus,dInputBidAmount,dInputStepRate,nOutputVersion);
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(true);
		outputResponseDto.setData(outputFetchAuctionDto);
		outputResponseDto.setMessage("Bid is Accepted");
		outputResponseDto.setStatusCode("bs-201-2009");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount)).willReturn(outputResponseDto);
		
		InputPlaceBidDto inputPlaceBidDto = new InputPlaceBidDto();
		inputPlaceBidDto.setdBidAmount(dInputBidAmount);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/auction/{nItemCode}/bid",nInputItemCode)
															  .header("context",sInputContext)
															  .header("ETag",nInputVersion.toString())
															  .contentType(sContentType)
															  .content(objectMapper.writeValueAsString(inputPlaceBidDto));
		
		
		mockMvc.perform(requestBuilder)
			   .andExpect(status().isCreated())
			   .andExpect(content().contentType(sContentType))
			   .andExpect(jsonPath("$.data.nItemCode").value(nInputItemCode))
			   .andExpect(jsonPath("$.data.sStatus").value(sInputStatus))
			   .andExpect(jsonPath("$.data.dHighestBidPrice").value(dInputBidAmount))
			   .andExpect(jsonPath("$.data.dStepRate").value(dInputStepRate))
			   .andExpect(jsonPath("$.data.nVersion").value(nOutputVersion));
		
		verify(iBiddingService, VerificationModeFactory.times(1)).placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount);
	    reset(iBiddingService);
	}
	
	@Test
	@Tag("PlaceBid")
	public void placeBidFailureContextNotFoundUnauthorizedTest() throws Exception {
		Long nInputItemCode = 10L;
		Long nInputVersion = 1L;
		Double dInputBidAmount = 5500.0;
		String sContentType = "application/json";
		
		InputPlaceBidDto inputPlaceBidDto = new InputPlaceBidDto();
		inputPlaceBidDto.setdBidAmount(dInputBidAmount);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/auction/{nItemCode}/bid",nInputItemCode)
															  .header("ETag",nInputVersion.toString())
															  .contentType(sContentType)
															  .content(objectMapper.writeValueAsString(inputPlaceBidDto));
		
		mockMvc.perform(requestBuilder)
			   .andExpect(status().isUnauthorized());
	}
	
	@Test
	@Tag("PlaceBid")
	public void placeBidFailureUserIdNotFoundUnauthorizedTest() throws Exception {
		Long nInputItemCode = 10L;
		Long nInputVersion = 1L;
		Double dInputBidAmount = 5500.0;
		String sInputContext = "ewogICJ0cmFjZUlkIjoiOTk5OSIsCiAgInVzZXJBZ2VudCI6IkNocm9tZSIsCiAgImxhbmd1YWdlQ29kZSI6ImVuIgp9";
		String sContentType = "application/json";
		
		InputPlaceBidDto inputPlaceBidDto = new InputPlaceBidDto();
		inputPlaceBidDto.setdBidAmount(dInputBidAmount);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/auction/{nItemCode}/bid",nInputItemCode)
															  .header("context",sInputContext)
															  .header("ETag",nInputVersion.toString())
															  .contentType(sContentType)
															  .content(objectMapper.writeValueAsString(inputPlaceBidDto));
		
		mockMvc.perform(requestBuilder)
			   .andExpect(status().isUnauthorized());
	}
	
	@Test
	@Tag("PlaceBid")
	public void placeBidAlreadyExistsFailedTest() throws Exception {
		Long nInputItemCode = 6L;
		Long nInputVersion = 6L;
		Double dInputBidAmount = 2500.0;
		String sInputContext = "ewogICJ1c2VySWQiOjY5MSwKICAidHJhY2VJZCI6Ijk5OTkiLAogICJ1c2VyQWdlbnQiOiJDaHJvbWUiLAogICJsYW5ndWFnZUNvZGUiOiJlbiIKfQ==";
		String sContentType = "application/json";
		
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(false);
		outputResponseDto.setData(null);
		outputResponseDto.setMessage("Bid Already Exists");
		outputResponseDto.setStatusCode("bs-409-2002");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount)).willReturn(outputResponseDto);
		
		InputPlaceBidDto inputPlaceBidDto = new InputPlaceBidDto();
		inputPlaceBidDto.setdBidAmount(dInputBidAmount);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/auction/{nItemCode}/bid",nInputItemCode)
															  .header("context",sInputContext)
															  .header("ETag",nInputVersion.toString())
															  .contentType(sContentType)
															  .content(objectMapper.writeValueAsString(inputPlaceBidDto));
		
		mockMvc.perform(requestBuilder)
			   .andExpect(status().isConflict())
			   .andExpect(content().contentType(sContentType));
		
		verify(iBiddingService, VerificationModeFactory.times(1)).placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount);
	    reset(iBiddingService);
	}
	
	@Test
	@Tag("PlaceBid")
	public void placeBidFailedRaceConditionTest() throws Exception {
		Long nInputItemCode = 6L;
		Long nInputVersion = 6L;
		Double dInputBidAmount = 2750.0;
		String sInputContext = "ewogICJ1c2VySWQiOjY5MSwKICAidHJhY2VJZCI6Ijk5OTkiLAogICJ1c2VyQWdlbnQiOiJDaHJvbWUiLAogICJsYW5ndWFnZUNvZGUiOiJlbiIKfQ==";
		String sContentType = "application/json";
		
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(false);
		outputResponseDto.setData(null);
		outputResponseDto.setMessage("Highest Bid Amount Changed, Please Use Latest Version");
		outputResponseDto.setStatusCode("bs-412-2003");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount)).willReturn(outputResponseDto);
		
		InputPlaceBidDto inputPlaceBidDto = new InputPlaceBidDto();
		inputPlaceBidDto.setdBidAmount(dInputBidAmount);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/auction/{nItemCode}/bid",nInputItemCode)
															  .header("context",sInputContext)
															  .header("ETag",nInputVersion.toString())
															  .contentType(sContentType)
															  .content(objectMapper.writeValueAsString(inputPlaceBidDto));
		
		mockMvc.perform(requestBuilder)
			   .andExpect(status().isPreconditionFailed())
			   .andExpect(content().contentType(sContentType));
		
		verify(iBiddingService, VerificationModeFactory.times(1)).placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount);
	    reset(iBiddingService);
	}
	
	@Test
	@Tag("PlaceBid")
	public void placeBidRejectedTest() throws Exception {
		Long nInputItemCode = 10L;
		String sInputStatus = "RUNNING";
		Long nInputVersion = 1L;
		Double dInputBidAmount = 5400.0;
		Double dInputHighestBidPrice = 4500.0;
		Double dInputStepRate = 1000.0;
		String sInputContext = "ewogICJ1c2VySWQiOjY5MSwKICAidHJhY2VJZCI6Ijk5OTkiLAogICJ1c2VyQWdlbnQiOiJDaHJvbWUiLAogICJsYW5ndWFnZUNvZGUiOiJlbiIKfQ==";
		String sContentType = "application/json";
		
		OutputFetchAuctionDto outputFetchAuctionDto = new OutputFetchAuctionDto(nInputItemCode,sInputStatus,dInputHighestBidPrice,dInputStepRate,nInputVersion);
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(false);
		outputResponseDto.setData(outputFetchAuctionDto);
		outputResponseDto.setMessage("Bid is Rejected");
		outputResponseDto.setStatusCode("bs-406-2005");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount)).willReturn(outputResponseDto);
		
		InputPlaceBidDto inputPlaceBidDto = new InputPlaceBidDto();
		inputPlaceBidDto.setdBidAmount(dInputBidAmount);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/auction/{nItemCode}/bid",nInputItemCode)
															  .header("context",sInputContext)
															  .header("ETag",nInputVersion.toString())
															  .contentType(sContentType)
															  .content(objectMapper.writeValueAsString(inputPlaceBidDto));
		
		
		mockMvc.perform(requestBuilder)
			   .andExpect(status().isNotAcceptable())
			   .andExpect(content().contentType(sContentType))
			   .andExpect(jsonPath("$.data.nItemCode").value(nInputItemCode))
			   .andExpect(jsonPath("$.data.sStatus").value(sInputStatus))
			   .andExpect(jsonPath("$.data.dHighestBidPrice").value(dInputHighestBidPrice))
			   .andExpect(jsonPath("$.data.dStepRate").value(dInputStepRate))
			   .andExpect(jsonPath("$.data.nVersion").value(nInputVersion));
		
		verify(iBiddingService, VerificationModeFactory.times(1)).placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount);
	    reset(iBiddingService);
	}
	
	@Test
	@Tag("PlaceBid")
	public void placeBidAuctionOverTest() throws Exception {
		Long nInputItemCode = 9L;
		Long nInputVersion = 1L;
		Double dInputBidAmount = 2750.0;
		String sInputContext = "ewogICJ1c2VySWQiOjY5MSwKICAidHJhY2VJZCI6Ijk5OTkiLAogICJ1c2VyQWdlbnQiOiJDaHJvbWUiLAogICJsYW5ndWFnZUNvZGUiOiJlbiIKfQ==";
		String sContentType = "application/json";
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(false);
		outputResponseDto.setData(null);
		outputResponseDto.setMessage("Auction Is Over For The Selected Item");
		outputResponseDto.setStatusCode("bs-406-2005");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount)).willReturn(outputResponseDto);
		
		InputPlaceBidDto inputPlaceBidDto = new InputPlaceBidDto();
		inputPlaceBidDto.setdBidAmount(dInputBidAmount);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/auction/{nItemCode}/bid",nInputItemCode)
															  .header("context",sInputContext)
															  .header("ETag",nInputVersion.toString())
															  .contentType(sContentType)
															  .content(objectMapper.writeValueAsString(inputPlaceBidDto));
		
		
		mockMvc.perform(requestBuilder)
			   .andExpect(status().isNotAcceptable())
			   .andExpect(content().contentType(sContentType));
		
		verify(iBiddingService, VerificationModeFactory.times(1)).placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount);
	    reset(iBiddingService);
	}
	
	@Test
	@Tag("PlaceBid")
	public void placeBidItemNotFoundest() throws Exception {
		Long nInputItemCode = 11L;
		Long nInputVersion = 1L;
		Double dInputBidAmount = 2750.0;
		String sInputContext = "ewogICJ1c2VySWQiOjY5MSwKICAidHJhY2VJZCI6Ijk5OTkiLAogICJ1c2VyQWdlbnQiOiJDaHJvbWUiLAogICJsYW5ndWFnZUNvZGUiOiJlbiIKfQ==";
		String sContentType = "application/json";
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(false);
		outputResponseDto.setData(null);
		outputResponseDto.setMessage("Item Not Found");
		outputResponseDto.setStatusCode("bs-404-2008");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount)).willReturn(outputResponseDto);
		
		InputPlaceBidDto inputPlaceBidDto = new InputPlaceBidDto();
		inputPlaceBidDto.setdBidAmount(dInputBidAmount);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/auction/{nItemCode}/bid",nInputItemCode)
															  .header("context",sInputContext)
															  .header("ETag",nInputVersion.toString())
															  .contentType(sContentType)
															  .content(objectMapper.writeValueAsString(inputPlaceBidDto));
		
		
		mockMvc.perform(requestBuilder)
			   .andExpect(status().isNotFound())
			   .andExpect(content().contentType(sContentType));
		
		verify(iBiddingService, VerificationModeFactory.times(1)).placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount);
	    reset(iBiddingService);
	}
	
	@Test
	@Tag("PlaceBid")
	public void placeMinimumBidBoundarySuccessTest() throws Exception {
		Long nInputItemCode = 10L;
		String sInputStatus = "RUNNING";
		Long nInputVersion = 1L;
		Double dInputBidAmount = 5500.0;
		Double dInputStepRate = 1000.0;
		String sInputContext = "ewogICJ1c2VySWQiOjY5MSwKICAidHJhY2VJZCI6Ijk5OTkiLAogICJ1c2VyQWdlbnQiOiJDaHJvbWUiLAogICJsYW5ndWFnZUNvZGUiOiJlbiIKfQ==";
		String sContentType = "application/json";
		
		Long nOutputVersion = 2L;
		OutputFetchAuctionDto outputFetchAuctionDto = new OutputFetchAuctionDto(nInputItemCode,sInputStatus,dInputBidAmount,dInputStepRate,nOutputVersion);
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(true);
		outputResponseDto.setData(outputFetchAuctionDto);
		outputResponseDto.setMessage("Bid is Accepted");
		outputResponseDto.setStatusCode("bs-201-2009");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount)).willReturn(outputResponseDto);
		
		InputPlaceBidDto inputPlaceBidDto = new InputPlaceBidDto();
		inputPlaceBidDto.setdBidAmount(dInputBidAmount);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/auction/{nItemCode}/bid",nInputItemCode)
															  .header("context",sInputContext)
															  .header("ETag",nInputVersion.toString())
															  .contentType(sContentType)
															  .content(objectMapper.writeValueAsString(inputPlaceBidDto));
		
		
		mockMvc.perform(requestBuilder)
			   .andExpect(status().isCreated())
			   .andExpect(content().contentType(sContentType))
			   .andExpect(jsonPath("$.data.nItemCode").value(nInputItemCode))
			   .andExpect(jsonPath("$.data.sStatus").value(sInputStatus))
			   .andExpect(jsonPath("$.data.dHighestBidPrice").value(dInputBidAmount))
			   .andExpect(jsonPath("$.data.dStepRate").value(dInputStepRate))
			   .andExpect(jsonPath("$.data.nVersion").value(nOutputVersion));
		
		verify(iBiddingService, VerificationModeFactory.times(1)).placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount);
	    reset(iBiddingService);
	}
	
	@Test
	@Tag("PlaceBid")
	public void placeMaximumBidBoundaryForRejectTest() throws Exception {
		Long nInputItemCode = 10L;
		String sInputStatus = "RUNNING";
		Long nInputVersion = 1L;
		Double dInputBidAmount = 5499.999999999999;
		Double dInputHighestBidPrice = 4500.0;
		Double dInputStepRate = 1000.0;
		String sInputContext = "ewogICJ1c2VySWQiOjY5MSwKICAidHJhY2VJZCI6Ijk5OTkiLAogICJ1c2VyQWdlbnQiOiJDaHJvbWUiLAogICJsYW5ndWFnZUNvZGUiOiJlbiIKfQ==";
		String sContentType = "application/json";
		
		OutputFetchAuctionDto outputFetchAuctionDto = new OutputFetchAuctionDto(nInputItemCode,sInputStatus,dInputHighestBidPrice,dInputStepRate,nInputVersion);
		
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		outputResponseDto.setSuccess(false);
		outputResponseDto.setData(outputFetchAuctionDto);
		outputResponseDto.setMessage("Bid is Rejected");
		outputResponseDto.setStatusCode("bs-406-2005");
		outputResponseDto.setTraceId("9999");
		
		given(iBiddingService.placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount)).willReturn(outputResponseDto);
		
		InputPlaceBidDto inputPlaceBidDto = new InputPlaceBidDto();
		inputPlaceBidDto.setdBidAmount(dInputBidAmount);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/auction/{nItemCode}/bid",nInputItemCode)
															  .header("context",sInputContext)
															  .header("ETag",nInputVersion.toString())
															  .contentType(sContentType)
															  .content(objectMapper.writeValueAsString(inputPlaceBidDto));
		
		
		mockMvc.perform(requestBuilder)
			   .andExpect(status().isNotAcceptable())
			   .andExpect(content().contentType(sContentType))
			   .andExpect(jsonPath("$.data.nItemCode").value(nInputItemCode))
			   .andExpect(jsonPath("$.data.sStatus").value(sInputStatus))
			   .andExpect(jsonPath("$.data.dHighestBidPrice").value(dInputHighestBidPrice))
			   .andExpect(jsonPath("$.data.dStepRate").value(dInputStepRate))
			   .andExpect(jsonPath("$.data.nVersion").value(nInputVersion));
		
		verify(iBiddingService, VerificationModeFactory.times(1)).placeAuctionsBid(nInputItemCode,nInputVersion,dInputBidAmount);
	    reset(iBiddingService);
	}
	
}
