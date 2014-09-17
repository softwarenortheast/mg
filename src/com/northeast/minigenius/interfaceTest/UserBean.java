package com.northeast.minigenius.interfaceTest;

import com.northeast.minigenius.model.User;
import com.northeast.minigenius.model.UserNotFoundException;
import com.northeast.minigenius.model.UserService;


/**
 * The mock implemetation of UserService.
 * @author Vladwoguer Bezerra
 */
public class UserBean implements UserService {
	public UserBean(){
		
	}
	@Override
	public User retrieveUserByLogin(String login) throws UserNotFoundException{
		return new User(login); // TODO Vladwoguer Bezerra May 2014
	}

}
