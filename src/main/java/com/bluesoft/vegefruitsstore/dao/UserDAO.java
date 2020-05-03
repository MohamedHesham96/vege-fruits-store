package com.bluesoft.vegefruitsstore.dao;

import java.util.List;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.Collect;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
import com.bluesoft.vegefruitsstore.entity.Seller;

public interface UserDAO {

	public List<Balance> getAllBalance();
	
	public void saveBalance(Balance theBalance);
	
	public List<HeaderResult> getBalanceHeader();

	public void deleteBalance(int id);

	public List<HeaderResult> getRelayHeader();

	public List<HeaderResult> getCasherHeader(String casherName);
	
	public List<Balance> getBalanceByCasherName(String casherName);

	public List<Balance> getBalanceByCasherNameAndDate(String casherName, String date);
	
	public List<HeaderResult> getCasherHeaderByDate(String casherName, String date);

	public List<Seller> getAllSeller();
	
	public Seller getSellerById(int id);

	public void addCollect(Collect collect);

	public List<Seller> searchForSellerByName(String sellerName);

}
