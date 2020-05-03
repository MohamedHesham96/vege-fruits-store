package com.bluesoft.vegefruitsstore.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Collect;
import com.bluesoft.vegefruitsstore.entity.Seller;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Collects {

	@Autowired
	UserService userService;

	@RequestMapping("/collect")
	public String getAllBalance(@RequestParam(name = "date", required = false) String theDate, Model theModel) {

		List<Collect> collectList;

		if (theDate == null) {

			theDate = LocalDate.now().toString();
			
			collectList = userService.getAllCollect();

		} else {

			collectList = userService.getCollectByDate(theDate);

		}

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("collectList", collectList);

		return "collect";
	}

}
