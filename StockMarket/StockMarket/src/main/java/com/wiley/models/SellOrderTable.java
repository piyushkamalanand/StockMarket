package com.wiley.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SellOrderTable {

		@Id
		@GeneratedValue
		private int orderId;
		private int noOfShares;
		private String companyName;
		private int sharePrice;
		@ManyToOne
		@JoinColumn(name="orderbook_id")
		private OrderBook orderBook;
		@ManyToOne
		@JoinColumn(name="user_id")
		private Users user_id;
		public SellOrderTable() {}
		
		public SellOrderTable(int orderId, int noOfShares, String companyName, int sharePrice, OrderBook orderBook,
				Users user_id) {
			super();
			this.orderId = orderId;
			this.noOfShares = noOfShares;
			this.companyName = companyName;
			this.sharePrice = sharePrice;
			this.orderBook = orderBook;
			this.user_id = user_id;
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
		public OrderBook getOrderBook() {
			return orderBook;
		}
		public void setOrderBook(OrderBook orderBook) {
			this.orderBook = orderBook;
		}
		public Users getUser_id() {
			return user_id;
		}
		public void setUser_id(Users user_id) {
			this.user_id = user_id;
		}
		@Override
		public String toString() {
			return "SellOrderTable [orderId=" + orderId + ", noOfShares=" + noOfShares + ", companyName=" + companyName
					+ ", sharePrice=" + sharePrice + ", orderBook=" + orderBook + ", user_id=" + user_id + "]";
		}

}
