package com.wiley.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wiley.dto.SortDTO;
import com.wiley.models.SellOrderTable;

@Repository
public interface SortRepository extends JpaRepository<SellOrderTable,Integer>{	
		
		@Query("SELECT new com.wiley.dto.SortDTO(o.orderId,o.companyName,o.noOfShares,o.sharePrice) FROM SellOrderTable o ")
		public List<SortDTO> findAllSellOrders();
	
		@Query("DELETE FROM SellOrderTable WHERE orderId=:orderId")
	    @Transactional
	    @Modifying
	    public int deleteSellOrderTable(@Param("orderId")int orderId);
		
		@Query("UPDATE SellOrderTable o SET o.noOfShares=:shares WHERE order_id=:OrderId")
		@Modifying
		@Transactional
		public int updateSellOrderByOrderId(@Param("OrderId") int OrderId,@Param("shares") int shares);
		
		@Query("SELECT new com.wiley.dto.SortDTO(o.orderId,o.companyName,o.noOfShares,o.sharePrice) FROM BuyOrderTable o ")
		public List<SortDTO> findAllBuyOrders();
	
		@Query("DELETE FROM BuyOrderTable WHERE orderId=:orderId")
	    @Transactional
	    @Modifying
	    public int deleteBuyOrderTable(@Param("orderId")int orderId);
		
		@Query("UPDATE BuyOrderTable o SET o.noOfShares=:shares WHERE order_id=:OrderId")
		@Modifying
		@Transactional
		public int updateBuyOrderByOrderId(@Param("OrderId") int OrderId,@Param("shares") int shares);
		
}
