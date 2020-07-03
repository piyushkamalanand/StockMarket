package com.wiley.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.dto.BuyOrderDTO;
import com.wiley.repositories.BuyOrderRepository;

@Service
public class BuyOrderService {
	
	@Autowired
	private BuyOrderRepository buyOrderRepository;
	
	public List<BuyOrderDTO> loadBuyOrders()
	{
		return buyOrderRepository.findAllBuyOrders();
	}
	
	public int insertBuyOrders(int orderId,int noOfShares,String companyName,int sharePrice,int orderBookID)
	{
		return buyOrderRepository.addBuyOrders(orderId, noOfShares, companyName, sharePrice, orderBookID);
	}
	
    public int deleteBuyOrders(int orderId)
    {
    	return buyOrderRepository.deleteBuyOrder(orderId);
    }

}
