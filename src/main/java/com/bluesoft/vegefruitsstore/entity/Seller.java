package com.bluesoft.vegefruitsstore.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@Table(name = "seller")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "name")
	@Pattern(regexp = "[ ء-ي]+", message = "ادخل الاسم بشكل صحيح")
	String name;

	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
	@Where(clause = "cash != total_amount")
	@OrderBy("date DESC")
	private List<Balance> balances;

	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
	@Where(clause = "amount != 0")
	@OrderBy("date DESC")
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

	public List<Balance> getBalances() {
		return balances;
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
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

	public float getDrawee() {

		if (!masters.isEmpty())
			return masters.get(0).getAmount();
		else
			return 0;
	}

	public boolean checkSeller() {

		LocalDate lastCollect;
		LocalDate lastBalance;
		LocalDate today;

		if (!collects.isEmpty())
			lastCollect = LocalDate.parse(collects.get(0).getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
		else
			lastCollect = LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_LOCAL_DATE);

		if (!balances.isEmpty())
			lastBalance = LocalDate.parse(getBalances().get(0).getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
		else
			lastBalance = LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_LOCAL_DATE);

		today = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE);

		Duration betweenTodayAndBalance = Duration.between(lastBalance.atStartOfDay(), today.atStartOfDay());

		Duration betweenTodayAndCollect = Duration.between(lastCollect.atStartOfDay(), today.atStartOfDay());

		long DaysBetweenTodayAndBalance = betweenTodayAndBalance.toDays();

		long DaysBetweenTodayAndCollect = betweenTodayAndCollect.toDays();
//
//		System.out.println("betweenTodayAndBalance >> " + diffDays1);
//
//		System.out.println("betweenTodayAndCollect >> " + diffDays2);

		if (DaysBetweenTodayAndBalance > 5 && DaysBetweenTodayAndBalance < DaysBetweenTodayAndCollect) {

//			System.out.println("Alert");
			return false;

		} else {

//			System.out.println("Okey");
			return true;

		}

	}

}
