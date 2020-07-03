package com.wiley.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users {
	@Id
	@GeneratedValue
	private int userId;
	@Column(unique=true)
	private String userName;
	private String password;
	private String email;
	@OneToMany(mappedBy="user_id")
	private List<SellOrderTable> sellOrders;
	@OneToMany(mappedBy="user_id")
	private List<BuyOrderTable> buyOrders;
	public Users() {}
	public Users(int userId, String userName, String password, String email, List<SellOrderTable> sellOrders,
			List<BuyOrderTable> buyOrders) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.sellOrders = sellOrders;
		this.buyOrders = buyOrders;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<SellOrderTable> getSellOrders() {
		return sellOrders;
	}
	public void setSellOrders(List<SellOrderTable> sellOrders) {
		this.sellOrders = sellOrders;
	}
	public List<BuyOrderTable> getBuyOrders() {
		return buyOrders;
	}
	public void setBuyOrders(List<BuyOrderTable> buyOrders) {
		this.buyOrders = buyOrders;
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", sellOrders=" + sellOrders + ", buyOrders=" + buyOrders + "]";
	}

}
