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

	
	@RequestMapping("/seller-collect")
	public String showSellerCollect(@RequestParam("id") int id, Model theModel) {

		Seller theSeller = userService.getSellerById(id);

		theModel.addAttribute("seller", theSeller);
		theModel.addAttribute("collect", new Collect());

		return "seller-collect";
	}

	@RequestMapping("/add-collect")
	public String addCollect(@ModelAttribute("sellerId") int sellerId,
			@ModelAttribute(name = "collect") Collect collect, Model theModel) {

		Seller theSeller = userService.getSellerById(sellerId);

		collect.setDate(LocalDate.now().toString());

		collect.setSeller(theSeller);

		userService.addCollect(collect);

		theModel.addAttribute("seller", theSeller);

		return "seller-collect";
	}
}
