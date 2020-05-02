package com.bluesoft.vegefruitsstore.entity;

public class HeaderResult {

	Long totalCount;
	Double totalWeight;
	Double totalCash;
	Double totalLater;
	Double totalAmount;

	String itemName;
	String clientName;
	String sellerName;
	String casherName;

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	public String getCasherName() {
		return casherName;
	}

	public void setCasherName(String casherName) {
		this.casherName = casherName;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Double getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(Double totalCash) {
		this.totalCash = totalCash;
	}

	public Double getTotalLater() {
		return totalLater;
	}

	public void setTotalLater(Double totalLater) {
		this.totalLater = totalLater;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
