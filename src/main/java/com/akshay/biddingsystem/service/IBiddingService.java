package com.akshay.biddingsystem.service;

import com.akshay.biddingsystem.dto.OutputResponseDto;

public interface IBiddingService {

	public OutputResponseDto fetchAutionsAsPerStatus(String sStatus, int nPageNo, int nPageSize);

	public OutputResponseDto placeAuctionsBid(Long nItemCode, Long nVersion, Double dBidAmount) throws Exception;

}
