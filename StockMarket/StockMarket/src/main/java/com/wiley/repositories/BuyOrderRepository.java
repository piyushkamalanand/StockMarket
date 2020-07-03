package com.wiley.repositories;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wiley.dto.BuyOrderDTO;
import com.wiley.models.BuyOrderTable;



@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrderTable,Integer>{
    
	@Query("SELECT new com.wiley.dto.BuyOrderDTO(o.orderId,o.noOfShares,o.companyName,o.sharePrice) FROM BuyOrderTable o ")
	public List<BuyOrderDTO> findAllBuyOrders();
    
    @Query(value="INSERT INTO buy_order_table (order_id,no_of_shares,company_name,share_price,orderbook_id) VALUES(:orderId,:noOfShares,:companyName,:sharePrice,:status,:oBookId)",nativeQuery=true)
    @Transactional
    @Modifying
    public int addBuyOrders(@Param("orderId")int orderId,@Param("noOfShares")int noOfShares,@Param("companyName")String companyName,@Param("sharePrice")int sharePrice,@Param("oBookId")int orderBookID);
    
    @Query(value="DELETE FROM buy_order_table WHERE order_id=:orderId",nativeQuery=true)
    @Transactional
    @Modifying
    public int deleteBuyOrder(@Param("orderId")int orderId);
    
}
