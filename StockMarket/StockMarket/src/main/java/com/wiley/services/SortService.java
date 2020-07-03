package com.wiley.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.dto.SortDTO;
import com.wiley.models.Users;
import com.wiley.repositories.SortRepository;

@Service
public class SortService {

	@Autowired
	private SortRepository sortRepository;
	
	@Autowired
	private ExchangeService exchangeService;
	
	public int[] executeBuyTrade(int userId,int noOfShares,String companyName,int sharePrice)
	{
		boolean flag = true;
		int res[]=new int[2];
		List<SortDTO> s = sortRepository.findAllSellOrders();
		
		for(SortDTO i: s)
		{
			if(i.getCompanyName().equals(companyName) && i.getNoOfShares()>=noOfShares && i.getSharePrice()>=sharePrice )
			{
				 int shares = i.getNoOfShares() - noOfShares;
				 sortRepository.updateSellOrderByOrderId(i.getOrderId(),shares);
				 flag=false;
				 res[0]= 1;
				 res[1]=0;
					return res;
			}
		}
		
		if(flag == true)
		{
			return exchangeService.executeBuyExchangeTrade(userId,noOfShares, companyName, sharePrice,"Buy");
			//Take it to Exchange Class
		}
		
		return res;
	}
	
	public int[] executeSellTrade(int userId,int noOfShares,String companyName,int sharePrice)
	{
		boolean flag = true;
		int res[]=new int[2];
		List<SortDTO> s = sortRepository.findAllBuyOrders();
		
		for(SortDTO i: s)
		{
			if(i.getCompanyName().equals(companyName) && i.getNoOfShares()>=noOfShares && i.getSharePrice()<=sharePrice )
			{
				 int shares = i.getNoOfShares() - noOfShares;
				 sortRepository.updateBuyOrderByOrderId(i.getOrderId(),shares);
				 flag=false;
				 res[0]= 1;
				 res[1]=0;
				return res;
			}
		}
		
		if(flag == true)
		{
			return exchangeService.executeSellExchangeTrade(userId,noOfShares, companyName, sharePrice,"Sell");
			//Take it to Exchange Class
		}
		
		return res;
	}
}
