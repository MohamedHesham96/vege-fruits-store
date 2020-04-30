package com.bluesoft.vegefruitsstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bluesoft.vegefruitsstore.dao.UserDAO;
import com.bluesoft.vegefruitsstore.entity.Balance;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public List<Balance> getAllBalance() {

		return userDAO.getAllBalance();
	}

}
