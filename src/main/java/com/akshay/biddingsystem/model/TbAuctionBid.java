package com.akshay.biddingsystem.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.annotation.Version;

/**
 * The persistent class for the auction_items database table.
 * 
 */
@Entity
@Table(name = "tb_auction_bid")
public class TbAuctionBid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_bid_id")
	private Long nBidId;
	
	@Column(name = "n_item_code")
	private Long nItemCode;

	@Column(name = "n_user_id")
	private Long nUserId;

	@Column(name = "s_status")
	private String sStatus="STATUS_NOT_CHECKED";

	@Column(name = "d_bid_amount")
	private Double dBidAmount;
	
	@Column(name = "n_version")
	private Long nVersion;

	@Column(name = "dt_created_on",insertable=false,updatable=false)
	private Timestamp dtCreatedOn;

	@Column(name = "dt_modified_on")
	private Timestamp dtModifiedOn;

	@Column(name = "n_created_by")
	private Long nCreatedBy;

	@Column(name = "n_modified_by")
	private Long nModifiedBy;
	

	public Long getnBidId() {
		return nBidId;
	}


	public void setnBidId(Long nBidId) {
		this.nBidId = nBidId;
	}


	public Long getnItemCode() {
		return nItemCode;
	}


	public void setnItemCode(Long nItemCode) {
		this.nItemCode = nItemCode;
	}


	public Long getnUserId() {
		return nUserId;
	}


	public void setnUserId(Long nUserId) {
		this.nUserId = nUserId;
	}


	public String getsStatus() {
		return sStatus;
	}


	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}


	public Double getdBidAmount() {
		return dBidAmount;
	}


	public void setdBidAmount(Double dBidAmount) {
		this.dBidAmount = dBidAmount;
	}

	public Long getnVersion() {
		return nVersion;
	}


	public void setnVersion(Long nVersion) {
		this.nVersion = nVersion;
	}
	
	public Timestamp getDtCreatedOn() {
		return dtCreatedOn;
	}


	public void setDtCreatedOn(Timestamp dtCreatedOn) {
		this.dtCreatedOn = dtCreatedOn;
	}


	public Timestamp getDtModifiedOn() {
		return dtModifiedOn;
	}


	public void setDtModifiedOn(Timestamp dtModifiedOn) {
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
		return "TbAuctionBid [nBidId=" + nBidId + ", nItemCode=" + nItemCode + ", nUserId=" + nUserId + ", sStatus="
				+ sStatus + ", dBidAmount=" + dBidAmount + ", nVersion=" + nVersion + ", dtCreatedOn=" + dtCreatedOn
				+ ", dtModifiedOn=" + dtModifiedOn + ", nCreatedBy=" + nCreatedBy + ", nModifiedBy=" + nModifiedBy
				+ "]";
	}
		
}
