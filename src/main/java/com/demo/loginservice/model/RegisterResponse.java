package com.demo.loginservice.model;

public class RegisterResponse {
	private final String email;
	private final long id;

	public RegisterResponse(String email, long id) {
		this.email = email;
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}
}
