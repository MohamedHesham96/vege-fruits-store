package com.bluesoft.vegefruitsstore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "casher")
public class Casher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "name")
	@Pattern(regexp = "[ ء-ي]+", message = "ادخل الاسم بشكل صحيح")
	String name;

	@Column(name = "password")
	String password;

	@OneToMany(mappedBy = "casher", fetch = FetchType.LAZY)
	private List<Balance> balances;

	@OneToMany(mappedBy = "casher", fetch = FetchType.LAZY)
	@Where(clause = "count > 0")
	private List<ClientBalance> clientBalances;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Balance> getBalances() {
		return balances;
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}

	public List<ClientBalance> getClientBalances() {
		return clientBalances;
	}

	public void setClientBalances(List<ClientBalance> clientBalances) {
		this.clientBalances = clientBalances;
	}

}
