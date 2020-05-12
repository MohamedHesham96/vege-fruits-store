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

import com.bluesoft.vegefruitsstore.entity.Client;
import com.bluesoft.vegefruitsstore.entity.ClientBalance;
import com.bluesoft.vegefruitsstore.entity.Item;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class ClientBalances {

	@Autowired
	UserService userService;

	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/client-balance")
	public String getAllBalance(@RequestParam(name = "clientId", required = false) Integer theClientID,
			Model theModel) {

		httpSession.setAttribute("loginCasherName", "محمد عصام");
		httpSession.setAttribute("loginCasherId", "1");

		List<Item> itemList = userService.getAllItems();
		List<Client> clientsList = userService.getAllClients();
	//	List<ClientBalance> clientBalanceList = userService.getAllClientBalance();

		theModel.addAttribute("clientBalance", new ClientBalance());
		theModel.addAttribute("itemsList", itemList);
		theModel.addAttribute("clientsList", clientsList);
	//	theModel.addAttribute("clientBalanceList", clientBalanceList);

		return "client-balance";

	}

	@RequestMapping("/add-client-balance")
	public String getAllBalance(@RequestParam("clientId") int clientId, @RequestParam("itemId") int itemId,
			@ModelAttribute("clientBalance") ClientBalance theClientBalance) {

		// get casher id from the session
		int casherId = Integer.parseInt(httpSession.getAttribute("loginCasherId").toString());

		theClientBalance.setDate(LocalDate.now().toString());

		theClientBalance.setCasher(userService.getCasher(casherId));
		theClientBalance.setClient(userService.getClient(clientId));

		// جلب الايتم للبالانس
		theClientBalance.setItem(userService.getItem(itemId));

		userService.saveClientBalance(theClientBalance);

		return "redirect:/client-balance";

	}

//	@RequestMapping("/delete-balance")
//	public String getAllBalance(@RequestParam(name = "id") int id) {
//
//		userService.deleteBalance(id);
//
//		return "redirect:/balance";
//	}

}