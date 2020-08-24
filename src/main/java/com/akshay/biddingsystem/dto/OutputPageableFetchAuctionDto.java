package com.akshay.biddingsystem.dto;

import java.util.ArrayList;
import java.util.List;

public class OutputPageableFetchAuctionDto {
	
	private List<OutputFetchAuctionDto>  lOutputFetchAuctionDto = new ArrayList<OutputFetchAuctionDto>();  
    private int nTotalPages;
    private long nTotalElements;
    private int nPageSize;
    private int nPageNumber;
    private boolean isFirst = false;
    private boolean isLast = false;    
    private boolean isSorted = false;
    
    public OutputPageableFetchAuctionDto(List<OutputFetchAuctionDto> lOutputFetchAuctionDto, int nTotalPages,
    		long nTotalElements, int nPageSize, int nPageNumber, boolean isFirst, boolean isLast, boolean isSorted) {
		super();
		this.lOutputFetchAuctionDto = lOutputFetchAuctionDto;
		this.nTotalPages = nTotalPages;
		this.nTotalElements = nTotalElements;
		this.nPageSize = nPageSize;
		this.nPageNumber = nPageNumber;
		this.isFirst = isFirst;
		this.isLast = isLast;
		this.isSorted = isSorted;
	}
    
	public OutputPageableFetchAuctionDto() {}

	public List<OutputFetchAuctionDto> getlOutputFetchAuctionDto() {
		return lOutputFetchAuctionDto;
	}
	public void setlOutputFetchAuctionDto(List<OutputFetchAuctionDto> lOutputFetchAuctionDto) {
		this.lOutputFetchAuctionDto = lOutputFetchAuctionDto;
	}
	public int getnTotalPages() {
		return nTotalPages;
	}
	public void setnTotalPages(int nTotalPages) {
		this.nTotalPages = nTotalPages;
	}
	public long getnTotalElements() {
		return nTotalElements;
	}
	public void setnTotalElements(long nTotalElements) {
		this.nTotalElements = nTotalElements;
	}
	public int getnPageSize() {
		return nPageSize;
	}
	public void setnPageSize(int nPageSize) {
		this.nPageSize = nPageSize;
	}
	public int getnPageNumber() {
		return nPageNumber;
	}
	public void setnPageNumber(int nPageNumber) {
		this.nPageNumber = nPageNumber;
	}
	public boolean isFirst() {
		return isFirst;
	}
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	public boolean isSorted() {
		return isSorted;
	}
	public void setSorted(boolean isSorted) {
		this.isSorted = isSorted;
	}
	
	@Override
	public String toString() {
		return "OutputPageableFetchAuctionDto [lOutputFetchAuctionDto=" + lOutputFetchAuctionDto + ", nTotalPages="
				+ nTotalPages + ", nTotalElements=" + nTotalElements + ", nPageSize=" + nPageSize + ", nPageNumber="
				+ nPageNumber + ", isFirst=" + isFirst + ", isLast=" + isLast + ", isSorted=" + isSorted + "]";
	}  
    
}
