package com.demo.loginservice.model;

import java.util.UUID;

public class RegisterResponse {
	private final String email;
	private final UUID uuid;

	public RegisterResponse(String email, UUID uuid)
	{
		this.email = email;
		this.uuid = uuid;
	}

	public String getEmail() {
		return email;
	}

	public UUID getUuid() {
		return uuid;
	}
}
