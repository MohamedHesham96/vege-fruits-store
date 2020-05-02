package com.bluesoft.vegefruitsstore.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

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

	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/relay")
	public String getAllBalance(Model theModel) {

		List<HeaderResult> theHeaderResult = userService.getRelayHeader();

		List<Balance> relayList = userService.getAllBalance();

		theModel.addAttribute("headerResult", theHeaderResult);

		theModel.addAttribute("relayList", relayList);

		return "relay";
	}

	@RequestMapping("/casher")
	public String getAllBalance(@RequestParam("casherName") String casherName,
			@RequestParam(name = "date", required = false) String theDate, Model theModel) {

		List<Balance> casherList;
		List<HeaderResult> theHeaderResult;

		if (theDate == null) {

			theDate = LocalDate.now().toString();
			casherList = userService.getBalanceByCasherName(casherName);
			theHeaderResult = userService.getCasherHeader(casherName);
			httpSession.setAttribute("casherName", casherName);

		} else {

			casherList = userService.getBalanceByCasherNameAndDate(casherName, theDate);
			theHeaderResult = userService.getCasherHeaderByDate(casherName, theDate);

			httpSession.setAttribute("casherName", casherName);

		}

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("headerResult", theHeaderResult);
		theModel.addAttribute("casherList", casherList);

		return "casher";
	}

}
