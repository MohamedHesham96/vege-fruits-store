package com.bluesoft.vegefruitsstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Balance> getAllBalance() {

		Session session = entityManager.unwrap(Session.class);

		List<Balance> balanceList = session.createQuery("from Balance order by date").getResultList();

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

		List<HeaderResult> theHeaderResult = session
				.createQuery("select sum(B.counter) as totalCount, sum(B.weight) as totalWeight, "
						+ "sum(B.cash) as totalCash," + "sum(B.later) as totalLater, "
						+ "sum(B.totalAmount) as totalAmount, "
						+ "B.itemName as itemName, B.clientName as clientName FROM Balance B GROUP BY clientName, itemName order by clientName, itemName")
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class)).getResultList();

		return theHeaderResult;

	}

	@Override
	@Transactional
	public void deleteBalance(int id) {

		Session session = entityManager.unwrap(Session.class);
		Balance theBalance = session.get(Balance.class, id);
		session.delete(theBalance);
	}

}
