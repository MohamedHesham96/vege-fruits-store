package com.bluesoft.vegefruitsstore.dao;

import java.util.List;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.Client;
import com.bluesoft.vegefruitsstore.entity.Collect;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
import com.bluesoft.vegefruitsstore.entity.Master;
import com.bluesoft.vegefruitsstore.entity.MasterResult;
import com.bluesoft.vegefruitsstore.entity.Seller;

public interface UserDAO {

	public List<Balance> getAllBalance();

	public void saveBalance(Balance theBalance);

	public List<HeaderResult> getBalanceHeader();

	public void deleteBalance(int id);

	public List<HeaderResult> getRelayHeader();

	public HeaderResult getCasherHeader(String casherName);

	public HeaderResult getCasherHeaderByDate(String casherName, String date);

	public List<Balance> getBalanceByCasherName(String casherName);

	public List<Balance> getBalanceByCasherNameAndDate(String casherName, String date);

	public List<Seller> getAllSeller();

	public Seller getSellerById(int id);

	public void addCollect(Collect collect);

	public List<Seller> searchForSellerByName(String sellerName);

	public List<Collect> getAllCollect();

	public List<Collect> getCollectByDate(String date);

	public List<Balance> getSellerRelay(String sellerName);

	public HeaderResult getSellerRelayHeader(String sellerName);
	
	public List<Master> getAllMaster();
	
	public void addMaster(int sellerId, String date, float amount);
	
	public MasterResult getMasterTotals();
	
	public List<Client> getAllClients();

}
