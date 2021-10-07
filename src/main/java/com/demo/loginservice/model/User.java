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
}
