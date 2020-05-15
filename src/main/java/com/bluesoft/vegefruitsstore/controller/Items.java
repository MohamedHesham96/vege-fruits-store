package com.bluesoft.vegefruitsstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
