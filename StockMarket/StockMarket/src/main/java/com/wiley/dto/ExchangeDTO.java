package com.wiley.dto;

public class ExchangeDTO {
	
	private int orderId;
	private String companyName;
	private int noOfShares;
	private int sharePrice;
	private String orderType;
	public ExchangeDTO(int orderId, String companyName, int noOfShares, int sharePrice, String orderType) {
		super();
		this.orderId = orderId;
		this.companyName = companyName;
		this.noOfShares = noOfShares;
		this.sharePrice = sharePrice;
		this.orderType = orderType;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	@Override
	public String toString() {
		return "ExchangeDTO [orderId=" + orderId + ", companyName=" + companyName + ", noOfShares=" + noOfShares
				+ ", sharePrice=" + sharePrice + ", orderType=" + orderType + "]";
	}
	
	

}
