package com.bluesoft.vegefruitsstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Balances {

	@Autowired
	UserService userService;

	@RequestMapping("/balance")
	public String getAllBalance(Model theModel) {

		List<Balance> balanceList = userService.getAllBalance();

		theModel.addAttribute("balance", new Balance());
		theModel.addAttribute("balanceList", balanceList);

		return "balance";
	}
}
