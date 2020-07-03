package com.wiley.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wiley.dto.BseDTO;
import com.wiley.dto.ExchangeDTO;
import com.wiley.dto.NseDTO;
import com.wiley.models.BseOrderTable;
import com.wiley.models.Users;

@Repository
public interface BseExchangeRepository extends JpaRepository<BseOrderTable,Integer> { 
		
//	@Query(value="INSERT INTO bse_order_table (company_name,no_of_shares,share_price) VALUES(:cName,:noShares,:sharePrice)",nativeQuery=true)
//		@Transactional
//		@Modifying
//		public int addOrderIntoBse(@Param("cName")String companyName,@Param("noShares")int noOfShares,@Param("sharePrice")int sharePrice);

	@Query("SELECT new com.wiley.dto.ExchangeDTO(o.orderId,o.companyName,o.noOfShares,o.sharePrice,o.orderType) FROM BseOrderTable o ORDER BY o.sharePrice")
	public List<ExchangeDTO> findAllOrders();	
	
	@Query("SELECT new com.wiley.dto.ExchangeDTO(o.orderId,o.companyName,o.noOfShares,o.sharePrice,o.orderType) FROM BseOrderTable o ORDER BY o.sharePrice Desc")
	public List<ExchangeDTO> findAllOrdersDesc();
	    
	@Query("UPDATE BseOrderTable o SET o.noOfShares=:shares WHERE order_id=:OrderId")
	@Modifying
	@Transactional
	public int updateBseOrderByOrderId(@Param("OrderId") int OrderId,@Param("shares") int shares);
	
	@Query(value="INSERT into bse_order_table (company_name,no_of_shares,order_type,share_price,user_id) VALUES (:companyName,:noOfShares,:orderType,:sharePrice,:userId)",nativeQuery=true)
	@Modifying
	@Transactional
	public int addOrderIntoBse(@Param("companyName")String companyName,@Param("noOfShares") int noOfShares, @Param("orderType")String orderType,@Param("sharePrice") int sharePrice,@Param("userId")int userId);
	
	@Query("SELECT new com.wiley.dto.BseDTO(o.orderId,o.companyName,o.noOfShares,o.orderType,o.sharePrice) FROM BseOrderTable o where o.orderType=:orderType")
	public List<BseDTO> findAllBseOrders(@Param("orderType")String orderType);
	
	@Query("SELECT new com.wiley.dto.BseDTO(o.orderId,o.companyName,o.noOfShares,o.orderType,o.sharePrice) FROM BseOrderTable o where o.userId=:userId")
	public List<BseDTO> findAllUsersOrders(@Param("userId")int userId);
}
