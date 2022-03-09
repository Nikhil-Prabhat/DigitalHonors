package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.model.UserDetail;

public interface DatabaseRepository {

	public String registerUser(String username, String password);

	public UserDetail login(String username, String password);
	
	public List<UserDetail> getAllUsers();

	public String changePassword(String username,String newPassword);

}
