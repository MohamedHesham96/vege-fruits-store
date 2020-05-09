package com.bluesoft.vegefruitsstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.Casher;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Relays {

	@Autowired
	UserService userService;

	@RequestMapping("/relay")
	public String getAllRelay(Model theModel) {

		List<HeaderResult> theHeaderResult = userService.getRelayHeader();

		List<Balance> relayList = userService.getAllBalance();

		List<Casher> casherList = userService.getAllCasher();

		theModel.addAttribute("headerResult", theHeaderResult);
		
		theModel.addAttribute("casherList", casherList);
		theModel.addAttribute("relayList", relayList);

		return "relay";
	}

	@RequestMapping("/seller-relay")
	public String getAllBalance(@RequestParam("sellerName") String theSellerName, Model theModel) {

		HeaderResult theHeaderResult = userService.getSellerRelayHeader(theSellerName);

		List<Balance> relayList = userService.getSellerRelay(theSellerName);

		theModel.addAttribute("headerResult", theHeaderResult);

		theModel.addAttribute("relayList", relayList);

		return "seller-relay";
	}

}
