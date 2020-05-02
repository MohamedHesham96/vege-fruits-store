package com.bluesoft.vegefruitsstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluesoft.vegefruitsstore.dao.UserDAO;
import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;

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

}
