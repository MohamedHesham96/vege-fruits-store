package com.bluesoft.vegefruitsstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluesoft.vegefruitsstore.dao.UserDAO;
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

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public List<Balance> getAllBalance() {

		return userDAO.getAllBalance();
	}

	@Override
	public void saveBalance(Balance theBalance) {

		userDAO.saveBalance(theBalance);
	}

	@Override
	public List<HeaderResult> getBalanceHeader() {

		return userDAO.getBalanceHeader();
	}

	@Override
	public void deleteBalance(Balance balance) {

		userDAO.deleteBalance(balance);
	}

	@Override
	public List<HeaderResult> getRelayHeader() {

		return userDAO.getRelayHeader();
	}

	@Override
	public HeaderResult getCasherHeader(int casherId) {

		return userDAO.getCasherHeader(casherId);
	}

	//
//	@Override
//	public List<Balance> getBalanceByCasherId(int casherId) {
//
//		return userDAO.getBalanceByCasherId(casherId);
//	}

	@Override
	public List<Balance> getBalanceByCasherIdAndDate(int casherId, String date) {

		return userDAO.getBalanceByCasherIdAndDate(casherId, date);
	}

	@Override
	public HeaderResult getCasherHeaderByDate(int casherId, String date) {

		return userDAO.getCasherHeaderByDate(casherId, date);
	}

	@Override
	public List<Seller> getAllSeller() {

		return userDAO.getAllSeller();
	}

	@Override
	public Seller getSellerById(int id) {

		return userDAO.getSellerById(id);
	}

	@Override
	public void addCollect(Collect collect) {

		userDAO.addCollect(collect);
	}

	@Override
	public List<Seller> searchForSellerByName(String sellerName) {

		return userDAO.searchForSellerByName(sellerName);
	}

	@Override
	public List<Collect> getAllCollect() {

		return userDAO.getAllCollect();
	}

	@Override
	public List<Collect> getCollectByDate(String date) {

		return userDAO.getCollectByDate(date);
	}

	@Override
	public List<Balance> getSellerRelay(String sellerName) {

		return userDAO.getSellerRelay(sellerName);
	}

	@Override
	public HeaderResult getSellerRelayHeader(String sellerName) {

		return userDAO.getSellerRelayHeader(sellerName);
	}

	@Override
	public List<Master> getAllMaster() {

		return userDAO.getAllMaster();
	}

	@Override
	public void updateMaster(int sellerId, String date, float amount, String operation) {

		userDAO.updateMaster(sellerId, date, amount, operation);
	}

	@Override
	public MasterResult getMasterTotals() {

		return userDAO.getMasterTotals();
	}

	@Override
	public List<Client> getAllClients() {

		return userDAO.getAllClients();
	}

	@Override
	public Casher getCasher(int id) {

		return userDAO.getCasher(id);
	}

	@Override
	public Client getClient(int id) {

		return userDAO.getClient(id);
	}

	@Override
	public Seller getSeller(int id) {

		return userDAO.getSeller(id);
	}

	@Override
	public List<Casher> getAllCasher() {

		return userDAO.getAllCasher();
	}

	@Override
	public List<Client> searchForClientByName(String clientName) {

		return userDAO.searchForClientByName(clientName);
	}

	@Override
	public void saveSeller(Seller seller) {

		userDAO.saveSeller(seller);
	}

	@Override
	public void saveClient(Client client) {

		userDAO.saveClient(client);
	}

	@Override
	public List<Item> getAllItems() {

		return userDAO.getAllItems();
	}

	@Override
	public Item getItem(int id) {

		return userDAO.getItem(id);
	}

	@Override
	public List<ClientBalance> getAllClientBalance() {

		return userDAO.getAllClientBalance();

	}

	@Override
	public void saveClientBalance(ClientBalance clientBalance) {

		userDAO.saveClientBalance(clientBalance);
	}

	@Override
	public boolean updateClientBalance(int clientBalanceId, int count, float weight) throws Exception {

		return userDAO.updateClientBalance(clientBalanceId, count, weight);
	}

	@Override
	public Balance getBalanceById(int id) {

		return userDAO.getBalanceById(id);
	}

	@Override
	public void saveCasher(Casher casher) {

		userDAO.saveCasher(casher);
	}

	@Override
	public void saveItem(Item item) {

		userDAO.saveItem(item);

	}

	@Override
	public List<Client> getAllClientsHaveItems() {

		return userDAO.getAllClientsHaveItems();
	}

	@Override
	public void deleteCollectByInfo(String date, int sellerId, float cash) {

		userDAO.deleteCollectByInfo(date, sellerId, cash);
	}

	@Override
	public List<Balance> getAllRelay() {

		return userDAO.getAllRelay();
	}

	@Override
	public List<ClientBalance> getClientBalanceByCasherIdAndDate(int casherId, String theDate) {

		return userDAO.getClientBalanceByCasherIdAndDate(casherId, theDate);
	}

	@Override
	public Collect getCollect(int id) {

		return userDAO.getCollect(id);
	}

	@Override
	public void deletCollect(Collect theCollect) {

		userDAO.deletCollect(theCollect);

	}

	@Override
	public double getAvgKiloPrice(int clientBalanceId) {

		return userDAO.getAvgKiloPrice(clientBalanceId);
	}

	@Override
	public List<ClientBalance> getClientBalancesWithCountZero(int clientId) {

		return userDAO.getClientBalancesWithCountZero(clientId);
	}

	@Override
	public List<Collect> getSellerCollectByDate(String theDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Collect> getSellerCollectByDate(int id, String date) {

		return userDAO.getSellerCollectByDate(id, date);
	}

	@Override
	public Casher getLoginCasher(String username, String password) {

		return userDAO.getLoginCasher(username, password);
	}

	@Override
	public List<Item> getAllItem() {

		return userDAO.getAllItem();
	}

	@Override
	public void deleteItem(int id) {

		userDAO.deleteItem(id);
	}

	@Override
	public void deleteSeller(int id) {

		userDAO.deleteSeller(id);
	}

	@Override
	public void deleteClient(int id) {

		userDAO.deleteClient(id);
	}

	@Override
	public List<HeaderResult> getMaxDatesForBalance() {

		return userDAO.getMaxDatesForBalance();
	}

	@Override
	public List<HeaderResult> getMaxDatesForCollect() {

		return userDAO.getMaxDatesForCollect();
	}

	@Override
	public void deleteClientBalance(int id) {

		userDAO.deleteClientBalance(id);
	}

	@Override
	public ClientBalance getClientBalance(int id) {

		return userDAO.getClientBalance(id);

	}
}
