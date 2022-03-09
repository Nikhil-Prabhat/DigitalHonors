package com.tweetapp.exceptions;

public class InvalidInput extends Exception {

	private static String INVALID_INPUT = "The input is invalid. Please input valid input";

	public InvalidInput() {
		super(INVALID_INPUT);
	}

}
