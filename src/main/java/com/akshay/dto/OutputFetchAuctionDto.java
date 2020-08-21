package com.akshay.dto;

public class OutputFetchAuctionDto {
	
	private Long nItemCode;
	private String sStatus;
	private Double dHighestBidPrice;
	private Double dStepRate;
	
	public OutputFetchAuctionDto(Long nItemCode, String sStatus, Double dHighestBidPrice, Double dStepRate) {
		this.nItemCode = nItemCode;
		this.sStatus = sStatus;
		this.dHighestBidPrice = dHighestBidPrice;
		this.dStepRate = dStepRate;
	}
	
	public Long getnItemCode() {
		return nItemCode;
	}
	public void setnItemCode(Long nItemCode) {
		this.nItemCode = nItemCode;
	}
	public String getsStatus() {
		return sStatus;
	}
	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
	public Double getdHighestBidPrice() {
		return dHighestBidPrice;
	}
	public void setdHighestBidPrice(Double dHighestBidPrice) {
		this.dHighestBidPrice = dHighestBidPrice;
	}
	public Double getdStepRate() {
		return dStepRate;
	}
	public void setdStepRate(Double dStepRate) {
		this.dStepRate = dStepRate;
	}
	
	@Override
	public String toString() {
		return "OutputFetchAuctionDto [nItemCode=" + nItemCode + ", sStatus=" + sStatus + ", dHighestBidPrice="
				+ dHighestBidPrice + ", dStepRate=" + dStepRate + "]";
	}
	
}
