package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class StoneTranController {

	@Autowired
	private IStoneTranService stoneTranService;

	@RequestMapping("/stoneTran")
	public String users(Model model) {
		//model.addAttribute("stoneTran", stoneTranService.findAll());

		return "stoneTran";
	}

}
