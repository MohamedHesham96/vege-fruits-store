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
public class Sellers {

	@Autowired
	UserService userService;

	@RequestMapping("/sellers")
	public String getSellers(Model theModel) {

		List<Seller> sellerList = userService.getAllSeller();

		theModel.addAttribute("sellerList", sellerList);

		return "sellers-list";
	}

	@RequestMapping("/seller-master")
	public String showSellerMaster(@RequestParam("id") int id, Model theModel) {

		Seller theSeller = userService.getSellerById(id);

		theModel.addAttribute("seller", theSeller);

		return "seller-master";
	}

	@RequestMapping("/seller-profile")
	public String showSellerProfile(@RequestParam("id") int id, Model theModel) {

		Seller theSeller = userService.getSellerById(id);

		theModel.addAttribute("seller", theSeller);

		return "seller-profile";
	}

	@RequestMapping("/search-for-seller")
	public String searchForSellerByName(@RequestParam(name = "sellerName") String sellerName, Model theModel) {

		List<Seller> theSellerList = userService.searchForSellerByName(sellerName);

		theModel.addAttribute("sellerList", theSellerList);

		return "sellers-list";
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
