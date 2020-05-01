package com.bluesoft.vegefruitsstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Balance> getAllBalance() {

		Session session = entityManager.unwrap(Session.class);

		List<Balance> balanceList = session.createQuery("from Balance order by sellerName, itemName").getResultList();

		return balanceList;
	}

	@Override
	public void saveBalance(Balance theBalance) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(theBalance);
	}

	@Override
	public List<HeaderResult> getBalanceHeader() {

		Session session = entityManager.unwrap(Session.class);

		List<HeaderResult> theHeaderResult = session.createQuery(
				"select sum(B.counter) as totalCount, B.itemName as itemName, B.sellerName as sellerName FROM Balance B GROUP BY sellerName, itemName order by sellerName, itemName")
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class)).getResultList();

		for (HeaderResult headerResult : theHeaderResult) {

		}
		return theHeaderResult;

	}

	@Override
	public void deleteBalance(int id) {
		
	}

}
