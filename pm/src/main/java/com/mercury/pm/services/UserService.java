package com.mercury.pm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mercury.pm.beans.User;
import com.mercury.pm.beans.UserInfo;
import com.mercury.pm.daos.UserDao;
import com.mercury.pm.daos.UserInfoDao;
import com.mercury.pm.daos.UserJdbcDao;
import com.mercury.pm.http.Response;
import com.mercury.pm.mail.EmailService;
import com.mercury.pm.security.SecurityUtils;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private UserJdbcDao userJdbcDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public Response saveUser(User u) {
		try {
			userDao.saveAndFlush(u);
			return new Response(true);
		} catch (Exception e) {
			return new Response(false);
		}
	}
	
	public UserInfo getUserInfoById(int id) {
		return userInfoDao.findById(id).get();
	}
	
	public void saveUserInfo(UserInfo ui, int uid, boolean register) {
		userJdbcDao.saveUserInfo(ui, uid, register);
	}
	
	public void saveUserGroup(int uid, int gid) {
		userJdbcDao.saveUserGroup(uid, gid);
	}
	
	public void saveUserRole(int uid, int rid) {
		userJdbcDao.saveUserRole(uid, rid);
	}

	public User findByUserId(int id) {
		return userDao.findById(id).get();
	}
	
	public User register(User user) {
		// TODO: validation.
//		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userDao.save(user);
			// TODO: send email. assume username is the email for now
//			emailService.sendSimpleMessage(user.getUsername(), "Account Created", user.getUsername() + ", your account is created!");
//		} catch (Exception e) {
//			return null;
//		}
	}
	
	public UserInfo updateUserInfo(UserInfo ui) {
		return userInfoDao.save(ui);
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
	
	public List<User> getAllUsers() {
		return userDao.findAll();
	}
}
