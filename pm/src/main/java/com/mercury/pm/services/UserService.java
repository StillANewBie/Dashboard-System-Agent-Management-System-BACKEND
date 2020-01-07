package com.mercury.pm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.pm.beans.User;
import com.mercury.pm.daos.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
}
