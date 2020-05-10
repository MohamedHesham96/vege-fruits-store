package com.bluesoft.vegefruitsstore.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.Casher;
import com.bluesoft.vegefruitsstore.entity.Client;
import com.bluesoft.vegefruitsstore.entity.Collect;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
import com.bluesoft.vegefruitsstore.entity.Master;
import com.bluesoft.vegefruitsstore.entity.MasterResult;
import com.bluesoft.vegefruitsstore.entity.Seller;

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
						+ "B.itemName as itemName, B.client.name as clientName FROM Balance B GROUP BY clientName, itemName order by clientName, itemName")
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

	@Override
	public List<HeaderResult> getRelayHeader() {

		Session session = entityManager.unwrap(Session.class);

		List<HeaderResult> theHeaderResult = session
				.createQuery("select sum(B.counter) as totalCount, sum(B.weight) as totalWeight, "
						+ "sum(B.totalAmount) as totalAmount, sum(B.cash) as totalCash, B.seller.name as sellerName "
						+ "FROM Balance B GROUP BY sellerName order by sellerName")
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class)).getResultList();

		return theHeaderResult;
	}

	@Override
	public HeaderResult getCasherHeader(String casherName) {

		Session session = entityManager.unwrap(Session.class);

		List<HeaderResult> headerResultList = session
				.createQuery("select sum(B.counter) as totalCount, sum(B.weight) as totalWeight, "
						+ "sum(B.totalAmount) as totalAmount, sum(B.cash) as totalCash, "
						+ "B.seller.name as sellerName, B.casher.name as casherName "
						+ "FROM Balance B where B.casher.name = :theCasherName GROUP BY casherName order by date")
				.setParameter("theCasherName", casherName)
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class)).getResultList();

		if (!headerResultList.isEmpty()) {

			return headerResultList.get(0);

		} else {

			return new HeaderResult();

		}

	}

	@Override
	public HeaderResult getCasherHeaderByDate(String casherName, String date) {

		Session session = entityManager.unwrap(Session.class);

		List<HeaderResult> headerResultList = session
				.createQuery("select sum(B.counter) as totalCount, sum(B.weight) as totalWeight, "
						+ "sum(B.totalAmount) as totalAmount, sum(B.cash) as totalCash, "
						+ "B.seller.name as sellerName, B.casher.name as casherName "
						+ "FROM Balance B where B.casher.name = :theCasherName and date = :theDate GROUP BY casherName order by date")
				.setParameter("theCasherName", casherName).setParameter("theDate", date)
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class)).getResultList();

		if (!headerResultList.isEmpty()) {

			return headerResultList.get(0);

		} else {

			return new HeaderResult();

		}

	}

	@Override
	public List<Balance> getBalanceByCasherName(String casherName) {

		Session session = entityManager.unwrap(Session.class);

		List<Balance> casherList = session
				.createQuery("from Balance B where  B.casher.name = :theCasherName order by date desc")
				.setParameter("theCasherName", casherName).getResultList();

		return casherList;
	}

	@Override
	public List<Balance> getBalanceByCasherNameAndDate(String casherName, String date) {

		Session session = entityManager.unwrap(Session.class);

		List<Balance> casherList = session
				.createQuery("from Balance B where B.casher.name = :theCasherName and date = :theDate order by date")
				.setParameter("theCasherName", casherName).setParameter("theDate", date).getResultList();

		return casherList;
	}

	@Override
	public List<Seller> getAllSeller() {

		Session session = entityManager.unwrap(Session.class);

		List<Seller> sellerList = session.createQuery("from Seller order by id").getResultList();

		return sellerList;
	}

	@Override
	public Seller getSellerById(int id) {

		Session session = entityManager.unwrap(Session.class);

		Seller seller = session.get(Seller.class, id);

		return seller;
	}

	@Override
	public void addCollect(Collect collect) {

		Session session = entityManager.unwrap(Session.class);

		session.save(collect);
	}

	@Override
	public List<Seller> searchForSellerByName(String sellerName) {

		Session session = entityManager.unwrap(Session.class);

		List<Seller> sellerList = session.createQuery("from Seller where name like :theSellerName")
				.setParameter("theSellerName", "%" + sellerName + "%").getResultList();

		return sellerList;
	}

	@Override
	public List<Collect> getAllCollect() {

		Session session = entityManager.unwrap(Session.class);

		List<Collect> collectList = session.createQuery("from Collect order by date desc").getResultList();

		return collectList;
	}

	@Override
	public List<Collect> getCollectByDate(String date) {

		Session session = entityManager.unwrap(Session.class);

		List<Collect> collectList = session.createQuery("from Collect where date = :thedate order by date desc")
				.setParameter("thedate", date).getResultList();

		return collectList;
	}

	@Override
	public List<Balance> getSellerRelay(String sellerName) {

		Session session = entityManager.unwrap(Session.class);

		List<Balance> sellerRelayList = session
				.createQuery(
						"from Balance B where B.cash != B.totalAmount and B.seller.name = :theSellerName order by date")
				.setParameter("theSellerName", sellerName).getResultList();

		return sellerRelayList;

	}

	@Override
	public HeaderResult getSellerRelayHeader(String sellerName) {

		Session session = entityManager.unwrap(Session.class);

		HeaderResult theHeaderResult = (HeaderResult) session
				.createQuery("select sum(B.counter) as totalCount, sum(B.weight) as totalWeight, "
						+ "sum(B.totalAmount) as totalAmount, sum(B.cash) as totalCash, B.seller.name as sellerName "
						+ "FROM Balance B  where B.cash != B.totalAmount and B.seller.name  = :theSellerName")
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class))
				.setParameter("theSellerName", sellerName).getSingleResult();

		return theHeaderResult;
	}

	@Override
	public List<Master> getAllMaster() {

		Session session = entityManager.unwrap(Session.class);

		List<Master> masterList = session.createQuery("from Master order by date").getResultList();

		return masterList;
	}

	@Override
	@Transactional
	public void updateMaster(int sellerId, String date, float amount, String operation) {

		Session session = entityManager.unwrap(Session.class);

		if (operation == "collect") // بينفذ على حسب نوع العملية لان الفرق ما بينهم الطرح والجمع فقط
			amount *= -1;

		Master master = new Master();

		Master lastMaster = (Master) session
				.createQuery("from Master m where m.seller.id = :theSellerId order by date desc").setMaxResults(1)
				.setParameter("theSellerId", sellerId).uniqueResult();

		List<Master> masterList = session
				.createQuery("from Master m where m.seller.id = :theSellerId and m.date = :thedate")
				.setParameter("thedate", date).setParameter("theSellerId", sellerId).getResultList();

		if (!masterList.isEmpty()) {

			master = masterList.get(0);

			master.setAmount(master.getAmount() + amount);

			session.saveOrUpdate(master);

		} else {

			Seller theSeller = session.get(Seller.class, sellerId);

			if (lastMaster != null) {

				master.setAmount(lastMaster.getAmount() + amount);

			} else {

				master.setAmount(master.getAmount() + amount);

			}

			master.setDate(LocalDate.now().toString());
			master.setSeller(theSeller);

			session.save(master);
		}

	}

	@Override
	public MasterResult getMasterTotals() {

		Session session = entityManager.unwrap(Session.class);

		MasterResult theMasterResult = (MasterResult) session
				.createQuery("SELECT sum(C.amount) as totalCollect FROM Collect C")
				.setResultTransformer(new AliasToBeanResultTransformer(MasterResult.class)).getSingleResult();

		MasterResult theMasterResult2 = (MasterResult) session
				.createQuery("SELECT sum(B.totalAmount) as totalRelay FROM Balance B where B.cash != B.totalAmount")
				.setResultTransformer(new AliasToBeanResultTransformer(MasterResult.class)).getSingleResult();

		theMasterResult.setTotalRelay(theMasterResult2.getTotalRelay());

		return theMasterResult;
	}

	@Override
	public List<Client> getAllClients() {

		Session session = entityManager.unwrap(Session.class);

		List<Client> clientList = session.createQuery("from Client order by id").getResultList();

		return clientList;
	}

	@Override
	public Casher getCasher(int id) {

		Session session = entityManager.unwrap(Session.class);

		Casher casher = session.get(Casher.class, id);

		return casher;

	}

	@Override
	public Client getClient(int id) {

		Session session = entityManager.unwrap(Session.class);

		Client client = session.get(Client.class, id);

		return client;
	}

	@Override
	public Seller getSeller(int id) {

		Session session = entityManager.unwrap(Session.class);

		Seller seller = session.get(Seller.class, id);

		return seller;
	}

	@Override
	public List<Casher> getAllCasher() {

		Session session = entityManager.unwrap(Session.class);

		List<Casher> casherList = session.createQuery("from Casher order by id").getResultList();

		return casherList;

	}

	@Override
	public List<Client> searchForClientByName(String clientName) {

		Session session = entityManager.unwrap(Session.class);

		List<Client> clientList = session.createQuery("from Client where name like :theClientName")
				.setParameter("theClientName", "%" + clientName + "%").getResultList();

		return clientList;
	}
}
