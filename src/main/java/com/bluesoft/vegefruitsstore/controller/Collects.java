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
	public String getAllCollect(@RequestParam(name = "date", required = false) String theDate, Model theModel) {

		List<Collect> collectList;

		if (theDate == null) {

			theDate = LocalDate.now().toString();

			collectList = userService.getAllCollect();

		} else {

			collectList = userService.getCollectByDate(theDate);

		}

		int collectTotal = 0;

		for (Collect collect : collectList) {

			collectTotal += collect.getAmount();
		}

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("collectList", collectList);
		theModel.addAttribute("collectTotal", collectTotal);

		return "collect";
	}

	@RequestMapping("/seller-collect")
	public String showSellerCollect(@RequestParam("id") int id,
			@RequestParam(name = "date", required = false) String theDate, Model theModel) {

		Seller theSeller = userService.getSellerById(id);

		if (theDate == null) {

			theDate = LocalDate.now().toString();

		} else {

			List<Collect> collectList = userService.getSellerCollectByDate(id, theDate);

			theSeller.setCollects(collectList);
		}

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("today", LocalDate.now().toString());
		theModel.addAttribute("seller", theSeller);
		theModel.addAttribute("drweeTotal", theSeller.getDrawee());
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

		userService.updateMaster(sellerId, collect.getDate(), collect.getAmount(), "collect");

		theModel.addAttribute("date", LocalDate.now().toString());
		theModel.addAttribute("today", LocalDate.now().toString());
		theModel.addAttribute("seller", theSeller);
		theModel.addAttribute("drweeTotal", theSeller.getDrawee());
		theModel.addAttribute("collect", new Collect());

		return "seller-collect";
	}

	@RequestMapping("/delete-collect")
	public String deleteCollect(@RequestParam(name = "id") int id) throws Exception {

		Collect theCollect = userService.getCollect(id);

		userService.updateMaster(theCollect.getSeller().getId(), theCollect.getDate(), theCollect.getAmount(), "relay");

		userService.deletCollect(theCollect);

		return "redirect:/seller-collect?id=" + theCollect.getSeller().getId();
	}

}
