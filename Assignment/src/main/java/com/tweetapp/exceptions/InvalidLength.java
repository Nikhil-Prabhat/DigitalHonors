package com.tweetapp.exceptions;

public class InvalidLength extends Exception {

	private static String INVALID_LENGTH = "Invalid length , The length should be greater than or equal to 5 !";

	public InvalidLength(String params) {
		super(params + " : "+ INVALID_LENGTH);
	}

}
