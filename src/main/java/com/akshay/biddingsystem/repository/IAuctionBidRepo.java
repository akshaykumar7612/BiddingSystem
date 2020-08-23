package com.akshay.biddingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akshay.biddingsystem.model.TbAuctionBid;

@Repository
public interface IAuctionBidRepo extends JpaRepository<TbAuctionBid, Long> {
	
	@Query(value = "select * from tb_auction_bid where n_item_code=:nItemCode \n" + 
			"and d_bid_amount=:dBidAmount and n_user_id=:nUserId", nativeQuery = true)
	public List<TbAuctionBid> findBynItemCodeAnddBidAmountAndnUserId(@Param("nItemCode") Long nItemCode, @Param("dBidAmount") Double dBidAmount, @Param("nUserId") Long nUserId);
}
