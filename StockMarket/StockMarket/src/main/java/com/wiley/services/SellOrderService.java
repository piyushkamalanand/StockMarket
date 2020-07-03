package com.wiley.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.dto.SellOrderDTO;
import com.wiley.repositories.SellOrderRepository;

@Service
public class SellOrderService {
	
	@Autowired
	private SellOrderRepository sellOrderRepository;
	
	public List<SellOrderDTO> loadSellOrders()
	{
		return sellOrderRepository.findAllSellOrders();
	}
	
	public int insertSellOrders(int orderId,int noOfShares,String companyName,int sharePrice,int orderBookID)
	{
		return sellOrderRepository.addSellorder(orderId, noOfShares, companyName, sharePrice, orderBookID);
	}
	
    public int deleteSellOrders(int orderId)
    {
    	return sellOrderRepository.deleteSellOrder(orderId);
    }

}
