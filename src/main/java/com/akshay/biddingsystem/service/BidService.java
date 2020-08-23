package com.akshay.biddingsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.akshay.biddingsystem.dto.OutputFetchAuctionDto;
import com.akshay.biddingsystem.dto.OutputResponseDto;
import com.akshay.biddingsystem.exception.TransactionRollbackException;
import com.akshay.biddingsystem.model.TbAuctionBid;
import com.akshay.biddingsystem.model.TbAuctionItem;
import com.akshay.biddingsystem.repository.IAuctionBidRepo;
import com.akshay.biddingsystem.repository.IAuctionItemRepo;
import com.akshay.biddingsystem.util.Constant;
import com.akshay.biddingsystem.util.Context;
import com.akshay.biddingsystem.util.Messages;

@Component
public class BidService {
	
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Messages messages;
	
	@Autowired
	private Context context; 
	
	@Autowired
	IAuctionItemRepo iAuctionItemRepo;
	
	@Autowired
	IAuctionBidRepo iAuctionBidRepo;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public OutputResponseDto bidAccepted(Long nVersion, Double dBidAmount, TbAuctionBid tbAuctionBid,
			TbAuctionItem tbAuctionItem) {
		try {
			tbAuctionItem.setdHighestBidPrice(dBidAmount);
			tbAuctionItem.setnVersion(++nVersion);
			logger.info("Bid Item Details Trying To Save in DB "+tbAuctionItem);
			tbAuctionItem=iAuctionItemRepo.save(tbAuctionItem);
			
			tbAuctionBid.setsStatus(Constant.BID_STATUS_ACCEPTED);
			logger.info("Bid Details Trying To Save in DB "+tbAuctionBid);
			iAuctionBidRepo.save(tbAuctionBid);
			
			OutputFetchAuctionDto outputFetchAuctionDto = new OutputFetchAuctionDto();
			outputFetchAuctionDto.setnItemCode(tbAuctionItem.getnItemCode());
			outputFetchAuctionDto.setdHighestBidPrice(tbAuctionItem.getdHighestBidPrice());
			outputFetchAuctionDto.setdStepRate(tbAuctionItem.getdStepRate());
			outputFetchAuctionDto.setnVersion(tbAuctionItem.getnVersion());
			outputFetchAuctionDto.setsStatus(tbAuctionItem.getsStatus());
			return new OutputResponseDto(true,outputFetchAuctionDto,
					messages.getStastusCode("bs.success.auction.placeBidSuccess.message.id"),
					messages.get("bs.success.auction.placeBidSuccess.message"),context.getTraceId());
		} catch (Exception e) {
			logger.error("Error Occured In Updating Highest Bid Amount", e);
			throw new TransactionRollbackException("Error Occured In Updating Highest Bid Amount",
					false,
					messages.getStastusCode("bs.failure.auction.placeBidSaveFailure.message.id"),
					messages.get("bs.failure.auction.placeBidSaveFailure.message"),
					e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public OutputResponseDto bidRejected(TbAuctionBid tbAuctionBid, TbAuctionItem tbAuctionItem) throws Exception {
		try {
			tbAuctionBid.setsStatus(Constant.BID_STATUS_REJECTED);
			logger.info("Bid Details Trying To Save in DB "+tbAuctionBid);
			iAuctionBidRepo.save(tbAuctionBid);
			
			OutputFetchAuctionDto outputFetchAuctionDto = new OutputFetchAuctionDto();
			outputFetchAuctionDto.setnItemCode(tbAuctionItem.getnItemCode());
			outputFetchAuctionDto.setdHighestBidPrice(tbAuctionItem.getdHighestBidPrice());
			outputFetchAuctionDto.setdStepRate(tbAuctionItem.getdStepRate());
			outputFetchAuctionDto.setnVersion(tbAuctionItem.getnVersion());
			outputFetchAuctionDto.setsStatus(tbAuctionItem.getsStatus());
			
			return new OutputResponseDto(false,outputFetchAuctionDto,
					messages.getStastusCode("bs.failure.auction.placeBidRejected.message.id"),
					messages.get("bs.failure.auction.placeBidRejected.message"),context.getTraceId());
		} catch (Exception e) {
			logger.error("Error Occured In Updating Rejected Status", e);
			throw new TransactionRollbackException("Error Occured In Updating Rejected Status",
					false,
					messages.getStastusCode("bs.failure.auction.placeBidRejectSaveFailure.message.id"),
					messages.get("bs.failure.auction.placeBidRejectSaveFailure.message"),
					e);
		}
	}
}
