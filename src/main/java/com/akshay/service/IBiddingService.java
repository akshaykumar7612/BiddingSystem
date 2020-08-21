package com.akshay.service;

import com.akshay.dto.OutputResponseDto;

public interface IBiddingService {

	OutputResponseDto fetchAutionsAsPerStatus(String sStatus, int nPageNo, int nPageSize);

}
