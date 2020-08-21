package com.akshay.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the auction_items database table.
 * 
 */
@Entity
@Table(name = "tb_auction_item")
@NamedQuery(name = "TbAuctionItem.findAll", query = "SELECT t FROM TbAuctionItem t")
public class TbAuctionItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_item_code")
	private Long nItemCode;
	
	@Column(name = "s_status")
	private String sStatus;

	@Column(name = "d_min_base_price")
	private Double dMinBasePrice;
	
	@Column(name = "d_highest_bid_price")
	private Double dHighestBidPrice;

	@Column(name = "d_step_rate")
	private Double dStepRate;

	@Column(name = "dt_created_on")
	private Timestamp dtCreatedOn;

	@Column(name = "dt_modified_on")
	private Timestamp dtModifiedOn;

	@Column(name = "n_created_by")
	private Long nCreatedBy;

	@Column(name = "n_modified_by")
	private Long nModifiedBy;

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

	public Double getdMinBasePrice() {
		return dMinBasePrice;
	}

	public void setdMinBasePrice(Double dMinBasePrice) {
		this.dMinBasePrice = dMinBasePrice;
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

	public Timestamp getdtCreatedOn() {
		return dtCreatedOn;
	}

	public void setdtCreatedOn(Timestamp dtCreatedOn) {
		this.dtCreatedOn = dtCreatedOn;
	}

	public Timestamp getdtModifiedOn() {
		return dtModifiedOn;
	}

	public void setdtModifiedOn(Timestamp dtModifiedOn) {
		this.dtModifiedOn = dtModifiedOn;
	}

	public Long getnCreatedBy() {
		return nCreatedBy;
	}

	public void setnCreatedBy(Long nCreatedBy) {
		this.nCreatedBy = nCreatedBy;
	}

	public Long getnModifiedBy() {
		return nModifiedBy;
	}

	public void setnModifiedBy(Long nModifiedBy) {
		this.nModifiedBy = nModifiedBy;
	}

	@Override
	public String toString() {
		return "TbAuctionItem [nItemCode=" + nItemCode + ", sStatus=" + sStatus + ", dMinBasePrice=" + dMinBasePrice
				+ ", dHighestBidPrice=" + dHighestBidPrice + ", dStepRate=" + dStepRate + ", dtCreatedOn=" + dtCreatedOn
				+ ", dtModifiedOn=" + dtModifiedOn + ", nCreatedBy=" + nCreatedBy + ", nModifiedBy=" + nModifiedBy
				+ "]";
	}
	
}
