package com.wiley.dto;

public class SellOrderDTO {

	private int orderId;
	private int noOfShares;
	private String companyName;
	private int sharePrice;
    public  SellOrderDTO() {}
	public SellOrderDTO(int orderId, int noOfShares, String companyName, int sharePrice) {
		super();
		this.orderId = orderId;
		this.noOfShares = noOfShares;
		this.companyName = companyName;
		this.sharePrice = sharePrice;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getNoOfShares() {
		return noOfShares;
	}
	public void setNoOfShares(int noOfShares) {
		this.noOfShares = noOfShares;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(int sharePrice) {
		this.sharePrice = sharePrice;
	}
	@Override
	public String toString() {
		return "SellOrderDTO [orderId=" + orderId + ", noOfShares=" + noOfShares + ", companyName=" + companyName
				+ ", sharePrice=" + sharePrice + "]";
	}
    
	
}
