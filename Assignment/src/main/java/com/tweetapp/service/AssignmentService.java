package com.tweetapp.service;

import java.util.List;
import java.util.Map;

import com.tweetapp.model.UserDetail;

public interface AssignmentService {

	public String registerUser(String username, String password);

	public UserDetail login(String username, String password);

	public List<UserDetail> getAllUsers();

	public String changePassword(String username, String newPassword);

	public String addTweet(UserDetail userDetail , String tweet);

	public List<String> viewMyTweets(UserDetail userDetail);

	public Map<UserDetail, List<String>> viewAllTweets();

	public List<UserDetail> viewAllUsers();

	public String resetPassword(String username, String newPassword);

}
