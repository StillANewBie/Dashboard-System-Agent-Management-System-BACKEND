package com.mercury.pm.http;

import com.mercury.pm.beans.Login;

public class AuthenticationSuccessResponse extends Response {
	private Login user;

	public AuthenticationSuccessResponse(boolean success, int code, String message, Login user) {
		super(success, code, message);
		this.user = user;
	}

	public Login getUser() {
		return user;
	}

	public void setUser(Login user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AuthenticationSuccessResponse [user=" + user + "]";
	}

}
