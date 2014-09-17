package com.northeast.minigenius.model;
/**
 * Exception for user not found.
 * @author Vladwoguer Bezerra
 *
 */
public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		super("User does not found");
	}
}
