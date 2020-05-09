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

@Entity
@Table(name = "seller")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "name")
	@Pattern(regexp = "[ ء-ي]+", message = "ادخل الاسم بشكل صحيح")
	String name;

	@OneToMany(mappedBy = "casher", fetch = FetchType.LAZY)
	private List<Balance> balances;

	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
	private List<Collect> collects;

	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
	@OrderBy("date DESC")
	private List<Master> masters;

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

	public List<Collect> getCollects() {
		return collects;
	}

	public void setCollects(List<Collect> collects) {
		this.collects = collects;
	}

	public List<Master> getMasters() {
		return masters;
	}

	public void setMasters(List<Master> masters) {
		this.masters = masters;
	}

}
