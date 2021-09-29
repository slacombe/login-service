package com.demo.loginservice;

public class ValidateResponse {
	private final String email;
	private final String newToken;

	public ValidateResponse(String email, String newToken)
	{
		this.email = email;
		this.newToken = newToken;
	}

	public String getEmail() {
		return email;
	}

	public String getNewToken() {
		return newToken;
	}
}
