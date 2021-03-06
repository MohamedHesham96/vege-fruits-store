package com.bluesoft.vegefruitsstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Client;
import com.bluesoft.vegefruitsstore.entity.ClientBalance;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Clients {

	@Autowired
	UserService userService;

	@RequestMapping("/clients")
	public String getSellers(Model theModel) {

		List<Client> clientList = userService.getAllClients();

		theModel.addAttribute("client", new Client());
		theModel.addAttribute("clientList", clientList);

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

		return "redirect:/client-balance";
	}

	@RequestMapping("/client-profile")
	public String showClientProfile(@RequestParam("id") int id, Model theModel) {

		Client theClient = userService.getClient(id);

		theModel.addAttribute("client", theClient);

		return "client-profile";
	}

	@RequestMapping("/client-bills")
	public String showClientBills(@RequestParam("id") int clientId, Model theModel) {

		List<ClientBalance> clientBalancesList = userService.getClientBalancesWithCountZero(clientId);

		Client theClient = userService.getClient(clientId);

		List<Double> avgKiloes = new ArrayList<Double>();

		Double theKiloPriceAvg;

		for (ClientBalance clientBalance : clientBalancesList) {

			int clientBalanceId = clientBalance.getId();

			// theMaxDate = userService.getMaxDateForItem(clientId, clientBalance);

			theKiloPriceAvg = userService.getAvgKiloPrice(clientBalanceId);

			avgKiloes.add(theKiloPriceAvg);

		}

		theModel.addAttribute("client", theClient);
		theModel.addAttribute("clientBalances", clientBalancesList);
		theModel.addAttribute("avgKiloes", avgKiloes);

		return "client-bills";
	}

	@RequestMapping("/delete-client")
	public String deleteClient(@RequestParam(name = "id") int id) {

		userService.deleteClient(id);

		return "redirect:/clients";
	}

}
