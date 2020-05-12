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
		theModel.addAttribute("client", new Client());

		return "clients-list";
	}

	@RequestMapping("/search-for-clients")
	public String searchForClientByName(@RequestParam(name = "clientName") String clientName, Model theModel) {

		List<Client> clientList = userService.searchForClientByName(clientName);

		theModel.addAttribute("clientList", clientList);
		theModel.addAttribute("client", new Client());
		
		return "clients-list";
	}

	@RequestMapping("/add-client")
	public String addClient(@ModelAttribute(name = "client") Client client) {

		userService.saveClient(client);

		return "redirect:/clients";
	}

	@RequestMapping("/client-profile")
	public String showClientProfile(@RequestParam("id") int id, Model theModel) {

		Seller theSeller = userService.getSellerById(id);

		theModel.addAttribute("seller", theSeller);

		return "seller-profile";
	}

	@RequestMapping("/delete-client")
	public String deleteClient(@RequestParam(name = "id") int id) {

//		userService.deleteSeller(id);

		return "redirect:/master";
	}

}
