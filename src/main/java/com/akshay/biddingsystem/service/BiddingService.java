package com.akshay.biddingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.akshay.biddingsystem.dto.OutputFetchAuctionDto;
import com.akshay.biddingsystem.dto.OutputPageableFetchAuctionDto;
import com.akshay.biddingsystem.dto.OutputResponseDto;
import com.akshay.biddingsystem.model.TbAuctionBid;
import com.akshay.biddingsystem.model.TbAuctionItem;
import com.akshay.biddingsystem.repository.IAuctionBidRepo;
import com.akshay.biddingsystem.repository.IAuctionItemRepo;
import com.akshay.biddingsystem.util.Constant;
import com.akshay.biddingsystem.util.Context;
import com.akshay.biddingsystem.util.Messages;

@Service
public class BiddingService implements IBiddingService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Messages messages;
	
	@Autowired
	private Context context; 
	
	@Autowired
	IAuctionItemRepo iAuctionItemRepo;
	
	@Autowired
	IAuctionBidRepo iAuctionBidRepo;
	
	@Autowired
	BidService bidService;
	
	@Override
	public OutputResponseDto fetchAutionsAsPerStatus(String sStatus, int nPageNo, int nPageSize) {
		Pageable paging = PageRequest.of(nPageNo, nPageSize);
		Page<TbAuctionItem> lTbAuctionItem = iAuctionItemRepo.findBysStatus(sStatus, paging);
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		if(lTbAuctionItem.hasContent()) {
			List<OutputFetchAuctionDto> lOutputFetchAuctionDto = new ArrayList<OutputFetchAuctionDto>();
			for(TbAuctionItem tbAuctionItem:lTbAuctionItem.getContent()) {
				lOutputFetchAuctionDto.add(new OutputFetchAuctionDto(tbAuctionItem.getnItemCode(),tbAuctionItem.getsStatus(),tbAuctionItem.getdHighestBidPrice(),tbAuctionItem.getdStepRate(),tbAuctionItem.getnVersion()));
			}
			OutputPageableFetchAuctionDto outputPageableFetchAuctionDto = new OutputPageableFetchAuctionDto();
			outputPageableFetchAuctionDto.setFirst(lTbAuctionItem.isFirst());
			outputPageableFetchAuctionDto.setLast(lTbAuctionItem.isLast());
			outputPageableFetchAuctionDto.setnPageNumber(lTbAuctionItem.getNumber());
			outputPageableFetchAuctionDto.setnPageSize(lTbAuctionItem.getSize());
			outputPageableFetchAuctionDto.setnTotalElements(lTbAuctionItem.getTotalElements());
			outputPageableFetchAuctionDto.setnTotalPages(lTbAuctionItem.getTotalPages());
			outputPageableFetchAuctionDto.setSorted(lTbAuctionItem.getSort().isSorted());
			outputPageableFetchAuctionDto.setlOutputFetchAuctionDto(lOutputFetchAuctionDto);
			outputResponseDto = new OutputResponseDto(true,outputPageableFetchAuctionDto,
					messages.getStastusCode("bs.success.auction.fetch.message.id"),
					messages.get("bs.success.auction.fetch.message"),context.getTraceId());			
		} else {
			logger.info("No Auction Details Found For Status"+sStatus);
			outputResponseDto = new OutputResponseDto(false,null,
					messages.getStastusCode("bs.failure.auction.fetch.message.id"),
					messages.get("bs.failure.auction.fetch.message"),context.getTraceId());
		}
		return outputResponseDto;
	}
	
	@Override
	public OutputResponseDto placeAuctionsBid(Long nItemCode,Long nVersion,Double dBidAmount) throws Exception {
		TbAuctionItem tbAuctionItem = iAuctionItemRepo.findBynItemCode(nItemCode);
		if(tbAuctionItem!=null) {
			List<TbAuctionBid> lTbAuctionBid = iAuctionBidRepo.findBynItemCodeAnddBidAmountAndnUserId(nItemCode,dBidAmount,context.getUserId());
			if(lTbAuctionBid!=null && lTbAuctionBid.size()>0) {
				logger.info("Bid Details Already Exist Hence Not Saved in Database");
				return new OutputResponseDto(false,null,
						messages.getStastusCode("bs.failure.auction.placeBidDataExists.message.id"),
						messages.get("bs.failure.auction.placeBidDataExists.message"),context.getTraceId());
			} else {
				TbAuctionBid tbAuctionBid = new TbAuctionBid();
				tbAuctionBid.setnItemCode(nItemCode);
				tbAuctionBid.setdBidAmount(dBidAmount);
				tbAuctionBid.setnUserId(context.getUserId());
				tbAuctionBid.setnCreatedBy(context.getUserId());
				tbAuctionBid.setnVersion(nVersion);
				logger.info("Bid Details Trying To Save in DB "+tbAuctionBid);
				iAuctionBidRepo.save(tbAuctionBid);
				logger.info("Bid Details Saved");
				
				if(tbAuctionItem.getsStatus()!=null && tbAuctionItem.getsStatus().equals(Constant.AUCTION_STATUS_RUNNING)) {
					if(tbAuctionItem.getnVersion()!=null && tbAuctionItem.getnVersion().equals(nVersion)) {
						if(dBidAmount>=(tbAuctionItem.getdHighestBidPrice()+tbAuctionItem.getdStepRate())) {
							return bidService.bidAccepted(nVersion, dBidAmount, tbAuctionBid, tbAuctionItem);
						} else {
							return bidService.bidRejected(tbAuctionBid, tbAuctionItem);
						}
					} else {
						return new OutputResponseDto(false,null,
								messages.getStastusCode("bs.failure.auction.placeBidVersionIssue.message.id"),
								messages.get("bs.failure.auction.placeBidVersionIssue.message"),context.getTraceId());
					}
				} else {
					return new OutputResponseDto(false,null,
							messages.getStastusCode("bs.failure.auction.placeBidStatusOver.message.id"),
							messages.get("bs.failure.auction.placeBidStatusOver.message"),context.getTraceId());
				}
			}
		} else {
			return new OutputResponseDto(false,null,
					messages.getStastusCode("bs.failure.auction.placeBidItemNotFound.message.id"),
					messages.get("bs.failure.auction.placeBidItemNotFound.message"),context.getTraceId());
		}
	}

}
