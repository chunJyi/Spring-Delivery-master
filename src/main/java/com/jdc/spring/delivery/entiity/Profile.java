package com.jdc.spring.delivery.entiity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PROFILE")
@Data
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Gender {
		Male, Female
	}

	@Id
	@Column(name = "account_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "account", referencedColumnName = "id")
	private Account account;

	private Gender gender;
	private LocalDate dob;

	@Embedded
	private Address address;
	
	public Profile() {
		account = new Account();
		address = new Address();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
		this.id = account.getId();
	}


}
