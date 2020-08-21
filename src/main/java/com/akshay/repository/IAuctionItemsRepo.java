package com.akshay.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.akshay.model.TbAuctionItem;

public interface IAuctionItemsRepo extends CrudRepository<TbAuctionItem, Long> {

	public Page<TbAuctionItem> findBysStatus(String sStatus,Pageable pageable);

}
