package com.wiley.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.dto.BseDTO;
import com.wiley.dto.ExchangeDTO;
import com.wiley.dto.NseDTO;
import com.wiley.models.FeeLadder;
import com.wiley.models.Users;
import com.wiley.repositories.BseExchangeRepository;
import com.wiley.repositories.NseExchangeRepository;

@Service
public class ExchangeService {
	   @Autowired
	   private BseExchangeRepository bseExchangeRepository;
	   
	   @Autowired
	   private NseExchangeRepository nseExchangeRepository;
	   
	   //@Autowired
	   FeeLadder feeLadder;
	   
	   public List<NseDTO> loadNseBuyOrders()
	   {
		   return nseExchangeRepository.findAllNseOrders("Buy");
	   }
	   
	   public List<NseDTO> loadNseSellOrders()
	   {
		   return nseExchangeRepository.findAllNseOrders("Sell");
	   }
	   
	   public List<BseDTO> loadBseBuyOrders()
	   {
		   return bseExchangeRepository.findAllBseOrders("Buy");
	   }
	   
	   public List<BseDTO> loadBseSellOrders()
	   {
		   return bseExchangeRepository.findAllBseOrders("Sell");
	   }
		
	   public List<NseDTO> loadUsersNseOrders(int userId)
	   {
		   return nseExchangeRepository.findAllUsersOrders(userId);
	   }
	   
	   public List<BseDTO> loadUsersBseOrders(int userId)
	   {
		   return bseExchangeRepository.findAllUsersOrders(userId);
	   }
	   
	   int sharesBse = 0, sharesNse = 0;
		int noOfSharesBse = 0, noOfSharesNse = 0;
		int orderIdBse = 0, orderIdNse = 0;
		int sharePriceBse = 99999,sharePriceNse = 99999;
	   
		public int[] executeBuyExchangeTrade(int userId,int noOfShares,String companyName,int sharePrice,String orderType)
		{
			int res[]=new int[2];
			int fee = 0;

			if(noOfShares<=25)
			{
				fee = (int)noOfShares*sharePrice*2/100; 
			}
			else if(noOfShares>25 && noOfShares<=50)
			{
				fee = (int)noOfShares*sharePrice*2/100;
			}
			else if(noOfShares>50 && noOfShares<=100)
			{
				fee = (int)noOfShares*sharePrice*4/100;
			}
			else if(noOfShares>100&& noOfShares<=200)
			{
				fee = (int)noOfShares*sharePrice*5/100;
			}
			else if(noOfShares>200)
			{
				fee = (int)noOfShares*sharePrice*8/100;
			}
			else
			{
				fee = (int)noOfShares*sharePrice*15/100;
			}
			
			fee = sharePrice + fee;
			
			res[1] = fee;

			System.out.println(res[1]);
			boolean flag = true;
			
			
			List<ExchangeDTO> s = bseExchangeRepository.findAllOrders();
			
			for(ExchangeDTO i: s)
			{
				if(i.getCompanyName().equals(companyName) && i.getNoOfShares()!=0 && 
						i.getSharePrice()>=sharePrice && i.getOrderType().equals("Sell"))
				{
					
					 if(i.getNoOfShares()>=noOfShares) {
						 sharePriceBse = i.getSharePrice();
						 sharesBse = i.getNoOfShares() - noOfShares;
						 noOfSharesBse = i.getNoOfShares();
						 orderIdBse = i.getOrderId();
					 }
					else {
						 sharePriceBse = i.getSharePrice();
						 sharesBse = noOfShares - i.getNoOfShares();
						 noOfSharesBse = i.getNoOfShares();
						 orderIdBse = i.getOrderId();
					}
					 flag = false;
					 break;
				}
			}
			
			s = nseExchangeRepository.findAllOrders();
			
			for(ExchangeDTO i: s)
			{
				if(i.getCompanyName().equals(companyName) && i.getNoOfShares()!=0 && 
						i.getSharePrice()>=sharePrice && i.getOrderType().equals("Sell"))
				{
					
					 if(i.getNoOfShares()>=noOfShares) {
						 sharePriceNse = i.getSharePrice();
						 sharesNse = i.getNoOfShares() - noOfShares;
						 noOfSharesNse = i.getNoOfShares();
						 orderIdNse = i.getOrderId();
						 System.out.println("In Nse");
					 }
					else {
						 sharePriceNse = i.getSharePrice();
						 sharesNse = noOfShares - i.getNoOfShares();
						 noOfSharesNse = i.getNoOfShares();
						 orderIdNse = i.getOrderId();
					}
					 flag = false;
					 break;
				}
			}
			
			if(sharePriceNse >= sharePriceBse && flag==false)
			{
				if(noOfSharesBse>=noOfShares) {
					 
					 bseExchangeRepository.updateBseOrderByOrderId(orderIdBse,sharesBse);
					 bseExchangeRepository.addOrderIntoBse(companyName,0,orderType,sharePrice,userId);
				 }
				else {
					 
					 bseExchangeRepository.updateBseOrderByOrderId(orderIdBse,0);
					 bseExchangeRepository.addOrderIntoBse(companyName,sharesBse,orderType,sharePrice,userId);
				}
				
			}
			else if(sharePriceNse < sharePriceBse && flag==false)
			{
				if(noOfSharesNse>=noOfShares) {
					 
					 nseExchangeRepository.updateNseOrderByOrderId(orderIdNse,sharesNse);
					 nseExchangeRepository.addOrderIntoNse(companyName,0,orderType,sharePrice,userId);
				 }
				else {
					 
					 nseExchangeRepository.updateNseOrderByOrderId(orderIdNse,0);
					 nseExchangeRepository.addOrderIntoNse(companyName,sharesNse,orderType,sharePrice,userId);
				}
				
			}
			
			// If possible we can give here choice to the User that where he/she want to put their order.
			
			if(flag == true)
			{
				bseExchangeRepository.addOrderIntoBse(companyName,noOfShares,orderType,sharePrice,userId);
				res[0]=0;
			}			
			else
			{
				res[0]=1;
			}
			return res;
		}
		
