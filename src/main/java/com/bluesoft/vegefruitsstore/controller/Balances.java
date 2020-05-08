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

		httpSession.setAttribute("casherName", "محمد عصام");
		
		theModel.addAttribute("balance", new Balance());
		theModel.addAttribute("sellersList", sellerList);
		theModel.addAttribute("clientsList", clientsList);
		theModel.addAttribute("balanceList", balanceList);
		theModel.addAttribute("headerResult", theHeaderResult);

		return "balance";
	}

	@RequestMapping("/add-balance")
	public String getAllBalance(@ModelAttribute(name = "balance") Balance theBalance) {

		theBalance.setDate(LocalDate.now().toString());

		theBalance.setTotalAmount(theBalance.getCash() + theBalance.getLater());

		userService.saveBalance(theBalance);

		return "redirect:/balance";
	}

	@RequestMapping("/delete-balance")
	public String getAllBalance(@RequestParam(name = "id") int id) {

		userService.deleteBalance(id);

		return "redirect:/balance";
	}

}
