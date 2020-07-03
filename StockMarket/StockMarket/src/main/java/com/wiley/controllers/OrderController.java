package com.wiley.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wiley.dto.BseDTO;
import com.wiley.dto.LoginDTO;
import com.wiley.dto.NseDTO;
import com.wiley.models.BseOrderTable;
import com.wiley.models.BuyOrderTable;
import com.wiley.models.NseOrderTable;
import com.wiley.models.SellOrderTable;
import com.wiley.models.Status;
import com.wiley.models.StatusLogin;
import com.wiley.models.Users;
import com.wiley.services.BuyOrderService;
import com.wiley.services.ExchangeService;
import com.wiley.services.SellOrderService;
import com.wiley.services.SortService;
import com.wiley.services.UserService;

@RestController
public class OrderController {
	
	@Autowired
	private BuyOrderService buyOrderService;
	
	@Autowired
	private SellOrderService sellOrderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	private SortService sortService;
	
	@PostMapping("/login")
	public StatusLogin login(@RequestBody LoginDTO l)
	{
		String userName = l.getUserName();
		String password = l.getPassword();
		int status = userService.loginUser(userName,password);
		if(status != 0)
		{
			 return new StatusLogin(true,"Login Success",status);
		}
		else
		{
			 return new StatusLogin(false,"Login Failed",status);
		}
	}
	
	@GetMapping("/viewNseBuyorders")
	public List<NseDTO> getNseBuyOrders()
	{      /* {
        "orderId": 1,
        "companyName": "apple",
        "noOfShares": 50,
        "orderType": "Buy",
        "sharePrice": 2000
    }*/
		return exchangeService.loadNseBuyOrders();
	}
	
	@GetMapping("/viewNseSellorders")
	public List<NseDTO> getNseSellOrders()
	{      /* {
        "orderId": 1,
        "companyName": "apple",
        "noOfShares": 50,
        "orderType": "Sell",
        "sharePrice": 2000
    }*/
		return exchangeService.loadNseSellOrders();
	}
	
	@GetMapping("/viewBseBuyorders")
	public List<BseDTO> getBseBuyOrders()
	{  
		   /* {
        "orderId": 1,
        "companyName": "apple",
        "noOfShares": 50,
        "orderType": "Buy",
        "sharePrice": 2000
    }*/
		return exchangeService.loadBseBuyOrders();
	}
	
	@GetMapping("/viewBseSellorders")
	public List<BseDTO> getBseSellOrders()
	{  
		   /* {
        "orderId": 1,
        "companyName": "apple",
        "noOfShares": 50,
        "orderType": "Sell",
        "sharePrice": 2000
    }*/
		return exchangeService.loadBseSellOrders();
	}
	

	
	@PostMapping("/cancelBuyOrder")
	public Status cancelBuyOrder(@RequestBody BuyOrderTable bot)
	{
		int orderId=bot.getOrderId();
		int i= buyOrderService.deleteBuyOrders(orderId);
		if (i==1)
		{
			return new Status(true,"success");
		}
		else
		{
			return new Status(false,"failed");
		}
	}
	@PostMapping("/cancelSellOrder")
	public Status cancelSellOrder(@RequestBody SellOrderTable sot )
	{
		int orderId=sot.getOrderId();
		int i= sellOrderService.deleteSellOrders(orderId);
		if (i==1)
		{
			return new Status(true,"success");
		}
		else
		{
			return new Status(false,"failed");
		}
		
	}
	
	@PostMapping("/adduser")
	public Status addUser(@RequestBody Users u)
	{
		String userName=u.getUserName();
		String password=u.getPassword();
		String email=u.getEmail();
		int i= userService.insertRecords(userName, password, email);
		if (i==1)
		{
			return new Status(true,"success");
		}
		else
		{
			return new Status(false,"failed");
		}
		
	}
	
	@PostMapping("/deleteuser")
	public Status deleteUser(@RequestBody Users u)
	{
		int userId=u.getUserId();
		int i= userService.deleteRecords(userId);
		if (i==1)
		{
			return new Status(true,"success");
		}
		else
		{
			return new Status(false,"failed");
		}
		
		
	}
	@PostMapping("/updateuser")
	public Status updateRecords(@RequestBody Users u)
	{
		
		String email=u.getEmail();
		String password=u.getPassword();
		int i=userService.updateRecords(email, password);
		if (i==1)
		{
			return new Status(true,"success");
		}
		else
		{
			return new Status(false,"failed");
		}
	}
	
	@PostMapping("/buyOrder")
	public Status buyOrder(@RequestBody NseOrderTable bot)
	{
		int res[];
		int userId = bot.getUserId();
		int noOfShares = bot.getNoOfShares();
		String companyName = bot.getCompanyName();
		int sharePrice = bot.getSharePrice();
		res=sortService.executeBuyTrade(userId,noOfShares, companyName, sharePrice);
		if (res[0]==1)
		{
			return new Status(true,"success",res[1]);
		}
		else
		{
			return new Status(false,"failed",res[1]);
		}	
	}
	
	@PostMapping("/sellOrder")
	public Status sellOrder(@RequestBody NseOrderTable sot)
	{
		int res[];
		int userId = sot.getUserId();
		int noOfShares = sot.getNoOfShares();
		String companyName = sot.getCompanyName();
		int sharePrice = sot.getSharePrice();
	    res = sortService.executeSellTrade(userId,noOfShares, companyName, sharePrice);
		if (res[0]==1)
		{
			return new Status(true,"success",res[1]);
		}
		else
		{
			return new Status(false,"failed",res[1]);
		}	
	}
	
	@GetMapping("viewNseOrder")
	public List<NseDTO> getUsersNseOrders(@RequestBody NseOrderTable sot)
	{
		
		/* {
        "orderId": 1,
        "companyName": "apple",
        "noOfShares": 50,
        "orderType": "Sell",
        "sharePrice": 2000
    }*/
		
		int userId = sot.getUserId();
		return exchangeService.loadUsersNseOrders(userId);
	}
	
	@GetMapping("viewBseOrder")
	public List<BseDTO> getUsersBseOrders(@RequestBody BseOrderTable sot)
	{
		
		/* {
        "orderId": 1,
        "companyName": "apple",
        "noOfShares": 50,
        "orderType": "Sell",
        "sharePrice": 2000
    }*/
		
		int userId = sot.getUserId();
		return exchangeService.loadUsersBseOrders(userId);
	}
	
	
}
