package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MetalTranController {

	@Autowired
	private IMetalTranService metalTranService;

	@RequestMapping("/metalTran")
	public String users(Model model) {
		//model.addAttribute("metalTran", metalTranService.findAll());

		return "metalTran";
	}

}
