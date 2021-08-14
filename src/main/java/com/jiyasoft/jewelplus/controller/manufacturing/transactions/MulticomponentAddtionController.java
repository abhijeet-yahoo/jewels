package com.jiyasoft.jewelplus.controller.manufacturing.transactions;


import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class MulticomponentAddtionController {
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ICompTranService compTranService;

	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
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
	
	@RequestMapping("/multiComponentAddition")
	public String multiComponentAddition(Model model,Principal principal) {
		model = populateModel(model,principal);
		return "multiComponentAddition";
	}
	
	private Model populateModel(Model model, Principal principal) {

		model.addAttribute("departmentMap",departmentService.getDepartmentList());
		model.addAttribute("deptMap",departmentService.getDepartmentListForComp());
		model.addAttribute("purityMap",purityService.getPurityList());
		model.addAttribute("colorMap",colorService.getColorList());
		
		User user = userService.findByName(principal.getName());
		/*
		 * UserRole userRole = userRoleService.findByUser(user); if
		 * (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		 * 
		 * model.addAttribute("canEditTranddate", "true");
		 * 
		 * }else { model.addAttribute("canEditTranddate", "false"); }
		 */
		
		model.addAttribute("canEditTranddate", "false");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		return model;
	}
	
	
	@RequestMapping(value = "/multiComponentAddition/add", method = RequestMethod.POST)
	@ResponseBody
	public String saveMultiComponentAddition(@Valid @ModelAttribute("compTran") CompTran compTran,
			BindingResult result, 
			@RequestParam(value = "vvBagNo") String vvBagNo,
			@RequestParam(value = "vFindingFlg") String vFindingFlg,
			@RequestParam(value = "vPresentDept") Integer vPresentDeptId,
			@RequestParam(value = "vTotQty") Double vTotQty,
			@RequestParam(value = "vTranDate") String vTranDate,
			Principal principal) throws ParseException {
		
		
			String retVal = "1";

			Date date= new Date();
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				date = originalFormat.parse(vTranDate);
					
			}
			
			synchronized (this) {
				
				if (result.hasErrors()) {
					return "multiComponentAddition";
				}
				
				retVal=compTranService.multiCompAdditionSave(compTran, vvBagNo,vTotQty,vPresentDeptId, vFindingFlg, principal,date);
			}
		
		
			return retVal;
			
	}
	
	
	@RequestMapping("/multiComponentAddition/deptWiseBag/listing")
	@ResponseBody
	public String tranDtListing(Model model,
			@RequestParam(value = "deptId", required = false) Integer deptId){
		
		return tranMtService.multiCompAdditionList(deptId);
		
	}
	
}
