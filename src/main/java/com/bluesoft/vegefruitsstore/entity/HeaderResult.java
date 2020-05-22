package com.bluesoft.vegefruitsstore.entity;

public class HeaderResult {

	Long totalCount;
	Double kiloPriceAvg;
	Double totalWeight;
	Double totalCash;
	Double totalLater;
	Double totalAmount;

	String maxDate;

	String itemName;
	String clientName;
	String sellerName;
	String sellerid;
	int SellerId;
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

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
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

	public Double getKiloPriceAvg() {
		return kiloPriceAvg;
	}

	public void setKiloPriceAvg(Double kiloPriceAvg) {
		this.kiloPriceAvg = kiloPriceAvg;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public int getSellerId() {
		return SellerId;
	}

	public void setSellerId(int sellerId) {
		SellerId = sellerId;
	}

}
