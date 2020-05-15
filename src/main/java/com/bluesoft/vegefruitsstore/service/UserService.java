package com.bluesoft.vegefruitsstore.service;

import java.util.List;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.Casher;
import com.bluesoft.vegefruitsstore.entity.Client;
import com.bluesoft.vegefruitsstore.entity.ClientBalance;
import com.bluesoft.vegefruitsstore.entity.Collect;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
import com.bluesoft.vegefruitsstore.entity.Item;
import com.bluesoft.vegefruitsstore.entity.Master;
import com.bluesoft.vegefruitsstore.entity.MasterResult;
import com.bluesoft.vegefruitsstore.entity.Seller;

public interface UserService {

	public List<Balance> getAllBalance();

	public void saveBalance(Balance theBalance);

	public List<HeaderResult> getBalanceHeader();

	public void deleteBalance(Balance balance);

	public List<HeaderResult> getRelayHeader();

	public HeaderResult getCasherHeader(int casherId);

//	public List<Balance> getBalanceByCasherId(int casherId);

	public List<Balance> getBalanceByCasherIdAndDate(int casherId, String date);

	public HeaderResult getCasherHeaderByDate(int casherId, String date);

	public List<Seller> getAllSeller();

	public Seller getSellerById(int id);

	public void addCollect(Collect collect);

	public List<Seller> searchForSellerByName(String sellerName);

	public List<Collect> getAllCollect();

	public List<Collect> getCollectByDate(String date);

	public List<Balance> getSellerRelay(String sellerName);

	public HeaderResult getSellerRelayHeader(String sellerName);

	public List<Master> getAllMaster();

	public void updateMaster(int sellerId, String date, float amount, String operation);

	public MasterResult getMasterTotals();

	public List<Client> getAllClients();

	public Casher getCasher(int id);

	public Client getClient(int id);

	public Seller getSeller(int id);

	public List<Casher> getAllCasher();

	public List<Client> searchForClientByName(String clientName);

	public void saveSeller(Seller seller);

	public void saveClient(Client client);

	public List<Item> getAllItems();

	public Item getItem(int id);

	public List<ClientBalance> getAllClientBalance();

	public void saveClientBalance(ClientBalance theClientBalance);

	public void updateClientBalance(int itemId, int clientId, int count, float weight) throws Exception;

	public Balance getBalanceById(int id);

	public void saveCasher(Casher casher);

	public void saveItem(Item item);

	public List<Client> getAllClientsHaveItems();

	public void deleteCollectByInfo(String date, int sellerId, float cash);
	
	public List<Balance> getAllRelay();

}
