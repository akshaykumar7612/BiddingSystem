package com.akshay.biddingsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.akshay.biddingsystem.model.TbAuctionItem;

@Repository
public interface IAuctionItemRepo extends CrudRepository<TbAuctionItem, Long> {

	public Page<TbAuctionItem> findBysStatus(String sStatus,Pageable pageable);

	public TbAuctionItem findBynItemCode(Long nItemCode);

}
