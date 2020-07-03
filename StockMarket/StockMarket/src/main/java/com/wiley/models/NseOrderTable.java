package com.wiley.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NseOrderTable {
	@Id
	@GeneratedValue
	private int orderId;
	private String companyName;
	private int noOfShares;
	private String orderType;
	private int sharePrice;
	private int userId;
	
	
	public NseOrderTable() {}


	public NseOrderTable(int orderId, String companyName, int noOfShares, String orderType, int sharePrice,
			int userId) {
		super();
		this.orderId = orderId;
		this.companyName = companyName;
		this.noOfShares = noOfShares;
		this.orderType = orderType;
		this.sharePrice = sharePrice;
		this.userId = userId;
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


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "NseOrderTable [orderId=" + orderId + ", companyName=" + companyName + ", noOfShares=" + noOfShares
				+ ", orderType=" + orderType + ", sharePrice=" + sharePrice + ", userId=" + userId + "]";
	}
	
}
