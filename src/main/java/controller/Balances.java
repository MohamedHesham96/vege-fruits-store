package controller;

import java.util.List;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bluesoft.vegefruitsstore.entity.Balance;
import com.bluesoft.vegefruitsstore.service.UserService;

@Controller
public class Balances {

	@Autowired
	UserService userService;

	public String getAllBalance(Model theModel) {

		List<Balance> balanceList = userService.getAllBalance();

		return "balance";
	}
}





