package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class BagSearchController {

	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@RequestMapping("/bagSearch")
	public String users(Model model, Principal principal) {
	    return "bagSearch";
	}
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	
	@ModelAttribute("bagMt")
	public BagMt construct() {
		return new BagMt();
	}
	
	
	
	@RequestMapping("/search/jobBag/details")
	@ResponseBody
	public String bagDetails(@RequestParam(value = "bagName", required = false) String name) {

		String uploadFilePath = "/jewels/uploads/" + uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";
		return bagMtService.bagSearch(name, uploadFilePath);
		
	}
	
	
	
	
	
	@RequestMapping("/bagSearch/jobBag/list")
	@ResponseBody
	public String bagList(@RequestParam(value = "term", required = false) String name) {
		Page<BagMt> bagMtList = bagMtService.findByName(15, 0, "name", "ASC", name.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (BagMt bagMt : bagMtList) {
			sb.append("\"")
				.append(bagMt.getName())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	
	
	

	
}
