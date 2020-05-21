package com.bluesoft.vegefruitsstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluesoft.vegefruitsstore.entity.Casher;
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

		return "login";

	}

	@RequestMapping("/login")
	public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

		Casher casher = userService.getLoginCasher(username, password);

		if (casher.getName().equals(username)) {
			System.out.println("is >> " + casher.isAdmin());
			httpSession.setAttribute("loginCasherName", casher.getName());
			httpSession.setAttribute("loginCasherPassword", casher.getPassword());
			httpSession.setAttribute("loginCasherId", casher.getId());
			httpSession.setAttribute("loginCasherIsAdmin", casher.isAdmin());

			return "redirect:/balance";

		} else {

			return "login";

		}

	}

}
