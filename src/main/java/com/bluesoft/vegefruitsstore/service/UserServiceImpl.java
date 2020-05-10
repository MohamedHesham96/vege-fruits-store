package com.bluesoft.vegefruitsstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluesoft.vegefruitsstore.dao.UserDAO;
import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.Casher;
import com.bluesoft.vegefruitsstore.entity.Client;
import com.bluesoft.vegefruitsstore.entity.Collect;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
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
	public void deleteBalance(int id) {

		userDAO.deleteBalance(id);
	}

	@Override
	public List<HeaderResult> getRelayHeader() {

		return userDAO.getRelayHeader();
	}

	@Override
	public HeaderResult getCasherHeader(String casherName) {

		return userDAO.getCasherHeader(casherName);
	}

	@Override
	public List<Balance> getBalanceByCasherName(String casherName) {

		return userDAO.getBalanceByCasherName(casherName);
	}

	@Override
	public List<Balance> getBalanceByCasherNameAndDate(String casherName, String date) {

		return userDAO.getBalanceByCasherNameAndDate(casherName, date);
	}

	@Override
	public HeaderResult getCasherHeaderByDate(String casherName, String date) {

		return userDAO.getCasherHeaderByDate(casherName, date);
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

}
