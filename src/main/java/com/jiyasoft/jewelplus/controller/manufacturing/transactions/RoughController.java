package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/manufacturing/transactions")
public class RoughController {

	
	@RequestMapping("/rough")
	public String rough(Model model){
		return "rough";
	}
	
	
	
	
	
}
