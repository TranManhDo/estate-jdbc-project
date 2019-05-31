package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService{

	@Override
	public UserDTO save(UserDTO newUser) {
		UserConverter userConverter = new UserConverter();
		
		return null;
	}

}
