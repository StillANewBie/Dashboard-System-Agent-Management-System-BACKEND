package com.mercury.pm.security.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 4270703855997440738L;
	
	private final String jwttoken;
	
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	public String getToken() {
		return this.jwttoken;
	}
}
