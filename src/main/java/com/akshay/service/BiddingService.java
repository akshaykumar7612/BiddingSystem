package com.akshay.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.akshay.dto.OutputFetchAuctionDto;
import com.akshay.dto.OutputPageableFetchAuctionDto;
import com.akshay.dto.OutputResponseDto;
import com.akshay.model.TbAuctionItem;
import com.akshay.repository.IAuctionItemsRepo;
import com.akshay.util.Context;
import com.akshay.util.Messages;

@Service
public class BiddingService implements IBiddingService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Messages messages;
	
	@Autowired
	private Context context; 
	
	@Autowired
	IAuctionItemsRepo iAuctionItemsRepo;
	
	@Override
	public OutputResponseDto fetchAutionsAsPerStatus(String sStatus, int nPageNo, int nPageSize) {
		Pageable paging = PageRequest.of(nPageNo, nPageSize);
		Page<TbAuctionItem> lTbAuctionItem = iAuctionItemsRepo.findBysStatus(sStatus, paging);
		OutputResponseDto outputResponseDto = new OutputResponseDto();
		if(lTbAuctionItem.hasContent()) {
			List<OutputFetchAuctionDto> lOutputFetchAuctionDto = new ArrayList<OutputFetchAuctionDto>();
			for(TbAuctionItem tbAuctionItem:lTbAuctionItem.getContent()) {
				lOutputFetchAuctionDto.add(new OutputFetchAuctionDto(tbAuctionItem.getnItemCode(),tbAuctionItem.getsStatus(),tbAuctionItem.getdHighestBidPrice(),tbAuctionItem.getdStepRate()));
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

}
