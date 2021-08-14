package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class ComponentTransactionController {

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IPurityService purityService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private IComponentService componentService;

	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("compTran")
	public CompTran construct() {
		return new CompTran();
	}

	@RequestMapping("/componentTransaction")
	public String users(Model model, Principal principal) {
		model = populateModel(model,principal);
		return "componentTransaction";
	}

	public Model populateModel(Model model, Principal principal) {
		model.addAttribute("departmentMap", departmentService.getDepartmentListForCompNotCentral());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		
		User user = userService.findByName(principal.getName());
		/*	UserRole userRole = userRoleService.findByUser(user);
		
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

	@RequestMapping(value = "/componentTransaction/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("compTran") CompTran compTran,
			BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "vTranDate") String tranDate,
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		String action = "added";
		String retVal = "1";

		if (result.hasErrors()) {
			return "Error";
		}
		
		
		Department sDeptLoc = departmentService.findOne(compTran.getDepartment().getId());
		Color sColor = colorService.findOne(compTran.getColor().getId());
		Purity sPurity = purityService.findOne(compTran.getPurity().getId());
		Component sComponent = componentService.findOne(compTran.getComponent().getId());
		
		Department toDept = departmentService.findOne(compTran.getDeptLocation().getId());
		
		
		Double balance = compTranService.getStockBalance(sPurity.getId(), sColor.getId(),sDeptLoc.getId(), sComponent.getId());
		if (balance != null) {
			if(balance < compTran.getMetalWt()){
				return retVal = "-1";
			}
		}else{
			return retVal = "-1";
		}
		
		Date date= new Date();
		
		if(tranDate !=null && !tranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(tranDate);
				
		}
		

		if (id == null || id.equals("") || (id != null && id == 0)) {

			for (int i = 1; i < 3; i++) {
				CompTran newComTran = new CompTran();
				
				Double compMtlRate=compTranService.getCompMetalRate(compTran.getPurity().getId(), compTran.getColor().getId(), compTran.getDepartment().getId(), 
						compTran.getComponent().getId(),compTran.getMetalWt());
				

				if (i == 1) {
					// debit entry
					newComTran.setDepartment(compTran.getDeptLocation());
					newComTran.setBalance(compTran.getMetalWt() * -1);
					newComTran.setInOutFld("D");
					if(sPurity.getPurityConv() != null){
						newComTran.setPurityConv(sPurity.getPurityConv());
					}else{
						newComTran.setPurityConv(null);
					}
					newComTran.setDeptLocation(compTran.getDepartment());
					newComTran.setColor(compTran.getColor());
					newComTran.setPurity(compTran.getPurity());
					newComTran.setComponent(compTran.getComponent());
					newComTran.setTranType("LooseComponent");
					newComTran.setMetalWt(compTran.getMetalWt());
					newComTran.setPcs(compTran.getPcs());
					newComTran.setBalancePcs(compTran.getPcs() * -1);
					newComTran.setRefTranId(null);
					newComTran.setRemark(compTran.getRemark());
					newComTran.setCreatedBy(principal.getName());
					newComTran.setCreatedDt(new Date());
					newComTran.setTrandate(date);
					newComTran.setDeactive(false);
					newComTran.setMetalRate(compMtlRate);
					
					
					compTranService.save(newComTran);
				}
				
				if(i == 2){
					//credit entry
					
					if(toDept.getName().equalsIgnoreCase("Central")){
						
					Double mtlRate=metalTranService.getMetalRate(compTran.getPurity().getId(), compTran.getColor().getId(), 
							compTran.getDeptLocation().getId(), compTran.getMetalWt());
						
						MetalTran metTran = new MetalTran();
						
						metTran.setDepartment(compTran.getDepartment());
						metTran.setBalance(compTran.getMetalWt());
						metTran.setInOutFld("C");
						if(sPurity.getPurityConv() != null){
							metTran.setPurityConv(sPurity.getPurityConv());
						}else{
							metTran.setPurityConv(null);
						}
						metTran.setDeptLocation(compTran.getDeptLocation());
						metTran.setColor(compTran.getColor());
						metTran.setPurity(compTran.getPurity());
						metTran.setTranType("LooseComponent");
						metTran.setMetalWt(compTran.getMetalWt());
						metTran.setRefTranId(null);
						metTran.setRemark(compTran.getRemark());				
						metTran.setCreatedBy(principal.getName());
						metTran.setCreatedDt(new java.util.Date());
						metTran.setTranDate(date);
						metTran.setDeactive(false);
						metTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
						metalTranService.save(metTran);
						
					}else{
						newComTran.setDepartment(compTran.getDepartment());
						newComTran.setBalance(compTran.getMetalWt());
						newComTran.setInOutFld("C");
						if(sPurity.getPurityConv() != null){
							newComTran.setPurityConv(sPurity.getPurityConv());
						}else{
							newComTran.setPurityConv(null);
						}
						newComTran.setDeptLocation(compTran.getDeptLocation());
						newComTran.setColor(compTran.getColor());
						newComTran.setPurity(compTran.getPurity());
						newComTran.setComponent(compTran.getComponent());
						newComTran.setTranType("LooseComponent");
						newComTran.setMetalWt(compTran.getMetalWt());
						newComTran.setPcs(compTran.getPcs());
						newComTran.setBalancePcs(compTran.getPcs());
						newComTran.setRefTranId(null);
						newComTran.setRemark(compTran.getRemark());
						newComTran.setCreatedBy(principal.getName());
						newComTran.setCreatedDt(new Date());
						newComTran.setTrandate(date);
						newComTran.setDeactive(false);
						newComTran.setMetalRate(compMtlRate);
						compTranService.save(newComTran);
						
					}
					
				}

			}// ending for loop

		} else {
				System.out.println("in else part");
		}

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/componentTransaction/toDept")
	@ResponseBody
	public String fillToDepartment(
			@RequestParam(value = "deptId", required = false) Integer deptId,
			@ModelAttribute("compTran") CompTran compTran) {

		return departmentService.getToDepartmentListDropDownForComp(deptId);
	}

	@RequestMapping("/componentTransaction/stock/metalWt")
	@ResponseBody
	public String metalStockCheck(
			@RequestParam(value = "metalWt", required = false) Double metalWt,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@RequestParam(value = "locationId", required = false) Integer locationId,
			@RequestParam(value = "componentId", required = false) Integer componentId,
			@ModelAttribute("compTran") CompTran compTran) {

		StringBuilder sb = new StringBuilder();

		Double balance = compTranService.getStockBalance(purityId, colorId,locationId, componentId);

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		return sb.toString();

	}
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
