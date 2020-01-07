package com.mercury.pm.http;

import com.mercury.pm.beans.User;

public class AuthenticationSuccessResponse extends Response {
	private User user;

	public AuthenticationSuccessResponse(boolean success, int code, String message, User user) {
		super(success, code, message);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AuthenticationSuccessResponse [user=" + user + "]";
	}

}
