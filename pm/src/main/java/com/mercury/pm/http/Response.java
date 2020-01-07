package com.mercury.pm.http;

public class Response {
	private boolean success;
	private int code;
	private String message;

	public Response() {
		super();
	}

	public Response(boolean success, int doe, String message) {
		super();
		this.success = success;
		this.code = doe;
		this.message = message;
	}

	public Response(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public Response(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [success=" + success + ", code=" + code + ", message=" + message + "]";
	}

}
