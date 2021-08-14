package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MulticomponentDeductionController 

{
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IComponentService componentService;
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;	

	@ModelAttribute("compTran")
	public CompTran constructCompTran() {
		return new CompTran();
	}
	
	@ModelAttribute("tranMt")
	public TranMt constructTraMt() {
		return new TranMt();
	}
	
	@RequestMapping("/multiComponentDeduction")
	public String multiComponentDeduction(Model model) {
		model = populateModel(model);
		return "multiComponentDeduction";
	}
	
	private Model populateModel(Model model) {
		model.addAttribute("departmentMap",departmentService.getDepartmentList());
		model.addAttribute("deptMap",departmentService.getDepartmentListForComp());
		model.addAttribute("purityMap",purityService.getPurityList());
		model.addAttribute("colorMap",colorService.getColorList());
		return model;
	}

	@RequestMapping(value = "//multiComponentDeduction/add", method = RequestMethod.POST)
	@ResponseBody
	public String saveMultiComponentDeduction(@Valid @ModelAttribute("compTran") CompTran compTran,
			BindingResult result, 
			@RequestParam(value = "vvBagNo") String vvBagNo,
			@RequestParam(value = "vFindingFlg") String vFindingFlg) {
		
		System.out.println("bag    "+vvBagNo);
		System.out.println("vFindingFlg    "+vFindingFlg);
	
		
		
return null;
	}
	
	
	
}
