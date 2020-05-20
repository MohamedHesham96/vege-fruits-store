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
import com.bluesoft.vegefruitsstore.entity.ClientBalance;
import com.bluesoft.vegefruitsstore.entity.Collect;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
import com.bluesoft.vegefruitsstore.entity.Item;
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

		List<Balance> balanceList = session.createQuery("from Balance order by date desc").getResultList();

		return balanceList;
	}

	@Override
	public List<Balance> getAllRelay() {

		Session session = entityManager.unwrap(Session.class);

		List<Balance> sellerRelayList = session
				.createQuery("from Balance B where B.cash != B.totalAmount order by date").getResultList();

		return sellerRelayList;

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
						+ "B.item.name as itemName, B.client.name as clientName FROM Balance B GROUP BY clientName, itemName order by clientName, itemName")
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class)).getResultList();

		return theHeaderResult;

	}

	@Override
	@Transactional
	public void deleteBalance(Balance balance) {

		Session session = entityManager.unwrap(Session.class);

		session.delete(balance);

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
	public HeaderResult getCasherHeader(int casherId) {

		Session session = entityManager.unwrap(Session.class);

		List<HeaderResult> headerResultList = session
				.createQuery("select sum(B.counter) as totalCount, sum(B.weight) as totalWeight, "
						+ "sum(B.totalAmount) as totalAmount, sum(B.cash) as totalCash, "
						+ "B.seller.name as sellerName, B.casher.name as casherName "
						+ "FROM Balance B where B.casher.id = :theCasherId GROUP BY casherName order by date")
				.setParameter("theCasherId", casherId)
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class)).getResultList();

		if (!headerResultList.isEmpty()) {

			return headerResultList.get(0);

		} else {

			return new HeaderResult();

		}

	}

	@Override
	public HeaderResult getCasherHeaderByDate(int casherId, String date) {

		Session session = entityManager.unwrap(Session.class);

		List<HeaderResult> headerResultList = session
				.createQuery("select sum(B.counter) as totalCount, sum(B.weight) as totalWeight, "
						+ "sum(B.totalAmount) as totalAmount, sum(B.cash) as totalCash, "
						+ "B.seller.name as sellerName, B.casher.name as casherName "
						+ "FROM Balance B where B.casher.id = :theCasherId and date = :theDate GROUP BY casherName order by date")
				.setParameter("theCasherId", casherId).setParameter("theDate", date)
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class)).getResultList();

		if (!headerResultList.isEmpty()) {

			return headerResultList.get(0);

		} else {

			return new HeaderResult();

		}

	}

	/*
	 * @Override public List<Balance> getBalanceByCasherId(int casherId) {
	 * 
	 * Session session = entityManager.unwrap(Session.class);
	 * 
	 * List<Balance> casherList = session
	 * .createQuery("from Balance B where  B.casher.id = :theCasherId order by date desc"
	 * ) .setParameter("theCasherId", casherId).getResultList();
	 * 
	 * return casherList; }
	 */

	@Override
	public List<Balance> getBalanceByCasherIdAndDate(int casherId, String date) {

		Session session = entityManager.unwrap(Session.class);

		List<Balance> casherList = session
				.createQuery("from Balance B where B.casher.id = :theCasherId and date = :theDate order by date")
				.setParameter("theCasherId", casherId).setParameter("theDate", date).getResultList();

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

		List<Collect> collectList = session.createQuery("from Collect where amount != 0 order by date desc")
				.getResultList();

		return collectList;
	}

	@Override
	public List<Collect> getCollectByDate(String date) {

		Session session = entityManager.unwrap(Session.class);

		List<Collect> collectList = session
				.createQuery("from Collect where date = :thedate and amount != 0 order by date desc")
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

	@Override
	public void saveSeller(Seller seller) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(seller);
	}

	@Override
	public void saveClient(Client client) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(client);
	}

	@Override
	public List<Item> getAllItems() {

		Session session = entityManager.unwrap(Session.class);

		List<Item> itemList = session.createQuery("from Item order by name").getResultList();

		return itemList;

	}

	@Override
	public Item getItem(int id) {

		Session session = entityManager.unwrap(Session.class);

		Item item = session.get(Item.class, id);

		return item;
	}

	@Override
	public List<ClientBalance> getAllClientBalance() {

		Session session = entityManager.unwrap(Session.class);

		List<ClientBalance> clientBalanceList = session.createQuery("from ClientBalance").getResultList();

		return clientBalanceList;

	}

	@Override
	@Transactional
	public void saveClientBalance(ClientBalance clientBalance) {

		Session session = entityManager.unwrap(Session.class);

		session.save(clientBalance);

		/*
		 * List<ClientBalance> clientBalances = session.createQuery(
		 * "from ClientBalance CB where CB.item.id = :theItemId and CB.client.id = :theClientId and CB.currentCounter > 0"
		 * ) .setParameter("theItemId", clientBalance.getItem().getId())
		 * .setParameter("theClientId",
		 * clientBalance.getClient().getId()).getResultList();
		 * 
		 * if (!clientBalances.isEmpty()) {
		 * 
		 * ClientBalance theClientBalance = clientBalances.get(clientBalances.size() -
		 * 1);
		 * 
		 * theClientBalance.setCounter(theClientBalance.getCounter() +
		 * clientBalance.getCounter());
		 * 
		 * theClientBalance.setWeight(theClientBalance.getWeight() +
		 * clientBalance.getWeight());
		 * 
		 * theClientBalance .setCurrentCounter(theClientBalance.getCurrentCounter() +
		 * clientBalance.getCurrentCounter());
		 * 
		 * theClientBalance.setCurrentWeight(theClientBalance.getCurrentWeight() +
		 * clientBalance.getCurrentWeight());
		 * 
		 * session.saveOrUpdate(theClientBalance);
		 * 
		 * } else {
		 * 
		 * session.saveOrUpdate(clientBalance);
		 * 
		 * }
		 */
	}

	@Override
	public void updateClientBalance(int itemId, int clientId, int count, float weight) throws Exception {

		Session session = entityManager.unwrap(Session.class);

		ClientBalance theClientBalance = (ClientBalance) session
				.createQuery("from ClientBalance CB where CB.item.id = :theItemId and CB.client.id = :theClientId")
				.setMaxResults(1).setParameter("theItemId", itemId).setParameter("theClientId", clientId)
				.uniqueResult();

//		if (theClientBalance.getCounter() < count || theClientBalance.getWeight() < weight)
//			throw new Exception("Errorrrrrrrrrrrrr");

		theClientBalance.setCurrentCounter(theClientBalance.getCurrentCounter() - count);
		theClientBalance.setCurrentWeight(theClientBalance.getCurrentWeight() - weight);

		session.saveOrUpdate(theClientBalance);
	}

	@Override
	public Balance getBalanceById(int id) {

		Session session = entityManager.unwrap(Session.class);

		Balance balance = session.get(Balance.class, id);

		return balance;

	}

	@Override
	public void saveCasher(Casher casher) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(casher);

	}

	@Override
	public void saveItem(Item item) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(item);

	}

	@Override
	public List<Client> getAllClientsHaveItems() {

		Session session = entityManager.unwrap(Session.class);

		List<Client> clientList = session
				.createQuery("from Client C where C.ClientBalance.currentCounter > 0 order by id").getResultList();

		return clientList;

	}

	@Override
	public void deleteCollectByInfo(String date, int sellerId, float cash) {

		Session session = entityManager.unwrap(Session.class);
		List<Collect> collectList = session.createQuery(
				"from Collect C where C.date = :theDate and C.seller.id = :theSellerId and C.amount = :theCash order by id desc")
				.setParameter("theDate", date).setParameter("theSellerId", sellerId).setParameter("theCash", cash)
				.getResultList();

		if (!collectList.isEmpty()) {

			session.delete(collectList.get(0));

		}
	}

	@Override
	public List<ClientBalance> getClientBalanceByCasherIdAndDate(int casherId, String theDate) {

		Session session = entityManager.unwrap(Session.class);

		List<ClientBalance> casherList = session
				.createQuery(
						"from ClientBalance CB where CB.casher.id = :theCasherId and date = :theDate order by date")
				.setParameter("theCasherId", casherId).setParameter("theDate", theDate).getResultList();

		return casherList;
	}

	@Override
	public Collect getCollect(int id) {

		Session session = entityManager.unwrap(Session.class);

		Collect collect = session.get(Collect.class, id);

		return collect;

	}

	@Override
	@Transactional
	public void deletCollect(Collect theCollect) {

		Session session = entityManager.unwrap(Session.class);

		session.delete(theCollect);
	}

	@Override
	public double getAvgKiloPrice(int itemId, int clientId, String date) {

		Session session = entityManager.unwrap(Session.class);

		HeaderResult theHeaderResult = (HeaderResult) session
				.createQuery("select Avg(B.kiloPrice) as kiloPriceAvg from Balance B "
						+ "where B.item.id = :theItemId and B.client.id = :theClientId and B.date >= :theDate")

				.setParameter("theItemId", itemId).setParameter("theClientId", clientId)
				.setParameter("theDate", "'" + date + "'")
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class)).setMaxResults(1)
				.uniqueResult();

		return theHeaderResult.getKiloPriceAvg();
	}

	@Override
	public String getMaxDateForItem(int clientId, int itemId) {

		Session session = entityManager.unwrap(Session.class);

		HeaderResult theHeaderResult = (HeaderResult) session
				.createQuery("select max(CB.date) as maxDate from ClientBalance CB "
						+ "where CB.item.id = :theItemId and CB.client.id = :theClientId")
				.setParameter("theItemId", clientId).setParameter("theClientId", itemId)
				.setResultTransformer(new AliasToBeanResultTransformer(HeaderResult.class)).setMaxResults(1)
				.uniqueResult();

		return theHeaderResult.getMaxDate();
	}

	@Override
	public List<ClientBalance> getClientBalancesWithCountZero(int clientId) {

		Session session = entityManager.unwrap(Session.class);

		List<ClientBalance> clientList = session
				.createQuery("from ClientBalance CB where CB.client.id = :theClientId and CB.currentCounter = 0")
				.setParameter("theClientId", clientId).getResultList();

		return clientList;
	}

	@Override
	public List<Collect> getSellerCollectByDate(int id, String date) {

		Session session = entityManager.unwrap(Session.class);

		List<Collect> collectList = session
				.createQuery("from Collect C where C.seller.id = :theSellerId and C.date = :thedate")
				.setParameter("theSellerId", id).setParameter("thedate", date).getResultList();

		return collectList;
	}

}
