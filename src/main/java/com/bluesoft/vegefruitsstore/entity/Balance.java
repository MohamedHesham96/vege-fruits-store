package com.bluesoft.vegefruitsstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "balance")
public class Balance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "item_name")
	String itemName;

	@Column(name = "count")
	int counter;

	@Column(name = "weight")
	float weight;

	@Column(name = "kilo_price")
	float kiloPrice;

	@Column(name = "cash")
	float cash;

	@Column(name = "later")
	float later;

	@Column(name = "total_amount")
	float totalAmount;

	@Column(name = "seller_name")
	String sellerName;

	@Column(name = "date")
	String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getKiloPrice() {
		return kiloPrice;
	}

	public void setKiloPrice(float kiloPrice) {
		this.kiloPrice = kiloPrice;
	}

	public float getCash() {
		return cash;
	}

	public void setCash(float cash) {
		this.cash = cash;
	}

	public float getLater() {
		return later;
	}

	public void setLater(float later) {
		this.later = later;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
