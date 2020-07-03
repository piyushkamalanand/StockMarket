package com.wiley.dto;

public class SortDTO {

	private int orderId;
	private String companyName;
	private int noOfShares;
	private int sharePrice;
	
	
	public SortDTO()
	{
		
	}

	public SortDTO(int orderId, String companyName, int noOfShares, int sharePrice) {
		super();
		this.orderId = orderId;
		this.companyName = companyName;
		this.noOfShares = noOfShares;
		this.sharePrice = sharePrice;
	}



	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public int getNoOfShares() {
		return noOfShares;
	}


	public void setNoOfShares(int noOfShares) {
		this.noOfShares = noOfShares;
	}


	public int getSharePrice() {
		return sharePrice;
	}


	public void setSharePrice(int sharePrice) {
		this.sharePrice = sharePrice;
	}


	

	
	
}
