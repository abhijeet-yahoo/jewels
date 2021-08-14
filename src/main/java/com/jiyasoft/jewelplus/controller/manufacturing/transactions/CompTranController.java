package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CompTranController {
	
	@Autowired
	private ICompTranService compTranService;
	
	@RequestMapping("/compTran")
	public String users(Model model) {
		//model.addAttribute("compTran", compTranService.findAll());

		return "compTran";
	}

}
