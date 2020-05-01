package com.bluesoft.vegefruitsstore.service;

import java.util.List;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;

public interface UserService {

	public List<Balance> getAllBalance();

	public void saveBalance(Balance theBalance);

	public List<HeaderResult> getBalanceHeader();

	public void deleteBalance(int id);

}
