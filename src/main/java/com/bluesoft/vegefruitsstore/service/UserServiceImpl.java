package com.bluesoft.vegefruitsstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluesoft.vegefruitsstore.dao.UserDAO;
import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
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
	public List<HeaderResult> getCasherHeader(String casherName) {

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
	public List<HeaderResult> getCasherHeaderByDate(String casherName, String date) {

		return userDAO.getCasherHeaderByDate(casherName, date);
	}

	@Override
	public List<Seller> getAllSeller() {

		return userDAO.getAllSeller();
	}

}
