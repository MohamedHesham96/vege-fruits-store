package com.bluesoft.vegefruitsstore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "name")
	@Pattern(regexp = "[ ء-ي]+", message = "ادخل الاسم بشكل صحيح")
	String name;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private List<Balance> balances;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	@Where(clause = "current_count > 0")
	@OrderBy("item.id")
	private List<ClientBalance> clientBalances;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private List<ClientBalance> allClientBalances;

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

	public boolean haveBalances() {

		return !allClientBalances.isEmpty() ? true : false;

	}
}