		public int[] executeSellExchangeTrade(int userId,int noOfShares,String companyName,int sharePrice,String orderType)
		{
			int fee = 0;
			
			int res[]=new int[2];
			if(noOfShares<=25)
			{
				fee = (int)noOfShares*sharePrice*2/100; 
			}
			else if(noOfShares>25 && noOfShares<=50)
			{
				fee = (int)noOfShares*sharePrice*2/100;
			}
			else if(noOfShares>50 && noOfShares<=100)
			{
				fee = (int)noOfShares*sharePrice*4/100;
			}
			else if(noOfShares>100&& noOfShares<=200)
			{
				fee = (int)noOfShares*sharePrice*5/100;
			}
			else if(noOfShares>200)
			{
				fee = (int)noOfShares*sharePrice*8/100;
			}
			else
			{
				fee = (int)noOfShares*sharePrice*15/100;
			}
			
			fee = sharePrice + fee;
			res[1]=fee;
			
			boolean flag = true;
			List<ExchangeDTO> s = bseExchangeRepository.findAllOrdersDesc();
			
			for(ExchangeDTO i: s)
			{
				if(i.getCompanyName().equals(companyName) && i.getNoOfShares()!=0 && i.getSharePrice()<=sharePrice && i.getOrderType().equals("Buy"))
				{
					if(i.getNoOfShares()>=noOfShares) {
						 sharePriceBse = i.getSharePrice();
						 sharesBse = i.getNoOfShares() - noOfShares;
						 noOfSharesBse = i.getNoOfShares();
						 orderIdBse = i.getOrderId();
					 }
					else {
						 sharePriceBse = i.getSharePrice();
						 sharesBse = noOfShares - i.getNoOfShares();
						 noOfSharesBse = i.getNoOfShares();
						 orderIdBse = i.getOrderId();
					}
					 flag = false;
					 break;
				}
			}
			
			s = nseExchangeRepository.findAllOrdersDesc();
			
			for(ExchangeDTO i: s)
			{
				if(i.getCompanyName().equals(companyName) && i.getNoOfShares()!=0 && i.getSharePrice()<=sharePrice && i.getOrderType().equals("Buy"))
				{
					if(i.getNoOfShares()>=noOfShares) {
						 sharePriceNse = i.getSharePrice();
						 sharesNse = i.getNoOfShares() - noOfShares;
						 noOfSharesNse = i.getNoOfShares();
						 orderIdNse = i.getOrderId();
					 }
					else {
						 sharePriceBse = i.getSharePrice();
						 sharesNse = noOfShares - i.getNoOfShares();
						 noOfSharesNse = i.getNoOfShares();
						 orderIdNse = i.getOrderId();
					}
					 flag = false;
					 break;
				}
			}
			
			if(sharePriceNse >= sharePriceBse && flag==false)
			{
				if(noOfSharesBse>=noOfShares) {
					 
					 bseExchangeRepository.updateBseOrderByOrderId(orderIdBse,sharesBse);
					 bseExchangeRepository.addOrderIntoBse(companyName,0,orderType,sharePrice,userId);
				 }
				else {
					 
					 bseExchangeRepository.updateBseOrderByOrderId(orderIdBse,0);
					 bseExchangeRepository.addOrderIntoBse(companyName,sharesBse,orderType,sharePrice,userId);
				}
				
			}
			else if(sharePriceNse < sharePriceBse && flag==false)
			{
				if(noOfSharesNse>=noOfShares) {
					 
					 nseExchangeRepository.updateNseOrderByOrderId(orderIdNse,sharesNse);
					 nseExchangeRepository.addOrderIntoNse(companyName,0,orderType,sharePrice,userId);
				 }
				else {
					 
					 nseExchangeRepository.updateNseOrderByOrderId(orderIdNse,0);
					 nseExchangeRepository.addOrderIntoNse(companyName,sharesNse,orderType,sharePrice,userId);
				}
				
			}
			
			if(flag == true)
			{
				nseExchangeRepository.addOrderIntoNse(companyName,noOfShares,orderType,sharePrice,userId);
				res[0]=0;
			}
			else
			{
				res[0]= 1;
			}
			return res;
			
		}
		

}
