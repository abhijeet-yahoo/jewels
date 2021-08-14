package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;


@RequestMapping("/manufacturing/transactions")
public class DemoController {

	
	@ModelAttribute("bagMt")
	private BagMt construct(){
		return new BagMt();
	}
	
	
	
	@RequestMapping("/xyz")
	public String print(){
		
		System.out.println("in demo controller");
		return "demo";
	}
	
}
