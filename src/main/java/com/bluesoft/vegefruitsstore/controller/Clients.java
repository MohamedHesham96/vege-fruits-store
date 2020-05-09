package com.bluesoft.vegefruitsstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Client;
import com.bluesoft.vegefruitsstore.entity.Seller;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Clients {

	@Autowired
	UserService userService;

	@RequestMapping("/clients")
	public String getSellers(Model theModel) {

		List<Client> clientList = userService.getAllClients();

		theModel.addAttribute("clientList", clientList);

		return "clients-list";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/client-profile")
	public String showClientProfile(@RequestParam("id") int id, Model theModel) {

		Seller theSeller = userService.getSellerById(id);

		theModel.addAttribute("seller", theSeller);

		return "seller-profile";
	}

	@RequestMapping("/search-for-client")
	public String searchForClientByName(@RequestParam(name = "sellerName") String clientName, Model theModel) {

		List<Seller> theSellerList = userService.searchForSellerByName(clientName);

		theModel.addAttribute("sellerList", theSellerList);

		return "master";
	}

	@RequestMapping("/add-client")
	public String addClient(@ModelAttribute(name = "seller") Seller theSeller) {

//		userService.saveSeller(theSeller);

		return "redirect:/master";
	}

	@RequestMapping("/delete-client")
	public String deleteClient(@RequestParam(name = "id") int id) {

//		userService.deleteSeller(id);

		return "redirect:/master";
	}

}
