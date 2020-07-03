package com.wiley.dto;

public class BseDTO {
	private int orderId;
	private String companyName;
	private int noOfShares;
	private String orderType;
	private int sharePrice;
	public BseDTO() {}
	public BseDTO(int orderId, String companyName, int noOfShares, String orderType, int sharePrice) {
		super();
		this.orderId = orderId;
		this.companyName = companyName;
		this.noOfShares = noOfShares;
		this.orderType = orderType;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public int getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(int sharePrice) {
		this.sharePrice = sharePrice;
	}
	@Override
	public String toString() {
		return "BseDTO [orderId=" + orderId + ", companyName=" + companyName + ", noOfShares=" + noOfShares
				+ ", orderType=" + orderType + ", sharePrice=" + sharePrice + "]";
	}
	

}
