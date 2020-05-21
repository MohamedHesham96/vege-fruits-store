package com.bluesoft.vegefruitsstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluesoft.vegefruitsstore.entity.Item;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Items {

	@Autowired
	UserService userService;

	@RequestMapping("/add-item")
	public String addClient(@ModelAttribute(name = "item") Item item) {

		userService.saveItem(item);

		return "redirect:/client-balance";
	}

	@RequestMapping("/items")
	public String getAllItems(Model theModel) {

		List<Item> itemsList = userService.getAllItem();

		theModel.addAttribute("itemsList", itemsList);

		return "items-list";
	}

	@RequestMapping("/delete-item")
	public String addClient(@ModelAttribute(name = "id") int theItemId) {

		userService.deleteItem(theItemId);

		return "redirect:/items";
	}

}
