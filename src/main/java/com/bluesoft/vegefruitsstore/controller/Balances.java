package com.bluesoft.vegefruitsstore.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.Casher;
import com.bluesoft.vegefruitsstore.entity.Client;
import com.bluesoft.vegefruitsstore.entity.ClientBalance;
import com.bluesoft.vegefruitsstore.entity.Collect;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
import com.bluesoft.vegefruitsstore.entity.Item;
import com.bluesoft.vegefruitsstore.entity.Seller;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Balances {

	@Autowired
	UserService userService;

	@Autowired
	private HttpSession httpSession;

	@GetMapping("/balance")
	public String getAllBalance(@RequestParam(name = "clientId", required = false) Integer theClientID,
			Model theModel) {

		List<HeaderResult> theHeaderResult = userService.getBalanceHeader();
		List<Item> itemList = new ArrayList<Item>();

		List<Seller> sellerList = userService.getAllSeller();
		List<Client> clientsList = userService.getAllClients();
		List<Balance> balanceList = userService.getAllBalance();

		Client client = new Client();
		Balance balance = new Balance();

		List<ClientBalance> clientBalances = new ArrayList<ClientBalance>();

		if (theClientID != null) {

			client = userService.getClient(theClientID);
			clientBalances = client.getClientBalances();
			balance.setClient(client);

			// add to be the selected client in balance form
			theModel.addAttribute("selectedClientId", theClientID);

		} else {

			if (!clientsList.isEmpty()) {
				client = clientsList.get(0);
				clientBalances = client.getClientBalances();
			}
		}

//		ClientBalance tempCB = new ClientBalance();
//		
//		for (Iterator iterator = clientBalances.iterator(); iterator.hasNext();) {
//
//			if (tempCB.getItem().equals(((ClientBalance) iterator.next()).getItem())) {
//
//				tempCB = (ClientBalance) iterator.next();
//				tempCB.getItem().setName(tempCB.getItem().getName() + "[ " + tempCB.getDate() + " ]");
//
//			}
//
//		}
		// Get Messages Count
		List<Seller> newSellerList = new ArrayList<Seller>();

		for (Seller seller : sellerList) {

			if (!seller.checkSeller())
				newSellerList.add(seller);
		}

		httpSession.setAttribute("messagesCount", newSellerList.size());

		theModel.addAttribute("today", LocalDate.now().toString());
		theModel.addAttribute("seller", new Seller());
		theModel.addAttribute("balance", balance);
		theModel.addAttribute("clientBalances", clientBalances);
		theModel.addAttribute("sellersList", sellerList);
		theModel.addAttribute("clientsList", clientsList);
		theModel.addAttribute("balanceList", balanceList);
		theModel.addAttribute("headerResult", theHeaderResult);

		return "balance";

	}

	@PostMapping("/add-balance")
	public String getAllBalance(@RequestParam("clientBalanceId") int clientBalanceId,
			@RequestParam("clientId") int clientId, @RequestParam("sellerId") int sellerId,
			@ModelAttribute("balance") Balance theBalance) throws Exception {

		boolean updateFlage = false;

		System.out.println("Refresh >> add-balance");

		// get casher id from the session
		int casherId = Integer.parseInt(httpSession.getAttribute("loginCasherId").toString());

		updateFlage = userService.updateClientBalance(clientBalanceId, theBalance.getCounter(), theBalance.getWeight());

		System.out.println(">>>>>>>>>> updateFlage >>>>>>>>> " + updateFlage);

		if (updateFlage) {

			theBalance.setDate(LocalDate.now().toString());

			theBalance.setTotalAmount(theBalance.getWeight() * theBalance.getKiloPrice());

			theBalance.setLater(theBalance.getWeight() * theBalance.getKiloPrice() - theBalance.getCash());

			Casher theCasher = userService.getCasher(casherId);
			Seller theSeller = userService.getSeller(sellerId);

			theBalance.setCasher(theCasher);
			theBalance.setClient(userService.getClient(clientId));
			theBalance.setSeller(theSeller);

			// جلب الايتم للبالانس
			theBalance.setItem(userService.getClientBalance(clientBalanceId).getItem());

			// جلب الكلاينت بالانس للبالانس
			theBalance.setClientBalance(userService.getClientBalance(clientBalanceId));

			userService.saveBalance(theBalance);

			if (theBalance.getCash() != theBalance.getTotalAmount() && theBalance.getCash() != 0) {

				userService.updateMaster(sellerId, theBalance.getDate(), theBalance.getTotalAmount(), "relay");

				userService.updateMaster(sellerId, theBalance.getDate(), theBalance.getCash(), "collect");

				userService.addCollect(new Collect(userService.getSeller(sellerId), theBalance.getCash(),
						theBalance.getDate(), theCasher.getName()));

			} else if (theBalance.getCash() == 0) {

				userService.updateMaster(sellerId, theBalance.getDate(), theBalance.getTotalAmount(), "relay");

			}

		} else
			throw new Exception("Errorrrrrrrrrrrrr");

		return "redirect:/balance";
	}

	@RequestMapping("/delete-balance")
	public String deleteBalance(@RequestParam(name = "id") int balanceId) throws Exception {

		Balance theBalance = userService.getBalanceById(balanceId);

		boolean b = userService.updateClientBalance(theBalance.getClientBalance().getId(), theBalance.getCounter() * -1,
				theBalance.getWeight() * -1);

		userService.updateMaster(theBalance.getSeller().getId(), theBalance.getDate(),
				theBalance.getTotalAmount() - theBalance.getCash(), "collect");

		userService.deleteCollectByInfo(theBalance.getDate(), theBalance.getSeller().getId(), theBalance.getCash());

		userService.deleteBalance(theBalance);

		return "redirect:/balance";
	}

}