package com.bluesoft.vegefruitsstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Relays {

	@Autowired
	UserService userService;

	@RequestMapping("/relay")
	public String getAllBalance(Model theModel) {

		List<HeaderResult> theHeaderResult = userService.getRelayHeader();

		List<Balance> relayList = userService.getAllBalance();

		theModel.addAttribute("headerResult", theHeaderResult);

		theModel.addAttribute("relayList", relayList);

		return "relay";
	}

	@RequestMapping("/casher")
	public String getAllBalance(@RequestParam(name = "casherName") String casherName, Model theModel) {

		List<HeaderResult> theHeaderResult = userService.getCasherHeader(casherName);

		List<Balance> casherList = userService.getBalanceByCasherName(casherName);

		theModel.addAttribute("headerResult", theHeaderResult);
		theModel.addAttribute("casherList", casherList);

		return "casher";
	}

}
