package com.demo.loginservice.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "public")
public class User {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GenericGenerator(
			name = "table",
			strategy = "enhanced-table",
			parameters = {
					@org.hibernate.annotations.Parameter(
							name = "table_name",
							value = "sequence_table"
					)
			})
	@GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
	private Long id;
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	@Column(name = "email_validated")
	private boolean emailValidated;

	public User() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean getEmailValidated() {
		return emailValidated;
	}

	public void setEmailValidated(boolean emailValidated) {
		this.emailValidated = emailValidated;
	}
}
