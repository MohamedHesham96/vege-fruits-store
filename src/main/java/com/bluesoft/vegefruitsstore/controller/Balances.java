package com.bluesoft.vegefruitsstore.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.Client;
import com.bluesoft.vegefruitsstore.entity.Collect;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
import com.bluesoft.vegefruitsstore.entity.Seller;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Balances {

	@Autowired
	UserService userService;

	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/balance")
	public String getAllBalance(Model theModel) {

		List<HeaderResult> theHeaderResult = userService.getBalanceHeader();

		List<Seller> sellerList = userService.getAllSeller();
		List<Client> clientsList = userService.getAllClients();

		List<Balance> balanceList = userService.getAllBalance();

		httpSession.setAttribute("loginCasherName", "محمد عصام");
		httpSession.setAttribute("loginCasherId", "1");

		theModel.addAttribute("balance", new Balance());
		theModel.addAttribute("sellersList", sellerList);
		theModel.addAttribute("clientsList", clientsList);
		theModel.addAttribute("balanceList", balanceList);
		theModel.addAttribute("headerResult", theHeaderResult);

		return "balance";
	}

	@RequestMapping("/add-balance")
	public String getAllBalance(@RequestParam("clientId") int clientId, @RequestParam("sellerId") int sellerId,
			@ModelAttribute("balance") Balance theBalance) {

		// get casher id from the session
		int casherId = Integer.parseInt(httpSession.getAttribute("loginCasherId").toString());
		System.out.println(">>>>> " + casherId);
	
		theBalance.setDate(LocalDate.now().toString());

		theBalance.setTotalAmount(theBalance.getWeight() * theBalance.getKiloPrice());

		theBalance.setLater(theBalance.getWeight() * theBalance.getKiloPrice() - theBalance.getCash());

		theBalance.setCasher(userService.getCasher(casherId));
		theBalance.setClient(userService.getClient(clientId));
		theBalance.setSeller(userService.getSeller(sellerId));

		userService.saveBalance(theBalance);

		if (theBalance.getCash() != theBalance.getTotalAmount()) {

			userService.updateMaster(sellerId, theBalance.getDate(), theBalance.getTotalAmount(), "relay");

			userService.updateMaster(sellerId, theBalance.getDate(), theBalance.getCash(), "collect");

			userService.addCollect(
					new Collect(userService.getSeller(sellerId), theBalance.getCash(), theBalance.getDate()));

		} else if (theBalance.getCash() == theBalance.getTotalAmount()) {

			// اسئله هل في تحصيل بيتسجل لو دفع المبلغ كله ولا لأ
			// userService.addCollect(new Collect(userService.getSeller(sellerId),
			// theBalance.getCash(), theBalance.getDate()));

		}

		else if (theBalance.getCash() == 0) {

			userService.updateMaster(sellerId, theBalance.getDate(), theBalance.getTotalAmount(), "relay");

		}

		return "redirect:/balance";
	}

	@RequestMapping("/delete-balance")
	public String getAllBalance(@RequestParam(name = "id") int id) {

		userService.deleteBalance(id);

		return "redirect:/balance";
	}

}
