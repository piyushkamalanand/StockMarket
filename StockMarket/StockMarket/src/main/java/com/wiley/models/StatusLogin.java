package com.wiley.models;

public class StatusLogin {
	
	private boolean status;
    private String message;
    private int userId;
    public StatusLogin() {}
	public StatusLogin(boolean status, String message, int userId) {
		super();
		this.status = status;
		this.message = message;
		this.userId = userId;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "StatusLogin [status=" + status + ", message=" + message + ", userId=" + userId + "]";
	}

    
}
