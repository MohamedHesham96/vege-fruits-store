package com.bluesoft.vegefruitsstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bluesoft.vegefruitsstore.entity.Balance;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Balance> getAllBalance() {

		Session session = entityManager.unwrap(Session.class);

		List<Balance> balanceList = session.createQuery("from Balance").getResultList();

		return balanceList;
	}

}
