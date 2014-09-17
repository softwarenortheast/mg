package com.northeast.minigenius.model;

/**
 * Interface that represents the retrieve of the users.
 * 
 * @author Vladwoguer Bezerra
 */
public interface UserService {
	public User retrieveUserByLogin(String login) throws UserNotFoundException;
}