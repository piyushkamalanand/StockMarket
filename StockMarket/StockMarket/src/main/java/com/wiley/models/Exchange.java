package com.wiley.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Exchange {
	@Id
	@GeneratedValue
	private int exchangeId;
	private int feeLadder;
	@OneToMany(mappedBy="exchange")
	private List<OrderBook> orderBooks;
	public Exchange(int exchangeId, int feeLadder, List<OrderBook> orderBooks) {
		super();
		this.exchangeId = exchangeId;
		this.feeLadder = feeLadder;
		this.orderBooks = orderBooks;
	}
	public int getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(int exchangeId) {
		this.exchangeId = exchangeId;
	}
	public int getFeeLadder() {
		return feeLadder;
	}
	public void setFeeLadder(int feeLadder) {
		this.feeLadder = feeLadder;
	}
	public List<OrderBook> getOrderBooks() {
		return orderBooks;
	}
	public void setOrderBooks(List<OrderBook> orderBooks) {
		this.orderBooks = orderBooks;
	}
	@Override
	public String toString() {
		return "Exchange [exchangeId=" + exchangeId + ", feeLadder=" + feeLadder + ", orderBooks=" + orderBooks + "]";
	}
    
	
	
	
	
	

}
