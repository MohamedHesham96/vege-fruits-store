package com.bluesoft.vegefruitsstore.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.entity.Casher;
import com.bluesoft.vegefruitsstore.entity.ClientBalance;
import com.bluesoft.vegefruitsstore.entity.HeaderResult;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Cashers {

	@Autowired
	UserService userService;

	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/casher-sellers")
	public String getCasherSellers(@RequestParam("casherId") int casherId,
			@RequestParam(name = "date", required = false) String theDate, Model theModel) {

		List<Balance> casherList;
		HeaderResult theHeaderResult;

		Casher theCasher = userService.getCasher(casherId);

		if (theDate == null) {

			theDate = LocalDate.now().toString();
			// casherList = userService.getBalanceByCasherId(casherId);

			casherList = theCasher.getBalances();

			theHeaderResult = userService.getCasherHeader(casherId);

		} else {

			casherList = userService.getBalanceByCasherIdAndDate(casherId, theDate);
			theHeaderResult = userService.getCasherHeaderByDate(casherId, theDate);

		}

		theModel.addAttribute("date", theDate);
		httpSession.setAttribute("casher", theCasher);
		theModel.addAttribute("headerResult", theHeaderResult);
		theModel.addAttribute("casherList", casherList);

		return "casher-sellers";

	}

	@RequestMapping("/casher-clients")
	public String getCasherClients(@RequestParam("casherId") int casherId,
			@RequestParam(name = "date", required = false) String theDate, Model theModel) {

		List<ClientBalance> casherList = new ArrayList<ClientBalance>();

		Casher theCasher = userService.getCasher(casherId);

		System.out.println(theCasher.getName());

		if (theDate == null) {

			theDate = LocalDate.now().toString();

			casherList = theCasher.getClientBalances();

		} else {

			// casherList = userService.getBalanceByCasherIdAndDate(casherId, theDate);

		}

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("casher", theCasher);
		theModel.addAttribute("casherList", casherList);

		return "casher-clients";

	}

	@RequestMapping("/add-casher")
	public String addClient(@ModelAttribute(name = "casher") Casher casher) {

		userService.saveCasher(casher);

		return "redirect:/relay";
	}
}
