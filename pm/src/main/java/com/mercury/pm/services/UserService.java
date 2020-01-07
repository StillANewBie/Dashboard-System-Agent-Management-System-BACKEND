package com.mercury.pm.services;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mercury.pm.beans.User;
import com.mercury.pm.daos.UserDao;
import com.mercury.pm.http.Response;
import com.mercury.pm.mail.EmailService;
import com.mercury.pm.security.SecurityUtils;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public Response register(User user) {
		// TODO
		return null;
	}
	
	public Response changePassword(User user, Authentication authentication) {
		if (user.getUsername().equals(authentication.getName()) || SecurityUtils.isAdmin(authentication.getAuthorities())) {
			User u = userDao.findByUsername(user.getUsername());
			u.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.save(u);
		} else {
			return new Response(false);
		}
		
		return new Response(true);
	}
	
	public Response deleteUser(int id) {
		if (userDao.findById(id).get() != null) {
			userDao.deleteById(id);
			return new Response(true);
		} else {
			return new Response(false, "User is not found!");
		}
	}
}
