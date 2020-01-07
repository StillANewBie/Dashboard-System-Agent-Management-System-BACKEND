package com.mercury.pm.http;

import com.mercury.pm.beans.UserInfo;

public class UserInfoResponse extends Response {
	private UserInfo userInfo;

	public UserInfoResponse(boolean success, UserInfo userInfo) {
		super(success);
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "UserInfoResponse [userInfo=" + userInfo + "]";
	}

}
