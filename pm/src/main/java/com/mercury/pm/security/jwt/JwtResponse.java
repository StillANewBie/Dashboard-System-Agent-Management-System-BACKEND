package com.mercury.pm.security.jwt;

import java.io.Serializable;

import com.mercury.pm.beans.User;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 4270703855997440738L;
	
	private final String jwttoken;
	private final User user;
	
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
		this.user = null;
	}
	
	public JwtResponse(String jwttoken, User user) {
		this.jwttoken = jwttoken;
		this.user = user;
	}
	
	public String getToken() {
		return this.jwttoken;
	}

	public User getUser() {
		return user;
	}
}
