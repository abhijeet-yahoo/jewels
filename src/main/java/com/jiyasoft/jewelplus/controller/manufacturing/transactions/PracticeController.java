package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practice")
public class PracticeController {

	
	@RequestMapping("/demo")
	public String practice(){
		System.out.println("in contr");
		return "practice";
	}
	
	
}
