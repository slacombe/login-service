package com.demo.loginservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "USER")
@DiscriminatorValue("USER")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	@Column(name="EMAIL", nullable = false, unique = true)
	private String email;

	@Column(name="PASSWORD", nullable = false)
	private String password;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
