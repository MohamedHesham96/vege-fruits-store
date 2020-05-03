package com.bluesoft.vegefruitsstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
