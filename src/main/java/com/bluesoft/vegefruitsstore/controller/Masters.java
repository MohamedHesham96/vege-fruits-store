package com.bluesoft.vegefruitsstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluesoft.vegefruitsstore.entity.MasterResult;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Masters {

	@Autowired
	UserService userService;

	@RequestMapping("/master")
	public String getAllMaster(Model theModel) {

		// List<Master> masterList = userService.getAllMaster();

		MasterResult masterResult = userService.getMasterTotals();

		theModel.addAttribute("masterResult", masterResult);

		return "master";
	}
}
