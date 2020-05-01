package com.bluesoft.vegefruitsstore.dao;

import java.util.List;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;

public interface UserDAO {

	public List<Balance> getAllBalance();
	
	public void saveBalance(Balance theBalance);
	
	public List<HeaderResult> getBalanceHeader();

	public void deleteBalance(int id);


}
