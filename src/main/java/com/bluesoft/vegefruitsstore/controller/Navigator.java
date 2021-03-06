package com.bluesoft.vegefruitsstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Casher;
import com.bluesoft.vegefruitsstore.entity.Seller;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Navigator {

	@Autowired
	UserService userService;

	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/")
	public String userLogin() {

		return "login";
	}

	@RequestMapping("/logout")
	public String userLogout() {

		httpSession.removeAttribute("loginCasherName");
		httpSession.removeAttribute("loginCasherPassword");
		httpSession.removeAttribute("loginCasherId");
		httpSession.removeAttribute("loginCasherIsAdmin");
		httpSession.removeAttribute("messagesCount");

		return "login";

	}

	@RequestMapping("/login")
	public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

		Casher casher = userService.getLoginCasher(username, password);

		if (new BCryptPasswordEncoder().matches(password, casher.getPassword())) {

			httpSession.setAttribute("loginCasherName", casher.getName());
			httpSession.setAttribute("loginCasherPassword", casher.getPassword());
			httpSession.setAttribute("loginCasherId", casher.getId());
			httpSession.setAttribute("loginCasherIsAdmin", casher.isAdmin());

			List<Seller> sellerList = userService.getAllSeller();

			List<Seller> newSellerList = new ArrayList<Seller>();

			for (Seller seller : sellerList) {

				if (!seller.checkSeller())
					newSellerList.add(seller);
			}

			httpSession.setAttribute("messagesCount", newSellerList.size());

			return "redirect:/balance";

		} else {

			return "login";

		}

	}

	@RequestMapping("/messages")
	public String showwMessages(Model theModel) {

		List<Seller> sellerList = userService.getAllSeller();

		List<Seller> newSellerList = new ArrayList<Seller>();

		for (Seller seller : sellerList) {

			if (!seller.checkSeller())
				newSellerList.add(seller);
		}

		httpSession.setAttribute("messagesCount", newSellerList.size());

		theModel.addAttribute("sellerList", newSellerList);

		return "messages";
	}

}
