package com.wiley.models;

public class Status {
	private boolean status;
     private String message;
     private int fee;
     public Status() {}
     public Status(boolean status, String message){
		this.status=status;
		this.message=message;
     }
     public Status(boolean status, String message,int fee){
 		this.status=status;
 		this.message=message;
 		this.fee=fee;
      }
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	@Override
	public String toString() {
		return "Status [status=" + status + ", message=" + message + ", fee=" + fee + "]";
	}
     



}
