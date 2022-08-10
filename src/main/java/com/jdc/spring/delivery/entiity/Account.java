package com.jdc.spring.delivery.entiity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Role {
		ROLE_OWNER {
			@Override
			public String toString() {
				return "Owner";
			}
		}, ROLE_CUSTOMER {
			@Override
			public String toString() {
				return "Customer";
			}
		};
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Please enter Name.")
	private String name;
	@NotEmpty(message = "Please enter email.")
	@Column(unique = true)
	private String email;
	@NotEmpty(message = "Please enter password.")
	private String password;
	private boolean enable;
	
	@OneToOne(mappedBy = "account")
	private Profile profile;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Account() {
		enable = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
