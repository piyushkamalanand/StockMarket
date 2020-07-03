package com.wiley.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wiley.dto.ExchangeDTO;
import com.wiley.dto.NseDTO;
import com.wiley.models.NseOrderTable;


@Repository
public interface NseExchangeRepository extends JpaRepository<NseOrderTable,Integer>{

//	@Query(value="INSERT INTO nse_order_table (company_name,no_of_shares,share_price) VALUES(:cName,:noShares,:sharePrice)",nativeQuery=true)
//	@Transactional
//	@Modifying
//	public int addOrderIntoNse(@Param("cName")String companyName,@Param("noShares")int noOfShares,@Param("sharePrice")int sharePrice);
	
	@Query("SELECT new com.wiley.dto.ExchangeDTO(o.orderId,o.companyName,o.noOfShares,o.sharePrice,o.orderType) FROM NseOrderTable o ORDER BY o.sharePrice")
	public List<ExchangeDTO> findAllOrders();	
	
	@Query("SELECT new com.wiley.dto.ExchangeDTO(o.orderId,o.companyName,o.noOfShares,o.sharePrice,o.orderType) FROM NseOrderTable o ORDER BY o.sharePrice Desc")
	public List<ExchangeDTO> findAllOrdersDesc();
	    
	@Query("UPDATE NseOrderTable o SET o.noOfShares=:shares WHERE order_id=:OrderId")
	@Modifying
	@Transactional
	public int updateNseOrderByOrderId(@Param("OrderId") int OrderId,@Param("shares") int shares);
	
	@Query(value="INSERT INTO nse_order_table (company_name,no_of_shares,order_type,share_price,user_id) VALUES (:companyName,:noOfShares,:orderType,:sharePrice,:userId)",nativeQuery=true)
	@Modifying
	@Transactional
	public int addOrderIntoNse(@Param("companyName")String companyName,@Param("noOfShares") int noOfShares, @Param("orderType")String orderType,@Param("sharePrice") int sharePrice,@Param("userId")int userId);
	
	@Query("SELECT new com.wiley.dto.NseDTO(o.orderId,o.companyName,o.noOfShares,o.orderType,o.sharePrice) FROM NseOrderTable o where o.orderType=:orderType")
	public List<NseDTO> findAllNseOrders(@Param("orderType")String orderType);
	
	@Query("SELECT new com.wiley.dto.NseDTO(o.orderId,o.companyName,o.noOfShares,o.orderType,o.sharePrice) FROM NseOrderTable o where o.userId=:userId")
	public List<NseDTO> findAllUsersOrders(@Param("userId")int userId);
}

