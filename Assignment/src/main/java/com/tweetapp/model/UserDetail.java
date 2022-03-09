package com.tweetapp.model;

public class UserDetail {
	
	private String username;
	private String password;
	
	public UserDetail() {
		super();
	}
	
	public UserDetail(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "{ Username : " + username + ", password : " + password + " }" ;
	}
	
	

}
