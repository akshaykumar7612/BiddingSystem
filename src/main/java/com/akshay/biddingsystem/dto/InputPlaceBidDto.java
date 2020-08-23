package com.akshay.biddingsystem.dto;

import javax.validation.constraints.NotNull;

public class InputPlaceBidDto {
	
	@NotNull(message="Bid Amount Should Not Be Null")
	private Double dBidAmount;
	
	public InputPlaceBidDto() {}
	
	public InputPlaceBidDto(Double dBidAmount) {
		this.dBidAmount = dBidAmount;
	}
	
	public Double getdBidAmount() {
		return dBidAmount;
	}

	public void setdBidAmount(Double dBidAmount) {
		this.dBidAmount = dBidAmount;
	}

	@Override
	public String toString() {
		return "InputPlaceBidDto [dBidAmount=" + dBidAmount + "]";
	}
	
}
