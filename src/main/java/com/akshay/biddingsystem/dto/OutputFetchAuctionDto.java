package com.akshay.biddingsystem.dto;

public class OutputFetchAuctionDto {
	
	private Long nItemCode;
	private String sStatus;
	private Double dHighestBidPrice;
	private Double dStepRate;
	private Long nVersion;
	
	public OutputFetchAuctionDto() {}
	
	public OutputFetchAuctionDto(Long nItemCode, String sStatus, Double dHighestBidPrice, Double dStepRate, Long nVersion) {
		this.nItemCode = nItemCode;
		this.sStatus = sStatus;
		this.dHighestBidPrice = dHighestBidPrice;
		this.dStepRate = dStepRate;
		this.nVersion = nVersion;
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
	public Long getnVersion() {
		return nVersion;
	}
	public void setnVersion(Long nVersion) {
		this.nVersion = nVersion;
	}

	@Override
	public String toString() {
		return "OutputFetchAuctionDto [nItemCode=" + nItemCode + ", sStatus=" + sStatus + ", dHighestBidPrice="
				+ dHighestBidPrice + ", dStepRate=" + dStepRate + ", nVersion=" + nVersion + "]";
	}
	
}
