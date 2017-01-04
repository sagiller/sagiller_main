package com.sagiller.main.service;

import java.util.List;

import com.sagiller.main.entity.User;

public interface UserService {

	List<User> getUserList(int offset, int limit);
	 
}
