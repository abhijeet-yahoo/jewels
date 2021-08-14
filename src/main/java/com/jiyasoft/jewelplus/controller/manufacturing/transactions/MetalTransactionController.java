package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MetalTransactionController {
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;

	
	@ModelAttribute("metalTran")
	public MetalTran construct() {
		return new MetalTran();
	}
	
	@RequestMapping("/metalTransaction")
	public String users(Model model, Principal principal) {
		model = populateModel(model,principal);
		return "metalTransaction";
	}
	
	public Model populateModel(Model model, Principal principal) {
		model.addAttribute("departmentMap",departmentService.getDepartmentListForMetal());
		model.addAttribute("purityMap",purityService.getPurityList());
		model.addAttribute("colorMap",colorService.getColorList());
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
	
		/*
		 * if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
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
	
	
	
	@RequestMapping(value = "/metalTransaction/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("metalTran") MetalTran metalTran,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "";

		if (result.hasErrors()) {
			return "metalTransaction";
		}
		
		Department sDeptLoc = departmentService.findOne(metalTran.getDepartment().getId());
		Color sColor = colorService.findOne(metalTran.getColor().getId());
		Purity sPurity = purityService.findOne(metalTran.getPurity().getId());
		
		if(metalTran.getMetalWt() > 0){
			Double freshBalance = metalTranService.getStockBalance(sPurity.getId(), sColor.getId(),sDeptLoc.getId());
			if (freshBalance != null) {
				if(freshBalance < metalTran.getMetalWt()){
					 retVal = "-1";
				}
			}else{
				retVal = "-1";
			}
		}
		
/*		if(metalTran.getuMetalWt() > 0){
			Double usedBalance = metalTranService.getUsedMetalStockBalance(sPurity.getId(), sColor.getId(), sDeptLoc.getId());
			if (usedBalance != null) {
				if(usedBalance < metalTran.getMetalWt()){
					if(retVal.length() > 0){
						retVal =  retVal+"_"+"-2";
					}else{
						retVal = "-2";
					}
				}
			}else{
				if(retVal.length() > 0){
					retVal =  retVal+"_"+"-2";
				}else{
					retVal = "-2";
				}
			}
		}*/
		
		if(retVal == ""){
			System.out.println("proceed");
		}else{
			if(retVal.length() > 0){
				return retVal;
			}
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			//for freshMetalWt
			
			if(metalTran.getMetalWt() > 0){
				
		
				
				Double mtlRate=metalTranService.getMetalRate(metalTran.getPurity().getId(), metalTran.getColor().getId(), metalTran.getDepartment().getId(),metalTran.getMetalWt());
				
				
				
				
				
				
				for(int i = 1; i<3;i++){
					MetalTran metTran = new MetalTran();
					
					if(i == 1){
						//debit entry
						metTran.setDepartment(metalTran.getDeptLocation());
						metTran.setBalance(metalTran.getMetalWt() * -1);
						metTran.setInOutFld("D");
						if(sPurity.getPurityConv() != null){
							metTran.setPurityConv(sPurity.getPurityConv());
						}else{
							metTran.setPurityConv(null);
						}
						metTran.setDeptLocation(metalTran.getDepartment());
						metTran.setColor(metalTran.getColor());
						metTran.setPurity(metalTran.getPurity());
						metTran.setTranType("LooseMetal");
						metTran.setMetalWt(metalTran.getMetalWt());
						metTran.setRefTranId(null);
						metTran.setRemark(null);
						
						metTran.setCreatedBy(principal.getName());
						metTran.setCreatedDt(new java.util.Date());
					//	metTran.setTranDate(metalTran.getTranDate());
						metTran.setDeactive(false);
						metTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
					}
					
					if(i == 2){
						//credit entry
						metTran.setDepartment(metalTran.getDepartment());
						metTran.setBalance(metalTran.getMetalWt());
						metTran.setInOutFld("C");
						if(sPurity.getPurityConv() != null){
							metTran.setPurityConv(sPurity.getPurityConv());
						}else{
							metTran.setPurityConv(null);
						}
						metTran.setDeptLocation(metalTran.getDeptLocation());
						metTran.setColor(metalTran.getColor());
						metTran.setPurity(metalTran.getPurity());
						metTran.setTranType("LooseMetal");
						metTran.setMetalWt(metalTran.getMetalWt());
						metTran.setRefTranId(null);
						metTran.setRemark(null);
						
						metTran.setCreatedBy(principal.getName());
						metTran.setCreatedDt(new java.util.Date());
					//	metTran.setTranDate(metalTran.getTranDate());
						metTran.setDeactive(false);
						metTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
					}
					
					if(metalTran.getTranDate() != null){
						metTran.setTranDate(metalTran.getTranDate());
					}else {
						metTran.setTranDate(new Date());
					}
					
					metalTranService.save(metTran);
					
				}
			}
			
			
			//for usedMetalWt
		/*	
			if(metalTran.getuMetalWt() > 0){
				for(int i = 1; i<3;i++){
					MetalTran metUsedTran = new MetalTran();
					
					if(i == 1){
						//debit entry
						metUsedTran.setDepartment(metalTran.getDeptLocation());
						metUsedTran.setuBalance(metalTran.getuMetalWt() * -1);
						metUsedTran.setInOutFld("D");
						if(sPurity.getPurityConv() != null){
							metUsedTran.setPurityConv(sPurity.getPurityConv());
						}else{
							metUsedTran.setPurityConv(null);
						}
						metUsedTran.setDeptLocation(metalTran.getDepartment());
						metUsedTran.setColor(metalTran.getColor());
						metUsedTran.setPurity(metalTran.getPurity());
						metUsedTran.setTranType("LooseMetal");
						metUsedTran.setuMetalWt(metalTran.getuMetalWt());
						metUsedTran.setRefTranId(null);
						metUsedTran.setRemark(null);
						
						metUsedTran.setCreatedBy(principal.getName());
						metUsedTran.setCreatedDt(new java.util.Date());
						metUsedTran.setTranDate(metalTran.getTranDate());
						metUsedTran.setDeactive(false);
					}
					
					if(i == 2){
						//credit entry
						metUsedTran.setDepartment(metalTran.getDepartment());
						metUsedTran.setuBalance(metalTran.getuMetalWt());
						metUsedTran.setInOutFld("C");
						if(sPurity.getPurityConv() != null){
							metUsedTran.setPurityConv(sPurity.getPurityConv());
						}else{
							metUsedTran.setPurityConv(null);
						}
						metUsedTran.setDeptLocation(metalTran.getDeptLocation());
						metUsedTran.setColor(metalTran.getColor());
						metUsedTran.setPurity(metalTran.getPurity());
						metUsedTran.setTranType("LooseMetal");
						metUsedTran.setuMetalWt(metalTran.getuMetalWt());
						metUsedTran.setRefTranId(null);
						metUsedTran.setRemark(null);
						
						metUsedTran.setCreatedBy(principal.getName());
						metUsedTran.setCreatedDt(new java.util.Date());
						metUsedTran.setTranDate(metalTran.getTranDate());
						metUsedTran.setDeactive(false);
					}
					
					metalTranService.save(metUsedTran);
					
				}
			}*/
			
			retVal = "1";
			
		} else {
			
			System.out.println("-------------->>>>>>>>>>>>>>>>>>in else part ");
		}

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}
	
	
	
	@RequestMapping("/metalTransaction/toDept")
	@ResponseBody
	public String fillToDepartment(
			@RequestParam(value = "deptId", required = false) Integer deptId,
			@ModelAttribute("metalTran") MetalTran metalTran) {

		return departmentService.getToDepartmentListDropDownForMetal(deptId);
	}
	
	@RequestMapping("/metalTransaction/stock/freshMetalWt")
	@ResponseBody
	public String freshMetalStockCheck(
			@RequestParam(value = "freshMetalWt", required = false) Double freshMetalWt,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@RequestParam(value = "locationId", required = false) Integer locationId,
			@ModelAttribute("metalTran") MetalTran metalTran) {

		StringBuilder sb = new StringBuilder();

		Double balance = metalTranService.getStockBalance(purityId, colorId,locationId);

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		return sb.toString();

	}
	
/*	@RequestMapping("/metalTransaction/stock/usedMetalWt")
	@ResponseBody
	public String usedMetalStockCheck(
			@RequestParam(value = "usedMetalWt", required = false) Double usedMetalWt,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@RequestParam(value = "locationId", required = false) Integer locationId,
			@ModelAttribute("metalTran") MetalTran metalTran) {

		StringBuilder sb = new StringBuilder();
		
		Double balance = metalTranService.getUsedMetalStockBalance(purityId, colorId, locationId);
		
		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		return sb.toString();

	}*/
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
