package com.wiley.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OrderBook {
	@Id
	@GeneratedValue
	private int orderBookId;
	@OneToMany(mappedBy="orderBook")
	private List<BuyOrderTable> buyOrderTable;
	@OneToMany(mappedBy="orderBook")
	private List<SellOrderTable> sellOrderTable;
	@ManyToOne
	@JoinColumn(name="exchange_id")
	private Exchange exchange;
   	public OrderBook() {}
	public OrderBook(int orderBookId, List<BuyOrderTable> buyOrderTable, List<SellOrderTable> sellOrderTable,
			Exchange exchange) {
		super();
		this.orderBookId = orderBookId;
		this.buyOrderTable = buyOrderTable;
		this.sellOrderTable = sellOrderTable;
		this.exchange = exchange;
	}
	public int getOrderBookId() {
		return orderBookId;
	}
	public void setOrderBookId(int orderBookId) {
		this.orderBookId = orderBookId;
	}
	public List<BuyOrderTable> getBuyOrderTable() {
		return buyOrderTable;
	}
	public void setBuyOrderTable(List<BuyOrderTable> buyOrderTable) {
		this.buyOrderTable = buyOrderTable;
	}
	public List<SellOrderTable> getSellOrderTable() {
		return sellOrderTable;
	}
	public void setSellOrderTable(List<SellOrderTable> sellOrderTable) {
		this.sellOrderTable = sellOrderTable;
	}
	public Exchange getExchange() {
		return exchange;
	}
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
	@Override
	public String toString() {
		return "OrderBook [orderBookId=" + orderBookId + ", buyOrderTable=" + buyOrderTable + ", sellOrderTable="
				+ sellOrderTable + ", exchange=" + exchange + "]";
	}
   	
   	

	

}
