package com.wiley.repositories;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wiley.dto.SellOrderDTO;
import com.wiley.models.SellOrderTable;



@Repository
public interface SellOrderRepository extends JpaRepository<SellOrderTable,Integer>{
    
	@Query("SELECT new com.wiley.dto.SellOrderDTO(o.orderId,o.noOfShares,o.companyName,o.sharePrice) FROM SellOrderTable o ")
	public List<SellOrderDTO> findAllSellOrders();
    
    @Query(value="INSERT INTO sell_order_table (order_id,no_of_shares,company_name,share_price,orderbook_id) VALUES(:orderId,:noOfShares,:companyName,:sharePrice,:status,:oBookId)",nativeQuery=true)
    @Transactional
    @Modifying
    public int addSellorder(@Param("orderId")int orderId,@Param("noOfShares")int noOfShares,@Param("companyName")String companyName,@Param("sharePrice")int sharePrice,@Param("oBookId")int orderBookID);
    
    @Query(value="DELETE FROM sell_order_table WHERE order_id=:orderId",nativeQuery=true)
    @Transactional
    @Modifying
    public int deleteSellOrder(@Param("orderId")int orderId);
    
}
