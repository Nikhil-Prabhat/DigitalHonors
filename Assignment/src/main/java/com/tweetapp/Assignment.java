package com.tweetapp;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.tweetapp.exceptions.InvalidInput;
import com.tweetapp.exceptions.InvalidLength;
import com.tweetapp.model.UserDetail;
import com.tweetapp.service.AssignmentService;
import com.tweetapp.service.AssignmentServiceImpl;

public class Assignment {

	private String USERNAME = "username";
	private String PASSWORD = "password";

	public static Scanner sc;
	public AssignmentService assignmentServiceImpl;

	public Assignment() {
		assignmentServiceImpl = new AssignmentServiceImpl();
	}

	public static void main(String[] args) throws Exception {

		sc = new Scanner(System.in);
		Assignment assignment = new Assignment();
		assignment.nonLoggedInUserMenu();

	}

	private void nonLoggedInUserMenu() throws InvalidInput {
		while (true) {
			System.out.println(" *****************  WELCOME TO TWEET APP !!  ********************");
			System.out.println(" 1. Register");
			System.out.println(" 2. Login");
			System.out.println(" 3. Forget Password");
			System.out.println("Please choose your option");
			int input = Integer.parseInt(sc.nextLine());

			switch (input) {
			case 1:
				registerUser();
				break;
			case 2:
				login();
				break;
			case 3:
				forgetPassword();
				break;
			default:
				throw new InvalidInput();
			}
		}
	}

	private void forgetPassword() {
		System.out.println("NOTE :: Password should be greater than or equal to 5 in length ");
		try {
			System.out.println("Enter username to change password");
			String username = sc.nextLine();

			if (username.length() < 5) {
				throw new InvalidLength(USERNAME);
			}

			System.out.println("Enter new password");
			String password = sc.nextLine();

			if (password.length() < 5) {
				throw new InvalidLength(PASSWORD);
			}

			String passwordChanged = assignmentServiceImpl.changePassword(username, password);
			System.out.println(passwordChanged);

		} catch (InvalidLength e) {
			e.printStackTrace();
		}

	}

	private void login() throws InvalidInput {

		System.out.println("NOTE :: Username and password should be greater than or equal to 5 in length ");

		try {

			System.out.println("Enter username");
			String username = sc.nextLine();

			if (username.length() < 5) {
				throw new InvalidLength(USERNAME);
			}

			System.out.println("Enter password");
			String password = sc.nextLine();

			if (password.length() < 5) {
				throw new InvalidLength(PASSWORD);
			}
			UserDetail userDetail = assignmentServiceImpl.login(username, password);
			if (userDetail != null)
				loggedInUserMenu(userDetail);
			else
				System.out.println("Invalid Credentials !!");

		} catch (InvalidLength e) {
			e.printStackTrace();
		}

	}

	private void registerUser() {

		System.out.println("NOTE :: Username and password should be greater than or equal to 5 in length ");

		try {
			System.out.println("Enter username");
			String username = sc.nextLine();

			if (username.length() < 5) {
				throw new InvalidLength(USERNAME);
			}

			System.out.println("Enter password");
			String password = sc.nextLine();

			if (password.length() < 5) {
				throw new InvalidLength(PASSWORD);
			}

			String registerUser = assignmentServiceImpl.registerUser(username, password);
			System.out.println(registerUser);

		} catch (InvalidLength e) {
			e.printStackTrace();
		}

	}

	private void loggedInUserMenu(UserDetail userDetail) throws InvalidInput {
		boolean keepTakingInput = true;
		while (keepTakingInput) {
			System.out.println("-- ***** LoggedIn User : " + userDetail.getUsername() + " **** --");
			System.out.println(" 1. Post a tweet");
			System.out.println(" 2. View my tweets");
			System.out.println(" 3. View all tweets");
			System.out.println(" 4. View all users");
			System.out.println(" 5. Reset Password");
			System.out.println(" 6. Logout");
			System.out.println("Please choose your option");
			int input = Integer.parseInt(sc.nextLine());

			switch (input) {
			case 1:
				postTweet(userDetail);
				break;
			case 2:
				viewMyTweet(userDetail);
				break;
			case 3:
				viewAllTweets();
				break;
			case 4:
				viewAllUsers();
				break;
			case 5:
				resetPassword(userDetail);
				break;
			case 6:
				keepTakingInput = false;
				break;
			default:
				throw new InvalidInput();
			}

		}

	}

	private void resetPassword(UserDetail userDetail) {
		System.out.println("NOTE :: Password should be greater than or equal to 5 in length ");
		try {
			System.out.println("Enter the new password");
			String newPassword = sc.nextLine();

			if (newPassword.length() < 5) {
				throw new InvalidLength(PASSWORD);
			}

			String changePassword = assignmentServiceImpl.changePassword(userDetail.getUsername(), newPassword);
			System.out.println(changePassword);
		} catch (InvalidLength e) {
			e.printStackTrace();
		}

	}

	private void viewAllUsers() {
		List<UserDetail> viewAllUsers = assignmentServiceImpl.viewAllUsers();
		for (UserDetail userDetail : viewAllUsers) {
			System.out.println(userDetail);
		}

	}

	private void viewAllTweets() {
		Map<UserDetail, List<String>> viewAllTweets = assignmentServiceImpl.viewAllTweets();
		for (Map.Entry<UserDetail, List<String>> tweets : viewAllTweets.entrySet()) {
			String username = tweets.getKey().getUsername();
			List<String> userTweets = tweets.getValue();
			for (String tweet : userTweets) {
				System.out.println(username + " : " + tweet);
			}
		}

	}

	private void viewMyTweet(UserDetail userDetail) {
		List<String> viewMyTweets = assignmentServiceImpl.viewMyTweets(userDetail);
		System.out.println(" Following are my tweets :");
		for (String myTweet : viewMyTweets)
			System.out.println(myTweet);

	}

	private void postTweet(UserDetail userDetail) {
		System.out.println("Please write your post");
		String post = sc.nextLine();
		String addTweet = assignmentServiceImpl.addTweet(userDetail, post);
		System.out.println(addTweet);
	}

}
