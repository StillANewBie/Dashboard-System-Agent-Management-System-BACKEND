package com.mercury.pm.security.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 4270703855997440738L;
	
	private final String jwttoken;
	private final String username;
	
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
		this.username = "";
	}
	
	public JwtResponse(String jwttoken, String username) {
		this.jwttoken = jwttoken;
		this.username = username;
	}
	
	public String getToken() {
		return this.jwttoken;
	}

	public String getUsername() {
		return username;
	}
}
