package com.bluesoft.vegefruitsstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Seller;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Masters {

	@Autowired
	UserService userService;

	@RequestMapping("/master")
	public String getAllBalance(Model theModel) {

		List<Seller> sellerList = userService.getAllSeller();

		theModel.addAttribute("sellerList", sellerList);

		return "master";
	}

	@RequestMapping("/seller-profile")
	public String showSellerProfile(@RequestParam("id") int id, Model theModel) {

		Seller theSeller = userService.getSellerById(id);

		theModel.addAttribute("seller", theSeller);

		return "seller-profile";
	}
	
	
	
	
	
	
	

	@RequestMapping("/add-seller")
	public String getAllBalance(@ModelAttribute(name = "seller") Seller theSeller) {

//		userService.saveSeller(theSeller);

		return "redirect:/master";
	}

	@RequestMapping("/delete-seller")
	public String getAllBalance(@RequestParam(name = "id") int id) {

//		userService.deleteSeller(id);

		return "redirect:/master";
	}

}
