package com.tweetapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tweetapp.dao.DatabaseRepository;
import com.tweetapp.dao.DatabaseRepositoryImpl;
import com.tweetapp.model.UserDetail;

public class AssignmentServiceImpl implements AssignmentService {
	
	private String TWEET_SUCCESS = "Tweet successful";
	
	public Map<UserDetail,List<String>> allPosts;
	public DatabaseRepository databaseRepositoryImpl;
	
	public AssignmentServiceImpl()
	{
		allPosts = new HashMap<UserDetail,List<String>>();
		databaseRepositoryImpl = new DatabaseRepositoryImpl();
	}
	
	public String registerUser(String username, String password) {
		String registerUser = databaseRepositoryImpl.registerUser(username, password);
		return registerUser;
	}

	public UserDetail login(String username, String password) {
		UserDetail loginUser = databaseRepositoryImpl.login(username, password);
		return loginUser;
	}

	
	public List<UserDetail> getAllUsers() {
		List<UserDetail> allUsers = databaseRepositoryImpl.getAllUsers();
		return allUsers;
	}

	public String changePassword(String username, String newPassword) {
		String changePassword = databaseRepositoryImpl.changePassword(username, newPassword);
		return changePassword;
	}

	public String addTweet(UserDetail userDetail , String tweet) {
				
		if(allPosts.containsKey(userDetail))
		{
			List<String> tweetList = allPosts.get(userDetail);
			tweetList.add(tweet);
			allPosts.put(userDetail, tweetList);
		}
		else
		{
			List<String> tweetList = new ArrayList<String>();
			tweetList.add(tweet);
			allPosts.put(userDetail, tweetList);
		}
		
		return TWEET_SUCCESS;
	}

	public List<String> viewMyTweets(UserDetail currentUser) {
		
		List<String> allMyPosts = allPosts.get(currentUser);
		return allMyPosts;
		
	}

	public Map<UserDetail,List<String>> viewAllTweets() {
		return allPosts;
	}

	public List<UserDetail> viewAllUsers() {
		List<UserDetail> allUsers = getAllUsers();
		return allUsers;
	}

	public String resetPassword(String username, String newPassword) {
		
		String passwordChanged = changePassword(username, newPassword);
		return passwordChanged;
		
	}

	

}
